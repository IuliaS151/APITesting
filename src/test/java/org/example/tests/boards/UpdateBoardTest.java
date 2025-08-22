package org.example.tests.boards;

import io.restassured.response.Response;
import org.example.client.Endpoints;
import org.example.models.BoardResponse;
import org.example.steps.PrepareExpectedResponse;
import org.example.tests.support.BoardPerMethodTest;
import org.testng.annotations.Test;

public class UpdateBoardTest extends BoardPerMethodTest {

    @Test
    public void updateBoard() {
        Response put = send.put(
                Endpoints.BOARDS_BY_ID,
                build.updateBoardParams("New PUT name", "Updated by PUT", false),
                build.boardIdPathParams(boardId)
        );
        check.statusIs(put, 200);
        BoardResponse updated = prepare.asPojo(put, BoardResponse.class);
        BoardResponse expected = new PrepareExpectedResponse()
                .boardWithId(boardId, "New PUT name", "Updated by PUT");

        compare.compareBoard(updated, expected);
    }
}
