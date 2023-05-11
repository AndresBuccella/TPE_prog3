package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import grafo.Arco;
import grafo.GrafoDirigido;
import servicios.ServicioDFS;

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
		gd.agregarVertice(90);
		gd.agregarVertice(110);
		gd.agregarVertice(18);
		gd.agregarVertice(27);

		gd.agregarArco(10, 50, null);
		gd.agregarArco(10, 20, null);
		gd.agregarArco(20, 50, null);
		gd.agregarArco(20, 30, null);
		gd.agregarArco(50, 30, null);
		gd.agregarArco(30, 10, null);

		gd.agregarArco(90, 110, null);
		gd.agregarArco(50, 90, null);
		gd.agregarArco(20, 90, null);
		gd.agregarArco(110, 30, null);
		gd.agregarArco(110, 27, null);
		gd.agregarArco(18, 27, null);
		gd.agregarArco(18, 110, null);
		
		Iterator<Integer> vertices = gd.obtenerVertices();
		List<Integer> l = new ServicioDFS(gd).dfsForest();
		ArrayList<Integer> arr = new ArrayList<Integer>(l);
		for(Integer vertice : arr) {
			System.out.println(" - " + vertice + " - ");			
		}
		
		// Recorrer todos los vértices del grafo usando el iterador
/*		while (iteradorArcos.hasNext()) {
			Arco<Integer> arco = iteradorArcos.next();
		    // Hacer algo con el vértice
		    System.out.println("Origen: " + arco.getVerticeOrigen());
		}
		
*/		
/*		for (Integer idVertice : gd) {
		    // Hacer algo con el vértice
		    System.out.println("Vértice " + idVertice + " en el grafo");
		}
*/
	}
	
}
