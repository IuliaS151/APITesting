package org.example.RFit.steps;

import org.example.RFit.models.Response.BoardResponse;

import static org.testng.Assert.assertEquals;

public class CheckActualVsExpectedResponses {

    public void compareBoard(BoardResponse actual, BoardResponse expected) {
        assertEquals(actual.getId(), expected.getId());
        assertEquals(actual.getName(), expected.getName());
        assertEquals(actual.getDesc(), expected.getDesc());
    }

    public void compareBoardIgnoringId(BoardResponse actual, BoardResponse expected) {
        assertEquals(actual.getName(), expected.getName());
        assertEquals(actual.getDesc(), expected.getDesc());
    }
}
