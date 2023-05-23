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
			HashSet<int[]> arcosVisitados = new HashSet<>();
			this.buscador(this.origen, arcosVisitados, caminoPosible, caminosTotales);
			return new ArrayList<>(caminosTotales);
		}else {
			return new ArrayList<>();
		}
			
	}
	
	private void buscador(int vertice, HashSet<int[]> arcosVisitados,
							List<Integer> caminoPosible, List<List<Integer>> caminosTotales){
		caminoPosible.add(vertice);
		if((caminoPosible.size() - 1) < this.lim) {
			if(vertice == this.destino)
				caminosTotales.add(new LinkedList<>(caminoPosible));
			Iterator<Integer> adyacentes = this.grafo.obtenerAdyacentes(vertice);
			existeArco:
			while(adyacentes.hasNext()) {
				Integer proximo = adyacentes.next();
				for(int[] arrAarc : arcosVisitados) {
					if(arrAarc[0] == vertice && arrAarc[1] == proximo)
						continue existeArco;
				}
				int[] arco = {vertice, proximo};
				arcosVisitados.add(arco);
				this.buscador(proximo, arcosVisitados, caminoPosible, caminosTotales);
				caminoPosible.remove(caminoPosible.size() - 1);
				arcosVisitados.remove(arco);
			}
		}
		
	}
}