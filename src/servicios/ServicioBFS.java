package servicios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import grafo.Grafo;

public class ServicioBFS { //min 43:00

	private Grafo<?> grafo;
	private HashMap<Integer, int[]> info; //estaria bien?
	
	//no me parece eficiente, pero podrÃ­a hacer un objeto estado?
	
	private final int BLANCO = 0; //no visitado
	private final int AMARILLO = 1; //visitado
	private final int NEGRO = 2; //finalizado
	
	private int tiempoTotal;
	
	public ServicioBFS(Grafo<?> grafo) {
		this.grafo = grafo;
		this.info = new HashMap<Integer, int[]>();
		this.tiempoTotal = 0;
	}

	private void inicializarInfo() {
		Iterator<Integer> it = this.grafo.obtenerVertices();
		while(it.hasNext()) {
			Integer vertice = it.next();
			int[] arr = new int[3];
			arr[0] = 0; //tiempo inicial
			arr[1] = 0; //tiempo final
			arr[2] = this.BLANCO; // 0=blanco 1=amarillo 2=negro
			this.info.put(vertice, arr);
		}
		
	}
	public List<Integer> bfsForest() {
		
		this.inicializarInfo();
		
		ArrayList<Integer> arrR = new ArrayList<Integer>();
		
		Iterator<Integer> vertices = this.grafo.obtenerVertices();
		
		while(vertices.hasNext()) {
			Integer vertice = vertices.next();
			if(this.info.get(vertice)[2] == BLANCO) {
				arrR.addAll(BFS_Visit(this.grafo, vertice));
			}
		}
		
		return arrR;
	}
	
	private List<Integer> BFS_Visit(Grafo<?> G, int vertice){
		
		this.info.get(vertice)[2] = this.AMARILLO;
		List<Integer> listaR = new LinkedList<>();
		List<Integer> fila = new LinkedList<>(); //esto debería ser una fila para mayor eficiencia en recorrido
		fila.add(vertice);
		listaR.add(vertice);
		while(!fila.isEmpty()) {
			Iterator<Integer> itAdyacentes = this.grafo.obtenerAdyacentes(fila.remove(0));
			 //le tengo que poner 0 porque es una lista, cuando sea una fila va a ser distinto
			while(itAdyacentes.hasNext()) {
				Integer nuevoVertice = itAdyacentes.next();
				if(this.info.get(nuevoVertice)[2] == this.BLANCO) {
					this.info.get(nuevoVertice)[2] = this.AMARILLO;
					fila.add(nuevoVertice);
					listaR.add(nuevoVertice);
				}
			}
			
		}
		return listaR;
	}
	
}
