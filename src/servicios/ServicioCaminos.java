package servicios;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import grafo.Arco;
import grafo.Grafo;


public class ServicioCaminos {

	private Grafo<?> grafo;
	private int origen;
	private int destino;
	private int lim;
	
	
	
	// Servicio caminos
	public ServicioCaminos(Grafo<?> grafo, int origen, int destino, int lim) {
		this.grafo = grafo;
		this.origen = origen;
		this.destino = destino;
		this.lim = lim;
	}

	public List<List<Integer>> caminos() {
		if(this.grafo.contieneVertice(origen) &&
				this.grafo.contieneVertice(destino)) {
			
			List<List<Integer>> caminosTotales = new LinkedList<>();
			List<Integer> caminoPosible = new LinkedList<>();
			int pasos = 0;
			HashSet<int[]> arcosVisitados = new HashSet<>();
			this.buscador(this.origen, pasos, arcosVisitados, caminoPosible, caminosTotales);
			return new ArrayList<>(caminosTotales);
		}else {
			return new ArrayList<>();
		}
			
	}
	//O(n^2)
	/**
	* Complejidad: O(n) donde n es la cantidad de vertices que tiene el grafo debido a que debe
	* recorrer todos para agregarlos a una estructura donde se agregan más detalles
	*/
	private void buscador(int vertice, int pasos, HashSet<int[]> arcosVisitados,
							List<Integer> caminoPosible, List<List<Integer>> caminosTotales){
		//1.4 Se cuenta como visitado el arco de vuelta en el grafo dirigido sin que pase por él
		caminoPosible.add(vertice);
		if((caminoPosible.size() - 1) < this.lim) {
			Iterator<Integer> adyacentes = this.grafo.obtenerAdyacentes(vertice);
			if(vertice == this.destino) {
				caminosTotales.add(caminoPosible);
			}
			existeArco:
			while(adyacentes.hasNext()) {
				Integer proximo = adyacentes.next();
				for(int[] arrAarc : arcosVisitados) { //O(this.lim)
					if((arrAarc[0] == vertice && arrAarc[1] == proximo) || 
							(arrAarc[1] == vertice && arrAarc[0] == proximo)) {
						continue existeArco;
					}
				}
				int[] arco = {vertice, proximo}; //podria hacerlo con una matriz?
				int[] arcoVuelta = {proximo, vertice};
				arcosVisitados.add(arco);
				arcosVisitados.add(arcoVuelta);
				List<Integer> caminoNuevo = new ArrayList<>(caminoPosible);
				this.buscador(proximo, pasos, arcosVisitados, caminoNuevo, caminosTotales);
				
				/* no lo puedo usar porque en el caso de que se repita el nodo, elimina el primero y no
				 * el segundo como corresponderia o lo toma como id en vez de objeto, por lo que da el error
				 * de rango
				 * caminoPosible.remove(vertice);
				*/
				arcosVisitados.remove(arco);
				arcosVisitados.remove(arcoVuelta);
			}
		}
				
				

/* Con: No se puede controlar camino de vuelta
 * 		Hay que castear.		
*/		//1.3
/*				caminoPosible.add(vertice);
				if(vertice == this.destino) {
					caminosTotales.add(caminoPosible);
				}else {
					if((caminoPosible.size() - 1) < this.lim) {
						Iterator<?> adyacentes = this.grafo.obtenerArcos(vertice);
						existeArco:
						while(adyacentes.hasNext()) {
							Arco<Integer> proximo = (Arco<Integer>) adyacentes.next();
							if(arcosVisitados.contains(proximo)) {
								continue existeArco;
							}
							arcosVisitados.add(proximo);
							List<Integer> caminoNuevo = new ArrayList<>(caminoPosible);
							this.buscador(proximo.getVerticeDestino(), pasos, arcosVisitados, caminoNuevo, caminosTotales);
							//caminoPosible.remove((Integer)proximo.getVerticeDestino());
							arcosVisitados.remove(proximo);
						}
					}
				}

*/
/*  1.2
		caminoPosible.add(vertice);
		if(vertice == this.destino) {
			caminosTotales.add(caminoPosible);
		}else {
			if((caminoPosible.size() - 1) < this.lim) {
				Iterator<Integer> adyacentes = this.grafo.obtenerAdyacentes(vertice);
				existeArco:
				while(adyacentes.hasNext()) {
					Integer proximo = adyacentes.next();
					for(int[] arrAarc : arcosVisitados) {
						if((arrAarc[0] == vertice && arrAarc[1] == proximo) || 
								(arrAarc[1] == vertice && arrAarc[0] == proximo)) {
							continue existeArco;
						}
					}
					int[] arco = {vertice, proximo};
					int[] arcoVuelta = {proximo, vertice};
					arcosVisitados.add(arco);
					arcosVisitados.add(arcoVuelta);
					List<Integer> caminoNuevo = new ArrayList<>(caminoPosible);
					this.buscador(proximo, pasos, arcosVisitados, caminoNuevo, caminosTotales);
					arcosVisitados.remove(arco);
					arcosVisitados.remove(arcoVuelta);
				}
			}
		}
*/
/*	1.1
		boolean existeArco = false;
		caminoPosible.add(vertice);
		if(vertice == this.destino) {
			caminosTotales.add(caminoPosible);
		}else {
			if(pasos < this.lim) {
				Iterator<Integer> adyacentes = this.grafo.obtenerAdyacentes(vertice);
				while(adyacentes.hasNext()) {
					Integer proximo = adyacentes.next();
					for(int[] arrAarc : arcosVisitados) {
						if((arrAarc[0] == vertice && arrAarc[1] == proximo) || 
								(arrAarc[1] == vertice && arrAarc[0] == proximo)) {
							existeArco = true;
						}
					}
					if(!existeArco) {
						int[] arco = {vertice, proximo};
						int[] arcoVuelta = {proximo, vertice};
						arcosVisitados.add(arco);
						arcosVisitados.add(arcoVuelta);
						pasos++;
						List<Integer> nuevoCamino = new ArrayList<>(caminoPosible);
						this.buscador(proximo, pasos, arcosVisitados, nuevoCamino, caminosTotales);
						pasos--;
						arcosVisitados.remove(arco);
						arcosVisitados.remove(arcoVuelta);
					}else
						existeArco = false;
				}
			}
		}
*/
	}
}