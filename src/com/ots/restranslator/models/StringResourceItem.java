package com.ots.restranslator.models;

import java.util.Locale;

/**
 * Created by jafar on 2/6/14.
 */
public class StringResourceItem {
    private static final String STRING_RES_FORMAT = "    <string name=\"%s\">%s</string>";

    private String key;
    private String originalText;
    private String translatedText;

    public StringResourceItem(String key) {
        this(key, null, null);
    }

    public StringResourceItem(String key, String originalText) {
        this(key, originalText, null);
    }

    public StringResourceItem(String key, String originalText, String translatedText) {
        checkValidKey(key);

        this.key = key;
        this.originalText = originalText;
        this.translatedText = translatedText;
    }

    public String getKey() {
        return key;
    }

    public void setOriginalText(String originalText) {
        this.originalText = originalText;
    }

    public String getOriginalText() {
        return originalText;
    }

    public String getTranslatedText() {
        return translatedText;
    }

    public void setTranslatedText(String translatedText) {
        this.translatedText = translatedText;
    }

    public String getFormatedOriginalText() {
        return String.format(Locale.ENGLISH, STRING_RES_FORMAT, key, (null != originalText) ? originalText : "");
    }

    public String getFormatedTranslatedText() {
        return String.format(Locale.ENGLISH, STRING_RES_FORMAT, key, (null != translatedText) ? translatedText : "");
    }

    public static void checkValidKey(String key) {
        if (null == key || key.length() < 1) {
            throw new IllegalArgumentException("The KEY cant be null or empty");
        }
    }
}
