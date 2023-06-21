package main;

import servicios.Backtracking;
import servicios.Greedy;

public class MainParte2 {

	public static void main(String[] args) {


		Greedy b = new Greedy("././datasets/dataset2.txt");
		System.out.println(b.greedy());
		System.out.println("Metrica: " + b.getMetrica());
	}

}
