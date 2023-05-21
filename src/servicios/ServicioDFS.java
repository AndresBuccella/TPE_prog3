package servicios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import grafo.Grafo;

public class ServicioDFS {

	private Grafo<?> grafo;
	private HashMap<Integer, int[]> info;
	
	private final int BLANCO = 0; //no visitado
	private final int AMARILLO = 1; //visitado
	private final int NEGRO = 2; //finalizado
	
	private int tiempoTotal;
	
	public ServicioDFS(Grafo<?> grafo) {
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

	public List<Integer> dfsForest() {
		
		this.inicializarInfo();
		
		Iterator<Integer> it = this.grafo.obtenerVertices();
		List<Integer> pilaR = new Stack<Integer>();
		while(it.hasNext()) {
			Integer vertice = it.next();
			if(this.info.get(vertice)[2] == this.BLANCO) {
				pilaR.addAll(DFS_Visit(vertice));
			}
		}
		return new ArrayList<>(pilaR);
	}

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
