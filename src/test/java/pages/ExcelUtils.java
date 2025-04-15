package pages;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	public static List<String[]> readDataFromExcelForDataDriver(String excelFilePath, String sheetName) {
		List<String[]> testData = new ArrayList<String[]>();
		try {
			FileInputStream fis = new FileInputStream(new File(excelFilePath));
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet(sheetName);

			int rowsCount = sheet.getLastRowNum();

			for (int i = 1; i <= rowsCount; i++) {
				String[] eachRowData = new String[sheet.getRow(i).getLastCellNum()];
				int index = 0;

				Iterator<Cell> allCells = sheet.getRow(i).cellIterator();
				while (allCells.hasNext()) {
					Cell eachCell = allCells.next();
					switch (eachCell.getCellType()) {
					case STRING:
						eachRowData[index] = eachCell.getStringCellValue();
						break;
					case NUMERIC:
						eachRowData[index] = String.valueOf(eachCell.getNumericCellValue());
						break;
					case BOOLEAN:
						eachRowData[index] = String.valueOf(eachCell.getBooleanCellValue());
						break;
					case BLANK:
						eachRowData[index] = "";
						break;
					default:
						break;
					}
					index++;
				}
				testData.add(eachRowData);
			}

		} catch (Exception e) {
			System.out.println("Exception occured while reading the data from excel: " + e.getMessage());
		}

		return testData;
	}

	public Iterator<Object[]> readDataFromExcelForHybridDriven(String excelFilePath, String sheetName,
			String testName) {

		List<Map<String, String>> testData = null;

		try {
			FileInputStream fis = new FileInputStream(new File(excelFilePath));
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet(sheetName);
			testData = getTestDataBySheetNameAndTestname(sheet,testName);

		} catch (Exception e) {
			System.out.println("Exception occured while reading the data from excel: " + e.getMessage());
		}

		return convertTestDataToIteratorOfObjectArray(testData);

	}

	public List<Map<String, String>> getTestDataBySheetNameAndTestname(XSSFSheet sheet, String testName) {
		List<Map<String, String>> testData = new LinkedList<Map<String, String>>();
		int startRowNo = getStartRowNo(sheet, testName);
		int endRowNo = getEndRowNo(sheet, testName, startRowNo);
		List<String> testDataHeaders = getTestDataHeaders(sheet, startRowNo);
		for (int i = startRowNo + 2; i < endRowNo; i++) {
			Map<String, String> eachRowDataMap = new Hashtable<String, String>();
			Row eachRow = sheet.getRow(i);
			Cell cellData;

			for (int j = 0; j < testDataHeaders.size(); j++) {
				if (!testDataHeaders.get(j).equalsIgnoreCase("CELL NOT FOUND")
						&& !testDataHeaders.get(j).equalsIgnoreCase("CELL DATA NOT FOUND")) {
					cellData = eachRow.getCell(j + 1, MissingCellPolicy.RETURN_NULL_AND_BLANK);
					if (cellData != null) {
						cellData.setCellType(CellType.STRING);
					}

					if (cellData != null && !cellData.getStringCellValue().trim().equals("")) {
						eachRowDataMap.put(testDataHeaders.get(j), cellData.getStringCellValue().trim());
					}
				}
			}

			testData.add(eachRowDataMap);
		}
		return testData;
	}

	public int getStartRowNo(XSSFSheet sheet, String testName) {
		try {
			for (int i = 1; i < sheet.getLastRowNum(); i++) {				
				if (sheet.getRow(i).getCell(0, MissingCellPolicy.RETURN_NULL_AND_BLANK) != null) {
					if (!sheet.getRow(i).getCell(0,MissingCellPolicy.RETURN_NULL_AND_BLANK).getStringCellValue().trim().equals("")) {
						if (sheet.getRow(i).getCell(0).getStringCellValue().trim().equals(testName)) {
							return i;
						}
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Exception Occured while fetching Start row of Test Name " + testName
					+ " and exception is: " + e.getMessage());
		}
		return 0;
	}

	public int getEndRowNo(XSSFSheet sheet, String testName, int startRowNo) {
		try {
			for (int i = startRowNo + 1; i < sheet.getLastRowNum(); i++) {
				if (sheet.getRow(i).getCell(0,MissingCellPolicy.RETURN_NULL_AND_BLANK) != null) {
					if (!sheet.getRow(i).getCell(0,MissingCellPolicy.RETURN_NULL_AND_BLANK).getStringCellValue().trim().equals("")) {
						if (sheet.getRow(i).getCell(0).getStringCellValue().trim().equals(testName)) {
							return i;
						}
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Exception Occured while fetching End row of Test Name " + testName
					+ " and exception is: " + e.getMessage());
		}
		return startRowNo;
	}

	public List<String> getTestDataHeaders(XSSFSheet sheet, int startRowNo) {
		List<String> headersList = new ArrayList<String>();
		Row headersRow = sheet.getRow(startRowNo + 1);
		for (int i = 1; i < headersRow.getLastCellNum(); i++) {
			Cell dataCell = headersRow.getCell(i, MissingCellPolicy.RETURN_NULL_AND_BLANK);
			if (dataCell == null) {
				headersList.add("CELL NOT FOUND");
			} else if (dataCell.getStringCellValue().trim().equals("")) {
				headersList.add("CELL DATA NOT FOUND");
			} else {
				dataCell.setCellType(CellType.STRING);
				headersList.add(dataCell.getStringCellValue().trim());
			}
		}

		return headersList;
	}
	
	public Iterator<Object[]> convertTestDataToIteratorOfObjectArray(List<Map<String, String>> listOfMaps) {
		List<Object[]> objArrList = new ArrayList<Object[]>();
		for(Map<String,String> eachMap : listOfMaps) {
			objArrList.add(new Object[] {eachMap});
			
			
//			Object[] objArr = new Object[4];
//			objArr[0] = 100;
//			objArr[1] = "Hello";
//			objArr[2] = new ArrayList();
//			objArr[2] = new HashMap();
			
		}
		
		return objArrList.iterator();
	}

}
