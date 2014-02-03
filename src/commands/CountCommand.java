package commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import entities.Person;

public class CountCommand extends Command{
	private String entity;	
	
	@Override
	public boolean parseCommand(String command) {
		if (command.startsWith("count")) {			
			final String[] parts = command.split(" ");
			final List<String> list = new ArrayList<String>();
			list.addAll(Arrays.asList(parts));
			
			entity = list.get(1);
			return true;
				
		}	
		return false;
	}
	
	@Override
	public void run(List<Person> list) {
		List<Person> personList = new ArrayList<Person>();
		personList.addAll(list);
		
		int count = 0;
		for (Person p : personList) {
			if (p.isA(entity)) {
				count++;
			}			
		}
		System.out.println("The number of " + entity + "s = " + count);
	}
}



