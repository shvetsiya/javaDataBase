package dto;

import java.util.Date;

public class TeacherDTO implements PersonDTO{
	public final String name;
	public final Date birthDate;	
	public final String subject;

	public TeacherDTO(String name, Date birthDate, String subject){
		this.name = name;
		this.birthDate = birthDate;
		this.subject = subject;				
	}
}