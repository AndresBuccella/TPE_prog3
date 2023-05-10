package servicios;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import grafo.Arco;
import grafo.Grafo;

public class ServicioDFS {

	private Grafo<?> grafo;
	private HashMap<Integer, int[]> estado; //estaria bien?
	
	//no me parece eficiente, pero podría hacer un objeto estado?
	
	private final int BLANCO = 0; //no visitado
	private final int AMARILLO = 1; //visitado
	private final int NEGRO = 2; //finalizado
	
	public ServicioDFS(Grafo<?> grafo) {
		this.grafo = grafo;
		this.estado = new HashMap<Integer, int[]>();
	}
	public List<Integer> dfsForest() {
		
		int tiempoTotal = 0;
		int tiempoI = 0;
		int tiempoF = 0;
		int estado = 0; // 0=blanco 1=amarillo 2=negro
		int[] arr = {tiempoI, tiempoF, estado};
		LinkedList<Integer> listR = new LinkedList<Integer>();

		Iterator<Integer> it = this.grafo.obtenerVertices();
		while(it.hasNext()) {
			Integer vertice = it.next();
			this.estado.put(vertice, arr);
		}
		while(it.hasNext()) {
			//por cada vertice se pregunta si no fue visitado
			//si es BLANCO va a DFS_Visit
			Integer vertice = it.next();
			if(this.estado.get(vertice)[2] == this.BLANCO) {
				listR.addAll(DFS_Visit(vertice, tiempoTotal));
			}
			
		}
		//se agrega al arraylist
		return new ArrayList<>();
	}
	
	private List<Integer> DFS_Visit(int vertice, int tiempoTotal){
		this.estado.get(vertice)[2] = this.AMARILLO;
		tiempoTotal++;
		this.estado.get(vertice)[0] = tiempoTotal;
		Iterator<Arco<Integer>> it = this.grafo.obtenerArcos(vertice);

		while(it.hasNext()) {
			Arco<T> arco = it.next();
			if(this.estado.get(vertice)[2] == this.BLANCO) {
				return new LinkedList<Integer>().addAll(this.DFS_Visit(vertice, tiempoTotal));
			}
			
		}
		return null;
	}

}
