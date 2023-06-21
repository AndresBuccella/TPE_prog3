package servicios;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import grafo.Arco;

public class Backtracking {

	private CSVReader dataset;
	private List<Arco<Integer>> tuneles;
	private HashSet<Integer> estaciones;
	private Estado solucion;
	private int metrica;
	
	public Backtracking(String path) {
		this.dataset = new CSVReader(path);
		this.tuneles = new LinkedList<>();
		this.estaciones = new HashSet<>();
		this.solucion = new Estado(0x7fffffff);
		this.metrica = 0;
	}
	public int getMetrica() {
		return this.metrica;
	}
	
	public Estado backT() {
		
		this.dataset.read();
		Iterator<Arco<Integer>> itTuneles = this.dataset.getTuneles();
		while(itTuneles.hasNext()) {
			Arco<Integer> tunel = itTuneles.next();
			this.tuneles.add(0,tunel);
			this.estaciones.add(tunel.getVerticeOrigen());
			this.estaciones.add(tunel.getVerticeDestino());
		}
		Estado e = new Estado(0);
		this.backie(e);
		return solucion;
	}
	
	private void backie(Estado e) {
		if(e.solucionPosible(this.estaciones.size())) {
			if(this.solucion.getKms() > e.getKms())
				this.solucion = e.copy();
		}else
			if(e.getCantTuneles() < this.estaciones.size() - 1) {
				for(int idTunel = 0; idTunel < this.tuneles.size(); idTunel++) {
					this.metrica++;
					
					Arco<Integer> tunel = this.tuneles.remove(idTunel);
					e.addTunel(tunel);
					
					this.backie(e);
					
					this.tuneles.add(idTunel,tunel);
					e.deleteTunel(tunel);
				}
			}
	}
	
	
	
	
}
