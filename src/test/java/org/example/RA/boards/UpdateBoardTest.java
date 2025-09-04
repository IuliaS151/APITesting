package org.example.RA.boards;

import org.example.RA.support.BaseTest;
import org.example.RA.client.Endpoints;
import org.example.RA.models.Board;
import org.example.RA.support.Specifications;
import org.testng.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static io.restassured.RestAssured.*;
import static org.assertj.core.api.Assertions.assertThat;

public class UpdateBoardTest extends BaseTest {

    protected String boardId;

    @AfterEach
    public  void cleanUp() {
        if (boardId != null && !boardId.isEmpty()) {
            given()
                    .pathParam("id", boardId)
                    .when()
                    .delete(Endpoints.BOARDS_BY_ID);
            boardId = null;
        }
    }

    @ParameterizedTest(name = "Update board with name={0}")
    @CsvFileSource(resources = "/boards.csv", numLinesToSkip = 1)
    public void shouldUpdateBoardSuccessfully(String name, String desc, boolean defaultLists,
                                              String newName, String newDesc) {

        boardId = Board.create(name, desc, defaultLists);

        Board updateBoard = given()
                        .pathParam("id", boardId)
                        .queryParam("name", newName)
                        .queryParam("desc", newDesc)
                        .when().put(Endpoints.BOARDS_BY_ID)
                        .then().log().all()
                        .spec(Specifications.responseSpec200())
                        .extract().as(Board.class);

        assertThat(updateBoard.getName()).isEqualTo(newName);
        assertThat(updateBoard.getDesc()).isEqualTo(newDesc);

        Assert.assertEquals(updateBoard.getName(), newName);
        Assert.assertEquals(updateBoard.getDesc(), newDesc);
    }
}
