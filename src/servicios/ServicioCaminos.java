package servicios;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

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
			List<Integer> caminoPosible = new ArrayList<>();
			int pasos = 0;
			HashSet<int[]> arcosVisitados = new HashSet<>();
			this.buscador(this.origen, pasos, arcosVisitados, caminoPosible, caminosTotales);
			return new ArrayList<>(caminosTotales);
		}else {
			return new ArrayList<>();
		}
			
	}
	
	private void buscador(int vertice, int pasos, HashSet<int[]> arcosVisitados,
							List<Integer> caminoPosible, List<List<Integer>> caminosTotales){
		//si tengo que tener en cuenta los dos grafos, no se que hacer
		//esta soluc sirve para no dirigido. Para dirigido tengo que sacar el arco de vuelta
		caminoPosible.add(vertice);
		if(vertice == this.destino) {
			caminosTotales.add(new LinkedList<>(caminoPosible));
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
					this.buscador(proximo, pasos, arcosVisitados, caminoPosible, caminosTotales);
					caminoPosible.remove(caminoPosible.size() - 1);
					arcosVisitados.remove(arco);
					arcosVisitados.remove(arcoVuelta);
				}
			}
		}
	}
}