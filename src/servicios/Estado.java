package servicios;

import java.util.HashSet;

import grafo.Arco;

public class Estado {

	private HashSet<Arco<Integer>> tuneles;
	private HashSet<Integer> estaciones;
	private UnionFind<Integer> uf;
	private int kms;
	
	public Estado(int kms) {
		this.tuneles = new HashSet<>();
		this.estaciones = new HashSet<>();
		this.uf = new UnionFind<>();
		this.kms = kms;
	}
	
	public Estado copy(){
		
		Estado copia = new Estado(0);
		for(Arco<Integer> tunel : this.tuneles) {
			copia.addTunel(tunel);
		}
		
		return copia;
	}
	
	public int getCantTuneles() {
		return this.tuneles.size();
	}
	
	public double getKms() {
		return this.kms;
	}
	
	public void addTunel(Arco<Integer> tunel) {
		this.kms = this.kms + tunel.getEtiqueta(); //O(1)
		this.tuneles.add(tunel); //O(1)
	}
	
	public void deleteTunel(Arco<Integer> tunel) {
		this.kms -= (double)tunel.getEtiqueta();
		this.tuneles.remove(tunel);
	}
	
	public boolean solucionPosible(int estaciones) {
		
		if(this.tuneles.size() == estaciones - 1) {
			for(Arco<Integer> tunel : this.tuneles) {
				this.estaciones.add(tunel.getVerticeOrigen());
				this.estaciones.add(tunel.getVerticeDestino());
			}
			this.uf.makeSet(this.estaciones.iterator());
			for(Arco<Integer> tunel : this.tuneles) {
				this.uf.union(tunel.getVerticeOrigen(), tunel.getVerticeDestino());
			}
			if(this.estaciones.size() == estaciones) {
				this.estaciones.clear();
				boolean respuesta = this.uf.numberOfSets() == 1;
				this.uf.reset();
				return respuesta;
			}
		}
		
		return false;
	}
	
	public String toString() {
		
		String respuesta = "";
		for(Arco<Integer> tunel : this.tuneles) {
			respuesta += tunel + " ";
		}
		respuesta += "\n" + this.kms + "kms";
		
		return respuesta;
	}
	
}
