package com.irdai.wc.portal.beans.analytics;

public class InsurancePOJO {
    public InsurancePOJO(String insurerName, Integer margin) {
        super();
        this.insurerName = insurerName;
        this.margin = margin;
    }
    
    private String insurerName;
    private Integer margin;

    public void setInsurerName(String insurerName) {
        this.insurerName = insurerName;
    }

    public String getInsurerName() {
        return insurerName;
    }

    public void setMargin(Integer margin) {
        this.margin = margin;
    }

    public Integer getMargin() {
        return margin;
    }
}
