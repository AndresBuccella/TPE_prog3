package servicios;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import grafo.Arco;

public class Greedy {

	private CSVReader dataset;
	private List<Arco<Integer>> tuneles;
	private HashSet<Integer> estacionesAUnir;
	private Estado solucion;
	private int metrica;
	private final int ESTADOINVALIDO = 0x80000000;
	
	public Greedy(String path) {
		this.dataset = new CSVReader(path);
		this.tuneles = new LinkedList<>();
		this.estacionesAUnir = new HashSet<>();
		this.solucion = new Estado(0);
		this.metrica = 0;
	}
	
	public int getMetrica() {
		return this.metrica;
	}
	
	public Estado resolverGreedy() {
		
		this.dataset.read();
		Arco<Integer> tunelMenor = null;
		Iterator<Arco<Integer>> itTuneles = this.dataset.getTuneles();
		while(itTuneles.hasNext()) {
			Arco<Integer> tunel = itTuneles.next();
			this.tuneles.add(0,tunel);
			this.estacionesAUnir.add(tunel.getVerticeOrigen());
			this.estacionesAUnir.add(tunel.getVerticeDestino());
		}

		this.tuneles.sort(Comparator.comparingInt(a -> a.getEtiqueta()));
		//utiliza el algoritmo de ordenamiento TimSort que en el peor de los casos es O(n log n)
		
		while(this.estacionesAUnir.size() > 0) {
			if (tunelMenor != null) {
				tunelMenor = buscarSiguienteTunel();
				if(tunelMenor != null)
					this.actualizarSolucionRemoviendoTunelMenor(tunelMenor);
				else
					return new Estado(this.ESTADOINVALIDO); //en el caso de cargar un grafo inconexo por error
			}else {
				this.metrica++;
				tunelMenor = this.tuneles.get(0);
				this.actualizarSolucionRemoviendoTunelMenor(tunelMenor);
			}
		}
		
		//Como el grafo siempre es conexo, siempre va a llegar a una solucion valida.
		
		return this.solucion;
	}
	
	private void actualizarSolucionRemoviendoTunelMenor(Arco<Integer> tunelMenor) {
		this.solucion.addTunel(tunelMenor);
		this.tuneles.remove(tunelMenor);
		this.estacionesAUnir.remove(tunelMenor.getVerticeOrigen());
		this.estacionesAUnir.remove(tunelMenor.getVerticeDestino());
	}
	
	private Arco<Integer> buscarSiguienteTunel() {
		for (Arco<Integer> tunel : tuneles) {
			this.metrica++;
			if(((this.estacionesAUnir.contains(tunel.getVerticeOrigen())) && 
					(!this.estacionesAUnir.contains(tunel.getVerticeDestino()))) 
			|| 
				((!this.estacionesAUnir.contains(tunel.getVerticeOrigen())) && 
						(this.estacionesAUnir.contains(tunel.getVerticeDestino()))))
				return tunel;
		}
		return null;
	}
}
