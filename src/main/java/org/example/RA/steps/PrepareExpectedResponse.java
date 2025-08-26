package org.example.RA.steps;

import org.example.RA.models.BoardResponse;

public class PrepareExpectedResponse {

    public BoardResponse createBoard(String name, String desc) {
        BoardResponse expected = new BoardResponse();
        expected.setName(name);
        expected.setDesc(desc);
        return expected;
    }

    public BoardResponse boardWithId(String id, String name, String desc){
        BoardResponse expected = new BoardResponse();
        expected.setId(id);
        expected.setName(name);
        expected.setDesc(desc);
        return expected;
    }
}
