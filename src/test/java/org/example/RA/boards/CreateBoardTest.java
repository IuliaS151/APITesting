package org.example.RA.boards;

import io.restassured.response.Response;
import org.example.RA.client.Endpoints;
import org.example.RA.models.Board;
import org.example.RA.support.BaseTest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.Map;

public class CreateBoardTest extends BaseTest {

    private String boardId;

    @AfterMethod(alwaysRun = true)
    public void deleteBoard() {
        if (boardId != null && !boardId.isEmpty()) {
            send.delete(Endpoints.BOARDS_BY_ID, boardId);
            boardId = null;
        }
    }

    @Test
    public void shouldCreateBoardSuccessfully() {
        Map<String, String> params =
                build.createBoardParams("ApiTestBoard", "Created from RA by POST", false);

        Response resp = send.post(Endpoints.BOARDS, params);
        check.statusIs(resp, 200);

        Board created = prepare.asPojo(resp, Board.class);
        Board expected = expect
                .createBoard("ApiTestBoard", "Created from RA by POST");
        compare.compareBoardIgnoringId(created, expected);

        boardId = created.getId();
    }


}
