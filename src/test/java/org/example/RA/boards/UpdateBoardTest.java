package org.example.RA.boards;

import io.restassured.response.Response;
import org.example.RA.support.BaseTest;
import org.example.RA.client.Endpoints;
import org.example.RA.models.Board;
import org.example.RA.steps.PrepareExpectedResponse;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UpdateBoardTest extends BaseTest {

    protected String boardId;

    protected String initialName() {    return "TemporaryBoard";    }
    protected String initialDesc() {    return "Created for test";   }

    @BeforeMethod
    public void createdBoard() {
        Response create = send.post(
                Endpoints.BOARDS,
                build.createBoardParams(initialName(), initialDesc(), false)
        );
        boardId = prepare.asPojo(create, Board.class).getId();
    }

    @AfterMethod(alwaysRun = true)
    public  void cleanUp() {
        if (boardId != null && !boardId.isEmpty()) {
            send.delete(Endpoints.BOARDS_BY_ID, boardId);
            boardId = null;
        }
    }

    @Test
    public void updateBoard() {
        Response put = send.put(
                Endpoints.BOARDS_BY_ID,
                build.boardIdPathParams(boardId),
                build.updateBoardParams("New PUT name", "Updated by PUT", false));

        check.statusIs(put, 200);

        Board updated = prepare.asPojo(put, Board.class);
        Board expected = new PrepareExpectedResponse()
                .boardWithId(boardId, "New PUT name", "Updated by PUT");

        compare.compareBoard(updated, expected);
    }
}
