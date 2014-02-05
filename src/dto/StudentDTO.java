package dto;

import java.util.Date;

public class StudentDTO implements PersonDTO{
	public final String name;
	public final Date birthDate;
	public final String groupName;
	public final String faculty;

	public StudentDTO(String name, Date birthDate, String groupName, String faculty){
		this.name = name;
		this.birthDate = birthDate;
		this.groupName = groupName;
		this.faculty = faculty;		
	}
}
