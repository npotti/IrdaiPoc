package com.irdai.wc.portal.beans.analytics;

import java.util.ArrayList;
import java.util.List;

public class InsuranceData {
    public InsuranceData() {
        super();
        insureData = new ArrayList<InsurancePOJO>();
        InsurancePOJO pojo = new InsurancePOJO("A", 25000);
        insureData.add(pojo);
        pojo = new InsurancePOJO("B", 100000);
        insureData.add(pojo);
        pojo = new InsurancePOJO("C", 10000);
        insureData.add(pojo);
        pojo = new InsurancePOJO("D", 50000);
        insureData.add(pojo);
        pojo = new InsurancePOJO("E", 75000);
        insureData.add(pojo);
    }
    
    private List<InsurancePOJO> insureData;

    public void setInsureData(List<InsurancePOJO> insureData) {
        this.insureData = insureData;
    }

    public List<InsurancePOJO> getInsureData() {
        return insureData;
    }
}
