package commands;

import java.util.List;

import entities.Person;

public class AgeCommand extends Command {
	private String name = null;

	/**
	 * Try to parse the command. If parsed -- returns true;
	 */
	@Override
	public boolean parseCommand(String command) {
		if (command.startsWith("age")) {
			final String[] parts = command.split(" ");

			if ((parts.length == 5) && parts[1].equals("where")
					&& parts[2].equals("name") && parts[3].equals("=")) {
				name = parts[4].substring(1, parts[4].length() - 1);

				return true;
			}
		}
		return false;
	}

	/**
	 * Print ages of all people with the specified name.
	 */
	@Override
	public void run(List<Person> list) {
		for (Person p : filterPersonsList(list)) {
			System.out.println(p.getAge());
		}
	}

	/** 
	 * Filter persons list
	 */
	private List<Person> filterPersonsList(List<Person> list) {
		return FP.<Person>filter(list,
		// Find those whose name equals to the specified name
				new FP.Predicate<Person>() {
					public boolean run(Person param) {
						return param.getName().equals(name);
					}
				});
	}
}