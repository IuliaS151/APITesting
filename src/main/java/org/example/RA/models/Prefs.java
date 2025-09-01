package org.example.RA.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Prefs {

    private String permissionLevel;
    private String comments;
    private Boolean cardCovers;

    public Prefs() {}

    public String getPermissionLevel()  {   return permissionLevel;    }

    public String getComments()         {   return comments;    }

    public Boolean getCardCovers()      {   return cardCovers;    }

    public void setPermissionLevel(String permissionLevel)  {   this.permissionLevel = permissionLevel;    }

    public void setComments(String comments)                {   this.comments = comments;   }

    public void setCardCovers(Boolean cardCovers)           {   this.cardCovers = cardCovers;   }
}
