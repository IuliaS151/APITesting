package org.example.dummyTest;

import org.example.RA.models.BoardResponse;
import org.example.RA.Specifications;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class myFirstTest {

    private final static String URL = "https://api.trello.com/";

    @Test
    public  void checkBoardIdTest() {

        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpec200());

        BoardResponse board = given()
                .when()
                .pathParam("id", "68a30acb1c754466e971e1b6")
                .get("1/boards/{id}")
                .then().log().all()
                .extract().body().jsonPath().getObject("root", BoardResponse.class);
    }
}