package com.example.lib;

import java.util.ArrayList;
import java.util.List;


public class Random {

    ReadFile readfile = new ReadFile();
    public List<Integer> Literal1 = new ArrayList<>();
    public List<Integer> Literal2 = new ArrayList<>();
    int variables;

    Random() {
        this.Literal1 = readfile.Literal1;
        this.Literal2 = readfile.Literal2;
        this.variables = readfile.variables;
    }

    public boolean CheckSat() {
        for (int i=0; i<Literal1.size(); i++) {
            if (Literal1.get(i)<0 && Literal2.get(i)<0) {return false;}
        }
        return true;
    }

    public boolean FlipValue() {
        int count = 0;
        while (!CheckSat() && count<variables*variables) {//stop when reach n^2 times of flipping
            int randomNum = (int)(Math.random() * variables);
            for (int i=0; i<Literal1.size(); i++) {
                if (Math.abs(Literal1.get(i))==randomNum) {Literal1.set(i, -Literal1.get(i));}
                if (Math.abs(Literal2.get(i))==randomNum) {Literal2.set(i, -Literal2.get(i));}
            }
        }
        if (CheckSat()) {return true;}
        else {return false;}
    }
}
