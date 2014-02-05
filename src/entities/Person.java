package entities;

import java.util.Calendar;
import java.util.Date;

import dto.PersonDTO;

public abstract class Person implements Comparable <Person>{	
	protected String name; 
	protected Date birthDate;	    
	
    public String getName(){
    	return name;
    }
    
    public Date getBirthDay() {    	
    	return birthDate;
    }
    
    public void setName(String name){
    	this.name = name;
    }
    
    public void setBirthDay(Date birthDate) {    	
    	this.birthDate = birthDate;    	
    }
    
    public int getAge() {
    	final Calendar cal = Calendar.getInstance();
    	final int curYear = cal.get(Calendar.YEAR);
    	
    	final Calendar birthCal = Calendar.getInstance();
    	birthCal.setTime(birthDate);
    	final int birthYear = birthCal.get(Calendar.YEAR); 
    	
    	return curYear - birthYear;
    }
    
    //implementation of comparable interface 	
    @Override
    public int compareTo(Person otherPerson){
    	return birthDate.compareTo(otherPerson.birthDate); 
    }
    
    public boolean isA(String s) {
		return s.equals(getEntityName());
	}
	
	protected abstract String getEntityName();
	
	protected abstract void fromDTO(PersonDTO dto);
    
    //return a string representation of the invoking object
    public String toString() {
        return name + "\n" + birthDate.toString();
    }
	
		
		
}
