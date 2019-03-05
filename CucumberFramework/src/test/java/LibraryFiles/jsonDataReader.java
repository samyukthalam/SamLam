package LibraryFiles;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class jsonDataReader extends ReUsableLibrary {
	public static JSONObject jsonDataObj;

	public static String jsonDataReaderMeth(String valueToRead, String jsonFileName) {
		try {
			FileInputStream file = new FileInputStream(propFilePath);
			Properties prop = new Properties();
			prop.load(file);
			String path = prop.getProperty("testDataResourcePath");
			JSONParser jsonParser = new JSONParser();
			jsonDataObj = (JSONObject) jsonParser.parse(new FileReader(path + jsonFileName));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (String) jsonDataObj.get(valueToRead);
	}

}
