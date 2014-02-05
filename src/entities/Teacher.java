package entities;


import dto.PersonDTO;
import dto.TeacherDTO;

/*************************************************************************
 *  The class Teacher contains information about teachers  
 *
 *************************************************************************/

public class Teacher extends Person{	
	public static final String NAME = "Teacher";
	private String subject;				

	
	public Teacher(TeacherDTO dto) {
		fromDTO(dto);
	}
	
	public String getSubject(){
    	return subject;    	
    }
    
    public void setSubject(String subject){
    	this.subject = subject;    	
    }
    
	@Override
	protected String getEntityName() {
		return NAME;
	}
    
    @Override
	public String toString() {
		return super.toString() + "\n" + subject;
	}

    @Override
	protected void fromDTO(PersonDTO dto){
		final TeacherDTO s = (TeacherDTO)dto;
		name = s.name;
		birthDate = s.birthDate;
		subject = s.subject;		
	}
}
