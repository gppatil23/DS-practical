import java.util.*;

class Bully {

    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);

        // Number of processes
        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        int processes[] = new int[n];

        // Input Process IDs
        for (int i = 0; i < n; i++) {
            System.out.print("Enter Process ID: ");
            processes[i] = sc.nextInt();
        }

        // Process initiating election
        System.out.print("Enter process initiating election: ");
        int initiator = sc.nextInt();

        int coordinator = initiator;

        System.out.println("\n Election started...");

        // Bully algorithm: higher ID processes respond
        for (int i = 0; i < n; i++) {
            if (processes[i] > coordinator) {
                System.out.println(
                    "Process " + initiator +
                    " sends ELECTION message to process " +
                    processes[i]
                );

                // The higher process becomes the new coordinator
                coordinator = processes[i];
            }
        }

        System.out.println("\n New Coordinator is process: " + coordinator);

        sc.close();
    }
}
