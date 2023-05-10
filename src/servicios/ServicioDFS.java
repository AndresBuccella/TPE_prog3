package servicios;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import grafo.Grafo;

public class ServicioDFS {

	private Grafo<?> grafo;
	private HashMap<Integer, Array[]> estado; //estaria bien?
	//no me parece eficiente, pero podría hacer un objeto estado?
	private final String NOVISITADO = "Blanco";
	private final String VISITADO = "Amarillo";
	private final String SINADYACENTES = "Negro";
	
	public ServicioDFS(Grafo<?> grafo) {
		this.grafo = grafo;
		this.estado = new HashMap<Integer, Array[]>();
	}
	public List<Integer> dfsForest() {
		//Para cada vertice, se le asigna NOVISITADO y tiempo 0
		int tiempo = 0;
		Iterator<Integer> it = this.grafo.obtenerVertices();
		while(it.hasNext()) {
			Integer vertice = it.next();
			int tiempoDesc = 0;
			int tiempoFin = 0;
			int estado = 0; // 0=blanco 1=amarillo 2=negro
			int[] arr = new int[tiempoDesc, tiempoFin, estado];
			
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
