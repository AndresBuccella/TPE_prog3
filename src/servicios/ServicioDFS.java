package servicios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import grafo.Grafo;

public class ServicioDFS {

	private Grafo<?> grafo;
	private int tiempo;
	private HashMap<Integer, HashMap<String, String>> estado; //estaria bien?
//	private HashMap<T,T> adyacentes; //estaria mal porque los indices deben ser unicos
	private final String NOVISITADO = "Blanco";
	private final String VISITADO = "Amarillo";
	private final String REVISITADO = "Negro";
	
	public ServicioDFS(Grafo<?> grafo) {
		this.grafo = grafo;
		this.tiempo = 0;
	}
	public List<Integer> dfsForest() {
		//Para cada vertice, se le asigna NOVISITADO y tiempo 0
		//por cada vertice se pregunta si es NOVISITADO
		//si es NOVISITADO va a DFS_Visit
		//se agrega al arraylist
		return new ArrayList<>();
	}
	
	private List<Integer> DFS_Visit(){
		
		
		return null;
	}

}
