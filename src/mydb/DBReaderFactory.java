package mydb;

import java.io.IOException;
import java.text.ParseException;

public class DBReaderFactory {
	public static enum type{TXT, XML};
	
	DBReaderFactory(){};
	
	public static DBReaderFactory instance = new DBReaderFactory();
		
	private Configuration config = Configuration.instance(); 
	
	public Reader getReader(String dbType) throws IOException, ParseException{
		//config.setFileName(fileName);
		if (dbType.equals("txt")) {
			config.setReaderType(DBReaderFactory.type.TXT);
			return new TxtDBReader();
		}else if (dbType.equals("xml")) {
			config.setReaderType(DBReaderFactory.type.XML);
			return new XmlDBReader();
		} else {
			throw new IOException("Incorrect extension of Data base file!");   
		}
	}
}