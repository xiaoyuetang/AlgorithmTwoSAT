package com.example.lib;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.io.*;
import java.util.*;
import java.util.Stack;


public class ImplicationGraph {

    ReadFile readfile = new ReadFile();
    List<Integer> Literal1;
    List<Integer> Literal2;
    int variables;
    int clauses;

    public HashMap<Integer, List<Integer>> adj = new HashMap<>();
    public HashMap<Integer, List<Integer>> adjinv = new HashMap<>();

    public boolean [] visited = new boolean[variables];
    public boolean [] visitedinv = new boolean[variables];

    public Stack<Integer> stack = new Stack<>();

//    public List<Integer> Literals = new ArrayList<Integer>();

    ImplicationGraph() {
        this.Literal1 = readfile.Literal1;
        this.Literal2 = readfile.Literal2;
        this.variables = readfile.variables;
        this.clauses = readfile.clauses;
        this.adj = adj;
        this.adjinv = adjinv;
        this.visited = visited;
        this.visitedinv = visitedinv;
        this.stack = stack;
//        this.Literals = Literals;//solve the problem if literal is negative
    }

    public void DrawGraph() {

        int L1, L2, l1, l2, nl1, nl2;
        for (int i = 0; i < clauses; i++){

//            setLiterals();
            L1 = Literal1.get(i);
            L2 = Literal2.get(i);

            l1 = RepresentLiteral(L1,"l");
            l2 = RepresentLiteral(L2,"l");
            nl1 = RepresentLiteral(-L1,"nl");
            nl2 = RepresentLiteral(-L2,"nl");

            if (L1 > 0 && L2 > 0){
                addEdges(nl1, l2);
                addEdgesInv(nl1, l2);
                addEdges(nl2, l1);
                addEdgesInv(nl2, l1);
            }
            //(L1 or L2) means notL1 ==> L2 and notL2 ==> L1

            else if (L1 > 0 && L2 < 0){
                addEdges(nl1, nl2);
                addEdgesInv(nl1, nl2);
                addEdges(l2, l1);
                addEdgesInv(l2, l1);
            //(L1 or notL2) means notLi ==> notL2 and L2 ==> L1

            } else if (L1 < 0 && L2 > 0){
                addEdges(l1, l2);
                addEdgesInv(l1, l2);
                addEdges(nl2, nl1);
                addEdgesInv(nl2, nl1);
            }
            //(notL1 or L2) means L1 ==> L2 and notL2 ==> notL1

            else {
                addEdges(nl1, nl2);
                addEdgesInv(nl1, nl2);
                addEdges(l2, nl1);
                addEdgesInv(l2, nl1);
            }
            //(notL1 or notL2) means L1 ==> notL2 and L2 ==> notL1
        }
    }


//    public void setLiterals() {
//        for (int i=0; i<2*variables; i++) {
//            Literals.add(i,i-variables);
//        }
//        this.Literals = Literals;
//    }

    public int RepresentLiteral(int L, String type) {
        if (L>0 && type == "l") {return L;}
        if (L<0 && type == "l") {return -L;}
        if (L>0 && type == "nl") {return L+variables;}
        else {return variables-L;}
        //use +variable represents not, and flip the sign of a negative number at the same time.
    }

    public void addEdges(int from, int to){
        if (adj.get(from) == null) {
            adj.put(from, new ArrayList<Integer>());// point from 'from' to multiple 'to's
        }else {adj.get(from).add(to);}
    }

    public void addEdgesInv(int from, int to){
        if (adjinv.get(to) == null) {
            adjinv.put(to, new ArrayList<Integer>());//reverse the direction
        }else{adjinv.get(to).add(from);}
    }

}


