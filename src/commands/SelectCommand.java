package commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import commands.FP.Predicate;
import entities.Person;

public class SelectCommand extends Command {
	private static final Pattern orderByDatePat = Pattern.compile("order\\s+by\\s+date");
	private static final Pattern whereNamePat = Pattern.compile("where\\s+name\\s*=\\s*'(.+)'");
	private boolean orderByDate;
	private String whereName;
	private String entity;

	@Override
	public boolean parseCommand(String command) {
		orderByDate = false;
		whereName = null;

		if (command.startsWith("select")) {
			final String[] parts = command.split(" ");
			final List<String> list = new ArrayList<String>();
			list.addAll(Arrays.asList(parts));

			entity = list.get(1);

			list.remove(0); // select
			list.remove(0); // Person

			checkWhereClause(list);
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

		if (orderByDate) {
			Collections.sort(personList);
		}

		if (whereName != null) {
			personList = FP.<Person> filter(personList,
					new Predicate<Person>() {
						@Override
						public boolean run(Person param) {
							return param.getName().equals(whereName);
						}
					});
		}

		for (Person p : personList) {
			if (p.isA(entity)) {
				System.out.println(p);
			}
		}
	}

}