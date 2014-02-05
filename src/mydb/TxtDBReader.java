package mydb;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import dto.StudentDTO;
import dto.TeacherDTO;
import entities.Person;
import entities.Student;
import entities.Teacher;

public class TxtDBReader implements Reader {
	private Configuration config = Configuration.instance();
	private final List<Person> people;
	private final BufferedReader bufferRead;
	
	public TxtDBReader() throws IOException{
		bufferRead = new BufferedReader(new InputStreamReader(new FileInputStream(config.getDBFileName())));
		people = new ArrayList<Person>();
	}
		
	@Override
	public List<Person> readDB() throws IOException, ParseException {
		String line = null;

		do {
			line = bufferRead.readLine();			
			if (line != null) {
				if (line.equals("Student")) {
					readStudent();
				} else if (line.equals("Teacher")) {
					readTeacher();
				} 
			}
		} while (line != null);
		close();
		return people;
	}

	private void readStudent() throws IOException, ParseException {
		final StudentDTO dto = new StudentDTO(		
								   bufferRead.readLine().trim(),
								   config.getDateFormat().parse(bufferRead.readLine().trim()),
								   bufferRead.readLine().trim(), 
								   bufferRead.readLine().trim());
		
		people.add(new Student(dto));

		bufferRead.readLine();
	}

	private void readTeacher() throws IOException, ParseException {
		final TeacherDTO dto = new TeacherDTO(
								       bufferRead.readLine().trim(), 
								       config.getDateFormat().parse(bufferRead.readLine().trim()),
								       bufferRead.readLine().trim());
		people.add(new Teacher(dto));

		bufferRead.readLine();
	}
	
	private void close() throws IOException {
		if (bufferRead != null) {
			bufferRead.close();
		}
	}

}


