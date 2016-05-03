package com.irdai.wc.portal.beans.search;

import com.irdai.wc.portal.beans.util.ContentItem;

import com.irdai.wc.portal.beans.util.ContentUtils;

import java.text.ParseException;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;

import javax.naming.NamingException;

import oracle.stellent.ridc.IdcClientException;

public class UCMSearchBean {
    public UCMSearchBean() {
        super();
    }
    
    private String titleText = "";
    private ContentUtils cont = new ContentUtils();
    private List<ContentItem> itemsUi = new ArrayList<ContentItem>();
    
    private List<ContentItem> searchContent(String title){
        List<ContentItem> results = new ArrayList<ContentItem>();
        try {
            results = cont.getContentItemsFromQuery("dDocTitle <starts> `" + title +
                                             "`", null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

    public void search(ActionEvent actionEvent) {
        List<ContentItem> result = searchContent(titleText);
        setItemsUi(result);
    }

    public void setTitleText(String titleText) {
        this.titleText = titleText;
    }

    public String getTitleText() {
        return titleText;
    }

    public void setItemsUi(List<ContentItem> itemsUi) {
        this.itemsUi = itemsUi;
    }

    public List<ContentItem> getItemsUi() {
        return itemsUi;
    }
}
