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
	private HashSet<Integer> estaciones;
	private Estado solucion;
	
	private int metrica;
	
	public Greedy(String path) {
		this.dataset = new CSVReader(path);
		this.tuneles = new LinkedList<>();
		this.estaciones = new HashSet<>();
		this.solucion = new Estado(0);
		this.metrica = 0;
	}
	
	public int getMetrica() {
		return this.metrica;
	}
	
	public Estado greedy() {
		
		this.dataset.read();
		Arco<Integer> tunelMenor = null;
		Iterator<Arco<Integer>> itTuneles = this.dataset.getTuneles();
		while(itTuneles.hasNext()) {
			Arco<Integer> tunel = itTuneles.next();
			this.tuneles.add(tunel);
			if(tunelMenor == null)
				tunelMenor = tunel;
			else
				if(tunelMenor.getEtiqueta() > tunel.getEtiqueta())
					tunelMenor = tunel;
			this.estaciones.add(tunel.getVerticeOrigen());
			this.estaciones.add(tunel.getVerticeDestino());
		}
		this.tuneles.sort(Comparator.comparingInt(a -> a.getEtiqueta()));
		this.solucion.addTunel(tunelMenor);
		this.tuneles.remove(tunelMenor);
		this.estaciones.remove(tunelMenor.getVerticeOrigen());
		this.estaciones.remove(tunelMenor.getVerticeDestino());
		while(this.estaciones.size() > 0) {
			this.metrica++;
			tunelMenor = buscarSiguienteTunel();
			this.solucion.addTunel(tunelMenor);
			this.tuneles.remove(tunelMenor);
			this.estaciones.remove(tunelMenor.getVerticeOrigen());
			this.estaciones.remove(tunelMenor.getVerticeDestino());
		}
		
		return this.solucion;
	}
	private Arco<Integer> buscarSiguienteTunel() {
		for (Arco<Integer> tunel : tuneles) {
			if(((this.estaciones.contains(tunel.getVerticeOrigen())) && 
					(!this.estaciones.contains(tunel.getVerticeDestino()))
				) 
					|| 
				((!this.estaciones.contains(tunel.getVerticeOrigen())) && 
						(this.estaciones.contains(tunel.getVerticeDestino())))){
				this.metrica++;
				return tunel;
			}
		}
		return null;
	}
}
