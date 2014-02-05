package mydb;


import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import dto.StudentDTO;
import dto.TeacherDTO;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import entities.Person;
import entities.Student;
import entities.Teacher;

public class XmlDBReader implements Reader {
	private Configuration config = Configuration.instance();
	private final List<Person> people;
	NodeList studentList;
	NodeList teacherList;

	public XmlDBReader() throws IOException{
		people = new ArrayList<Person>();
		try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            File file = new File(config.getDBFileName());
            if (file.exists()) {
            	Document doc = db.parse(file);
                Element docEle = doc.getDocumentElement();

                studentList = docEle.getElementsByTagName("Student");
                teacherList = docEle.getElementsByTagName("Teacher");

            } else {
            	throw new IOException("File " + config.getDBFileName() + " can not be open!");            	
            }
		
		} catch (Exception e) {
			System.out.println(e);
		}

	}
	
	@Override
	public List<Person> readDB() throws IOException, ParseException {
		if (studentList != null && studentList.getLength() > 0) {
			for (int i = 0; i < studentList.getLength(); i++) {
		        Node node = studentList.item(i);
		        if (node.getNodeType() == Node.ELEMENT_NODE) {
		        	Element e = (Element) node;
		            people.add(readStudent(e));
		        }
		    }

			
		} else{
			 throw new IOException("There are no students in " + config.getDBFileName() +" data base");
		} 
			
		if (teacherList != null && teacherList.getLength() > 0) {
			for (int i = 0; i < teacherList.getLength(); i++) {
		        Node node = teacherList.item(i);
		        if (node.getNodeType() == Node.ELEMENT_NODE) {
		        	Element e = (Element) node;		            		           
		            people.add(readTeacher(e));
		        }
		    }
			
		}else{
			throw new IOException("There are no teachers in " + config.getDBFileName() +" data base");
		}
		
		return people;
	}
	
	
	private String getText(Element e, String text){
		return e.getElementsByTagName(text).item(0).getTextContent();        	
	}
	
	private Date getDate(Element e, String date) throws DOMException, ParseException{
		return config.getDateFormat().parse(e.getElementsByTagName(date).item(0).getTextContent());        	
	}
	
	private Student readStudent(Element e) throws IOException, ParseException {
		final StudentDTOBuilder builder = new StudentDTOBuilder();
				
		return new Student(
				builder.name(getText(e, "name")).
				birthDate(getDate(e, "birthDate")).
				groupName(getText(e, "groupName")).
				faculty(getText(e, "faculty")).				
				build());											
	}

	
	private Teacher readTeacher(Element e) throws IOException, ParseException {
		final TeacherDTOBuilder builder = new TeacherDTOBuilder();
		
		return new Teacher(
				builder.name(getText(e, "name")).
				birthDate(getDate(e, "birthDate")).				
				subject(getText(e, "subject")).				
				build());
	}

	private class StudentDTOBuilder{
		private String name;
		private Date birthDate;
		private String groupName;
		private String faculty;
		
		public StudentDTOBuilder name(String name){
			this.name = name;
			return this;
		}
		
		public StudentDTOBuilder birthDate(Date birthDate){
			this.birthDate = birthDate;
			return this;
		} 
		
		public StudentDTOBuilder groupName(String groupName){
			this.groupName = groupName;
			return this;
		}
		
		public StudentDTOBuilder faculty(String faculty){
			this.faculty = faculty;
			return this;
		}
		
		public StudentDTO build(){		
			return new StudentDTO(name, birthDate, groupName, faculty);
		}			
	}

	private class TeacherDTOBuilder{
		private String name;
		private Date birthDate;
		private String subject;		
		
		public TeacherDTOBuilder name(String name){
			this.name = name;
			return this;
		}
		
		public TeacherDTOBuilder birthDate(Date birthDate){
			this.birthDate = birthDate;
			return this;
		} 
		
		public TeacherDTOBuilder subject(String subject){
			this.subject = subject;
			return this;
		}		
		
		public TeacherDTO build(){		
			return new TeacherDTO(name, birthDate, subject);
		}
	}
}

