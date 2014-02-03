package mydb;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

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
		final Student student = new Student();
		student.setName(bufferRead.readLine().trim());
		student.setBirthDay(config.getDateFormat().parse(bufferRead.readLine().trim()));
		student.setGroupName(bufferRead.readLine().trim());
		student.setFaculty(bufferRead.readLine().trim());
		people.add(student);

		bufferRead.readLine();
	}

	private void readTeacher() throws IOException, ParseException {
		final Teacher teacher = new Teacher();
		teacher.setName(bufferRead.readLine().trim());
		teacher.setBirthDay(config.getDateFormat().parse(bufferRead.readLine().trim()));
		teacher.setSubject(bufferRead.readLine().trim());
		people.add(teacher);

		bufferRead.readLine();
	}
	
	private void close() throws IOException {
		if (bufferRead != null) {
			bufferRead.close();
		}
	}

}


