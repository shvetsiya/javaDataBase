package mydb;

import java.io.IOException;
import java.text.ParseException;

public class DBReaderFactory {
	public static enum type{TXT, XML};
	
	private DBReaderFactory(){};
	
	public static final DBReaderFactory instance = new DBReaderFactory();
		
	private Configuration config = Configuration.instance(); 
	
	public Reader getReader() throws IOException, ParseException{
		//config.setFileName(fileName);
		if (config.getReaderType() == type.TXT) {			
			return new TxtDBReader();
		}else if (config.getReaderType() == type.XML) {			
			return new XmlDBReader();
		} else {
			throw new IOException("Incorrect extension of Data base file!");   
		}
	}
}