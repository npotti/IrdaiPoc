package com.irdai.wc.portal.beans.analytics;

import java.text.DateFormat;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ReimbursementData {
    public ReimbursementData() {
        super();
        reimbursements = new ArrayList<ReimbursementPOJO>();
        
        ReimbursementPOJO pojo = new ReimbursementPOJO(getOldDate(300),1500,1400,"2015");
        reimbursements.add(pojo);
        pojo = new ReimbursementPOJO(getOldDate(200),2000,1700,"2015");
        reimbursements.add(pojo);
        pojo = new ReimbursementPOJO(getOldDate(365),500,300,"2015");
        reimbursements.add(pojo);
        pojo = new ReimbursementPOJO(getOldDate(50),2000,1500,"2016");
        reimbursements.add(pojo);
        pojo = new ReimbursementPOJO(getOldDate(20),1200,1000,"2016");
        reimbursements.add(pojo);
        pojo = new ReimbursementPOJO(getOldDate(10),8000,4500,"2016");
        reimbursements.add(pojo);
        pojo = new ReimbursementPOJO(getOldDate(100),200,175,"2016");
        reimbursements.add(pojo);
    }
    
    private List<ReimbursementPOJO> reimbursements;


    public void setReimbursements(List<ReimbursementPOJO> reimbursements) {
        this.reimbursements = reimbursements;
    }

    public List<ReimbursementPOJO> getReimbursements() {
        return reimbursements;
    }
    
    private Date getOldDate(Integer noOfDays){
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date myDate = new Date(System.currentTimeMillis());
        System.out.println("result is " + dateFormat.format(myDate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(myDate);
        cal.add(Calendar.DATE, -noOfDays);
        return cal.getTime();
    }
}
