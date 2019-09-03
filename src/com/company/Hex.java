package com.company;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class Hex {

    int HexID;

    static int nextHexID = 0;
    static ArrayList<Hex> allHexesList = new ArrayList<Hex>();
    static HashMap<Key, Hex> hexesByCoords = new HashMap<Key, Hex>();



    String terrainString;

    Integer xCoord;
    Integer yCoord;
    /*
    Each hex has 6 neighbors: one located at x+2,y+1 (northeast neighbor);
    one located at x, y+2 (north neighbor); one located at x-2,y+1 (northwest);
    one located at x-2,y-1 (southwest); one located at x, y-2 (south neighbor);
    and one located at x+2,y-1(southeast).
     */

    /*
    String[][] names = {
                            {"Sam", "Smith"},
                            {"Robert", "Delgro"},
                            {"James", "Gosling"},
                           };
     */
    static int[][] NeighborDist = new int[][] {
            {2,1}, {0,2}, {-2,1}, {-2,-1}, {0,-2}, {2,-1}
    };

    private Hex(int terrainInt) {
        this.terrainString = Terrain.valueOf(terrainInt).name();
        this.HexID = nextHexID;
        nextHexID++;
        allHexesList.add(this);
    }

    public Hex(int x, int y, int terrain) {
        this(terrain);
        this.xCoord = x;
        this.yCoord = y;
        Key intArray = new Key(x,y);
        hexesByCoords.put(intArray, this);
    }


    public String getTerrainString() {
        return terrainString;
    }
    public int[] getCoords() {
        int[] array = {xCoord, yCoord};
        return array;
    }
    public Integer getX() {
        return xCoord;
    }
    public Integer getY() {
        return yCoord;
    }

    public Hex[] makeNeighbors() {
        Hex[] hexArray = new Hex[6];

        if(xCoord == null) {
            xCoord = 0;
        }
        if(yCoord == null) {
            yCoord = 0;
        }

        Random random = new Random();
        hexArray[0] = new Hex(xCoord + 2, yCoord + 1,random.nextInt(5));
        hexArray[1] = new Hex(xCoord, yCoord + 2,random.nextInt(5));
        hexArray[2] = new Hex(xCoord - 2, yCoord + 1,random.nextInt(5));
        hexArray[3] = new Hex(xCoord - 2, yCoord - 1,random.nextInt(5));
        hexArray[4] = new Hex(xCoord, yCoord - 2,random.nextInt(5));
        hexArray[5] = new Hex(xCoord + 2, yCoord - 1,random.nextInt(5));
        return hexArray;
    }

    public Boolean hasNorthNeighbor() {
        Key intArray = new Key(xCoord, yCoord + 2);
        if (hexesByCoords.containsKey(intArray)) {
            return true;
        }
        return false;
    }

    /*
    int i, x;

        // iterating over an array
        for (i = 0; i < ar.length; i++) {

            // accessing each element of array
            x = ar[i];
            System.out.print(x + " ");
     */

    public Hex[] getNeighbors() {
        Hex[] hexArray = new Hex[6];
        Key NEintArray = new Key(xCoord + 2, yCoord + 1);
        Key Nints = new Key(xCoord, yCoord + 2);
        Key NWints = new Key(xCoord-2, yCoord+1);
        Key SWints = new Key(xCoord-2, yCoord-1);
        Key Sints = new Key(xCoord, yCoord-2);
        Key SEints = new Key(xCoord+2, yCoord-1);
        Random random = new Random();
        Key[] Keys = new Key[] {NEintArray, Nints, NWints, SWints, Sints, SEints};
        int i; Key x;
        for (i = 0; i<Keys.length; i++) {
            x = Keys[i];
            if (hexesByCoords.containsKey(x)) {
                hexArray[i] = hexesByCoords.get(x);
            }
            else {
                hexArray[i] = new Hex(NeighborDist[i][0], NeighborDist[i][1], random.nextInt(5));
            }
        }
        return hexArray;
    }

    public static int checkNumberOfHexes() {
        return nextHexID;
    }

    public static int checkNumberOfHexesInArray() {
        return allHexesList.size();
    }

    public static Hex getHexById(int id) {
        return allHexesList.get(id);
    }
}
