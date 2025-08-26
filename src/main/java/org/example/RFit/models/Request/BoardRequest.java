package org.example.RFit.models.Request;

public class BoardRequest {

    private String id;
    private String desc;
    private Boolean defaultLists;

    public BoardRequest(String id, String desc, Boolean defaultLists) {
        this.id = id;
        this.desc = desc;
        this.defaultLists = defaultLists;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Boolean getDefaultLists() {
        return defaultLists;
    }

    public void setDefaultLists(Boolean defaultLists) {
        this.defaultLists = defaultLists;
    }
}
