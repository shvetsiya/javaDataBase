package mydb;


import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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
			readStudent();
		} else{
			 throw new IOException("There are no students in " + config.getDBFileName() +" data base");
		} 
			
		if (teacherList != null && teacherList.getLength() > 0) {
			readTeacher();
		}else{
			throw new IOException("There are no teachers in " + config.getDBFileName() +" data base");
		}
		
		return people;
	}
	
	private void readStudent() throws IOException, ParseException {
		final Student student = new Student();
		
	    for (int i = 0; i < studentList.getLength(); i++) {

	        Node node = studentList.item(i);

	        if (node.getNodeType() == Node.ELEMENT_NODE) {

	            Element e = (Element) node;
	            NodeList nodeList = e.getElementsByTagName("name");
	            student.setName(nodeList.item(0).getChildNodes().item(0).getNodeValue());
	                        
	            nodeList = e.getElementsByTagName("birthDate");
	            student.setBirthDay(config.getDateFormat().parse(nodeList.item(0).getChildNodes().item(0).getNodeValue()));                               

	            nodeList = e.getElementsByTagName("groupName");
	            student.setGroupName(nodeList.item(0).getChildNodes().item(0).getNodeValue());
	            
	            nodeList = e.getElementsByTagName("faculty");
	            student.setFaculty(nodeList.item(0).getChildNodes().item(0).getNodeValue());
	            people.add(student);
	        }
	    }
	}

	private void readTeacher() throws IOException, ParseException {
		final Teacher teacher = new Teacher();
		
		for (int i = 0; i < teacherList.getLength(); i++) {

	        Node node = teacherList.item(i);

	        if (node.getNodeType() == Node.ELEMENT_NODE) {

	            Element e = (Element) node;
	            NodeList nodeList = e.getElementsByTagName("name");
	            teacher.setName(nodeList.item(0).getChildNodes().item(0).getNodeValue());
	                        
	            nodeList = e.getElementsByTagName("birthDate");
	            teacher.setBirthDay(config.getDateFormat().parse(nodeList.item(0).getChildNodes().item(0).getNodeValue()));                               

	            nodeList = e.getElementsByTagName("subject");
	            teacher.setSubject(nodeList.item(0).getChildNodes().item(0).getNodeValue());	            
	            
	            people.add(teacher);
	        }
	    }
	}

	

}

