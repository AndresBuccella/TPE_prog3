package servicios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import grafo.Grafo;

public class ServicioBFS {

	private Grafo<?> grafo;
	private HashMap<Integer, int[]> info;
	
	private final int BLANCO = 0; //no visitado
	private final int AMARILLO = 1; //visitado
	private final int NEGRO = 2; //finalizado
	
	public ServicioBFS(Grafo<?> grafo) {
		this.grafo = grafo;
		this.info = new HashMap<Integer, int[]>();
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
		List<Integer> arrR = new ArrayList<Integer>();
		Iterator<Integer> vertices = this.grafo.obtenerVertices();
		
		while(vertices.hasNext()) {
			Integer vertice = vertices.next();
			if(this.info.get(vertice)[2] == BLANCO) {
				arrR.addAll(BFS_Visit(vertice));
			}
		}
		return arrR;
	}
	
	private List<Integer> BFS_Visit(int vertice){
		
		this.info.get(vertice)[2] = this.AMARILLO;
		List<Integer> listaR = new LinkedList<>();
		List<Integer> fila = new LinkedList<>();
		fila.add(vertice);
		listaR.add(vertice);
		while(!fila.isEmpty()) {
			Iterator<Integer> itAdyacentes = this.grafo.obtenerAdyacentes(fila.remove(0));
			while(itAdyacentes.hasNext()) {
				Integer nuevoVertice = itAdyacentes.next();
				if(this.info.get(nuevoVertice)[2] == this.BLANCO) {
					this.info.get(nuevoVertice)[2] = this.AMARILLO;
					fila.add(nuevoVertice);
					listaR.add(nuevoVertice);
				}
			}
			this.info.get(vertice)[2] = this.NEGRO;			
			
		}
		return listaR;
	}
	
}
