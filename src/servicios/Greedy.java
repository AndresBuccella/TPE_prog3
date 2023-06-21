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
			this.tuneles.add(0,tunel);
			this.estaciones.add(tunel.getVerticeOrigen());
			this.estaciones.add(tunel.getVerticeDestino());
		}
		this.tuneles.sort(Comparator.comparingInt(a -> a.getEtiqueta()));
		
		while(this.estaciones.size() > 0) {
			if (tunelMenor != null) {
				this.metrica++;
				tunelMenor = buscarSiguienteTunel();
				this.actualizarSolucionRemoviendoTunelMenor(tunelMenor);
			}else {
				tunelMenor = this.tuneles.get(0);
				this.actualizarSolucionRemoviendoTunelMenor(tunelMenor);
			}
		}
		//si tiene que devolver un estado sin solucion hago
		/* if (this.solucion.posibleSolucion()){
		 * 	return this.solucion;
		 * else
		 * 	return null; //o algo...
		 * 
		 * */
		return this.solucion;
	}
	
	private void actualizarSolucionRemoviendoTunelMenor(Arco<Integer> tunelMenor) {
		this.solucion.addTunel(tunelMenor);
		this.tuneles.remove(tunelMenor);
		this.estaciones.remove(tunelMenor.getVerticeOrigen());
		this.estaciones.remove(tunelMenor.getVerticeDestino());
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
