import java.util.*;

class Process {
    int id;
    boolean active;

    Process(int id) {
        this.id = id;
        this.active = true; // process is initially active
    }
}

class Ring {
    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        // Create array of processes
        Process p[] = new Process[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter Process ID: ");
            int id = sc.nextInt();
            p[i] = new Process(id);
        }

        // Sort processes by ID (ascending)
        Arrays.sort(p, Comparator.comparingInt(a -> a.id));

        // Simulate coordinator failure: last process becomes inactive
        p[n - 1].active = false;

        int ch;

        do {
            System.out.println("\n 1. Election");
            System.out.println("2. Exit");
            System.out.print("Enter choice: ");
            ch = sc.nextInt();

            switch (ch) {

                case 1:
                    System.out.print("Enter initiator process index (0-based): ");
                    int init = sc.nextInt();

                    List<Integer> list = new ArrayList<>();
                    int i = init;

                    // Ring Election Algorithm: message passes around the ring
                    do {
                        if (p[i].active) {
                            System.out.println(
                                "Process " + p[i].id + " passes election message"
                            );
                            list.add(p[i].id);
                        }
                        i = (i + 1) % n; // move to next process in the ring
                    } while (i != init); // until message comes back to initiator

                    // Highest ID in active processes becomes coordinator
                    int leader = Collections.max(list);
                    System.out.println("\n Process " + leader + " becomes COORDINATOR");
                    break;

                case 2:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice");
            }

        } while (ch != 2);

        sc.close();
    }
}
