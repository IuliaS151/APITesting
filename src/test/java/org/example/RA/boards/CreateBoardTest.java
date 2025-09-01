package org.example.RA.boards;

import io.restassured.response.Response;
import org.example.RA.client.Endpoints;
import org.example.RA.models.Board;
import org.example.RA.support.BaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import io.restassured.common.mapper.TypeRef;


import java.util.Map;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

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
        check.statusIs(resp, 200); // TestNG

        Assert.assertTrue(resp.time() < 2_000, "API must respond <2s");

        Board created = prepare.asPojo(resp, Board.class);
        Board expected = expect
                .createBoard("ApiTestBoard", "Created from RA by POST");
        compare.compareBoardIgnoringId(created, expected); // AssertJ

        assertThat(resp.getHeader("Content-Type")).contains("application/json");

        Map<String, Object> json = resp.as(new TypeRef<Map<String, Object>>() {});

        String id = (String) json.get("id");
        String shortUrl = (String) json.get("shortUrl");

        assertThat(id).matches("[\\w\\d]{8,}");
        assertThat(shortUrl).contains("trello.com");

        boardId = created.getId();
    }


}
