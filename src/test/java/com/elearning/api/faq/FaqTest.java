package com.epmrdpt.api.faq;

import static com.epmrdpt.api.Endpoints.FAQ;
import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

@Test(description = "Verify 'Faq'",
    groups = {"api_deprecated"})
public class FaqTest {

  @Test
  public void verifyFaq() {
    given()
        .when()
        .get(FAQ)
        .then()
        .statusCode(200);
  }
}
