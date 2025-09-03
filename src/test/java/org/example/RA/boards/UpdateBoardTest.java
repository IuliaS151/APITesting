//package org.example.RA.boards;
//
//import io.restassured.http.ContentType;
//import io.restassured.response.Response;
//import org.assertj.core.api.AssertionsForClassTypes;
//import org.example.RA.support.BaseTest;
//import org.example.RA.client.Endpoints;
//import org.example.RA.models.Board;
//import org.testng.Assert;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.CsvFileSource;
//
//import static io.restassured.RestAssured.*;
//import static org.assertj.core.api.Assertions.assertThat;
//
//public class UpdateBoardTest extends BaseTest {
//
//    protected String boardId;
//
//    @AfterEach
//    public  void cleanUp() {
//        if (boardId != null && !boardId.isEmpty()) {
//            Response deleteBoard = given()
//                    .pathParam("id", boardId)
//                    .when()
//                    .delete(Endpoints.BOARDS_BY_ID);
//            boardId = null;
//        }
//    }
//    private String createBoard(String name, String desc, boolean defaultLists) {
//        Response resp = given()
//                .spec(requestSpecification)
//                .contentType(ContentType.JSON)
//                .queryParam("name", name)
//                .queryParam("desc", desc)
//                .queryParam("defaultLists", defaultLists)
//                .when()
//                .post(Endpoints.BOARDS);
//
//        resp.then().spec(responseSpecification);
//
//        boardId = resp.then().extract().path("id");
//        return boardId;
//    }
//
//    @ParameterizedTest(name = "Update board with name={0}")
//    @CsvFileSource(resources = "/boards.csv", numLinesToSkip = 1)
//    public void shouldUpdateBoardSuccessfully(String name, String desc, boolean defaultLists,
//                                              String newName, String newDesc) {
//        String id = createBoard(name, desc, defaultLists);
//
//        Board updateBoard = given().spec(requestSpecification)
//                        .pathParam("id", id)
//                        .queryParam("name", newName)
//                        .queryParam("desc", newDesc)
//                        .when().put(Endpoints.BOARDS_BY_ID)
//                        .then().spec(responseSpecification)
//                        .extract().as(Board.class);
//
//        Assert.assertTrue(put.time() < 2_000, "API must respond <2s");
//
//        Board updated = prepare.asPojo(put, Board.class);
//        Board expected = new PrepareExpectedResponse()
//                .boardWithId(boardId, "New PUT name", "Updated by PUT");
//
//        assertThat(put.getHeader("Content-Type")).contains("application/json");
//
//        compare.compareBoard(updated, expected);
//
//        AssertionsForClassTypes.assertThat(updated.getName()).isEqualTo(newName());
//        AssertionsForClassTypes.assertThat(updated.getDesc()).isEqualTo(newDesc());
//
//        AssertionsForClassTypes.assertThat(updated.getShortUrl()).contains("trello.com");
//        AssertionsForClassTypes.assertThat(updated.getPrefs().getPermissionLevel())
//                .isEqualTo("private");
//        AssertionsForClassTypes.assertThat(updated.getPrefs().getComments())
//                .isEqualTo("members");
//
//        AssertionsForClassTypes.assertThat(updated.getPrefs().getCardCovers()).isTrue();
//    }
//}
