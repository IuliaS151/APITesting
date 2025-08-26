package org.example.RA.boards;

import io.restassured.response.Response;
import org.example.RA.client.Endpoints;
import org.example.RA.models.BoardResponse;
import org.example.RA.support.BoardPerMethodTest;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class GetBoardTest extends BoardPerMethodTest {

    @Test
    public void getBoard() {
        Response resp = send.get(
                Endpoints.BOARDS_BY_ID,
                build.boardIdPathParams(boardId)
        );
        check.statusIs(resp, 200);
        BoardResponse got = prepare.asPojo(resp, BoardResponse.class);
        assertThat(got.getId()).isEqualTo(boardId);
    }

}
