package com.epmrdpt.framework.ui.pseudoelement;

public enum PseudoElementEnum {
  BEFORE(":before"),
  AFTER(":after");

  private String name;

  PseudoElementEnum(String name) {
    this.name = name;
  }

  public String getPseudoClass() {
    return name;
  }
}
