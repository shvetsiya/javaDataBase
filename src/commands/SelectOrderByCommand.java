package commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import entities.Person;

public class SelectOrderByCommand extends Command{
	private final Command select;
	private boolean orderByDate;
	private static final Pattern orderByDatePat = Pattern.compile("order\\s+by\\s+date");	

	public SelectOrderByCommand(Command select){
		this.select = select;
	}
	
	@Override
	public boolean parseCommand(String command) {
		orderByDate = false;
		
		if (select.parseCommand(command)) {
			final String[] parts = command.split(" ");
			final List<String> list = new ArrayList<String>();
			list.addAll(Arrays.asList(parts));
					
			list.remove(0); // select
			list.remove(0); // Person

			checkOrderByClause(list);

			return true;
		}
		return false;
	}
	
	private void checkOrderByClause(List<String> list) {
		String clause = FP.<String> reduce(list, FP.strConcat);

		if (clause != null) {
			Matcher matcher = orderByDatePat.matcher(clause);
			orderByDate = matcher.find();
		}
	}
			
	@Override
	public void run(List<Person> list) {
		List<Person> personList = new ArrayList<Person>();
		personList.addAll(list);
		
		//only the feature of the 'order by date command'
		if (orderByDate) {
			Collections.sort(personList);
		}

		select.run(personList);
	}

}
