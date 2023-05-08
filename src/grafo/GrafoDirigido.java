package grafo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class GrafoDirigido<T> implements Grafo<T> {

	private HashMap<Integer, LinkedList<Arco<T>>> vertices;
	
	public GrafoDirigido() { //tiene sentido crear un grafo vacio?
		vertices = new HashMap<Integer, LinkedList<Arco<T>>>();
	}

	@Override
	public void agregarVertice(int verticeId) {
		if(this.vertices.get(verticeId) == null) {
			this.vertices.put(verticeId, new LinkedList<Arco<T>>());
		}
	}

	@Override
	public void borrarVertice(int verticeId) {
		if(this.vertices.get(verticeId) != null) { //si existe
			this.vertices.get(verticeId).clear(); //borra todos sus vertices adyacentes
			for(Arco a : this.vertices.get(verticeId)) {//borra todos los que apuntaban a el
				//O(n)
				if(a.getVerticeDestino() == verticeId)
					this.vertices.get(verticeId).remove(a);
			}
			this.vertices.remove(verticeId); //se borra el v�rtice
		}
	}

	@Override
	public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
		if((this.vertices.get(verticeId1) != null) &&  
		(this.vertices.get(verticeId2) != null)){ 
			//Si los vertices existen =>
			//O(1)
			for(Arco a : this.vertices.get(verticeId1)) {
				//O(n)
				if((a.getVerticeOrigen() == verticeId1) && (a.getVerticeDestino() == verticeId2)) {
					//Recorre toda la lista de adyacencia, si encuentra un arco igual, sale
					return;
				}
			}
			//Si no encuentra ningun arco igual =>
			Arco<T> newArco = new Arco(verticeId1, verticeId2, etiqueta); //es algun error que no veo?
			this.vertices.get(verticeId1).add(newArco);
		}
			
			/* no funca porque se fija tambien en la etiqueta
			if(!this.vertices.get(verticeId1).contains(new Arco<T>(verticeId1, verticeId2, etiqueta))) { 
				//O(n)
				Arco<T> newArco = new Arco(verticeId1, verticeId2, etiqueta); //es algun error que no veo?
				this.vertices.get(verticeId1).add(newArco);
			}
			*/

	}

	@Override
	public void borrarArco(int verticeId1, int verticeId2) { //O(2n) = O(n)
		if((this.vertices.get(verticeId1) != null) &&  
		(this.vertices.get(verticeId2) != null)){ //O(1)
			Arco a = this.obtenerArco(verticeId1, verticeId2); //O(n)
			if(a != null) {
				this.vertices.get(verticeId1).remove(a); //O(n)
			}			
		}

	}

	@Override
	public boolean contieneVertice(int verticeId) {
		return this.vertices.get(verticeId) != null;
	}

	@Override
	public boolean existeArco(int verticeId1, int verticeId2) { //O(n)

		if((this.vertices.get(verticeId1) != null) &&  (this.vertices.get(verticeId2) != null)){
			for(Arco a : this.vertices.get(verticeId1)) {
				//O(n)
				if(a.getVerticeDestino() == verticeId2)
					return true;
			}			
		}
		return false;
	}

	@Override
	public Arco<T> obtenerArco(int verticeId1, int verticeId2) {

		if((this.vertices.get(verticeId1) != null) &&  (this.vertices.get(verticeId2) != null)){
			for(Arco a : this.vertices.get(verticeId1)) {
				//O(n)
				if(a.getVerticeDestino() == verticeId2)
					return a;
			}			
		}
		return null;
	}

	@Override
	public int cantidadVertices() {
		return this.vertices.size();
	}

	@Override
	public int cantidadArcos() {
		int result = 0;
		for (LinkedList<Arco<T>> lAd : this.vertices.values()) { //que complejidad ser�a??
			result = result + lAd.size();
		}
		return result;
	}

	@Override
	public Iterator<Integer> obtenerVertices() {
		return this.vertices.keySet().iterator();
	}

	@Override
	public Iterator<Integer> obtenerAdyacentes(int verticeId) {
		
		if (vertices.containsKey(verticeId)) {
			ArrayList<Integer> adyacentesIds = new ArrayList<>();
			LinkedList<Arco<T>> adyacentes = vertices.get(verticeId);
			for (Arco arco : adyacentes) {
				adyacentesIds.add(arco.getVerticeDestino());
			}
			return adyacentesIds.iterator();
	    }
		return Collections.emptyIterator();
	}

	@Override
	public Iterator<Arco<T>> obtenerArcos() { // O(n^2)?
		Stack<Arco<T>> pila = new Stack<Arco<T>>();
		for(LinkedList<Arco<T>> lArco : this.vertices.values()) {
			for(Arco a : lArco) {
				pila.add(a);
			}
		}
		
		return pila.iterator();
	}

	@Override
	public Iterator<Arco<T>> obtenerArcos(int verticeId) {
		if(this.vertices.containsKey(verticeId)) {
			LinkedList<Arco<T>> listArc = this.vertices.get(verticeId); 
			return listArc.iterator();			
		}
		return Collections.emptyIterator();
	}

}
