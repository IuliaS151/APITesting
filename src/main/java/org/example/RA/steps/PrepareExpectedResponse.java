package org.example.RA.steps;

import org.example.RA.models.Board;

public class PrepareExpectedResponse {

    public Board createBoard(String name, String desc) {
        Board expected = new Board();
        expected.setName(name);
        expected.setDesc(desc);
        return expected;
    }

    public Board boardWithId(String id, String name, String desc){
        Board expected = new Board();
        expected.setId(id);
        expected.setName(name);
        expected.setDesc(desc);
        return expected;
    }
}
