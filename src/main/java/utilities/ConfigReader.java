package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	
	private Properties prop;
	
	/**
	 * This method is used to load the properties from config.properties file
	 * 
	 */
	
	public Properties init_Prop()
	{
		prop = new Properties();
		try {
			//FileInputStream fis = new FileInputStream("/Users/parakhta/Desktop/RestAsured/src/test/resources/config/config.properties");
			prop.load(ConfigReader.class.getClassLoader().getResourceAsStream("config.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return prop;
		
	}

}
