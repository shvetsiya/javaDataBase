package mydb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.List;

import commands.CommandProcessor;
import entities.Person;


public class Main {
	public static void main(String[] args) throws IOException, ParseException {
		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		
		Configuration config = Configuration.instance();
		config.setReaderType(DBReaderFactory.type.XML);
		
		DBReaderFactory dbReader = DBReaderFactory.instance; 
		final List<Person> people = dbReader.getReader().readDB();
		
		
		final CommandProcessor proc = new CommandProcessor(people);
		
		String cmd = null;
		
		System.out.println("Database system started. Waiting instructions...");
		
		do {
			System.out.print(">> ");
			cmd = bufferRead.readLine();
			if (cmd.equals("quit")) break;
			
			proc.processCommand(cmd);
			
		} while (true);

		
	}
}