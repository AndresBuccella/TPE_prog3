package grafo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

public class GrafoDirigido<T> implements Grafo<T> {

	private HashMap<Integer, LinkedList<Arco<T>>> vertices;
	
	public GrafoDirigido() { //tiene sentido crear un grafo vacio?
		vertices = new HashMap<Integer, LinkedList<Arco<T>>>();
	}

	/**
	* Complejidad: O(1) debido a que debe
	* verificar si no existe el vertice en el hashmap, para agregarlo
	* junto a una lista de arcos.
	*/
	@Override
	public void agregarVertice(int verticeId) {
		if(this.vertices.containsKey(verticeId)) { //O(1)
			this.vertices.put(verticeId, new LinkedList<Arco<T>>());//O(1)
		}
	}

	/**
	* Complejidad: O(n^2) donde n es la cantidad de vertices y arcos debido a que debe
	* recorrer todos los vertices y arcos para borrar todos los arcos que tienen como destino
	* el vertice que se quiere eliminar.
	*/
	@Override
	public void borrarVertice(int verticeId) {

		if(this.vertices.containsKey(verticeId)) { // O(1)
			this.vertices.get(verticeId).clear(); //borra todos sus vertices adyacentes O(n)
			
			for(LinkedList<Arco<T>> list : this.vertices.values()) {// for * for O(n^2)
				//recorre todo el hashmap devolviendo las listas
				//O(n)
				for(Arco<T> a: list) {
					//borra todos los arcos que apuntaban lo tenian como destino
					//O(n)
					if(a.getVerticeDestino() == verticeId)
						this.vertices.get(verticeId).remove(a);					
				}
			}

			this.vertices.remove(verticeId); //se borra el vértice O(n)
		}
	}
	/**
	* Complejidad: O(n) donde n es la cantidad de arcos del vertice origen debido a que debe
	* buscar si ya tiene un arco que lo lleve al mismo destino.
	*/

	@Override
	public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
		if(this.vertices.containsKey(verticeId1) &&  
		this.vertices.containsKey(verticeId2)){
			//Si los vertices existen =>
			//O(1)
			for(Arco<T> a : this.vertices.get(verticeId1)) {
				//O(n)
				if((a.getVerticeOrigen() == verticeId1) && (a.getVerticeDestino() == verticeId2)) {
					//Recorre toda la lista de adyacencia, si encuentra un arco igual, sale
					return;
				}
			}
			//Si no existe el arco lo crea y agrega =>
			Arco<T> newArco = new Arco<T>(verticeId1, verticeId2, etiqueta);
			this.vertices.get(verticeId1).add(newArco);
		}

	}
	/**
	* Complejidad: O(n) donde n es la cantidad de nodos de la lista de adyacencia 
	* debido a que debe encontrar y borrar el arco hallado. Como tiene otra complejidad O(n)
	* pero no está anidada, no la tomamos en cuenta.
	*/

	@Override
	public void borrarArco(int verticeId1, int verticeId2) { //O(2n) = O(n)
		
		if(this.vertices.containsKey(verticeId1) &&  
		this.vertices.containsKey(verticeId2)){ //O(1)
			Arco<T> a = this.obtenerArco(verticeId1, verticeId2); //O(n)
			if(a != null) {
				this.vertices.get(verticeId1).remove(a); //O(n)
			}
		}

	}
	/**
	* Complejidad: O(1) debido a que debe
	* verificar si existe la key(vertice) en el hashmap.
	*/

	@Override
	public boolean contieneVertice(int verticeId) {
		return this.vertices.containsKey(verticeId);
	}
	
	/**
	* Complejidad: O(n) donde n es la cantidad de arcos debido a que debe
	* recorrer la lista de adyacentes de un vértice para verificar si existe un arco.
	*/

	@Override
	public boolean existeArco(int verticeId1, int verticeId2) { //O(n)

		if(this.vertices.containsKey(verticeId1) &&  this.vertices.containsKey(verticeId2)){//O(1)
			for(Arco<T> a : this.vertices.get(verticeId1)) { //O(n)
				if(a.getVerticeDestino() == verticeId2)//O(1)
					return true;
			}			
		}
		return false;
	}
	
	/**
	* Complejidad: O(n) donde n es la cantidad de vertices adyacentes que tenga el vertice origen debido a que debe
	* recorrer todos los arcos para verificar si alguno tiene como destino el vertice destino recibido por parámetro.
	*/

	@Override
	public Arco<T> obtenerArco(int verticeId1, int verticeId2) {

		if(this.vertices.containsKey(verticeId1) &&  this.vertices.containsKey(verticeId2)){
			for(Arco<T> a : this.vertices.get(verticeId1)) { //O(n)
				//O(n)
				if(a.getVerticeDestino() == verticeId2)
					return a;
			}			
		}
		return null;
	}
	/**
	* Complejidad: O(1) debido a que debe
	* devolver el size de un hashmap.
	*/

	@Override
	public int cantidadVertices() {
		return this.vertices.size();
	}
	/**
	* Complejidad: O(n) donde n es la cantidad de vertices debido a que debe
	* recorrer todos los vertices del hashmap para preguntar el size de cada lista e ir sumando
	* 	*/

	@Override
	public int cantidadArcos() {
		
		int result = 0;
		for (LinkedList<Arco<T>> lAd : this.vertices.values()) { //O(n)
			result = result + lAd.size(); //O(1)
		}
		return result;
	}
	
	/**
	* Complejidad: O(n) donde n es la cantidad de vertices debido a que debe
	* devolver todos los vertices existentes.
	*/
	@Override
	public Iterator<Integer> obtenerVertices() {
		return this.vertices.keySet().iterator();
	}
	/**
	* Complejidad: O(n) donde n es la cantidad de arcos que tiene la lista de adyacentes debido a que debe
	* almacenar todos los destinos de cada arco para después devolverlo con .iterator().
	*/

	@Override
	public Iterator<Integer> obtenerAdyacentes(int verticeId) {
		
		if (vertices.containsKey(verticeId)) {
			ArrayList<Integer> adyacentesIds = new ArrayList<>();
			LinkedList<Arco<T>> adyacentes = vertices.get(verticeId);
			for (Arco<T> arco : adyacentes) { //O(n)
				adyacentesIds.add(arco.getVerticeDestino());
			}
			return adyacentesIds.iterator(); //O(n)?
	    }
		return Collections.emptyIterator();
	}
	/**
	* Complejidad: O(n^2) donde n es la cantidad de vertices y la cantidad de arcos debido a que debe
	* recorrer cada vertice buscando sus adyacentes para agregarlos a una pila y asi devolver todos.
	*/

	@Override
	public Iterator<Arco<T>> obtenerArcos() { // O(n^2)
		Stack<Arco<T>> pila = new Stack<Arco<T>>();
		for(LinkedList<Arco<T>> lArco : this.vertices.values()) { //O(n)
			for(Arco<T> a : lArco) {//O(n)
				pila.add(a);
			}
		}
		
		return pila.iterator();
	}
	/**
	* Complejidad: O(n) donde n es la cantidad de arcos debido a que debe
	* devolver todos los arcos de un vertice especifico.
	*/

	@Override
	public Iterator<Arco<T>> obtenerArcos(int verticeId) {
		if(this.vertices.containsKey(verticeId)) {
			LinkedList<Arco<T>> listArc = this.vertices.get(verticeId); 
			return listArc.iterator(); //O(n)
		}
		return Collections.emptyIterator();
	}

}
