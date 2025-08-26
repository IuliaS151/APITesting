package org.example.RA.boards;

import io.restassured.response.Response;
import org.example.RA.client.Endpoints;
import org.example.RA.models.BoardResponse;
import org.example.RA.steps.PrepareExpectedResponse;
import org.example.RA.BaseTest;
import org.testng.annotations.Test;

import java.util.Map;

public class CreateBoardTest extends BaseTest {

    @Test
    public void createBoard() {
        Map<String, Object> params =
                build.createBoardParams("ApiTestBoard", "Created from RA", false);

        Response resp = send.post(Endpoints.BOARDS, params);
        check.statusIs(resp, 200);

        BoardResponse created = prepare.asPojo(resp, BoardResponse.class);
        BoardResponse expected = new PrepareExpectedResponse()
                .createBoard("ApiTestBoard", "Created from RA");

        compare.compareBoardIgnoringId(created, expected);
        send.delete(Endpoints.BOARDS_BY_ID, build.boardIdPathParams(created.getId()));


    }
}
