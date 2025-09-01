package org.example.RA.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Board {
        private String id;
        private String name;
        private String desc;
        private String shortUrl;
        private Prefs prefs;


    public Board(String id, String name, String desc) {
        this.id = id;
        this.name = name;
        this.desc = desc;
    }

    public Board(){}

    public void setId(String id)                {   this.id = id;   }

    public void setName(String name)            {   this.name = name;   }

    public void setDesc(String desc)            {   this.desc = desc;   }

    public void setShortUrl(String shortUrl)    {   this.shortUrl = shortUrl;   }

    public void setPrefs(Prefs prefs)           {   this.prefs = prefs;    }

    public String getId()       {   return id;  }

    public String getName()     {   return name;    }

    public String getDesc()     {   return desc;    }

    public String getShortUrl() {   return shortUrl;    }

    public Prefs getPrefs()     {   return prefs;   }
}
