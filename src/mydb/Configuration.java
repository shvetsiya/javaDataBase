package mydb;


import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Configuration {
	private DBReaderFactory.type readerType;
	private static Configuration instance;
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		
	private Configuration(){};
	
	public static Configuration instance(){
		if (instance == null){
			instance = new Configuration();
		}
		return instance;
	}
	
	public void setReaderType(DBReaderFactory.type readerType){		
		this.readerType = readerType;
	}
	
	public DBReaderFactory.type getReaderType() {		
		return readerType;				
	}
	
	public DateFormat getDateFormat(){
		return dateFormat;
	}
	
	public String getDBFileName(){
		if (getReaderType() == DBReaderFactory.type.TXT){
			return "data.txt";
		} else {
			return "data.xml";
		}
	}
}
