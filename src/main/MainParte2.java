package main;

import servicios.Backtracking;

public class MainParte2 {

	public static void main(String[] args) {


		Backtracking b = new Backtracking("././datasets/dataset2.txt");
		System.out.println(b.backT());
		System.out.println("Metrica: " + b.getMetrica());
	}

}
