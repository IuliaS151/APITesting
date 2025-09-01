package org.example.RA.boards;

import io.restassured.response.Response;
import org.example.RA.client.Endpoints;
import org.example.RA.models.Board;
import org.example.RA.support.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

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

        assertThat(del.getHeader("Content-Type")).contains("application/json");

        Assert.assertTrue(del.time() < 2_000, "API must respond <2s");

        Object value = del.jsonPath().get("_value");

        Assert.assertNull(value);

        Response getAfter = send.get(Endpoints.BOARDS_BY_ID, build.boardIdPathParams(boardId));
        check.statusIs(getAfter, 404);

        assertThat(getAfter.getHeader("Content-Type")).contains("text/plain");
    }
}
