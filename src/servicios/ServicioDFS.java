package servicios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import grafo.Grafo;

public class ServicioDFS {

	private Grafo<?> grafo;
	private HashMap<Integer, int[]> info; //estaria bien?
	
	//no me parece eficiente, pero podrÃ­a hacer un objeto estado?
	
	private final int BLANCO = 0; //no visitado
	private final int AMARILLO = 1; //visitado
	private final int NEGRO = 2; //finalizado
	
	private int tiempoTotal;
	
	public ServicioDFS(Grafo<?> grafo) {
		this.grafo = grafo;
		this.info = new HashMap<Integer, int[]>();
		this.tiempoTotal = 0;
	}
	/**
	* Complejidad: O(n) donde n es la cantidad de vertices que tiene el grafo debido a que debe
	* recorrer todos para agregarlos a una estructura donde se agregan más detalles
	*/
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
	
	/**
	* Complejidad: O(n) donde n es la cantidad de vertices debido a que debe
	* recorrer cada uno buscando un camino posible y un orden de descubrimiento. Si bien serían ciclos anidados,
	* los dos tienen como limitacion no pasar por vertices visitados, por lo que en el peor de los casos, visita todos
	* los vertices una sola vez.
	*/
	public List<Integer> dfsForest() {
		
		this.inicializarInfo(); //O(n)
		
		Iterator<Integer> it = this.grafo.obtenerVertices();
		List<Integer> pilaR = new Stack<Integer>();
		while(it.hasNext()) { //O(n)
			//por cada vertice se pregunta si no fue visitado
			//si es BLANCO va a DFS_Visit
			Integer vertice = it.next();
			if(this.info.get(vertice)[2] == this.BLANCO) {
				pilaR.addAll(DFS_Visit(vertice)); // O(n)
			}
		}
		return new ArrayList<>(pilaR);
	}
	
	/**
	* Complejidad: O(n) donde  es la cantidad de vertices debido a que debe
	* recorrer los vertices buscando un camino no transitado anteriormente.
	*/
	private List<Integer> DFS_Visit(int vertice){
		List<Integer> pilaA = new Stack<Integer>();
		pilaA.add(vertice);
		
		this.info.get(vertice)[2] = this.AMARILLO;
		tiempoTotal++;
		this.info.get(vertice)[0] = tiempoTotal;
		
		Iterator<Integer> it = this.grafo.obtenerAdyacentes(vertice);
		while(it.hasNext()) {
			Integer adyacente = it.next();
			if(this.info.get(adyacente)[2] == this.BLANCO) {
				pilaA.addAll(this.DFS_Visit(adyacente));
			}
			
		}
		this.info.get(vertice)[2] = this.NEGRO;
		tiempoTotal++;
		this.info.get(vertice)[1] = tiempoTotal;
		
		return pilaA;
	}

}
