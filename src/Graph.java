import CustomExceptions.ArreteExisteDejaException;
import CustomExceptions.ArreteInexistanteException;
import CustomExceptions.SommetExisteDejaException;
import CustomExceptions.SommetInexistantException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Graph {
    private ArrayList<Node> nodes = new ArrayList<>();

    public int getNumberOfNodes() {
        return nodes.size();
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public void addNode(String label) throws SommetExisteDejaException {
        if (this.hasNode(label)) {
            throw new SommetExisteDejaException();
        }

        Node newNode = new Node(label);
        nodes.add(newNode);
        System.out.println("Sommet " + label + " ajouté !");
    }

    public void removeNode(String label) throws SommetInexistantException, ArreteInexistanteException {
        Node n = getNode(label);
        if (n == null) {
            throw new SommetInexistantException();
        }

        nodes.remove(n);
        for (Node node : nodes) {
            node.removeAdjacent(n);
        }
    }

    public void addEdge(String node1Label, String node2Label)
            throws SommetInexistantException, ArreteExisteDejaException {

        Node node1 = getNode(node1Label);
        if (node1 == null) {
            throw new SommetInexistantException("Le sommet " + node1Label + " n'existe pas");
        }
        Node node2 = getNode(node2Label);
        if (node2 == null) {
            throw new SommetInexistantException("Le sommet " + node2Label + " n'existe pas");
        }

        node1.addAdjacent(node2);
        node2.addAdjacent(node1);

    }

    public void removeEdge(String node1Label, String node2Label)
            throws SommetInexistantException, ArreteInexistanteException {

        Node node1 = getNode(node1Label);
        if (node1 == null) {
            throw new SommetInexistantException("Le sommet " + node1Label + " n'existe pas");
        }
        Node node2 = getNode(node2Label);
        if (node2 == null) {
            throw new SommetInexistantException("Le sommet " + node2Label + " n'existe pas");
        }

        node1.removeAdjacent(node2);
        node2.removeAdjacent(node1);

    }

    private boolean hasNode(String label) {
        boolean result = false;
        for (Node n : nodes) {
            if (n.getLabel().equals(label)) {
                result = true;
                break;
            }
        }
        return result;
    }

    private Node getNode(String label) {
        for (Node n : nodes) {
            if (n.getLabel().equals(label)) {
                return n;
            }
        }
        return null;
    }

    public int getNodesCount() {
        return nodes.size();
    }

    public int bfs(String skipNode, String srartingNode) throws Exception {
        if(skipNode.equals(srartingNode)){
            throw new Exception("Le noeud de depart ne peut etre le noeud a ignorer");
        }

        ArrayList<Node> visitedNodes = new ArrayList<Node>();

        Queue<Node> queue = new LinkedList<Node>();

        visitedNodes.add(this.getNode(srartingNode));
        queue.add(this.getNode(srartingNode));

        int numberOfVisitedNodes = 0;

        while (!queue.isEmpty()) {
            Node currentNode = queue.remove();
            numberOfVisitedNodes++;

            for (Node adjacent : currentNode.getAdjacents()) {
                if (!visitedNodes.contains(adjacent) && !adjacent.getLabel().equals(skipNode)) {
                    visitedNodes.add(adjacent);
                    queue.add(adjacent);
                }
            }
        }

        return numberOfVisitedNodes;

    }

    public void showEdges() {

        ArrayList<Node> visitedNodes = new ArrayList<Node>();

        Queue<Node> queue = new LinkedList<Node>();
//        queue.add(nodes.get(0));

        int numberOfVisitedNodes = 0;
        while (numberOfVisitedNodes!= nodes.size()) {
            for (Node node : nodes){
                if(!visitedNodes.contains(node)){
                    queue.add(node);
                }
            }

            while (!queue.isEmpty()) {
                Node currentNode = queue.remove();

                visitedNodes.add(currentNode);

                numberOfVisitedNodes++;

                for (Node adjacent : currentNode.getAdjacents()) {
                    if (!visitedNodes.contains(adjacent)) {
                        System.out.print(currentNode.getLabel() + "----" + adjacent.getLabel() + ", ");
                        if (!queue.contains(adjacent))
                            queue.add(adjacent);
                    }
                }
            }
        }
        System.out.println();
    }
    public void findArticulationPoints() throws Exception {
        for (Node n : nodes) {
            if (n.getDegree() < 2)
                continue;

            int numberOfVisitedNodesBefore = bfs("", n.getAdjacents().get(0).getLabel());
            int numberOfVisitedNodesAfter = bfs(n.getLabel(), n.getAdjacents().get(0).getLabel());

            if (numberOfVisitedNodesAfter < numberOfVisitedNodesBefore - 1)
                System.out.println(n.getLabel() + " est un point d'articulation");
        }
    }

}
