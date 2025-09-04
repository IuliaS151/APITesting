package org.example.RA.boards;

import io.restassured.response.Response;
import org.example.RA.client.Endpoints;
import org.example.RA.models.Board;
import org.example.RA.support.BaseTest;
import org.testng.Assert;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import static io.restassured.RestAssured.*;
import static org.assertj.core.api.Assertions.assertThat;

public class DeleteBoardTest extends BaseTest {

    @ParameterizedTest(name = "Delete board with name={0}")
    @CsvFileSource(resources = "/boards.csv", numLinesToSkip = 1)
    public void shouldDeleteBoardSuccessfully(String name, String desc, boolean defaultLists) {

        String boardId = Board.create(name, desc, defaultLists);

        Response deleteResp = given()
                .pathParam("id", boardId).log().all()
                .when().delete(Endpoints.BOARDS_BY_ID)
                .then().log().all()
                .extract().response();

        assertThat(deleteResp.statusCode()).isEqualTo(200);
        assertThat(deleteResp.body().path("_value"), is(equalTo(null)));

        Assert.assertEquals(deleteResp.statusCode(), 200);
        Assert.assertNull(deleteResp.body().path("_value"), null);

        Response getAfter = given()
                .pathParam("id", boardId).log().all()
                .when().get(Endpoints.BOARDS_BY_ID)
                .then().log().all()
                .extract().response();

        assertThat(getAfter.statusCode()).isEqualTo(404);
        Assert.assertEquals(getAfter.statusCode(), 404);
    }
}
