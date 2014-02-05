package commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import entities.Person;

public class SelectCommand extends Command {	
	private String entity;

	@Override
	public boolean parseCommand(String command) {
		
		if (command.startsWith("select")) {
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

		for (Person p : personList) {
			if (p.isA(entity)) {
				System.out.println(p);
			}
		}
	}

}