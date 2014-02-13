package com.ots.restranslator.xml;

import com.ots.restranslator.models.StringResourceItem;
import com.ots.restranslator.models.StringResources;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;

/**
 * Created by jafar on 2/11/14.
 */
public class XmlResourceParser {

    private static final String Q_NAME_STRING = "string";
    private static final String ATTRIBUTE_NAME_NAME = "name";

    private StringResources mStringResources;

    public XmlResourceParser() {
        mStringResources = new StringResources();
    }

    public StringResources getmStringResources() {
        return mStringResources;
    }

    public void parse(String path, boolean isSourceFile) {
        parse(new File(path), isSourceFile);
    }

    public void parse(File file, boolean isSourceFile) {
        if (null == file) {
            return;
        }

        try {
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            ResourceHandler handler = new ResourceHandler(isSourceFile);
            InputStream is = new FileInputStream(file);
            parser.parse(is, handler);

            System.out.println("Successfully parsed " + (isSourceFile ? "source " : "target ") + "file");
        } catch (ParserConfigurationException e) {
            System.out.println(e.getMessage());
        } catch (SAXException e) {
            System.out.println(e.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public class ResourceHandler extends DefaultHandler {

        private boolean isOriginalText;
        private StringBuilder builder;
        private StringResourceItem currentItem;

        public ResourceHandler(boolean isOriginalText) {
            this.isOriginalText = isOriginalText;
        }

        @Override
        public void startDocument() throws SAXException {
            super.startDocument();

            builder = new StringBuilder();
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            super.startElement(uri, localName, qName, attributes);

            if (Q_NAME_STRING.equals(qName)) {
                final String key = attributes.getValue(ATTRIBUTE_NAME_NAME);
                currentItem = mStringResources.getResourceItemByKey(key);
                if (null == currentItem) {
                    currentItem = mStringResources.addResourceItem(key);
                }

                builder.setLength(0);
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            super.characters(ch, start, length);

            builder.append(ch, start, length);
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            super.endElement(uri, localName, qName);

            if (Q_NAME_STRING.equals(qName) && null != currentItem) {
                if (isOriginalText) {
                    currentItem.setOriginalText(builder.toString());
                } else {
                    currentItem.setTranslatedText(builder.toString());
                }

                currentItem = null;
                builder.setLength(0);
            }
        }

        @Override
        public void endDocument() throws SAXException {
            super.endDocument();
        }
    }
}
