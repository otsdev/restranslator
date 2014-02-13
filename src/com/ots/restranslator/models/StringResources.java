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

    public StringResourceItem getResourceItemByKey(String key) {
        StringResourceItem.checkValidKey(key);

        final int size = getItemsCount();

        for (int i = 0; i < size; i++) {
            if (key.equals(items.get(i).getKey())) {
                return items.get(i);
            }
        }

        return null;
    }

    public StringResourceItem getResourceItemAt(int position) {
        if (position < 0 || position >= getItemsCount()) {
            throw new IllegalArgumentException("Invalid position " + position);
        }

        return items.get(position);
    }

    public String getItemKeyAt(int position){
        return getResourceItemAt(position).getKey();
    }

    public String getItemOriginalTextAt(int position){
        return getResourceItemAt(position).getOriginalText();
    }

    public String getItemTranslatedTextAt(int position){
        return getResourceItemAt(position).getTranslatedText();
    }

    public void addResourceItem(StringResourceItem resourceItem) {
        if (null == resourceItem) {
            throw new IllegalArgumentException("Trying to add a null StringResourceItem");
        }

        final StringResourceItem existResourceItem = getResourceItemByKey(resourceItem.getKey());
        if (null == existResourceItem) {
            // new key; add it.
            items.add(resourceItem);
        } else {
            // exist key; replace it.
            int position = -1;
            final int size = getItemsCount();
            for (int i = 0; i < size; i++) {
                if (existResourceItem == items.get(i)) {
                    position = i;
                    break;
                }
            }

            if (-1 == position) {
                throw new IllegalArgumentException("Invalid position, where did the item go!!!");
            }

            items.set(position, resourceItem);
        }
    }

    public StringResourceItem addResourceItem(String key) {
        return addResourceItem(key, null, null);
    }

    public StringResourceItem addResourceItem(String key, String originalText) {
        return addResourceItem(key, originalText, null);
    }

    public StringResourceItem addResourceItem(String key, String originalText, String translatedText) {
        StringResourceItem resourceItem = getResourceItemByKey(key);
        if (null == resourceItem) {
            resourceItem = new StringResourceItem(key, originalText, translatedText);
            items.add(resourceItem);
        } else {
            resourceItem.setOriginalText(originalText);
            resourceItem.setTranslatedText(translatedText);
        }

        return resourceItem;
    }

    public void setOriginalText(String key, String originalText) {
        StringResourceItem resourceItem = getResourceItemByKey(key);
        if (null == resourceItem) {
            addResourceItem(key, originalText, null);
        } else {
            resourceItem.setOriginalText(originalText);
        }
    }

    public void setTranslatedText(String key, String translatedText) {
        StringResourceItem resourceItem = getResourceItemByKey(key);
        if (null == resourceItem) {
            addResourceItem(key, null, translatedText);
        } else {
            resourceItem.setTranslatedText(translatedText);
        }
    }

    public boolean containKey(String key) {
        return (null != getResourceItemByKey(key));
    }


    public String flatten(){
        final int size = items.size();
        final StringBuilder builder = new StringBuilder();
        for(int i=0;i<size;i++){
            StringResourceItem resourceItem = items.get(i);
            builder.append(resourceItem.getKey());
            builder.append(": ");
            builder.append(resourceItem.getOriginalText());
            builder.append(" -> ");
            builder.append(resourceItem.getTranslatedText());
            builder.append("\n");
        }

        return builder.toString();
    }
}
