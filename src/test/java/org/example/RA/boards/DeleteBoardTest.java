package org.example.RA.boards;

import io.restassured.response.Response;
import org.example.RA.client.Endpoints;
import org.example.RA.models.Board;
import org.example.RA.support.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DeleteBoardTest extends BaseTest {

    private String boardId;
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

    @Test
    public void shouldDeleteBoardSuccessfully() {

        Response del =  send.delete(Endpoints.BOARDS_BY_ID, build.boardIdPathParams(boardId));
        check.statusIs(del, 200);

        Response getAfter = send.get(Endpoints.BOARDS_BY_ID, build.boardIdPathParams(boardId));
        check.statusIs(getAfter, 404);
    }
}
