package main;

import java.util.HashMap;
import java.util.LinkedList;

public class Main {

	public static void main(String[] args) {
		HashMap<Integer, LinkedList<LinkedList<String>>> m = new HashMap<Integer, LinkedList<LinkedList<String>>>();
		m.put(1, new LinkedList<LinkedList<String>>());
		LinkedList<String> l = new LinkedList<String>(); 
		l.add("asd");
		m.get(1).add(l);
		System.out.println(m.get(1).get(1-1));

	}

}
