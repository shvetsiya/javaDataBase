package commands;

import java.util.ArrayList;
import java.util.List;

import entities.Person;

public class CommandProcessor {
	private final List<Person> people; //peopleList
	private final List<Command> commandList;
		
	public CommandProcessor(List<Person> people){
		this.people = people;
		commandList = new ArrayList<Command>();
		//commandList.add(new SelectOrderByCommand(new SelectWhereCommand(new SelectCommand())));
		commandList.add(new SelectOrderByCommand(
						new SelectWhereCommand(
						new SelectCommand())));
		commandList.add(new AgeCommand());
		commandList.add(new CountCommand());
	}
	
	public void processCommand(String cmd){
		for (Command command: commandList){
			if (command.parseCommand(cmd)){
				command.run(people);
				return;
			}
		}
	}
}
	
