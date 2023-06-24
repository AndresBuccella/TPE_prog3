package grafo;

import java.util.Iterator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

public class GrafoDirigido<T> implements Grafo<T> {

	private HashMap<Integer, HashSet<Arco<T>>> vertices;
	private int cantidadArcos;
	
	public GrafoDirigido() {
		this.vertices = new HashMap<Integer, HashSet<Arco<T>>>();
		this.cantidadArcos = 0;
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
	* Complejidad: O(n) donde n es la cantidad de vertices del HashMap
	*/
	@Override
	public void borrarVertice(int verticeId) {

		if(this.vertices.containsKey(verticeId)) {
			this.cantidadArcos = this.cantidadArcos - this.vertices.get(verticeId).size();
			this.vertices.remove(verticeId);
			for(HashSet<Arco<T>> setDeArcos : this.vertices.values()) {
				if (!setDeArcos.isEmpty()) {
					Arco<T> arcoABorrar = new Arco<>(setDeArcos.iterator().next().getVerticeOrigen(), verticeId, null);
					if(setDeArcos.remove(arcoABorrar)) {
						arcoABorrar = null;
						this.cantidadArcos--;
					}					
				}
			}
		}
	}
	
	/**
	* Complejidad: O(1) porque remueve por hashcode y agrega en un HashSet
	*/
	@Override
	public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
		if(this.vertices.containsKey(verticeId1) && this.vertices.containsKey(verticeId2)) {
			Arco<T> arcoNuevo = new Arco<T>(verticeId1, verticeId2, etiqueta);
			HashSet<Arco<T>> setArcosAdyacentes = this.vertices.get(verticeId1);
			if(setArcosAdyacentes != null) {
				if(setArcosAdyacentes.remove(arcoNuevo))	
					this.cantidadArcos--;
			}
			setArcosAdyacentes.add(arcoNuevo);
			this.cantidadArcos++;
		}

	}

	/**
	* Complejidad: O(1) donde n es la cantidad de arcos del set de adyacencia 
	* debido a que debe encontrar y borrar el arco hallado.
	*/
	@Override
	public void borrarArco(int verticeId1, int verticeId2) {
		
		if(this.vertices.containsKey(verticeId1) &&  
		this.vertices.containsKey(verticeId2)){
			Arco<T> arcoABorrar = new Arco<>(verticeId1, verticeId2, null);
			if(this.vertices.get(verticeId1).remove(arcoABorrar))
				this.cantidadArcos--;
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
	* Complejidad: O(1) ya que tanto el metodo contains busca por hashcode
	*/
	@Override
	public boolean existeArco(int verticeId1, int verticeId2) {

		if(this.vertices.containsKey(verticeId1) &&  this.vertices.containsKey(verticeId2)){
			Arco<T> arcoAux = new Arco<>(verticeId1, verticeId2, null);
			return this.vertices.get(verticeId1).contains(arcoAux);
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
	* Complejidad: O(1) debido a que devuelve el size de un hashmap.
	*/
	@Override
	public int cantidadVertices() {
		return this.vertices.size();
	}
	
	/**
	* Complejidad: O(1) ya que solo devuelve la variable cantidadArcos
	* 	*/
	@Override
	public int cantidadArcos() {
		return this.cantidadArcos;
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
