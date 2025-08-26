package org.example.RFit.steps;

import org.example.RFit.models.Response.BoardResponse;

public class PrepareExpectedResponse {
    public BoardResponse boardWithoutId(String name, String desc) {
        BoardResponse expected = new BoardResponse();
        expected.setName(name);
        expected.setDesc(desc);

        return expected;
    }

    public BoardResponse boardWithId(String id, String name, String desc) {
        BoardResponse expexted = new BoardResponse();
        expexted.setId(id);
        expexted.setName(name);
        expexted.setDesc(desc);

        return expexted;
    }
}
