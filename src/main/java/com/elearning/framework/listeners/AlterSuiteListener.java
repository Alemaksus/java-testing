package com.epmrdpt.framework.listeners;

import static com.epmrdpt.framework.properties.ScopeProperties.getScope;

import java.util.List;
import org.testng.IAlterSuiteListener;
import org.testng.xml.XmlSuite;

public class AlterSuiteListener implements IAlterSuiteListener {

  @Override
  public void alter(List<XmlSuite> suites) {
    XmlSuite suite = suites.get(0);
    suite.addIncludedGroup(getScope());
  }
}
