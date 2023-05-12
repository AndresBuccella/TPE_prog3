package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import grafo.Arco;
import grafo.GrafoDirigido;
import servicios.ServicioBFS;
import servicios.ServicioCaminos;
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
		gd.agregarArco(90, 20, null);

		Integer arcos = gd.cantidadArcos();
		System.out.println(arcos);
/*		List<List<Integer>> listaCompuesta = new ServicioCaminos(gd, 50, 20, 3).caminos();
		for(List<Integer> listaSimple : listaCompuesta) {
				System.out.println(" ------------------------ ");
			for(Integer vertice : listaSimple) {
				System.out.println(" - " + vertice + " - ");						
			}
				System.out.println(" ------------------------ ");
		}
*/		
		
		//System.out.println(gd.cantidadArcos());
		
		// Recorrer todos los vértices del grafo usando el iterador
/*		Iterator<Arco<Integer>> itArcos = gd.obtenerArcos();
		while (itArcos.hasNext()) {
			Arco<Integer> arco = itArcos.next();
		    // Hacer algo con el vértice
		    System.out.println("Origen: " + arco.getVerticeOrigen() + ", Destino: " + arco.getVerticeDestino());
		}
*/
/*		Iterator<Integer> vertices = gd.obtenerAdyacentes(20);
		while (vertices.hasNext()) {
			Integer destino = vertices.next();
		    // Hacer algo con el vértice
		    System.out.println("Destino: " + destino);	
		}
*/		
/*		Iterator<Integer> vertices = gd.obtenerVertices();
		while (vertices.hasNext()) {
			Integer destino = vertices.next();
		    // Hacer algo con el vértice
		    System.out.println("Destino: " + destino);	
		}
*/
	}
	
}
