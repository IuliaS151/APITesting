package org.example.OkHttp.models;

public class BoardResponse {

    private String id;
    private String name;
    private String desc;

    public BoardResponse() {} // Конструктор без аргументов, gson может заполнить дажеприватные поля без сеттеров

    public String getId()   {   return id;  }
    public String getName() {   return name;    }
    public String getDesc() {   return desc;    }

    public void setId(String id) {  this.id = id;   }
    public void setName(String name) {  this.name = name;   }
    public void setDesc(String desc) {  this.desc = desc;   }
}