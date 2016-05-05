package com.irdai.wc.portal.beans.circulars;

import com.irdai.wc.portal.beans.util.ContentItem;
import com.irdai.wc.portal.beans.util.ContentUtils;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;

public class CircularsBean {
    public CircularsBean() {
    }

    public void contentByDate(ValueChangeEvent valueChangeEvent) {
        if(valueChangeEvent.getNewValue() != null && valueChangeEvent.getOldValue() != valueChangeEvent.getNewValue()){
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
        viewCirculars();
        for(ContentItem item : itemsFull){
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
