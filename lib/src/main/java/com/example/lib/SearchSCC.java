package com.example.lib;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class SearchSCC {

    ReadFile readfile = new ReadFile();
    ImplicationGraph g = new ImplicationGraph();

    List Literal1;
    List Literal2;
    int variables;
    int clauses;


    public HashMap<Integer, List<Integer>> adj = new HashMap<>();
    public HashMap<Integer, List<Integer>> adjinv = new HashMap<>();

    public boolean [] visited = new boolean[variables];
    public boolean [] visitedinv = new boolean[variables];

    public Stack<Integer> stack = new Stack<>();

    private int [] SCCs;
    private int SCCi;

    public SearchSCC() {
        this.Literal1 = readfile.Literal1;
        this.Literal2 = readfile.Literal2;
        this.variables = readfile.variables;
        this.clauses = readfile.clauses;
        this.adj = g.adj;
        this.adjinv = g.adjinv;
        this.visited = g.visited;
        this.visitedinv = g.visitedinv;
        this.stack = stack;
    }

    public void FirstDFS(int vertex) {
        if (visited[vertex] || adj.get(vertex) == null){return;}

        visited[vertex] = true;//mark it as visited

        for (int i = 0; i < adj.get(vertex).size(); i++){
            FirstDFS(adj.get(vertex).get(i));//continue mark following the edges
        }

        stack.push(vertex);
        //STEP1 Create an empty stack ‘S’
        //push the vertex whose neighbors are all visited to the stack
    }

    public void SecondDFS(int vertex){
        if (visitedinv[vertex] || adjinv.get(vertex) == null){return;}

        visitedinv[vertex] = true;

        for (int i = 0;  i < adjinv.get(vertex).size(); i++){
            SecondDFS(adjinv.get(vertex).get(i));
            SCCs[vertex] = SCCi; //vertex belongs to No.i SCC
        }
    }

    public boolean Satisfiability() {
        //STEP2 Do a DFS traversal of a graph
        for (int i = 1; i<=2*clauses; i++){
            if (!visited[i]){
                FirstDFS(i);
            }
        }

        //Reverse directions of all arcs to obtain the transpose graph and again do a DFS traversal.
        while (stack.size() != 0){
            //One by one pop a vertex from ’S’ while it is not empty.
            // Let the popped vertex be the source to do a DFS.
            // Increase SCCindex after recording the prexious one.
            int top = stack.pop();

            if (!visitedinv[top]) {
                SecondDFS(top);
                SCCi++;
            }
        }

        for (int i=1;i<=variables;i++) {

            if(SCCs[i]==SCCs[i+variables] && SCCs[i] != 0) {
                //if x and not x are on a cycle then the CNF is unsatisfiable
                return false;
            }
        }
        return true;
    }
}
