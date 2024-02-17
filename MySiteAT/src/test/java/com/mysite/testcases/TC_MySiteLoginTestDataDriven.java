package com.mysite.testcases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.mysite.pageobject.HomePage;
import com.mysite.utilities.ReadExcelFile;

public class TC_MySiteLoginTestDataDriven extends BaseClass {

	@Test(dataProvider = "loginDataProvider")
	public Object verifyMySiteLogin(String url, String header) {
		driver.get(url);
		logger.info(" Given URL Opened");
		System.out.println(url);

		HomePage hp = new HomePage(driver);
		hp.verifysearchHeader(header);
		logger.info("Login Verified, User succesfully logged into MySite");
		return header;
				
	}

	@DataProvider(name="loginDataProvider")
	public String[][] loginDataProvider() {
		System.out.println(System.getProperty("user.dir"));
		String fileName = System.getProperty("user.dir") + "\\TestData\\MySiteTestData.xlsx";

		int ttlRows = ReadExcelFile.getRowCount(fileName, "URLDetails");
		int ttlColumns = ReadExcelFile.getColCount(fileName, "URLDetails");

		String data[][] = new String[ttlRows - 1][ttlColumns];

		for (int i = 1; i < ttlRows; i++)// rows =1,2
		{
			for (int j = 0; j < ttlColumns; j++)// col=0, 1,2
			{

				data[i - 1][j] = ReadExcelFile.getCellValue(fileName, "URLDetails", i, j);
				
			}

		}
		return data;
	}
}
