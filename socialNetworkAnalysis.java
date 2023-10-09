import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import socialnetworkanalysis.SimpleGraph;

public class SocialNetworkAnalysis {
    public static void main(String[] args) {
        Graph<String, DefaultEdge> socialNetwork = new SimpleGraph<>(DefaultEdge.class);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nOptions:");
            System.out.println("1. Add User");
            System.out.println("2. Add Connection");
            System.out.println("3. Find Mutual Friends");
            System.out.println("4. Quit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter User Name: ");
                    String user = scanner.nextLine();
                    socialNetwork.addVertex(user);
                    System.out.println("User added: " + user);
                    break;
                case 2:
                    System.out.print("Enter User 1: ");
                    String user1 = scanner.nextLine();
                    System.out.print("Enter User 2: ");
                    String user2 = scanner.nextLine();
                    if (socialNetwork.containsVertex(user1) && socialNetwork.containsVertex(user2)) {
                        socialNetwork.addEdge(user1, user2);
                        System.out.println("Connection established between " + user1 + " and " + user2);
                    } else {
                        System.out.println("One or both users do not exist.");
                    }
                    break;
                case 3:
                    System.out.print("Enter User 1: ");
                    user1 = scanner.nextLine();
                    System.out.print("Enter User 2: ");
                    user2 = scanner.nextLine();
                    if (socialNetwork.containsVertex(user1) && socialNetwork.containsVertex(user2)) {
                        List<String> mutualFriends = findMutualFriends(socialNetwork, user1, user2);
                        System.out.println("Mutual Friends: " + mutualFriends);
                    } else {
                        System.out.println("One or both users do not exist.");
                    }
                    break;
                case 4:
                    scanner.close();
                    System.out.println("Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static List<String> findMutualFriends(Graph<String, DefaultEdge> graph, String user1, String user2) {
        List<String> friendsOfUser1 = Graphs.neighborListOf(graph, user1);
        List<String> friendsOfUser2 = Graphs.neighborListOf(graph, user2);
        List<String> mutualFriends = new ArrayList<>(friendsOfUser1);
        mutualFriends.retainAll(friendsOfUser2);
        return mutualFriends;
    }
}
