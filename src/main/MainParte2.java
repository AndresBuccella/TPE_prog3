package main;

import servicios.Backtracking;
import servicios.Greedy;

public class MainParte2 {

	public static void main(String[] args) {


		Backtracking<Integer> b = new Backtracking("././datasets/dataset3.txt");
		System.out.println(b.backT());
		System.out.println("Metrica: " + b.getMetrica());
	}

}
