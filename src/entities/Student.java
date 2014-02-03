package entities;

import java.util.Date;

/*************************************************************************
 *  The class Student contains information about students  
 *
 *************************************************************************/

public class Student extends Person{	
	public static final String NAME = "Student";
	private String groupName;	
	private	String faculty;	
			
	// construct a new student with given fields
	public Student() {
		super();
	}
    public Student(String name, Date birthDate, String groupName, String faculty) {
    	super(name, birthDate);        
        this.groupName = groupName;        
        this.faculty = faculty;
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
    public String toString() {
        return super.toString() + "\n" + groupName + "\n" + faculty;
    }

}