package org.example.tests.boards;

import io.restassured.response.Response;
import org.example.client.Endpoints;
import org.example.models.BoardResponse;
import org.example.steps.PrepareExpectedResponse;
import org.example.tests.BaseTest;
import org.testng.annotations.Test;

import java.util.Map;

public class DeleteBoardTest extends BaseTest {

    @Test
    public void deleteBoard() {

        Response resp = send.post(Endpoints.BOARDS,
                build.createBoardParams("TmpDelete", "for DELETE", false));
        String id = prepare.asPojo(resp, BoardResponse.class).getId();

        Response del =  send.delete(Endpoints.BOARDS_BY_ID, build.boardIdPathParams(id));
        check.statusIs(del, 200);

        Response getAfter = send.get(Endpoints.BOARDS_BY_ID, build.boardIdPathParams(id));
        check.statusIs(getAfter, 404);
    }
}
