package com.company;

import java.util.HashMap;
import java.util.Map;

public enum Terrain {
    Water (0),
    Mountain (1),
    Forest (2),
    Swamp (3),
    Desert (4);

    private final int terrainCode;
    Terrain(int terrainCode) {
        this.terrainCode = terrainCode;
    }


    public int getTerrainCode() {
        return terrainCode;
    }


    private static Map<Integer, Terrain> map = new HashMap<Integer, Terrain>();

    static {
        for (Terrain terrEnum : Terrain.values()) {
            map.put(terrEnum.terrainCode, terrEnum);
        }
    }

    public static Terrain valueOf(int terrainCode) {
        return map.get(terrainCode);
    }


}
