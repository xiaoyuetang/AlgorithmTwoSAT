package com.example.lib;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {

    public List<Integer> Literal1 = new ArrayList<>();
    public List<Integer> Literal2 =  new ArrayList<>();
    public int clauses;
    public int variables;

    ReadFile () {
        this.Literal1 = Literal1;
        this.Literal2 = Literal2;
        this.variables = variables;
        this.clauses = clauses;
    }

    public void CNF(File f) throws IOException {

        System.out.println("---Reading File---");
        long startRead = System.nanoTime();
        FileReader fileReader = new FileReader(f);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;

        while ((line = bufferedReader.readLine()) != null) {

            if (line.isEmpty()){
                continue;
            }

            else if (line.charAt(0) == 'c') {
                continue;
            }
            else if (line.charAt(0) == 'p') {
                String[] args = line.split(" ");
                variables = Integer.parseInt(args[2]);
                clauses = Integer.parseInt(args[3]);
            }
            else{
                String[] literals = line.split(" ");
                Literal1.add(Integer.parseInt(literals[0]));
                Literal2.add(Integer.parseInt(literals[1]));
                //two literals inside the same clause are save separetly to two arrays
                //use Literal[ClauseIdex] to call
            }
        }

        long endRead = System.nanoTime();
        long tReadTime = endRead - startRead;
        System.out.println("Reading Time: " + tReadTime/1000000000.0 + "s");
        System.out.println("---Solver starts---");
    }

}
