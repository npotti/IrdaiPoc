package com.irdai.wc.portal.beans.analytics;

import java.util.Date;

public class ReimbursementPOJO {
    public ReimbursementPOJO() {
        super();
    }
    
    public ReimbursementPOJO(Date claimDate, Integer claimAmount, Integer approvalAmount, String year){
        this.claimDate = claimDate;
        this.claimAmount = claimAmount;
        this.approvalAmount = approvalAmount;
        this.year = year;
    }
    
    private Date claimDate;
    private Integer claimAmount;
    private Integer approvalAmount;
    private String year;

    public void setClaimDate(Date claimDate) {
        this.claimDate = claimDate;
    }

    public Date getClaimDate() {
        return claimDate;
    }

    public void setClaimAmount(Integer claimAmount) {
        this.claimAmount = claimAmount;
    }

    public Integer getClaimAmount() {
        return claimAmount;
    }

    public void setApprovalAmount(Integer approvalAmount) {
        this.approvalAmount = approvalAmount;
    }

    public Integer getApprovalAmount() {
        return approvalAmount;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getYear() {
        return year;
    }
}
