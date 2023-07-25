package com.epmrdpt.api.training_management;

import static com.epmrdpt.api.Endpoints.TRAINING_MANAGEMENT_GROUP_CONTROLLER_GROUP_WITH_ID;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.testng.Assert.assertEquals;

import com.epmrdpt.bo.group_controller.GroupInfo;
import com.epmrdpt.tokens_storage.TokensStorage;
import com.epmrdpt.framework.properties.ApiProperty;
import com.epmrdpt.framework.properties.ApiPropertyService;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import java.util.ArrayList;
import org.apache.http.HttpStatus;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT-106245 Verify 'GET /api/training-management/{planId}/groups/{groupId}' endpoint",
    groups = {"api"})

public class GetGroupsControllerGroupsIdEndpointApiTest {

  private final int planId = 3506;
  private final int groupId = Integer.parseInt(ApiPropertyService.getValueOf(ApiProperty.GROUP_ID));
  private final int invalidGroupId = -1;
  private final int invalidPlanId = 888;

  @DataProvider(name = "Provider data with path param")
  private Object[][] dataProviderPathParam() {
    return new Object[][]{
        {planId, invalidGroupId},
        {invalidPlanId, groupId},
    };
  }

  @Test(priority = 1)
  public void verifyGetGroupInfoStatusOk() {
    GroupInfo actualGroupInfo = getGroupInfo(planId, groupId)
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .body()
        .jsonPath()
        .getObject("GroupInfo", GroupInfo.class);
    GroupInfo expectedGroupInfo = getGroupInfoForTest();
    assertEquals(actualGroupInfo, expectedGroupInfo,
        "Actual group info isn't equal to expected response!");
  }

  @Test(priority = 1, dataProvider = "Provider data with path param")
  public void verifyGetGroupInfoBadRequest(int planId, int groupId) {
    getGroupInfo(planId, groupId)
        .then()
        .statusCode(HttpStatus.SC_BAD_REQUEST);
  }

  @Test(priority = 2)
  public void verifyGetGroupInfoUnauthorized() {
    ((RequestSpecificationImpl) requestSpecification).removeHeaders();
    getGroupInfo(planId, groupId)
        .then()
        .statusCode(HttpStatus.SC_UNAUTHORIZED);
  }

  @Test(priority = 3)
  public void verifyGetGroupInfoForbidden() {
    TokensStorage.setUpStudentRestAssuredCredentials();
    getGroupInfo(planId, groupId)
        .then()
        .statusCode(HttpStatus.SC_FORBIDDEN);
  }

  private Response getGroupInfo(int planId, int groupId) {
    return given()
        .pathParam("planId", planId)
        .pathParam("groupId", groupId)
        .when()
        .get(TRAINING_MANAGEMENT_GROUP_CONTROLLER_GROUP_WITH_ID);
  }

  private GroupInfo getGroupInfoForTest() {
    return new GroupInfo(
        groupId,
        "Software Testing 1",
        false,
        false,
        "2022-11-01T00:00:00Z",
        "2023-12-29T00:00:00Z",
        1,
        20,
        new ArrayList<>());
  }
}
