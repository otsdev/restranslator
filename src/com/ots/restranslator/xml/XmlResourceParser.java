package com.ots.restranslator.xml;

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

    private StringResources mStringResources;

    public XmlResourceParser() {
        mStringResources = new StringResources();
    }

    public void parse(String path) {
        parse(new File(path));
    }

    public void parse(File file) {
        try {
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            ResourceHandler handler = new ResourceHandler();
            InputStream is = new FileInputStream(file);
            parser.parse(is, handler);
        } catch (ParserConfigurationException e) {

        } catch (SAXException e) {

        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }
    }


    public class ResourceHandler extends DefaultHandler {

        @Override
        public void startDocument() throws SAXException {
            super.startDocument();
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            super.startElement(uri, localName, qName, attributes);

            if(Q_NAME_STRING.equals(qName)){
                System.out.println("attrib len: " + attributes.getLength());

            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            super.characters(ch, start, length);

            System.out.println("ch: " + new String(ch, start, length));
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            super.endElement(uri, localName, qName);
        }

        @Override
        public void endDocument() throws SAXException {
            super.endDocument();
        }
    }
}
