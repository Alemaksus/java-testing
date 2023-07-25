package com.epmrdpt.utils;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class ClipboardUtils {

  private ClipboardUtils() {
  }

  public static String pasteCopiedText() {
    String copiedText;
    try {
      copiedText = (String) Toolkit.getDefaultToolkit().getSystemClipboard()
          .getData(DataFlavor.stringFlavor);
    } catch (UnsupportedFlavorException | IOException e) {
      throw new RuntimeException("Could not get text from clipboard");
    }
    return copiedText;
  }
}
