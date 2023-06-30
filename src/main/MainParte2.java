package main;

import servicios.Backtracking;
import servicios.Greedy;

public class MainParte2 {

	public static void main(String[] args) {
		
		String pathG = "././datasets/dataset1.txt";
		Greedy g1 = new Greedy(pathG);
		System.out.println("Greedy dataset 1");
		System.out.println(g1.resolverGreedy());
		System.out.println("Metrica: " + g1.getMetrica() + "\n");
		
		String pathG2 = "././datasets/dataset2.txt";
		Greedy g2 = new Greedy(pathG2);
		System.out.println("Greedy dataset 2");
		System.out.println(g2.resolverGreedy());
		System.out.println("Metrica: " + g2.getMetrica() + "\n");
		
		String pathG3 = "././datasets/dataset3.txt";
		Greedy g3 = new Greedy(pathG3);
		System.out.println("Greedy dataset 3");
		System.out.println(g3.resolverGreedy());
		System.out.println("Metrica: " + g3.getMetrica() + "\n");
		
			String pathB = "././datasets/dataset1.txt";
		Backtracking b1 = new Backtracking(pathB);
		System.out.println("Backtracking dataset 1");
		System.out.println(b1.resolverBacktracking());
		System.out.println("Metrica: " + b1.getMetrica() + "\n");

		String pathB2 = "././datasets/dataset2.txt";
		Backtracking b2 = new Backtracking(pathB2);
		System.out.println("Backtracking dataset 2");
		System.out.println(b2.resolverBacktracking());
		System.out.println("Metrica: " + b2.getMetrica() + "\n");

		String pathB3 = "././datasets/dataset3.txt";
		Backtracking b3 = new Backtracking(pathB3);
		System.out.println("Backtracking dataset 3");
		System.out.println(b3.resolverBacktracking());
		System.out.println("Metrica: " + b3.getMetrica());
	}
}
