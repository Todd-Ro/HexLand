package com.company;

public class Key {

    /*Key class is used in the hexesByCoords HashMap, but the changes to the way
    equality is assessed mean that, unlike an array of two ints,
    two Key objects with the exact same int values will never result in
    two distinct keys in the HashMap.
     */


    private final int x;
    private final int y;

    public Key(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Key)) return false;
        Key key = (Key) o;
        return x == key.x && y == key.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

}