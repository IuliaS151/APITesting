package org.example.RA.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.restassured.response.Response;
import org.example.RA.client.Endpoints;
import org.example.RA.support.Specifications;

import static io.restassured.RestAssured.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Board {
        private String id;
        private String name;
        private String desc;
        private Boolean defaultLists;
        private String shortUrl;
        private Prefs prefs;


    public Board(String id, String name, String desc, boolean defaultLists) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.defaultLists = defaultLists;
    }

    public Board(){}

    public void setId(String id)                {   this.id = id;   }

    public void setName(String name)            {   this.name = name;   }

    public void setDesc(String desc)            {   this.desc = desc;   }

    public void setDefaultLists(boolean defaultLists)   {   this.defaultLists = defaultLists;   }

    public void setShortUrl(String shortUrl)    {   this.shortUrl = shortUrl;   }

    public void setPrefs(Prefs prefs)           {   this.prefs = prefs;    }

    public String getId()       {   return id;  }

    public String getName()     {   return name;    }

    public String getDesc()     {   return desc;    }

    public boolean getDefaultLists()   {   return defaultLists;    }

    public String getShortUrl() {   return shortUrl;    }

    public Prefs getPrefs()     {   return prefs;   }

    public static String create(String name, String desc, boolean defaultLists) {

        Response resp = given()
                //.spec(Specifications.requestSpec("https://api.trello.com/"))
                .queryParam("name", name)
                .queryParam("desc", desc)
                .queryParam("defaultLists", defaultLists)
                .when()
                .post(Endpoints.BOARDS);

        resp.then().log().all().spec(Specifications.responseSpec200());

        return resp.then().extract().path("id");
    }
}
