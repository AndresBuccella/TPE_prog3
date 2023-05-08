package iterator;

import java.util.Iterator;
import java.util.LinkedList;

import grafo.Arco;

import java.util.ArrayList;
import java.util.HashMap;

public class obtenerVertices<Integer> implements Iterator<Integer> {
	
	private ArrayList<Integer> arrVertices;
	private int indice;
	
	public obtenerVertices(HashMap<Integer, LinkedList<Arco>> hm) {
		for(Integer vertice : hm.keySet()) {
			arrVertices.add(vertice);
		}
		indice = 0;
	}

	@Override
	public boolean hasNext() {
		return this.arrVertices.size() > indice;
	}

	@Override
	public Integer next() {
		Integer aux = this.arrVertices.get(indice);
		indice++;
		return (Integer) aux;
	}
	
	
}
