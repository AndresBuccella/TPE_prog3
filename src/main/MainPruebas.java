package main;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;


public class MainPruebas {

	public static void main(String[] args) {
		
		HashMap<Integer, HashSet<Integer>> hm = new HashMap<>();
		Iterator<Integer> hs = hm.values().stream().filter(Objects::nonNull).flatMap(HashSet::stream).iterator();
		while(hs.hasNext()) {
			Integer i = hs.next();
			System.out.println(i);
		}
/*		Queue<Integer> l = new LinkedList<>();

		LinkedList<Integer> l2 = new LinkedList<>();
		l2.add(4);
		l2.add(1);
		l2.add(2);
		l2.add(3);
		l.offer(null)
		for(Integer i : l) {
			System.out.println(i);
		}
		
		HashMap<Integer, Integer> hm = new HashMap<>();
		int limite = 5;
		aer(hm,limite);
		
		hm.put(null, null);*/

	//System.out.println(null == null);	
		
/*		HashSet<Integer> hs = new HashSet<>();
		
		if(hs.add(1)) {
			System.out.println(1);
			if(hs.add(1)) {
				System.out.println(2);
			}else
				System.out.println("falso");
		}
		System.out.println(hs.iterator());
*/	}
/*	public static void aer(HashMap<Integer, Integer> hm, int limite){
		
		if(limite != 0) {
			hm.put(limite, limite+1);
			System.out.println("Antes de: " + hm.get(limite));
			aer(hm, limite-1);
			if(hm.get(limite) == 3) {
				limite = 1;
			}
			System.out.println("Despues de: " + hm.get(limite));
		}
	}
*/
}
