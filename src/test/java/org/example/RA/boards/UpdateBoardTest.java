package org.example.RA.boards;

import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.AssertionsForClassTypes;
import org.example.RA.support.BaseTest;
import org.example.RA.client.Endpoints;
import org.example.RA.models.Board;
import org.example.RA.steps.PrepareExpectedResponse;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UpdateBoardTest extends BaseTest {

    protected String boardId;

    protected String initialName() {    return "TemporaryBoard";    }
    protected String initialDesc() {    return "Created for test";   }

    protected String newName() {return "New PUT name";}
    protected String newDesc() {return "Updated by PUT";}
    protected Boolean closed() {return false;}

    @BeforeMethod
    public void createdBoard() {
        Response create = send.post(
                Endpoints.BOARDS,
                build.createBoardParams(initialName(), initialDesc(), false)
        );
        boardId = prepare.asPojo(create, Board.class).getId();
    }

    @AfterMethod(alwaysRun = true)
    public  void cleanUp() {
        if (boardId != null && !boardId.isEmpty()) {
            send.delete(Endpoints.BOARDS_BY_ID, boardId);
            boardId = null;
        }
    }

    @Test
    public void shouldUpdateBoardSuccessfully() {
        Response put = send.put(
                Endpoints.BOARDS_BY_ID,
                build.boardIdPathParams(boardId),
                build.updateBoardParams(newName(), newDesc(), closed()));

        check.statusIs(put, 200);

        Assert.assertTrue(put.time() < 2_000, "API must respond <2s");

        Board updated = prepare.asPojo(put, Board.class);
        Board expected = new PrepareExpectedResponse()
                .boardWithId(boardId, "New PUT name", "Updated by PUT");

        assertThat(put.getHeader("Content-Type")).contains("application/json");

        compare.compareBoard(updated, expected);

        AssertionsForClassTypes.assertThat(updated.getName()).isEqualTo(newName());
        AssertionsForClassTypes.assertThat(updated.getDesc()).isEqualTo(newDesc());

        AssertionsForClassTypes.assertThat(updated.getShortUrl()).contains("trello.com");
        AssertionsForClassTypes.assertThat(updated.getPrefs().getPermissionLevel())
                .isEqualTo("private");
        AssertionsForClassTypes.assertThat(updated.getPrefs().getComments())
                .isEqualTo("members");

        AssertionsForClassTypes.assertThat(updated.getPrefs().getCardCovers()).isTrue();
    }
}
