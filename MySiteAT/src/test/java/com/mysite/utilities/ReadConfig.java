package com.mysite.utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {

	Properties properties;
	String path = System.getProperty("user.dir") + "\\Configuration\\config.properties";

	public ReadConfig() {
		try {
			properties = new Properties();

			FileInputStream fis = new FileInputStream(path);
			properties.load(fis);
		} catch (Exception e) {

			e.printStackTrace();
		}

	}
	
	public String getURL()
	{
		String  value = properties.getProperty("T1URL");
		if(value!=null)
			return value;
		else
			throw new RuntimeException("URL not specified in Config file");
		
	}
	
	public String getBrowser()
	{
		String  value = properties.getProperty("browser");
		if(value!=null)
			return value;
		else
			throw new RuntimeException("Browser not specified in Config file");
		
	}

}
