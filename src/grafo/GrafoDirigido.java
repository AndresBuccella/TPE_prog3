package grafo;

import java.util.Iterator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

public class GrafoDirigido<T> implements Grafo<T> {

	private HashMap<Integer, HashSet<Arco<T>>> vertices;
	
	public GrafoDirigido() {
		vertices = new HashMap<Integer, HashSet<Arco<T>>>();
	}

	/**
	* Complejidad: O(1) debido a que debe verificar si no existe el vertice en el hashmap, 
	* para agregarlo junto a una lista de arcos.
	*/
	@Override
	public void agregarVertice(int verticeId) {
		if(!this.vertices.containsKey(verticeId)) {
			this.vertices.put(verticeId, new HashSet<Arco<T>>());
		}
	}

	/**
	* Complejidad: O(n^2) donde n es la cantidad de vertices y arcos debido a que debe
	* recorrer todos los vertices y arcos para borrar todos los arcos que tienen como destino
	* el vertice que se quiere eliminar.
	*/
	@Override
	public void borrarVertice(int verticeId) {

		if(this.vertices.containsKey(verticeId)) {
			this.vertices.remove(verticeId);
			
			for(HashSet<Arco<T>> setDeArcos : this.vertices.values()) {
				setDeArcos.removeIf(arco -> arco.getVerticeDestino() == verticeId);
			}

		}
	}
	
	/**
	* Complejidad: O(n) donde n es la cantidad de arcos adyacentes al vertice debido a que tiene
	* que recorrer todos los adyacentes al vertice.
	*/
	@Override
	public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
		if(this.vertices.containsKey(verticeId1) && this.vertices.containsKey(verticeId2)) {
			Arco<T> arcoNuevo = new Arco<T>(verticeId1, verticeId2, etiqueta);
			HashSet<Arco<T>> setArcosAdyacentes = this.vertices.get(verticeId1);
			if(setArcosAdyacentes != null) {
				setArcosAdyacentes.removeIf(arco -> arco.getVerticeDestino() == verticeId2);				
			}
			setArcosAdyacentes.add(arcoNuevo);
		}

	}

	/**
	* Complejidad: O(n) donde n es la cantidad de arcos del set de adyacencia 
	* debido a que debe encontrar y borrar el arco hallado.
	*/
	@Override
	public void borrarArco(int verticeId1, int verticeId2) {
		
		if(this.vertices.containsKey(verticeId1) &&  
		this.vertices.containsKey(verticeId2)){
			
			HashSet<Arco<T>> setDeAdyacentes = this.vertices.get(verticeId1);
			setDeAdyacentes.removeIf(arco -> arco.getVerticeDestino() == verticeId2);
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
	* recorrer el set de adyacentes de un vertice para verificar si existe el arco.
	*/
	@Override
	public boolean existeArco(int verticeId1, int verticeId2) {

		if(this.vertices.containsKey(verticeId1) &&  this.vertices.containsKey(verticeId2)){
			HashSet<Arco<T>> setDeAdyacentes = this.vertices.get(verticeId1);
			for(Arco<T> arco : setDeAdyacentes) {
				if(arco.getVerticeDestino() == verticeId2)
					return true;
			}
		}
		return false;
	}
	
	/**
	* Complejidad: O(n) donde n es la cantidad de vertices adyacentes que tenga el vertice origen debido a que debe
	* recorrer todos los arcos para verificar si alguno tiene como destino el vertice destino recibido por parametro.
	*/
	@Override
	public Arco<T> obtenerArco(int verticeId1, int verticeId2) {

		if(this.vertices.containsKey(verticeId1) &&  this.vertices.containsKey(verticeId2)){
			
			for(Arco<T> arco : this.vertices.get(verticeId1)) {
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
	* recorrer todos los vertices del hashmap para preguntar el size de cada lista
	* 	*/
	@Override
	public int cantidadArcos() {
		
		int sumatoria = 0;
		for (HashSet<Arco<T>> setDeAdyacentes : this.vertices.values()) {
			sumatoria = sumatoria + setDeAdyacentes.size();
		}
		return sumatoria;

	}
	
	/**
	* Complejidad: O(1) debido a que busca en un hashmap por key(hash) y devuelve un iterator
	*/
	@Override
	public Iterator<Integer> obtenerVertices() {
		return this.vertices.keySet().iterator();
	}
	
	/**
	* Complejidad: O(n) donde n es la cantidad de vertices adyacentes a verticeId que recorre
	* map y crea un flujo con los vertice de destino de cada Arco<T>
	*/
	@Override
	public Iterator<Integer> obtenerAdyacentes(int verticeId) {
	
		return this.vertices.getOrDefault(verticeId, new HashSet<>()) //O(1)
							.stream() //O(1)
							.map(Arco<T>::getVerticeDestino) //O(n)
							.iterator(); //O(1)
	}
	
	/**
	* Complejidad: O(n) donde n es la cantidad de hashset(adyacentes) que recorre filter para sacar los valores null 
	* y posteriormente concatenarlos en .flatMap
	*/

	@Override
	public Iterator<Arco<T>> obtenerArcos() {
		
		return this.vertices.values().stream() //O(1) convierte todos los hashset en un flujo de datos
				.filter(Objects::nonNull) //O(n) donde n es la cantidad de flujos que tiene el stream
				.flatMap(HashSet::stream) //O(n - nulls) donde n es la cantidad de hashset que concatene
				.iterator(); //O(1) devuelve un iterator
		
		//.values() trae todos los hashset
		//.stream() crea un flujo entre todos los hashset
		//.filter(Objects::nonNull) se fija que cada uno no sea null, si algun hashset es null lo saca
		//.flatMap(HashSet::stream) toma cada hashset y los concatena en un solo flujo de datos
		//.iterator() devuelve un iterator de todos los arcos
		
/*		//Reduce la complejidad de O(n^2) -> O(n)
		
			Complejidad O(n^2)
		 * 
		 * HashSet<Arco<T>> setDeAdyacentes = new HashSet<>(); 
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
	* Complejidad: O(n) donde n es la cantidad de values debido a que en .filter
	* recorre todos fijandose y sacando si alguno es null 
	*/
	@Override
	public Iterator<Arco<T>> obtenerArcos(int verticeId) {

		return this.vertices.getOrDefault(verticeId, new HashSet<>())
							.stream()
							.filter(Objects::nonNull)
							.iterator();
	}
}
