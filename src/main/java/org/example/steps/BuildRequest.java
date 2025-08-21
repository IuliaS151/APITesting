package org.example.steps;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class BuildRequest {

    public Map<String, Object> createBoardParams(String name, String desc, boolean defaultLists) {
        Map<String, Object> p = new HashMap<>();
        p.put("name", name);
        if (desc != null && !desc.isEmpty()) p.put("desc", desc);
        p.put("defaultLists", defaultLists);
        return p;
    }
    public Map<String, Object> boardIdPathParams(String id) {
        Map<String, Object> p = new HashMap<>();
        p.put("id", id);
        return p;
    }

    public Map<String, Object> updateBoardParams(String newName, String newDesc, Boolean closed) {
        Map<String, Object> p = new HashMap<>();
        if(newName != null && !newName.isEmpty()) p.put("name", newName);
        if(newDesc != null) p.put("desc", newDesc);
        if(closed != null) p.put("closed", closed);
        return p;
    }
}
