package main;

import java.util.Iterator;
import java.util.List;

import grafo.Arco;
import grafo.GrafoDirigido;
import grafo.GrafoNoDirigido;
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
		GrafoNoDirigido<Integer> gd = new GrafoNoDirigido<Integer>();
		

		gd.agregarVertice(10);
		gd.agregarVertice(50);
		gd.agregarVertice(30);
		gd.agregarVertice(20);
		gd.agregarVertice(90);
		gd.agregarVertice(110);
		gd.agregarVertice(18);
		gd.agregarVertice(27);

		System.out.println("---------GD---------------");
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
		gd.agregarArco(50, 50, null);
/*
		Iterator<Integer> itAdy = gd.obtenerAdyacentes(50);
		while(itAdy.hasNext()) {
			Integer in = itAdy.next();
			System.out.println("Adyacentes: " + in);
		}

		System.out.println("Cantidad de arcos: " + gd.cantidadArcos());
		
		
		System.out.println("--------.borrarVertice()-----------");
		Iterator<Arco<Integer>> itA1 = gd.obtenerArcos();
		while(itA1.hasNext()) {
			Arco<Integer> a = itA1.next();
			System.out.println("Origen: " + a.getVerticeOrigen() + " Destino: " + a.getVerticeDestino());
		}
		
		System.out.println("cantVert antedeliminar: " + gd.cantidadVertices());
		//gd.borrarVertice(110);
		System.out.println("cantVert despdeliminar: " + gd.cantidadVertices());

		gd.agregarArco(110, 27, 10);
		gd.agregarArco(110, 27, 5); //no usarlo seguido, rompe. Reinicie y dejo de hacerlo

		System.out.println("---------.borrarArco(int,int)----------");
		
		//gd.borrarArco(90, 20);
		
		System.out.println("Cantidad de arcos despues de eliminar 90-20: " + gd.cantidadArcos());
		System.out.println("--------.obtenerArcos()-----------");
		
		Iterator<Arco<Integer>> itA = gd.obtenerArcos();
		while(itA.hasNext()) {
			Arco<Integer> a = itA.next();
			System.out.println("Origen: " + a.getVerticeOrigen() + " Destino: " + a.getVerticeDestino() + " Etiqueta: " + a.getEtiqueta());
		}

		System.out.println("--------.contieneVertice()-----------");
		System.out.println("Contiene el vertice: 27 " + gd.contieneVertice(27));
		

		System.out.println("--------.existeArco()-----------");
		System.out.println("Contiene el arco: 110-27 " + gd.existeArco(110, 27));
		

		System.out.println("--------.obtenerArco()-----------");
		Arco<Integer> arco = gd.obtenerArco(10, 50);
		System.out.println("Origen: " + arco.getVerticeOrigen() + " Destino: " + arco.getVerticeDestino());

		System.out.println("--------.cantidadVertices()-----------");
		System.out.println("cantVert: " + gd.cantidadVertices());
		
		System.out.println("--------.cantidadArcos()-----------");
		System.out.println("cantVert: " + gd.cantidadArcos());
		

		System.out.println("--------.obtenerVertices()-----------");
		Iterator<Integer> verticesTotales = gd.obtenerVertices();
		while(verticesTotales.hasNext()) {
			Integer vertice = verticesTotales.next();
			System.out.println(vertice);
		}
		
		System.out.println("--------.obtenerAdyacentes(int)-----------");
		
		Iterator<Integer> itAdy = gd.obtenerAdyacentes(10);
		while(itAdy.hasNext()) {
			Integer ady = itAdy.next();
			System.out.println(ady);
		}
		

		System.out.println("--------.obtenerArcos()-----------");
		
		Iterator<Arco<Integer>> itArc = gd.obtenerArcos();
		while(itArc.hasNext()) {
			Arco<Integer> arco2 = itArc.next();
			System.out.println("Origen: " + arco2.getVerticeOrigen() + " Destino: " + arco2.getVerticeDestino());
		}

		System.out.println("--------.obtenerArcos(int)-----------");
		
		Iterator<Arco<Integer>> itArc2 = gd.obtenerArcos(20);
		while(itArc2.hasNext()) {
			Arco<Integer> arco3 = itArc2.next();
			System.out.println("Origen: " + arco3.getVerticeOrigen() + " Destino: " + arco3.getVerticeDestino());
		}
		
		System.out.println(" -----------DFS------------- ");
		List<Integer> lista1 = new ServicioDFS(gd).dfsForest();
		for(Integer vertice : lista1) {
				System.out.println(" - " + vertice + " - ");
		}
		
		System.out.println(" -----------BFS------------- ");
		List<Integer> lista2 = new ServicioBFS(gd).bfsForest();
		for(Integer vertice : lista2) {
				System.out.println(" - " + vertice + " - ");
		}
*/		
		System.out.println(" -----------CAMINOS POSIBLES------------- ");
		List<List<Integer>> listaCompuesta = new ServicioCaminos(gd, 50, 90, 10).caminos();
		for(List<Integer> listaSimple : listaCompuesta) {
			for(Integer vertice : listaSimple) {
				System.out.println(" - " + vertice + " - ");						
			}
				System.out.println(" ------------------------ ");
		}
	

	}
	
}