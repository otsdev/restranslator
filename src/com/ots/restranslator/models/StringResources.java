package com.ots.restranslator.models;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by jafar on 2/6/14.
 */
public class StringResources {

    private List<StringResourceItem> items;

    public StringResources() {
        items = new LinkedList<StringResourceItem>();
    }

    public int getItemsCount() {
        return items.size();
    }

    public boolean containKey(String key) {
        return (null != getItemByKey(key));
    }

    public StringResourceItem getItemByKey(String key) {
        checkValidKey(key);

        final int size = getItemsCount();

        for (int i = 0; i < size; i++) {
            if (key.equals(items.get(i).getKey())) {
                return items.get(i);
            }
        }

        return null;
    }

    public void addTranslatedText(String key, String translatedText) {
        checkValidKey(key);

    }

    public static void checkValidKey(String key) {
        if (null == key || key.length() < 1) {
            throw new IllegalArgumentException("The KEY cant be null or empty");
        }
    }
}
