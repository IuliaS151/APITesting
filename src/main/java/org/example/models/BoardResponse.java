package org.example.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BoardResponse {
        private String id;
        private String name;
        private String desc;

    public BoardResponse(String id, String name, String desc) {
        this.id = id;
        this.name = name;
        this.desc = desc;
    }

    public BoardResponse(){

    }

    public void setId(String id) {  this.id = id;   }

    public void setName(String name) {  this.name = name;   }

    public void setDesc(String desc) {  this.desc = desc;   }

    public String getId() {     return id;  }

    public String getName() {   return name;    }

    public String getDesc() {   return desc;    }
}
