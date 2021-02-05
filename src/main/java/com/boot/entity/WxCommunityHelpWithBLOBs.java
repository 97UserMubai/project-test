package com.boot.entity;

public class WxCommunityHelpWithBLOBs extends WxCommunityHelp {
    private String comment;

    private String dealRemark;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public String getDealRemark() {
        return dealRemark;
    }

    public void setDealRemark(String dealRemark) {
        this.dealRemark = dealRemark == null ? null : dealRemark.trim();
    }
}