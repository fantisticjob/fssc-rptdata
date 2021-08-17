package com.longfor.fsscreportdb.ods.entity;

public class MsgData {
    private Integer conversationType;
    private String groupId;

    public MsgData() {
    }

    public MsgData(Integer conversationType, String groupId) {
        this.conversationType = conversationType;
        this.groupId = groupId;
    }

    public Integer getConversationType() {
        return conversationType;
    }

    public void setConversationType(Integer conversationType) {
        this.conversationType = conversationType;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    @Override
    public String toString() {
        return "MsgData{" +
                "conversationType=" + conversationType +
                ", groupId='" + groupId + '\'' +
                '}';
    }
}
