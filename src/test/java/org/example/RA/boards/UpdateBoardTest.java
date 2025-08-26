package org.example.RA.boards;

import io.restassured.response.Response;
import org.example.RA.client.Endpoints;
import org.example.RA.models.BoardResponse;
import org.example.RA.steps.PrepareExpectedResponse;
import org.example.RA.support.BoardPerMethodTest;
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
