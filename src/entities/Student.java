package entities;


import dto.PersonDTO;
import dto.StudentDTO;

/*************************************************************************
 *  The class Student contains information about students  
 *
 *************************************************************************/

public class Student extends Person{	
	public static final String NAME = "Student";
	private String groupName;	
	private	String faculty;	
			
	
    public Student(StudentDTO dto) {
    	fromDTO(dto);
    }
	    
    public String getGroupName(){
    	return groupName;    	
    }
    
    public void setGroupName(String groupName){
    	this.groupName = groupName;    	
    }
    
    public String getFaculty(){
    	return faculty;
    }
    
    public void setFaculty(String faculty){
    	this.faculty = faculty;
    }
    
	@Override
	protected String getEntityName() {
		return NAME;
	}
	
	@Override
	protected void fromDTO(PersonDTO dto){
		final StudentDTO s = (StudentDTO)dto;
		name = s.name;
		birthDate = s.birthDate;
		groupName = s.groupName;
		faculty = s.faculty;
	}
	    
    @Override
    public String toString() {
        return super.toString() + "\n" + groupName + "\n" + faculty;
    }

}