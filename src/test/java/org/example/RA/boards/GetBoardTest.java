package org.example.RA.boards;

import io.restassured.response.Response;
import org.example.RA.support.BaseTest;
import org.example.RA.client.Endpoints;
import org.example.RA.models.Board;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class GetBoardTest extends BaseTest {

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

    @AfterMethod (alwaysRun = true)
    public  void cleanUp() {
        if (boardId != null && !boardId.isEmpty()) {
            send.delete(Endpoints.BOARDS_BY_ID, boardId);
            boardId = null;
        }
    }

    @Test
    public void shouldGetBoardByIdSuccessfully() {
        Response resp = send.get(
                Endpoints.BOARDS_BY_ID,
                build.boardIdPathParams(boardId)
        );
        check.statusIs(resp, 200);
        Board got = prepare.asPojo(resp, Board.class);
        assertThat(got.getId()).isEqualTo(boardId);
    }

}
