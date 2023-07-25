package com.epmrdpt.framework.listeners;

import static com.epmrdpt.framework.properties.ReportPortalProperties.getLaunchName;
import static com.epmrdpt.framework.properties.ReportPortalProperties.getMode;

import com.epam.reportportal.listeners.ListenerParameters;
import com.epam.reportportal.service.Launch;
import com.epam.reportportal.service.ReportPortal;
import com.epam.reportportal.testng.TestNGService;
import com.epam.reportportal.utils.properties.PropertiesLoader;
import com.epam.ta.reportportal.ws.model.launch.StartLaunchRQ;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import rp.com.google.common.base.Strings;
import rp.com.google.common.base.Supplier;

public class ReportPortalParameterOverrideTestNgService extends TestNGService {

  public ReportPortalParameterOverrideTestNgService() {
    super(getLaunchOverriddenProperties());
  }

  private static Supplier<Launch> getLaunchOverriddenProperties() {
    ListenerParameters parameters = new ListenerParameters(PropertiesLoader.load());
    parameters.setLaunchRunningMode(getMode());
    parameters.setLaunchName(getLaunchName());
    ReportPortal reportPortal = ReportPortal.builder().withParameters(parameters).build();
    StartLaunchRQ rq = buildStartLaunch(reportPortal.getParameters());
    return () -> reportPortal.newLaunch(rq);
  }

  private static StartLaunchRQ buildStartLaunch(ListenerParameters parameters) {
    StartLaunchRQ rq = new StartLaunchRQ();
    rq.setName(parameters.getLaunchName());
    rq.setStartTime(Calendar.getInstance().getTime());
    rq.setMode(parameters.getLaunchRunningMode());
    rq.setDescription(String.format("%s %s %s %s",
        new SimpleDateFormat("MM/dd/yyyy").format(System.currentTimeMillis()),
        System.getProperty("locale"),
        System.getProperty("env"),
        System.getProperty("scope")));
    if (!Strings.isNullOrEmpty(parameters.getDescription())) {
      rq.setDescription(parameters.getDescription());
    }
    return rq;
  }
}
