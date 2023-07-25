package com.epmrdpt.framework.listeners;

import com.epam.reportportal.testng.BaseTestNGListener;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.ISuite;

public class ReportPortalApiTestListener extends BaseTestNGListener {

  public ReportPortalApiTestListener() {
    super(new ReportPortalParameterOverrideTestNgService());
  }

  @Override
  public void onStart(ISuite suite) {
    RestAssured.filters(new ResponseLoggingFilter(), new RequestLoggingFilter());
    RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
  }
}
