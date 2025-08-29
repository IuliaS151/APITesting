package org.example.OkHttp.steps;

import java.util.HashMap;
import java.util.Map;

public class BuildRequest {
    public Map<String, String> createBoardParams(String desc, Boolean defaultLists) {
        Map<String, String> p = new HashMap<>();
        if (desc != null && !desc.isEmpty()) p.put("desc", desc);
        if (defaultLists != null) p.put("defaultLists", String.valueOf(defaultLists));

        return p;
    }

    public Map<String, String> boardIdPathParams(String id) {
        Map<String, String> p = new HashMap<>();
        p.put("id", id);

        return p;
    }

    public Map<String, String> updateBoardParams(String newName, String newDesc, Boolean closed) {
        Map<String, String> p = new HashMap<>();
        if (newName != null && !newName.isEmpty()) p.put("name", newName);
        if (newDesc != null && !newDesc.isEmpty()) p.put("desc", newDesc);
        if (closed != null) p.put("closed", String.valueOf(closed));

        return p;
    }
}
