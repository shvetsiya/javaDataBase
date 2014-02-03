package commands;

import java.util.List;

import entities.Person;

public abstract class Command {
	public abstract boolean parseCommand(String command);
	public abstract void run(List<Person> list);
}
