package iterator;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

import grafo.Arco;

public class IteratorAdyacentes<T> implements Iterator<Integer>{

	Stack<Arco> pila = new Stack<>();
	Integer i;
	
	public IteratorAdyacentes(LinkedList<Arco> raiz) {
		for(Arco a : raiz) {
			pila.push(a);
		}
	}
	@Override
	public boolean hasNext() {
		return this.pila.isEmpty();
	}

	@Override
	public Integer next() {
		return this.pila.pop().getVerticeDestino();
	}

}
