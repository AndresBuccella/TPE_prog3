package grafo;

import java.util.Iterator;

import java.util.Collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

public class GrafoDirigido<T> implements Grafo<T> {

	private HashMap<Integer, HashSet<Arco<T>>> vertices;
	
	public GrafoDirigido() { //tiene sentido crear un grafo vacio?
		vertices = new HashMap<Integer, HashSet<Arco<T>>>();
	}

	/**
	* Complejidad: O(1) debido a que debe
	* verificar si no existe el vertice en el hashmap, para agregarlo
	* junto a una lista de arcos.
	*/
	@Override
	public void agregarVertice(int verticeId) {
		if(!this.vertices.containsKey(verticeId)) {
			this.vertices.put(verticeId, new HashSet<Arco<T>>());//O(1)
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
			
			for(HashSet<Arco<T>> setDeAdyacentes : this.vertices.values()) {// for * for O(n^2)
				//recorre todo el hashmap devolviendo las listas
				//O(n)
				setDeAdyacentes.removeIf(arco -> arco.getVerticeDestino() == verticeId); //O(n)
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
//tengo que controlar que no se agreguen arcos iguales. si la etiqueta es distinta, actualizar etiqueta
		if(this.vertices.containsKey(verticeId1) &&  
		this.vertices.containsKey(verticeId2)){
			//Si los vertices existen =>
			//O(1)
			//Puede permitir arcos iguales con distinto peso??
/*			for(Arco<T> arco : this.vertices.get(verticeId1)) {
				//O(n)
				if((arco.getVerticeOrigen() == verticeId1) && (arco.getVerticeDestino() == verticeId2) &&
					(arco.getEtiqueta.equals(etiqueta) {
					//Recorre toda la lista de adyacencia, si encuentra un arco igual, sale
					return;
				}
			}
*/			//Si no existe el arco lo crea y agrega =>
			Arco<T> newArco = new Arco<T>(verticeId1, verticeId2, etiqueta);
			this.vertices.get(verticeId1).add(newArco); //aca permite arcos iguales con distinto peso
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
			
			HashSet<Arco<T>> setDeAdyacentes = this.vertices.get(verticeId1);
			setDeAdyacentes.removeIf(arco -> arco.getVerticeDestino() == verticeId2); //O(n)
				
/*			Arco<T> arco = this.obtenerArco(verticeId1, verticeId2); //O(n)
			if(arco != null) {
				this.vertices.get(verticeId1).remove(arco); //O(n)
			}
*/		}

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
			HashSet<Arco<T>> setDeAdyacentes = this.vertices.get(verticeId1);
			if(setDeAdyacentes != null) { //hay que comprobar porque puede no tener adyacentes
				return setDeAdyacentes.contains(new Arco<T>(verticeId1, verticeId2, null));				
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
			
			for(Arco<T> arco : this.vertices.get(verticeId1)) { //O(n)
				//O(n)
				if(arco.getVerticeDestino() == verticeId2)
					return arco;
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
		
		int sumatoria = 0;
		for (HashSet<Arco<T>> setDeAdyacentes : this.vertices.values()) { //O(n)
			sumatoria = sumatoria + setDeAdyacentes.size(); //O(1)
		}
		return sumatoria;

	}
	
	/**
	* Complejidad: O(1) debido a que busca en un hashmap por key y devuelve un iterator
	*/
	@Override
	public Iterator<Integer> obtenerVertices() {
		return this.vertices.keySet().iterator();
	}
	
	/**
	* Complejidad: O(n) donde n es la cantidad de vertices adyacentes a verticeId
	* map convierte los elementos (Arco<T>) de esa secuencia en el vertice de destino (Integer)
	*/
	@Override
	public Iterator<Integer> obtenerAdyacentes(int verticeId) {
	
		return this.vertices.getOrDefault(verticeId, new HashSet<>()) //O(1)
							.stream() //O(1)
							.map(Arco<T>::getVerticeDestino) //O(n)
							.iterator(); //O(1)
		
		
/*		if (vertices.containsKey(verticeId)) {
			HashSet<Arco<T>> setDeAdyacentes = vertices.get(verticeId);

			if(setDeAdyacentes != null) {
				ArrayList<Integer> arrAuxAdyacentes = new ArrayList<>(setDeAdyacentes.size());
				for (Arco<T> arco : setDeAdyacentes) { //O(n)
					arrAuxAdyacentes.add(arco.getVerticeDestino());
				}
				return arrAuxAdyacentes.iterator();				
			}
	    }
		return Collections.emptyIterator();
*/	}
	
	/**
	* Complejidad: O(n^2) donde n es la cantidad de vertices y la cantidad de arcos debido a que debe
	* recorrer cada vertice buscando sus adyacentes para agregarlos a una pila y asi devolver todos.
	*/

	@Override
	public Iterator<Arco<T>> obtenerArcos() { // O(1)
		
		return this.vertices.values().stream().filter(Objects::nonNull).flatMap(HashSet::stream).iterator();
		//.values() trae todos los hashset
		//.stream() encapsula cada value en un stream
		//.filter(Objects::nonNull) se fija que cada uno no sea null, si algun stream es null lo saca
		//.flatMap(HashSet::stream) toma cada stream y los concatena en un solo stream
		//.iterator() devuelve un iterator de todos los arcos
		
		/*		List<Arco<T>> listaDeAdyacentes = new LinkedList<Arco<T>>();
		for(HashSet<Arco<T>> setDeArcos : this.vertices.values()) { //O(n)
			if(setDeArcos != null) {
				for(Arco<T> arco : setDeArcos) {//O(n)
					listaDeAdyacentes.add(arco);
				}				
			}
		}
		
		return listaDeAdyacentes.iterator();
*/	}
	/**
	* Complejidad: O(1) debido a que busca en un hashmap por key y devuelve un iterator
	*/

	@Override
	public Iterator<Arco<T>> obtenerArcos(int verticeId) {
//deberia usar getOrDefault por si el vértice no tiene arcos
		return this.vertices.get(verticeId).stream()
							.filter(Objects::nonNull)
							.iterator();
		
/*		if(this.vertices.containsKey(verticeId)) {
			HashSet<Arco<T>> setDeArcosAdyacentes = this.vertices.get(verticeId); 
			return setDeArcosAdyacentes.iterator(); //O(1)
		}
		return Collections.emptyIterator();
*/	}

}
