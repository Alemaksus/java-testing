package com.epmrdpt.api.training_management;

import static com.epmrdpt.api.Endpoints.TRAINING_MANAGEMENT_AUTO_REPLY_TAB_CONTROLLER_AUTOMATED_DEFAULT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.NOTIFICATION_SCREEN_DATE_OF_TRAINING_MESSAGE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.NOTIFICATION_SCREEN_GRATEFULNESS_MESSAGE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.NOTIFICATION_SCREEN_INFORM_MESSAGE;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

import com.epmrdpt.bo.autoreply_tab.AutoReplyTab;
import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.tokens_storage.TokensStorage;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_107097 Verify 'GET api/training-management/plan/automated/default' endpoint",
    groups = {"api"})
public class GetAutoReplyTabControllerAutomatedDefaultEndpointApiTest {

  private static final String EXPECTED_FIRST_LINE_VALUE =
      LocaleProperties.getValueOf(NOTIFICATION_SCREEN_GRATEFULNESS_MESSAGE) + " <training name>.";
  private static final String EXPECTED_SECOND_LINE_VALUE =
      LocaleProperties.getValueOf(NOTIFICATION_SCREEN_DATE_OF_TRAINING_MESSAGE)
          + " <start date> - <end date>";
  private static final String EXPECTED_THIRD_LINE_VALUE =
      LocaleProperties.getValueOf(NOTIFICATION_SCREEN_INFORM_MESSAGE);
  private final Set<String> expectedLocalizations = Set.of(
      "en-US",
      "ru-RU",
      "uk-UA");

  @Test(priority = 1)
  public void verifyGetAutoReplyTabControllerAutomatedDefaultStatusOk() {
    SoftAssert softAssert = new SoftAssert();
    AutoReplyTab[] autoReplyTabs = getAutoReplyTabControllerAutomatedDefault()
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .body()
        .as(AutoReplyTab[].class);

    Set<String> actualLocalizations = Arrays.stream(autoReplyTabs)
        .map(tab -> tab.getCulture()).collect(Collectors.toSet());
    softAssert.assertEquals(actualLocalizations, expectedLocalizations,
        "Unexpected list of Localizations!");
    softAssert.assertTrue(Arrays.stream(autoReplyTabs)
            .anyMatch(tab -> tab.getFirstLine().equals(EXPECTED_FIRST_LINE_VALUE)
                && tab.getSecondLine().equals(EXPECTED_SECOND_LINE_VALUE)
                && tab.getThirdLine().equals(EXPECTED_THIRD_LINE_VALUE)),
        "There is no block that contains the necessary notifications!");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void verifyGetTypesUnauthorized() {
    ((RequestSpecificationImpl) requestSpecification).removeHeaders();
    getAutoReplyTabControllerAutomatedDefault()
        .then()
        .statusCode(HttpStatus.SC_UNAUTHORIZED);
  }

  @Test(priority = 3)
  public void verifyGetTypesForbidden() {
    TokensStorage.setUpStudentRestAssuredCredentials();
    getAutoReplyTabControllerAutomatedDefault()
        .then()
        .statusCode(HttpStatus.SC_FORBIDDEN);
  }

  private Response getAutoReplyTabControllerAutomatedDefault() {
    return given()
        .when()
        .get(TRAINING_MANAGEMENT_AUTO_REPLY_TAB_CONTROLLER_AUTOMATED_DEFAULT);
  }
}
