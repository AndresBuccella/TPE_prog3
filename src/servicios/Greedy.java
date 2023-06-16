package servicios;

import java.util.HashSet;
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
		this.solucion = new Estado(0x7fffffff);
		this.metrica = 0;
	}
	
	public int getMetrica() {
		return this.metrica;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
