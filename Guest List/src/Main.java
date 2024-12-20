import java.util.*;
class Main {
    private static void showCommands() {
        System.out.println("help         - Afiseaza aceasta lista de comenzi");
        System.out.println("add          - Adauga o noua persoana (inscriere)");
        System.out.println("check        - Verifica daca o persoana este inscrisa la eveniment");
        System.out.println("remove       - Sterge o persoana existenta din lista");
        System.out.println("update       - Actualizeaza detaliile unei persoane");
        System.out.println("guests       - Lista de persoane care participa la eveniment");
        System.out.println("waitlist     - Persoanele din lista de asteptare");
        System.out.println("available    - Numarul de locuri libere");
        System.out.println("guests_no    - Numarul de persoane care participa la eveniment");
        System.out.println("waitlist_no  - Numarul de persoane din lista de asteptare");
        System.out.println("subscribe_no - Numarul total de persoane inscrise");
        System.out.println("search       - Cauta toti invitatii conform sirului de caractere introdus");
        System.out.println("save         - Salveaza lista cu invitati");
        System.out.println("restore      - Completeaza lista cu informatii salvate anterior");
        System.out.println("reset        - Sterge informatiile salvate despre invitati");
        System.out.println("quit         - Inchide aplicatia");
    }

    private static void addNewGuest(Scanner sc, GuestsList list) {
        list.add(new Guest(sc.nextLine(), sc.nextLine(), sc.nextLine(), sc.nextLine()));
    }

    private static void checkGuest(Scanner sc, GuestsList list) {
        int opt = Integer.parseInt(sc.nextLine());
        Guest found = null;
        if (opt == 1) {
            String lastName = sc.nextLine();
            String firstName = sc.nextLine();
            found = list.search(firstName, lastName);
        }
        if (opt == 2) {
            String email = sc.nextLine();
            found = list.search(2, email);
        }

        if (opt == 3) {
            String phoneNumber = sc.nextLine();
            found = list.search(3, phoneNumber);
        }

        if (found != null) {
            System.out.println(found);
        }
    }

    private static void removeGuest(Scanner sc, GuestsList list) {
        int opt = Integer.parseInt(sc.nextLine());
        if (opt == 1) {
            String lastName = sc.nextLine();
            String firstName = sc.nextLine();
            list.remove(firstName, lastName);
        }
        if (opt == 2) {
            String email = sc.nextLine();
            list.remove(2, email);
        }

        if (opt == 3) {
            String phoneNumber = sc.nextLine();
            list.remove(3, phoneNumber);
        }
    }

    private static void updateGuest(Scanner sc, GuestsList list) {
        int opt = Integer.parseInt(sc.nextLine());
        if (opt == 1) {
            String lastName = sc.nextLine();
            String firstName = sc.nextLine();
            Guest guest = list.search(firstName, lastName);
            setting(sc, guest);
        }
        if (opt == 2) {
            String email = sc.nextLine();
            Guest guest = list.search(2, email);
            setting(sc, guest);
        }
        if (opt == 3) {
            String phoneNumber = sc.nextLine();
            Guest guest = list.search(3, phoneNumber);
            setting(sc, guest);
        }
    }

    private static void setting(Scanner sc, Guest guest) {
        int id = Integer.parseInt(sc.nextLine());
        if (id == 1){
            guest.setLastName(sc.nextLine());
        }
        if (id == 2){
            guest.setFirstName(sc.nextLine());
        }
        if (id == 3){
            guest.setEmail(sc.nextLine());
        }
        if (id == 4){
            guest.setPhoneNumber(sc.nextLine());
        }
    }


    private static void searchList(Scanner sc, GuestsList list) {
        String match = sc.nextLine();
        List<Guest> result = list.partialSearch(match);
        if (result.isEmpty()) {
            System.out.println("Nothing found");
        } else {
            for (Guest guest : result) {
                //System.out.println((i + 1) + ". " + result.get(i));
                System.out.println(guest);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        GuestsList list = new GuestsList(size);
        scanner.nextLine();

        boolean running = true;
        while (running) {
            String command = scanner.nextLine();

            switch (command) {
                case "help":
                    showCommands();
                    break;
                case "add":
                    addNewGuest(scanner, list);
                    break;
                case "check":
                    checkGuest(scanner, list);
                    break;
                case "remove":
                    removeGuest(scanner, list);
                    break;
                case "update":
                    updateGuest(scanner, list);
                    break;
                case "guests":
                    list.showGuestsList();
                    break;
                case "waitlist":
                    list.showWaitingList();
                    break;
                case "available":
                    System.out.println("Numarul de locuri ramase: " + list.numberOfAvailableSpots());
                    break;
                case "guests_no":
                    System.out.println("Numarul de participanti: " + list.numberOfGuests());
                    break;
                case "waitlist_no":
                    System.out.println("Dimensiunea listei de asteptare: " + list.numberOfPeopleWaiting());
                    break;
                case "subscribe_no":
                    System.out.println("Numarul total de persoane: " + list.numberOfPeopleTotal());
                    break;
                case "search":
                    searchList(scanner, list);
                    break;
                case "quit":
                    System.out.println("Aplicatia se inchide...");
                    scanner.close();
                    running = false;
                    break;
                default:
                    System.out.println("Comanda introdusa nu este valida.");
                    System.out.println("Incercati inca o data.");
            }
        }
    }
}
