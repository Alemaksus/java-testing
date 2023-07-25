package com.epmrdpt.utils;

import static com.epmrdpt.utils.FileUtils.FILE_DELIMITER;

import com.epmrdpt.framework.loging.Log;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

  private XSSFWorkbook workbook;
  private XSSFSheet sheet;
  private XSSFSheet url;
  private FileInputStream fileInput;
  private FormulaEvaluator evaluator;

  public ExcelUtils(String path, Integer sheetNumber) {
    File sourceFile = new File(path);
    try {
      fileInput = new FileInputStream(sourceFile);
      workbook = new XSSFWorkbook(fileInput);
      sheet = workbook.getSheetAt(sheetNumber);
      url = workbook.getSheetAt(sheetNumber);
      evaluator = workbook.getCreationHelper().createFormulaEvaluator();
      fileInput.close();
      Log.logInfoMessage("Successfully fetched file from given path: " + path);
    } catch (Exception e) {
      Log.logInfoMessage("Couldn't find file at given path: " + path);
    }
  }

  public ExcelUtils(String filePath, String fileName, Integer sheetNumber) {
    this(filePath + FILE_DELIMITER + fileName, sheetNumber);
  }

  public XSSFCell getCell(int row, int column) {
    return sheet.getRow(row).getCell(column);
  }

  public int getRowsCount() {
    return sheet.getLastRowNum() + 1;
  }
  public String getCellURL (int row, int column) {
    Cell cell = getCell(row, column);
    Hyperlink cellURL = cell.getHyperlink();
    return cellURL.getAddress();
  }

  public String getCellValue(int row, int column) {
    String cellValue = null;
    Cell cell = getCell(row, column);
    if (cell != null) {
      switch (cell.getCellType()) {
        case _NONE:
        case BLANK:
          cellValue = "";
          break;
        case BOOLEAN:
          cellValue = Boolean.toString(cell.getBooleanCellValue());
          break;
        case NUMERIC:
          cellValue = Double.toString(cell.getNumericCellValue());
          break;
        case STRING:
          cellValue = cell.getStringCellValue();
          break;
        case ERROR:
          cellValue = Byte.toString(cell.getErrorCellValue());
          break;
        case FORMULA:
          cellValue = cell.getCellFormula() + " " + (evaluator.evaluate(cell).getNumberValue());
          break;
        default:
          cellValue = null;
      }
    }
    Log.logInfoMessage("Value at cell (%s,%s) is %s", row, column, cellValue);
    return cellValue;
  }

  public String getCellValueByFormula(String cellFormula) {
    CellReference cellReference = new CellReference(cellFormula);
    Log.logInfoMessage("Fetching value at cell %s", cellFormula);
    return getCellValue(cellReference.getRow(), cellReference.getCol());
  }

  public String readAndParseExcelToString() {
    StringBuilder excelStringBuilder = new StringBuilder();
    for (int rowCounter = 1; rowCounter <= getRowsCount(); rowCounter++) {
      excelStringBuilder.append(readAndParseRowToString(rowCounter));
    }
    return excelStringBuilder.toString();
  }

  public String readAndParseRowToString(int rowNumber) {
    StringBuilder excelStringBuilder = new StringBuilder();
    for (int cellCounter = 1; cellCounter <= sheet.getRow(rowNumber - 1).getLastCellNum();
        cellCounter++) {
      excelStringBuilder.append(getCellValue(rowNumber - 1, cellCounter - 1));
      excelStringBuilder.append("\t");
    }
    excelStringBuilder.append("\n");
    return excelStringBuilder.toString();
  }

  public int getNumberOfFilledRows() {
    return sheet.getPhysicalNumberOfRows();
  }

  public List<String> getAllColumnValues(int columnIndex) {
    List<String> values = new ArrayList<>();
    DataFormatter dataFormatter = new DataFormatter();
    Row row;
    for(int i = 1; i < getNumberOfFilledRows(); i++) {
      row = sheet.getRow(i);
      Cell cell = row.getCell(columnIndex);
      values.add(dataFormatter.formatCellValue(cell));
    }
    return values;
  }

  public List<String> getAllRowValues(int rowIndex) {
    List<String> values = new ArrayList<>();
    Row row = sheet.getRow(rowIndex);
    Iterator<Cell> cellIterator = row.cellIterator();
    while (cellIterator.hasNext()){
      values.add(cellIterator.next().getStringCellValue());
    }
    return values;
  }
}
