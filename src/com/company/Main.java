package com.company;

public class Main {

	public static void main(String[] args) {
		int i = 0;
		Hex baseHex = new Hex(0, 0, i);
		System.out.println(baseHex.getTerrainString());
		System.out.println(Hex.checkNumberOfHexes());
		System.out.println(Hex.checkNumberOfHexesInArray());
		System.out.println(Hex.getHexById(0).getTerrainString());
		///baseHex.makeNeighbors();
		baseHex.makeMissingNeighbors();
		System.out.println(baseHex.hasNorthNeighbor());
		System.out.print( "\n" );
		Hex[] baseNeighbors = baseHex.getNeighbors();
		for (Hex h: baseNeighbors) {
			System.out.println(h.getX() + ", " + h.getY());
			System.out.println(h.getTerrainString());
		}

		int[] n = Hex.NeighborDist[1];
		System.out.println("North neighbor x distance: " + n[0]);
		System.out.println("North neighbor y distance: " + n[1]);

		Hex southOne = baseHex.getSouthNeighbor();
		southOne.makeMissingNeighbors();
		Hex southTwo = southOne.getSouthNeighbor();
		System.out.println(southTwo.getX() + ", " + southTwo.getY());
		System.out.println(southTwo.getTerrainString());

		baseHex.makeTwoAwayNeighbors();
		Hex northOne = baseHex.getNorthNeighbor();
		System.out.println(northOne.getX() + ", " + northOne.getY());
		System.out.println(northOne.getTerrainString());
		System.out.println(northOne.getY()==null);

		Hex northTwo = northOne.getNorthNeighbor();
		Key n2 = new Key(0, 4);
		Hex altNorthTwo = Hex.hexesByCoords.get(n2);
		System.out.println(altNorthTwo.getX() + ", " + altNorthTwo.getY());
		System.out.println(altNorthTwo.getTerrainString());
		System.out.println(northTwo.getX() + ", " + northTwo.getY());
		System.out.println(northTwo.getTerrainString());

	}
}
