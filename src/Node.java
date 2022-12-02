import CustomExceptions.ArreteExisteDejaException;
import CustomExceptions.ArreteInexistanteException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Node {
    private String label;
    private int degree;
    private final ArrayList<Node> adjacents;

    public Node(String label) {
        this.label = label;
        this.degree = 0;
        this.adjacents = new ArrayList<>();
    }

    public String getLabel() {
        return label;
    }

    public int getDegree() {
        return degree;
    }

    public ArrayList<Node> getAdjacents(){
        return adjacents;
    }

    public boolean isAdjacentTo(Node n) {
        return this.adjacents.contains(n);
    }

    public void addAdjacent(Node n) throws ArreteExisteDejaException {
        if (this.isAdjacentTo(n)) {
            throw new ArreteExisteDejaException();
        } else {
            this.adjacents.add(n);
            this.degree++;
        }
    }

    public void removeAdjacent(Node n){
        if (this.isAdjacentTo(n)) {
            this.adjacents.remove(n);
            this.degree--;
        }
    }
}
