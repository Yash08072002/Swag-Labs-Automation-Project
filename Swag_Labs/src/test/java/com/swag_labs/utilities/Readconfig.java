package com.swag_labs.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class Readconfig {

	Properties prop;
	//Enter the path of the configration file.
	String path ="C:\\Users\\yashr\\OneDrive\\Desktop\\Swag Labs Automation Project\\Swag_Labs\\Configuration\\config.properties";

	//Method to read the properties from the configgration file.
	public Readconfig() {
		prop = new Properties();
		try {
			FileInputStream fil = new FileInputStream(path);
			prop.load(fil);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//Method to get the url from the configration file.
	public String Baseurl() {
		String value = prop.getProperty("baseurl"
				+ "");
		if (value!=null)
			return value;
		
		else {throw new RuntimeException("url note found");}
	}
	
	//Method to get the browser from the configration file.
	public String Browser() {
		String value = prop.getProperty("browser");
		if (value!=null)
			return value;
		
		else {throw new RuntimeException("browserSS note found");}
	}
}
