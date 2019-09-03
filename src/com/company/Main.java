package com.company;

public class Main {

	public static void main(String[] args) {
		int i = 0;
		Hex baseHex = new Hex(0, 0, i);
		System.out.println(baseHex.getTerrainString());
		System.out.println(Hex.checkNumberOfHexes());
		System.out.println(Hex.checkNumberOfHexesInArray());
		System.out.println(Hex.getHexById(0).getTerrainString());
		baseHex.makeNeighbors();
		System.out.println(baseHex.hasNorthNeighbor());
		System.out.print( "\n" );
		Hex[] baseNeighbors = baseHex.getNeighbors();
		for (Hex h: baseNeighbors) {
			System.out.println(h.getX() + ", " + h.getY());
			System.out.println(h.getTerrainString());
		}
	}
}
