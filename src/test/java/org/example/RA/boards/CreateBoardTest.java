package org.example.RA.boards;

import io.restassured.response.Response;
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

        /*Assert.assertTrue(resp.time() < 2_000, "API must respond <2s");

        Board created = prepare.asPojo(response, Board.class);
        Board expected = expect
                .createBoard("ApiTestBoard", "Created from RA by POST");
        compare.compareBoardIgnoringId(created, expected); // AssertJ

        assertThat(resp.getHeader("Content-Type")).contains("application/json");

        Map<String, Object> json = resp.as(new TypeRef<Map<String, Object>>() {});

        String id = (String) json.get("id");
        String shortUrl = (String) json.get("shortUrl");

        assertThat(id).matches("[\\w\\d]{8,}");
        assertThat(shortUrl).contains("trello.com");
        */
    }
}
