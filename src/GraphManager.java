import java.util.ArrayList;

public class GraphManager {

    private static GraphManager instance;
    private Graph graph;

    private static synchronized GraphManager initializeInstance() {
        if (instance == null) {
            instance = new GraphManager();
            instance.graph = new Graph();
        }
        return instance;
    }

    public static GraphManager getGraphManager() {
        if (instance == null) {
            instance = initializeInstance();
        }
        return instance;
    }

    public void showNodesHandler() {
        if (graph.getNodesCount() == 0) {
            System.out.println("Le graphe est vide");
            return;
        }

        System.out.println("Liste des sommets :");
        for (Node n : graph.getNodes()) {
            System.out.print(n.getLabel() + " ");
        }
        System.out.println("");
    }

    public void showEdgesHandler() {
        if (graph.getNodesCount() == 0) {
            return;
        }
        System.out.println("Liste des arretes :");
        graph.showEdges();
    }

    public void addNodeHandler() {
        System.out.println("Nom du sommet (label) : ");
        String label = ScannerClass.getScanner().nextLine();
        try {
            graph.addNode(label);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeNodeHandler(){
        System.out.println("Nom du sommet (label) : ");
        String label = ScannerClass.getScanner().nextLine();
        try {
            graph.removeNode(label);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void addEdgeHandler() {
        System.out.println("Nom du premier sommet : ");
        String label1 = ScannerClass.getScanner().nextLine();

        System.out.println("Nom du deuxieme sommet : ");
        String label2 = ScannerClass.getScanner().nextLine();

        try {
            graph.addEdge(label1, label2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeEdgeHandler() {
        System.out.println("Nom du premier sommet : ");
        String label1 = ScannerClass.getScanner().nextLine();

        System.out.println("Nom du deuxieme sommet : ");
        String label2 = ScannerClass.getScanner().nextLine();

        try {
            graph.removeEdge(label1, label2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void findArticulationPointsHandler() throws Exception {
        graph.findArticulationPoints();
    }

}
