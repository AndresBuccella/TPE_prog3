package grafo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import iterator.IteratorAdyacentes;


public class GrafoDirigido<T> implements Grafo<T>, Iterable<Integer> {

	private HashMap<Integer, LinkedList<Arco>> vertices;
	
	public GrafoDirigido() { //tiene sentido crear un grafo vacio?
		vertices = new HashMap<Integer, LinkedList<Arco>>();
	}

	@Override
	public void agregarVertice(int verticeId) {
		if(this.vertices.get(verticeId) == null) {
			this.vertices.put(verticeId, new LinkedList<Arco>());
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
			this.vertices.remove(verticeId); //se borra el vértice
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
		for (LinkedList<Arco> lAd : this.vertices.values()) { //que complejidad sería??
			result = result + lAd.size();
		}
		return result;
	}

	@Override
	public Iterator<Integer> obtenerVertices() {
		return null;
	}

	@Override
	public Iterator<Integer> obtenerAdyacentes(int verticeId) {
		
		return new IteratorAdyacentes(this.vertices.get(verticeId));
	}

	@Override
	public Iterator<Arco<T>> obtenerArcos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<Arco<T>> obtenerArcos(int verticeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<Integer> iterator() {
		return obtenerAdyacentes(10);
	}


}
