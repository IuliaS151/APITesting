package org.example.RA.support;

import io.restassured.response.Response;
import org.example.RA.client.Endpoints;
import org.example.RA.models.Board;
import org.example.RA.BaseTest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BoardPerMethodTest extends BaseTest {

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
    public void cleanup() {
        if(boardId != null) {
            send.delete(Endpoints.BOARDS_BY_ID, build.boardIdPathParams(boardId));
            boardId = null;
        }
    }
}
