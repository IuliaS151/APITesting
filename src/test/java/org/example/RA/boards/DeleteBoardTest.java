package org.example.RA.boards;

import io.restassured.response.Response;
import org.example.RA.client.Endpoints;
import org.example.RA.models.Board;
import org.example.RA.BaseTest;
import org.testng.annotations.Test;

public class DeleteBoardTest extends BaseTest {

    @Test
    public void deleteBoard() {

        Response resp = send.post(Endpoints.BOARDS,
                build.createBoardParams("TmpDelete", "for DELETE", false));
        String id = prepare.asPojo(resp, Board.class).getId();

        Response del =  send.delete(Endpoints.BOARDS_BY_ID, build.boardIdPathParams(id));
        check.statusIs(del, 200);

        Response getAfter = send.get(Endpoints.BOARDS_BY_ID, build.boardIdPathParams(id));
        check.statusIs(getAfter, 404);
    }
}
