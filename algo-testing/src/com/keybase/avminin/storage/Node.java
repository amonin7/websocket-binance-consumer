package com.keybase.avminin.storage;

// A class that represents the tree, which calculates the hash from string
public class Node {
    
    // may be nullable
    private Integer value;
    
    private Node childA;
    private Node childB;
    private Node childC;
    private Node childD;
    private Node childE;
    private Node childF;
    private Node childG;
    private Node childH;
    private Node childI;
    private Node childJ;
    private Node childK;
    private Node childL;
    private Node childM;
    private Node childN;
    private Node childO;
    private Node childP;
    private Node childQ;
    private Node childR;
    private Node childS;
    private Node childT;
    private Node childU;
    private Node childV;
    private Node childW;
    private Node childX;
    private Node childY;
    private Node childZ;

    public Node getFieldByIndex(int index) {
        return switch (index) {
            case 0 -> childA;
            case 1 -> childB;
            case 2 -> childC;
            case 3 -> childD;
            case 4 -> childE;
            case 5 -> childF;
            case 6 -> childG;
            case 7 -> childH;
            case 8 -> childI;
            case 9 -> childJ;
            case 10 -> childK;
            case 11 -> childL;
            case 12 -> childM;
            case 13 -> childN;
            case 14 -> childO;
            case 15 -> childP;
            case 16 -> childQ;
            case 17 -> childR;
            case 18 -> childS;
            case 19 -> childT;
            case 20 -> childU;
            case 21 -> childV;
            case 22 -> childW;
            case 23 -> childX;
            case 24 -> childY;
            case 25 -> childZ;
            default -> null;
        };
    }

    public void setChildByIndex(int index, Node child) {
        switch (index) {
            case 0 -> childA = child;
            case 1 -> childB = child;
            case 2 -> childC = child;
            case 3 -> childD = child;
            case 4 -> childE = child;
            case 5 -> childF = child;
            case 6 -> childG = child;
            case 7 -> childH = child;
            case 8 -> childI = child;
            case 9 -> childJ = child;
            case 10 -> childK = child;
            case 11 -> childL = child;
            case 12 -> childM = child;
            case 13 -> childN = child;
            case 14 -> childO = child;
            case 15 -> childP = child;
            case 16 -> childQ = child;
            case 17 -> childR = child;
            case 18 -> childS = child;
            case 19 -> childT = child;
            case 20 -> childU = child;
            case 21 -> childV = child;
            case 22 -> childW = child;
            case 23 -> childX = child;
            case 24 -> childY = child;
            case 25 -> childZ = child;
            default -> throw new IllegalArgumentException(
                    "The provided index does not match to any child"
            );
        }
    }

}
