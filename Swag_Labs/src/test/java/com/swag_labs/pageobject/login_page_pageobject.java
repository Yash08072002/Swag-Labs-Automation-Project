package com.swag_labs.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;

public class login_page_pageobject {

	// Create object of web driver
	WebDriver ldriver;

	public login_page_pageobject(WebDriver rdriver) {

		ldriver = rdriver;

		PageFactory.initElements(rdriver, this);

	}

	// identify webelement
	@FindBy(id = "user-name")
	WebElement username;

	@FindBy(id = "password")
	WebElement password;

	@FindBy(id = "login-button")
	WebElement login;

	public void user(String user) {
		username.sendKeys(user);
	}

	public void psw(String pswd) {
		password.sendKeys(pswd);
	}

	public void loginbutton() {
		login.click();
	}

	public String getPageTitle() {
		return ldriver.getTitle();
	}
	

}
