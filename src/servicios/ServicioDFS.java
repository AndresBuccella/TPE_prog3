package servicios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import grafo.Arco;
import grafo.Grafo;

public class ServicioDFS {

	private Grafo<?> grafo;
	private HashMap<Integer, HashMap<Integer, String>> estado; //estaria bien?
//	private HashMap<T,T> adyacentes; //estaria mal porque los indices deben ser unicos
	private final String NOVISITADO = "Blanco";
	private final String VISITADO = "Amarillo";
	private final String REVISITADO = "Negro";
	
	public ServicioDFS(Grafo<?> grafo) {
		this.grafo = grafo;
		this.estado = new HashMap<Integer, HashMap<Integer, String>>();
	}
	public List<Integer> dfsForest() {
		//Para cada vertice, se le asigna NOVISITADO y tiempo 0
		int tiempo = 0;
		Iterator<Integer> it = this.grafo.obtenerVertices();
		while(it.hasNext()) {
			Integer vertice = it.next();
			HashMap<Integer, String> hmDesc = new HashMap<Integer, String>();
			hmDesc.put(tiempo, NOVISITADO);
			this.estado.put(vertice, hmDesc);
		}
		//por cada vertice se pregunta si es NOVISITADO
		this.estado.forEach((k,v) -> {
			Iterator<Integer> itAd = this.grafo.obtenerAdyacentes(k);
			while(itAd.hasNext()) {
				Integer destino = itAd.next();
				
			}
		});
		//si es NOVISITADO va a DFS_Visit
		//se agrega al arraylist
		return new ArrayList<>();
	}
	
	private List<Integer> DFS_Visit(){
		
		
		return null;
	}

}
