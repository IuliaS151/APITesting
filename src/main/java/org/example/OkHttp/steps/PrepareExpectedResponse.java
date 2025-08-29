package org.example.OkHttp.steps;

import org.example.OkHttp.models.BoardResponse;

public class PrepareExpectedResponse {

    public BoardResponse boardWithoutId(String name, String desc) {
        BoardResponse expected = new BoardResponse();
        expected.setName(name);
        expected.setDesc(desc);
        return expected;
    }

    public BoardResponse boardWithId(String id, String name, String desc) {
        BoardResponse expected = new BoardResponse();
        expected.setId(id);
        expected.setName(name);
        expected.setDesc(desc);
        return expected;
    }
}
