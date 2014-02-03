package entities;

import java.util.Calendar;
import java.util.Date;

public abstract class Person implements Comparable <Person>{	
	private String name; 
	private Date birthDate;
	
	// construct a new student with given fields
	public Person() {
		
	}
    public Person(String name, Date birthDate) {
        this.name = name;        
        this.birthDate = birthDate;
    }
	
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
    
    //return a string representation of the invoking object
    public String toString() {
        return name + "\n" + birthDate.toString();
    }
}
