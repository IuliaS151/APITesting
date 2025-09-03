package org.example.RA.steps;

import java.util.HashMap;
import java.util.Map;

public class BuildRequest {

    private static void putIfNotBlank(Map<String, String> map, String key, String value) {
        if (value != null && !value.isEmpty()) map.put(key, value);
    }
    private static void putIfNotNull(Map<String, String> map, String key, Boolean value) {
        if (value != null) map.put(key, Boolean.toString(value));
    }

    public Map<String, String> createBoardParams(String name, String desc, boolean defaultLists) {
        Map<String, String> p = new HashMap<>();

        putIfNotBlank(p,"name", name);
        putIfNotBlank(p, "desc", desc);
        p.put("defaultLists", Boolean.toString(defaultLists));

        return p;
    }
    public String boardIdPathParams(String id) {
        return id;
    }

    public Map<String, String> updateBoardParams(String newName, String newDesc, Boolean closed) {
        Map<String, String> p = new HashMap<>();

        putIfNotBlank(p, "name", newName);
        putIfNotBlank(p, "desc", newDesc);
        putIfNotNull(p, "closed", closed);

        return p;
    }
}
