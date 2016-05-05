package com.irdai.wc.portal.beans;

import com.irdai.wc.portal.beans.util.ContentItem;
import com.irdai.wc.portal.beans.util.ContentUtils;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;

public class CircBean {
    public CircBean() {
    }

    public void contentByDate(ValueChangeEvent valueChangeEvent) {
        if(valueChangeEvent.getNewValue() != null && valueChangeEvent.getOldValue() != valueChangeEvent.getNewValue()){
            if(itemsUi != null)
                itemsUi.clear();
            viewCircularsByDate((String)valueChangeEvent.getNewValue());
        }
    }
    
    private ContentUtils cont = new ContentUtils();
    private List<ContentItem> itemsUi = new ArrayList<ContentItem>();
    private List<ContentItem> itemsFull = new ArrayList<ContentItem>();
    
    private void viewCirculars(){
        if(itemsFull == null || itemsFull.size() ==0)
            setItemsFull(cont.getFolderContentItemsByCollectionPath("/Contribution Folders/Vigilance/Circulars/"));
    }
    
    private void viewCircularsByDate(String createdDate){
        System.err.println(createdDate);
        viewCirculars();
        for(ContentItem item : itemsFull){
            System.err.println(" 1 : "+item.getCreationDateInGIFormat());
            if(item.getCreationDateInGIFormat().equalsIgnoreCase(createdDate)){
                itemsUi.add(item);
            }
        }
    }

    public void setItemsUi(List<ContentItem> itemsUi) {
        this.itemsUi = itemsUi;
    }

    public List<ContentItem> getItemsUi() {
        return itemsUi;
    }

    public void setItemsFull(List<ContentItem> itemsFull) {
        this.itemsFull = itemsFull;
    }

    public List<ContentItem> getItemsFull() {
        return itemsFull;
    }
}
