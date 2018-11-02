package com.example.lib;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class TwoSATSolver {
    public static void main(String args[]) throws IOException {
        ReadFile readfile = new ReadFile();
        SearchSCC SCC = new SearchSCC();
        Random R = new Random();

        System.out.println("---Solving---");
        File testing = new File("/Users/keelia/Desktop/testing.cnf");

        readfile.CNF(testing);

        long startSlove = System.nanoTime();
        boolean satisfiable = SCC.Satisfiability();
        long endSolve = System.nanoTime();

        long SolvingTime = endSolve - startSlove;

        if (satisfiable) {
            System.out.println("FORMULA SATISFIABLE\n");
        } else {
            System.out.println("FORMULA UNSATISFIABLE\n");
        }
        System.out.println("Solving Time: " + SolvingTime/1000000000.0 + "s");

        ////////////////////////////////////////////////////////////////////

        System.out.println("---Randomizing Solving---");

        long startSloveR = System.nanoTime();
        boolean satisfiableR = R.FlipValue();
        long endSolveR = System.nanoTime();

        long SolvingTimeR = endSolveR - startSloveR;

        if (satisfiableR) {
            System.out.println("FORMULA SATISFIABLE\n");
        } else {
            System.out.println("FORMULA UNSATISFIABLE\n");
        }
        System.out.println("Random Algorithm Solving Time: " + SolvingTimeR/1000000000.0 + "s");
    }


}
