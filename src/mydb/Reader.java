package mydb;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import entities.Person;

public interface Reader {
	List<Person> readDB() throws IOException, ParseException; 	
}
