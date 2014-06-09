package com.fmb.datadriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import jxl.Cell;
import jxl.Workbook;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;

public class ExcelUtils
{
	private static XSSFWorkbook ExcelWBook;
	private static XSSFSheet ExcelWSheet;
	private static XSSFRow Row;
	private static XSSFCell Cell;
    
	/*
	 * 获取到相应Path路径下文件的SheetName下内容
	 */
	public static void setExcelFile(String Path, String SheetName) throws Exception
	{
		try
		{
		FileInputStream ExcelFile = new FileInputStream(Path);
		ExcelWBook = new XSSFWorkbook(ExcelFile);
		ExcelWSheet = ExcelWBook.getSheet(SheetName);
		}
		catch (Exception e)
		{
			throw(e);
		}
	}
	
	public static Object[][] getTableArray(String Path, String SheetName, int iTestCaseRow) throws Exception
	{
		String[][] tabArray = null;
		try
		{
			FileInputStream ExcelFile = new FileInputStream(Path);
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
			
			int startCol = 1;
			int ci=0, cj=0;
			int totalRows = 1;
			int totalCols =2;
			tabArray = new String[totalRows][totalCols];
			for(int j=startCol; j<=totalCols; j++, cj++)
			{
				tabArray[ci][cj] = getCellData(iTestCaseRow,j);
				System.out.println(tabArray[ci][cj]);
			}
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		}
		catch(IOException e)
		{
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		}
		return (tabArray);
	}
	/*
	 * 获取某个单元格数据，根据单元格内容格式不同来获取
	 */
	public static String getCellData(int RowNum, int ColNum) throws Exception
	{
		Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
		String CellData ="";
		switch(Cell.getCellType())
		{
		case XSSFCell.CELL_TYPE_STRING:
			CellData = Cell.getStringCellValue();
			break;
		case XSSFCell.CELL_TYPE_NUMERIC:
			CellData = String.valueOf((int)Cell.getNumericCellValue());    //将纯数字类型用getNumericCellValue()方法，然后再转换成String类型
			break;
		case XSSFCell.CELL_TYPE_BOOLEAN:
			CellData = String.valueOf(Cell.getBooleanCellValue());
			break;
		case XSSFCell.CELL_TYPE_BLANK:
			CellData = "";
			break;
		default:
			CellData = "";
			break;
		}
		return CellData;
	}
	/*
	 * 给某个单元格设置内容
	 */
	public static void setCellData(String Result, int RowNum, int ColNum) throws Exception
	{
		Row = ExcelWSheet.getRow(RowNum);
		Cell = Row.getCell(ColNum, Row.RETURN_BLANK_AS_NULL);
		if(Cell == null)
		{
			Cell = Row.createCell(ColNum);
			Cell.setCellValue(Result);
		}
		else
		{
			Cell.setCellValue(Result);
		}
		FileOutputStream fileOut = new FileOutputStream(Constant.Path_TestData + Constant.File_TestData);
		ExcelWBook.write(fileOut);
		fileOut.flush();
		fileOut.close();
	}
	
	

}
