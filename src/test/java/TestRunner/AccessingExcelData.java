package TestRunner;

import java.io.IOException;
import java.util.ArrayList;

import org.testng.annotations.Test;

public class AccessingExcelData {
@Test
public void getprofileData() throws IOException {
	DataDrivenTesting ddt=new DataDrivenTesting();
	ArrayList data = ddt.getData("testduplicate","add profile","testcases");
	int count = data.size();
	for(int i=0;i<count;i++) {
		System.out.println(data.get(i));
	}
	String value = ddt.getExcelData("testduplicate",4 , 0);
	System.out.println(value);
}
}
