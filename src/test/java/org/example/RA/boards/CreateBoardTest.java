package org.example.RA.boards;

import org.example.RA.client.Endpoints;
import org.example.RA.models.Board;
import org.example.RA.support.BaseTest;
import org.example.RA.support.Specifications;
import org.testng.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static io.restassured.RestAssured.*;
import static org.assertj.core.api.Assertions.assertThat;

public class CreateBoardTest extends BaseTest {

    private String boardId;

    @AfterEach
    public void deleteBoard() {
        if (boardId != null && !boardId.isEmpty()) {
            given()
                    .pathParam("id", boardId)
                    .when()
                    .delete(Endpoints.BOARDS_BY_ID);
            boardId = null;
        }
    }

    @ParameterizedTest(name = "Create board with name={0}")
    @CsvFileSource(resources = "/boards.csv", numLinesToSkip = 1)
    public void shouldCreateBoardSuccessfully(String name, String desc, boolean defaultLists) {

        boardId = Board.create(name, desc, defaultLists);

        Board createdBoard = given()
                .pathParam("id", boardId).log().all()
                .when().get(Endpoints.BOARDS_BY_ID)
                .then().log().all()
                .spec(Specifications.responseSpec200())
                .extract().as(Board.class);

        assertThat(createdBoard.getId()).isNotBlank();
        assertThat(createdBoard.getName()).isEqualTo(name);
        assertThat(createdBoard.getDesc()).isEqualTo(desc);

        Assert.assertNotNull(createdBoard.getId());
        Assert.assertEquals(createdBoard.getName(), name);
        Assert.assertEquals(createdBoard.getDesc(), desc);
    }
}