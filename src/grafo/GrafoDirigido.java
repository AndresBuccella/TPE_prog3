package grafo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class GrafoDirigido<T> implements Grafo<T> {

	private HashMap<Integer, LinkedList<Arco>> vertices;
	private HashMap<T,String> estado; //estaria bien
//	private HashMap<T,T> adyacentes; //estaria mal porque los indices deben ser unicos
/*	private final String EBLANCO = "Blanco";
	private final String EAMARILLO = "Amarillo";
	private final String ENEGRO = "Negro";
*/	
	
	public GrafoDirigido(int vertice) {
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
		if(this.vertices.get(verticeId) != null) {
			this.vertices.get(verticeId).clear();
			this.vertices.remove(verticeId);
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
			for(Arco a : this.vertices.get(verticeId1)) {
				//O(n)
				if((a.getVerticeOrigen() == verticeId1) && (a.getVerticeDestino() == verticeId2)) {
					//Recorre toda la lista de adyacencia, si encuentra un arco igual, lo borra
					this.vertices.get(verticeId1).remove(a); //O(n)
				}
			}			
		}

	}

	@Override
	public boolean contieneVertice(int verticeId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean existeArco(int verticeId1, int verticeId2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Arco<T> obtenerArco(int verticeId1, int verticeId2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int cantidadVertices() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int cantidadArcos() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Iterator<Integer> obtenerVertices() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<Integer> obtenerAdyacentes(int verticeId) {
		// TODO Auto-generated method stub
		return null;
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

}
