package com.ots.restranslator.models;

/**
 * Created by jafar on 2/6/14.
 */
public class StringResourceItem {
    private String key;
    private String originalText;
    private String translatedText;

    public StringResourceItem(String key) {
        this(key, null);
    }

    public StringResourceItem(String key, String originalText) {
        this.key = key;
        this.originalText = originalText;
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
}
