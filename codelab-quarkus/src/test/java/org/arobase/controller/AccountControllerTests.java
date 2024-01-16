package org.arobase.controller;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.arobase.infrastructure.persistence.service.AccountService;
import org.jboss.resteasy.reactive.RestResponse;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;

@QuarkusTest
public class AccountControllerTests {

    @InjectMock
    AccountService accountService;

    @Test
    void testGetAccounts() {
        get("/api/v1/accounts")
                .then()
                .statusCode(RestResponse.StatusCode.OK)
                .contentType(ContentType.JSON);

    }
}
