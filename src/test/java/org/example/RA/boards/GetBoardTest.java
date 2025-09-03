package org.example.RA.boards;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
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

public class GetBoardTest extends BaseTest {

    private String boardId;

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

    @ParameterizedTest(name = "Get board with name={0}")
    @CsvFileSource(resources = "/boards.csv", numLinesToSkip = 1)
    public void shouldGetBoardByIdSuccessfully(String name, String desc, boolean defaultLists) {

        String boardId = Board.create(name, desc, defaultLists);

        Board getBoard = given()
                .pathParam("id", boardId)
                .when().get(Endpoints.BOARDS_BY_ID)
                .then().spec(Specifications.responseSpec200())
                .extract().as(Board.class);

        assertThat(getBoard.getId()).isEqualTo(boardId);
        assertThat(getBoard.getName()).isEqualTo(name);
        assertThat(getBoard.getDesc()).isEqualTo(desc);

        Assert.assertEquals(getBoard.getId(), boardId);
        Assert.assertEquals(getBoard.getName(), name);
        Assert.assertEquals(getBoard.getDesc(), desc);
    }
}
