package TestRunner;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class DataDrivenTesting {

public ArrayList getData(String Sheetname,String rowname,String CellName) throws IOException {
	ArrayList a=new ArrayList();
	FileInputStream fis=new FileInputStream("C:\\Users\\jinka\\eclipse-workspace\\testing\\src\\main\\datatest.xlsx");
	XSSFWorkbook workbook=new XSSFWorkbook(fis);
	int sheetcount = workbook.getNumberOfSheets();
	for(int i=0;i<sheetcount;i++) {
		String sheetname = workbook.getSheetName(i);
		if(sheetname.equalsIgnoreCase(Sheetname)) {
		XSSFSheet sheet = workbook.getSheetAt(i);
		Iterator<Row> row = sheet.iterator();
		Row eachrow = row.next();
		Iterator<Cell> cell = eachrow.cellIterator();
		int k=0;
		int column=0;
		while(cell.hasNext()) {
		Cell eachcell = cell.next();
		
		String cellvalue = eachcell.getStringCellValue();
		if(cellvalue.equalsIgnoreCase(CellName)) {
			column=k;
		}
		k++;
		}
		System.out.println(column);
		while(row.hasNext()) {
			Row rowvalue = row.next();
			if(rowvalue.getCell(column).getStringCellValue().equalsIgnoreCase(rowname)) {
			Iterator<Cell> purchasecell = rowvalue.cellIterator();
			while(purchasecell.hasNext()) {
				 Cell purchasecellvalue = purchasecell.next();
				 if(purchasecellvalue.getCellType()==CellType.STRING)
				a.add(purchasecellvalue.getStringCellValue());
				 else
					 a.add(purchasecellvalue.getNumericCellValue());
			}
			}
		}
		}
	}
	return a;
}
public String getExcelData(String sheetname,int rownum,int cellnum) throws EncryptedDocumentException, IOException {
	
	FileInputStream fis=new FileInputStream("C:\\Users\\jinka\\eclipse-workspace\\testing\\src\\main\\datatest.xlsx");	
	Workbook wb = WorkbookFactory.create(fis);
 Cell value = wb.getSheet(sheetname).getRow(rownum).getCell(cellnum);
 if(value.getCellType()==CellType.STRING) {
	 return value.getStringCellValue();
 }
	 else {
		 return NumberToTextConverter.toText(value.getNumericCellValue());
	 }
 }
	
}

