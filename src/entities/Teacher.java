package entities;

import java.util.Date;

/*************************************************************************
 *  The class Teacher contains information about teachers  
 *
 *************************************************************************/

public class Teacher extends Person{	
	public static final String NAME = "Teacher";
	private String subject;				

	//construct a new student with given fields
	public Teacher() {
		super();
	}
	public Teacher(String name, Date birthDate, String subject) {
		super(name, birthDate);        
		this.subject = subject;        
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

}
