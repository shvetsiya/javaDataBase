package commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import commands.FP.Predicate;
import entities.Person;


public class SelectWhereCommand extends Command {
	private final Command select;
	private String whereName;
	private static final Pattern whereNamePat = Pattern.compile("where\\s+name\\s*=\\s*'(.+)'");
		
	public SelectWhereCommand(Command select){
		this.select = select;
	}
	@Override
	public boolean parseCommand(String command) {		
		whereName = null;
		
		if (select.parseCommand(command)) {			
			final String[] parts = command.split(" ");
			final List<String> list = new ArrayList<String>();
			list.addAll(Arrays.asList(parts));
			
			list.remove(0); // select
			list.remove(0); // Person

			checkWhereClause(list);
			
			return true;
		}
		return false;
	}
	
	private void checkWhereClause(List<String> list) {
		String clause = FP.<String> reduce(list, FP.strConcat);
		if (clause != null) {
			Matcher matcher = whereNamePat.matcher(clause);
			if (matcher.find()) {
				whereName = clause.substring(matcher.start(1), matcher.end(1));
			}
		}
	}
	
	@Override
	public void run(List<Person> list) {
		List<Person> personList = new ArrayList<Person>();
		personList.addAll(list);

		//only the feature of the 'where name command'
		if (whereName != null) {
			personList = FP.<Person> filter(personList,
					new Predicate<Person>() {
						@Override
						public boolean run(Person param) {
							return param.getName().equals(whereName);
						}
					});
		}

		select.run(personList);
	}	
}




	

	
