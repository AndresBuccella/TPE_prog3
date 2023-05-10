package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import grafo.Arco;
import grafo.GrafoDirigido;

public class Main {

	public static void main(String[] args) {

		/*		HashMap<Integer, LinkedList<LinkedList<String>>> m = new HashMap<Integer, LinkedList<LinkedList<String>>>();
		m.put(1, new LinkedList<LinkedList<String>>());
		m.put(2, new LinkedList<LinkedList<String>>());
		LinkedList<String> l = new LinkedList<String>(); 
		l.add("asd");
		m.get(1).add(l);
		System.out.println(m.values().iterator());
*/
		GrafoDirigido<Integer> gd = new GrafoDirigido<Integer>();
		gd.agregarVertice(10);
		gd.agregarVertice(50);
		gd.agregarVertice(30);
		gd.agregarVertice(20);
		gd.agregarArco(10, 50, null);
		gd.agregarArco(10, 20, null);
		gd.agregarArco(20, 50, null);
		gd.agregarArco(20, 30, null);
		gd.agregarArco(50, 30, null);
		gd.agregarArco(30, 10, null);
		gd.agregarArco(30, 10, null);

		Iterator<Arco<Integer>> iteradorArcos = gd.obtenerArcos();

		// Recorrer todos los vértices del grafo usando el iterador
		while (iteradorArcos.hasNext()) {
			Arco<Integer> arco = iteradorArcos.next();
		    // Hacer algo con el vértice
		    System.out.println("Origen: " + arco.getVerticeOrigen());
		}
		
		
/*		for (Integer idVertice : gd) {
		    // Hacer algo con el vértice
		    System.out.println("Vértice " + idVertice + " en el grafo");
		}
*/
	}
	
}
