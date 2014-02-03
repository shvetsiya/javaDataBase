package commands;

import java.util.ArrayList;
import java.util.List;

public class FP {
	/////////////////////////////////////////
	// Interfaces
	
	public interface Predicate<P> {
		public boolean run(P param);
	}
	
	public interface Reducer<T> {
		public T run(T first, T second);
	}
	
	public interface Mapper<T,S> {
		public S run(T item);
	}
	
	
	/////////////////////////////////////////
	// List comprehensions

	/**
	 *	Returns the first element of the list 
	 */
	public static <T> T first(List<T> list) {
		return list.get(0);
	}
	
	/**
	 * Returns the tail of the list (everything excluding first element) 
	 */
	public static <T> List<T> next(List<T> list) {
		return list.subList(1, list.size());
	}
	
	/**
	 * Returns list elements on which the predicate returns true 
	 */
	public static <T> List<T> filter(List<T> list, Predicate<T> pred) {
		final ArrayList<T> result = new ArrayList<T>();
		
		for (T o: list) {
			if (pred.run(o)) {
				result.add(o);
			}
		}
		
		return result;
	}
	
	/**
	 * Maps a function over a list and returns new list whose elements are the result
	 * of applying the mapper on the original list item.
	 */
	public static <T,S> List<S> map(List<T> list, Mapper<T,S> mapper) {
		final List<S> result = new ArrayList<S>();
		
		for (T t: list) {
			result.add(mapper.run(t));
		}
		
		return result;
	}
	
	/**
	 * Reduces the list using a function
	 */
	public static <T> T reduce(List<T> list, Reducer<T> reducer) {
		if (list.size() == 0) {
			return null;
		} else {
			return reduce(first(list), next(list), reducer);
		}
	}
	
	private static <T> T reduce(T item, List<T> list, Reducer<T> reducer) {
		if (list.size() == 0) {
			return item;
		} else {
			final T result = reducer.run(item, first(list));
			return reduce(result, next(list), reducer);
		}
	}
	
	/////////////////////////////////////////
	// Predefined functions

	public final static Reducer<String> strConcat = new FP.Reducer<String>() {
		@Override
		public String run(String first, String second) {
			return first + " " + second;
		}
	};
}