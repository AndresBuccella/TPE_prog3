package servicios;

import java.util.ArrayList;
import java.util.HashMap;
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
			List<Integer> caminoPosible = new LinkedList<>();
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
		
		caminoPosible.add(vertice);
		if(vertice == this.destino) {
			caminosTotales.add(caminoPosible);
		}else {
			if(pasos < this.lim) {
				Iterator<Integer> adyacentes = this.grafo.obtenerAdyacentes(vertice);
				while(adyacentes.hasNext()) {
					Integer proximo = adyacentes.next();
					int[] arco = {vertice, proximo};
					int[] arcoVuelta = {proximo, vertice};
					if(arcosVisitados.add(arco) &&
							arcosVisitados.add(arcoVuelta)) { // si no se agrega es porque ya paso
						pasos++;
						List<Integer> nuevoCamino = new ArrayList<>(caminoPosible);
						this.buscador(proximo, pasos, arcosVisitados, nuevoCamino, caminosTotales);
					}
				}
				
			}
		}
	}
	

}
