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
		gd.agregarArco(18, 27, null);
		gd.agregarArco(18, 110, null);
		gd.agregarArco(20, 50, null);
		gd.agregarArco(20, 30, null);
		gd.agregarArco(20, 90, null);
		gd.agregarArco(30, 10, null);
		gd.agregarArco(30, 27, null);
		gd.agregarArco(27, 20, null);
		gd.agregarArco(50, 30, null);
		gd.agregarArco(50, 50, null);
		gd.agregarArco(50, 90, null);
		gd.agregarArco(90, 18, null);
		gd.agregarArco(90, 20, null);
		gd.agregarArco(90, 27, null);
		gd.agregarArco(90, 110, null);
		gd.agregarArco(110, 30, null);
		gd.agregarArco(110, 27, null);

		System.out.println("----------.obtenerAdyacentes(int)--------------");
		Iterator<Integer> itAdy = gd.obtenerAdyacentes(50);
		while(itAdy.hasNext()) {
			Integer in = itAdy.next();
			System.out.println("Adyacentes: " + in);
		}

		System.out.println("cantVert antedeliminar vertice 110: " + gd.cantidadVertices());
		System.out.println("Cantidad de arcos antes de eliminar el vertice: " + gd.cantidadArcos());
		System.out.println("Se elimina...");
		gd.borrarVertice(110);
		System.out.println("cantVert despdeliminar vertice 110: " + gd.cantidadVertices());
		System.out.println("Cantidad de arcos despues de eliminar el vertice: " + gd.cantidadArcos());

		System.out.println("---------.agregarArco(int,int,T) para modificar----------");
		gd.agregarArco(90, 27, 5);
		gd.agregarArco(90, 27, 6);
		gd.agregarArco(10, 20, 13);
		gd.agregarArco(10, 0, 31); //no existe el segundo vertice
		gd.agregarArco(0, 10, 31); //no existe el primer vertice

		System.out.println("---------.borrarArco(int,int)----------");
		System.out.println("Cantidad de arcos antes de eliminar: " + gd.cantidadArcos());
		
		gd.borrarArco(90, 20);
		
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
		System.out.println("Contiene el arco: 50-90 " + gd.existeArco(50, 90));
		

		System.out.println("--------.obtenerArco()-----------");
		Arco<Integer> arco = gd.obtenerArco(10, 50);
		System.out.println("Origen: " + arco.getVerticeOrigen() + " Destino: " + arco.getVerticeDestino());

		System.out.println("--------.cantidadVertices()-----------");
		System.out.println("cantVert: " + gd.cantidadVertices());
		
		System.out.println("--------.cantidadArcos()-----------");
		System.out.println("cantArcos: " + gd.cantidadArcos());
		

		System.out.println("--------.obtenerVertices()-----------");
		Iterator<Integer> verticesTotales = gd.obtenerVertices();
		while(verticesTotales.hasNext()) {
			Integer vertice = verticesTotales.next();
			System.out.println(vertice);
		}
		
		System.out.println("--------.obtenerAdyacentes(int) a 10-----------");
		
		Iterator<Integer> itAdy1 = gd.obtenerAdyacentes(10);
		while(itAdy1.hasNext()) {
			Integer ady = itAdy1.next();
			System.out.println(ady);
		}
		

		System.out.println("--------.obtenerArcos()-----------");
		
		Iterator<Arco<Integer>> itArc = gd.obtenerArcos();
		while(itArc.hasNext()) {
			Arco<Integer> arco2 = itArc.next();
			System.out.println("Origen: " + arco2.getVerticeOrigen() + " Destino: " + arco2.getVerticeDestino());
		}

		System.out.println("--------.obtenerArcos(int) de 20-----------");
		
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

		System.out.println(" -----------SERVICIO CAMINOS------------ ");
		List<List<Integer>> listaCompuesta = new ServicioCaminos(gd, 10, 50, 6).caminos();
		for(List<Integer> listaSimple : listaCompuesta) {
			for(Integer vertice : listaSimple) {
				System.out.println(" - " + vertice + " - ");						
			}
			System.out.println(" ------------------------ ");
		}
	}
}