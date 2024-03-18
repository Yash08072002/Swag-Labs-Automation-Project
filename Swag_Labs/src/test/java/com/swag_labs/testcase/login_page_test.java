package com.swag_labs.testcase;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.swag_labs.utilities.ReadExcelFile;
import com.swag_labs.pageobject.login_page_pageobject;

public class login_page_test extends baseclass {

    @Test(dataProvider = "loginData")
    public void login(String user, String pswd) {
        login_page_pageobject lg = new login_page_pageobject(driver);        
        logger.info("Enter username");
        lg.user(user);
        logger.info("Enter password");
        lg.psw(pswd);
        logger.info("Click on login button");
        lg.loginbutton();
    }

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        String filePath = "C:\\Users\\yashr\\OneDrive\\Desktop\\Swag Labs Automation Project\\Swag_Labs\\TestData\\test data.xlsx";
        int ttlRows = ReadExcelFile.getRowCount(filePath, "LoginTestData");
        int ttlColumns = ReadExcelFile.getColCount(filePath, "LoginTestData");

        Object data[][] = new Object[ttlRows - 1][ttlColumns];

        for (int i = 1; i < ttlRows; i++) {
            for (int j = 0; j < ttlColumns; j++) {
                data[i - 1][j] = ReadExcelFile.getCellValue(filePath, "LoginTestData", i, j);
            }
        }
        return data;
    }
} 	
