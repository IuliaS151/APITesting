package org.example.tests;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.example.steps.*;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertEquals;

public abstract class BaseTest {

    protected final BuildRequest build = new BuildRequest();
    protected final SendRequest send = new SendRequest();
    protected final CheckResponseIsValid check = new CheckResponseIsValid();
    protected final PrepareActualResponse prepare = new PrepareActualResponse();
    protected final CheckActualVsExpected compare = new CheckActualVsExpected();

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "https://api.trello.com/";

        Specifications.installSpecification(
                Specifications.requestSpec(RestAssured.baseURI),
                Specifications.responseSpec200());

        // логи (очень помогают)
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }
}

