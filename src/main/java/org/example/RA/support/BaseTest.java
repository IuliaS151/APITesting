package org.example.RA.support;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;

public abstract class BaseTest {

    protected static RequestSpecification requestSpec;

    private final static String URL = "https://api.trello.com/";

    @BeforeAll
    public static void setUp() {
        Specifications.installSpecification(Specifications.requestSpec(URL), null);

        // логи (очень помогают)
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }
    @AfterAll
    public static void tearDown() {

        RestAssured.reset();
    }
}

