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

    //TODO - Use a data structure that can hold information about how many
    // - hexes of each terrain type there are total, and a method to compile this
    // - information and set the values in the data structure.



    String terrainString;



    int terrainTypeInt;

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

    static int[][] NeighborWithinTwoDist;

    //TODO Make method that initializes an array like NeighborDist that specifies
    // - the coordinate distances to hexes within distance two.
    /* static int[][] InitializeNeighborWithinTwoDist() {
            
    } */

    Hex[] Neighbors;

    boolean bordersWater;
    boolean bordersMountain;
    boolean bordersForest;
    boolean bordersSwamp;
    boolean bordersDesert;

    /* private Hex(int terrainInt) {
        this.terrainString = Terrain.valueOf(terrainInt).name();
        this.terrainTypeInt = terrainInt;
        this.HexID = nextHexID;
        nextHexID++;
        allHexesList.add(this);
    } */

    public Hex(int x, int y, int terrain) {
        this.terrainString = Terrain.valueOf(terrain).name();
        this.terrainTypeInt = terrain;
        this.HexID = nextHexID;
        nextHexID++;
        allHexesList.add(this);

        this.xCoord = x;
        this.yCoord = y;
        Key intPair = new Key(x,y);
        hexesByCoords.put(intPair, this);
    }


    public String getTerrainString() {
        /*if (terrainString.isEmpty()) {
            return null;
        } */
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
        Neighbors = hexArray;
        return hexArray;
    }

    public Hex[] makeMissingNeighbors() {
        Hex[] hexArray = new Hex[6];

        /* if(xCoord == null) {
            xCoord = 0;
        }
        if(yCoord == null) {
            yCoord = 0;
        } */

        int ourX = getX();
        int ourY = getY();

        Random random = new Random();
        int i;
        int[] x;
        Key k;
        for (i = 0; i<6; i++) {
            x = NeighborDist[i];
            k = new Key(ourX + x[0], ourY + x[1]);
            if (hexesByCoords.containsKey(k)) {
                hexArray[i] = hexesByCoords.get(k);
            }
            else {
                hexArray[i] = new Hex(ourX + x[0], ourY+ x[1], random.nextInt(5));
            }
        }

        return hexArray;
    }

    public void makeTwoAwayNeighbors() {
        Hex[] hexArray = this.makeMissingNeighbors();
        //Hex[] outHexArray = new Hex[12];

        for (Hex hex:hexArray) {
            hex.makeMissingNeighbors();
        }


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

    public Hex[] getAndSetNeighbors() {
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
        Neighbors = hexArray;
        return hexArray;
    }

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
        }
        Neighbors = hexArray;
        return hexArray;
    }

    public boolean hasSwampNeighbor() {
        if (Neighbors.length<6) {
            this.getNeighbors();
        }
        int i;
        Hex x;
        for (i = 0; i<Neighbors.length; i++) {
            x = Neighbors[i];
            if (x.terrainTypeInt == 3) {
                return true;
            }
        }
        return false;
    }

    //TODO Make method that checks for presence of something within two or three tiles

    public Hex getNeighborByDirectionInt(int i) {
        if ((0 <= i) && (i < 6)) {
            Hex ret;
            int[] c = NeighborDist[i];
            int x = getX() + c[0];
            int y = getY() + c[1];
            Key k = new Key(x, y);
            if (hexesByCoords.containsKey(k)) {
                ret = hexesByCoords.get(k);
                return ret;
            }
            else {
                return null;
            }
        }
        else {
            return getNeighborByDirectionInt(i % 6);
        }
    }

    public Hex getNorthEastNeighbor() {
        return getNeighborByDirectionInt(0);
    }

    public Hex getNorthNeighbor() {
        return getNeighborByDirectionInt(1);
    }

    public Hex getNorthWestNeighbor() {
        return getNeighborByDirectionInt(2);
    }

    public Hex getSouthWestNeighbor() {
        return getNeighborByDirectionInt(3);
    }

    public Hex getSouthNeighbor() {
        return getNeighborByDirectionInt(4);
    }

    public Hex getSouthEastNeighbor() {
        return getNeighborByDirectionInt(5);
    }

    public int getTerrainTypeInt() {
        return terrainTypeInt;
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

    public static Hex getHexByCoords(int x, int y) {
        Key k = new Key(x, y);
        return hexesByCoords.get(k);
    }

    public void setTerrainNeighborBool() {
        this.bordersWater = false;
        this.bordersMountain = false;
        this.bordersForest = false;
        this.bordersSwamp = false;
        this.bordersDesert = false;

        Hex[] neighbors = getNeighbors();
        for (Hex aNeighbor : neighbors) {
            int t = aNeighbor.getTerrainTypeInt();
            if (t == 0) {
                this.bordersWater = true;
            }
            if (t == 1) {
                this.bordersMountain = true;
            }
            if (t == 2) {
                this.bordersForest = true;
            }
            if (t == 3) {
                this.bordersSwamp = true;
            }
            if (t == 4) {
                this.bordersDesert = true;
            }
        }
    }
}
