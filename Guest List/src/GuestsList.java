import java.util.ArrayList;
import java.util.List;

public class GuestsList {
    private int guestsCapacity;
    private final List<Guest> guests = new ArrayList<>();
    private final List<Guest> waitingGuests = new ArrayList<>();

    public GuestsList(int guestsCapacity) {
        this.guestsCapacity = guestsCapacity;
    }

    public int add(Guest g) {
        for (Guest guest : guests) {
            if (g.equals(guest)) {
                return -1;
            }
        }
        if (guestsCapacity == 0) {
            if (!waitingGuests.contains(g)) {
                waitingGuests.add(g);
                System.out.println("[" + g.getLastName() + " " + g.getFirstName() + "] " + "Te-ai inscris cu succes in lista de asteptare si ai primit numarul de ordine " + (waitingGuests.indexOf(g) + 1) + ". Te vom notifica daca un loc devine disponibil.");
                return waitingGuests.indexOf(g) + 1;
            }
            return -1;
        } else {
            guests.add(g);
            System.out.println("[" + g.getLastName() + " " + g.getFirstName() + "] " + "Felicitari! Locul tau la eveniment este confirmat. Te asteptam!");
            guestsCapacity--;
            return 0;
        }
    }

    public boolean isOnTheListAlready(Guest g) {
        for (Guest guest1 : guests) {
            if (g.equals(guest1)) {
                return true;
            }
        }
        for (Guest guest1 : waitingGuests) {
            if (g.equals(guest1)) {
                return true;
            }
        }
        return false;
    }


    public Guest search(String firstName, String lastName) {
        for (Guest guest : guests) {
            if (firstName.equals(guest.getFirstName()) && lastName.equals(guest.getLastName())) {
                //System.out.println(guest);
                return guest;
            }
        }
        for (Guest guest : waitingGuests) {
            if (firstName.equals(guest.getFirstName()) && lastName.equals(guest.getLastName())) {
                //System.out.println(guest);
                return guest;
            }
        }
        return null;
    }

    public Guest search(int opt, String match) {
        for (Guest guest : guests) {
            if (opt == 2) {
                if (match.equals(guest.getEmail())) {
                    //System.out.println(guest);
                    return guest;
                }
            }
            if (opt == 3) {
                if (match.equals(guest.getPhoneNumber())) {
                    //System.out.println(guest);
                    return guest;
                }
            }
        }
        for (Guest guest : waitingGuests) {
            if (opt == 2) {
                if (match.equals(guest.getEmail())) {
                    //System.out.println(guest);
                    return guest;
                }
            }
            if (opt == 3) {
                if (match.equals(guest.getPhoneNumber())) {
                    //System.out.println(guest);
                    return guest;
                }
            }
        }
        return null;
    }

    public boolean remove(String firstName, String lastName) {
        int index = -1;

        for (int i = 0; i < guests.size(); i++) {
            if (firstName.equals(guests.get(i).getFirstName()) && lastName.equals(guests.get(i).getLastName())) {
                index = i;
                break;
            }
        }
        if(index != -1) {
            guests.remove(index);
        }

        //boolean anyRemoved = guests.removeIf(guest -> guest.getFirstName().equals(firstName) && guest.getLastName().equals(lastName));
        Guest firstMatch = guests.stream().filter(guest -> guest.getFirstName().equals(firstName)
                        && guest.getLastName().equals(lastName))
                .findFirst()
                .orElse(null);

        if (firstMatch != null) {
            guests.remove(firstMatch);
            guestsCapacity++;
            if (!waitingGuests.isEmpty()) {
                guests.add(waitingGuests.get(0));
                System.out.println("[" + waitingGuests.get(0).getLastName() + " " + waitingGuests.get(0).getFirstName() + "] " + "Felicitari! Locul tau la eveniment este confirmat. Te asteptam!");
                guestsCapacity--;
                waitingGuests.remove(0);
            }
            return true;
        }

        firstMatch = waitingGuests.stream().filter(guest -> guest.getFirstName().equals(firstName)
                        && guest.getLastName().equals(lastName))
                .findFirst()
                .orElse(null);
        if (firstMatch != null) {
            waitingGuests.remove(firstMatch);
            return true;
        }

        return false;
    }

    public boolean remove(int opt, String match) {
        if (opt == 2){
            //boolaen anyRemoved = guests.removeIf(guest -> guest.getEmail().equals(match));
            Guest firstMatch = guests.stream().filter(guest -> guest.getEmail().equals(match))
                    .findFirst()
                    .orElse(null);
            if (firstMatch != null) {
                guests.remove(firstMatch);
                guestsCapacity++;
                if (!waitingGuests.isEmpty()) {
                    guests.add(waitingGuests.get(0));
                    System.out.println("[" + waitingGuests.get(0).getLastName() + " " + waitingGuests.get(0).getFirstName() + "] " + "Felicitari! Locul tau la eveniment este confirmat. Te asteptam!");
                    guestsCapacity--;
                    waitingGuests.remove(0);
                }
                return true;
            }

            firstMatch = waitingGuests.stream().filter(guest -> guest.getEmail().equals(match))
                    .findFirst()
                    .orElse(null);
            if (firstMatch != null) {
                waitingGuests.remove(firstMatch);
                return true;
            }
        }
        if (opt == 3) {
            Guest firstMatch = guests.stream().filter(guest -> guest.getPhoneNumber().equals(match))
                    .findFirst()
                    .orElse(null);
            if (firstMatch != null) {
                guests.remove(firstMatch);
                guestsCapacity++;
                if (!waitingGuests.isEmpty()) {
                    guests.add(waitingGuests.get(0));
                    guestsCapacity--;
                    System.out.println("[" + waitingGuests.get(0).getLastName() + " " + waitingGuests.get(0).getFirstName() + "] " + "Felicitari! Locul tau la eveniment este confirmat. Te asteptam!");
                    waitingGuests.remove(0);
                }
                return true;
            }

            firstMatch = waitingGuests.stream().filter(guest -> guest.getPhoneNumber().equals(match))
                    .findFirst()
                    .orElse(null);
            if (firstMatch != null) {
                waitingGuests.remove(firstMatch);
                return true;
            }
        }

        return false;
    }

    // Show the list of guests.
    public void showGuestsList() {
        if(guests.isEmpty()){
            System.out.println("Nothing found");
        }
        for (Guest guest : guests) {
            System.out.println((guests.indexOf(guest) + 1) + ". " + guest.toString());
        }
    }

    // Show the people on the waiting list.
    public void showWaitingList() {
        if (waitingGuests.isEmpty()) {
            System.out.println("Lista de asteptare este goala...");
        }
        for (Guest guest : waitingGuests) {
            System.out.println((1 + waitingGuests.indexOf(guest)) + ". " + guest.toString());
        }
    }

    public int numberOfAvailableSpots() {
        return guestsCapacity;
    }

    public int numberOfGuests() {
        int count = 0;
        for (Guest ignored : guests) {
            count++;
        }
        return count;
    }

    public int numberOfPeopleWaiting() {
        int count = 0;
        for (Guest ignored : waitingGuests) {
            count++;
        }
        return count;
    }

    public int numberOfPeopleTotal() {
        return numberOfPeopleWaiting() + numberOfGuests();
    }

    public List<Guest> partialSearch(String match) {
        List<Guest> list = new ArrayList<>();
        String lowerMatch = match.toLowerCase();
        for (Guest guest : guests) {
            if (guest.getFullName().toLowerCase().contains(lowerMatch) ||
                    guest.getEmail().toLowerCase().contains(lowerMatch) ||
                    guest.getPhoneNumber().contains(lowerMatch)) {
                list.add(guest);
            }
        }
        for (Guest guest : waitingGuests) {
            if (guest.getFullName().toLowerCase().contains(lowerMatch) ||
                    guest.getEmail().toLowerCase().contains(lowerMatch) ||
                    guest.getPhoneNumber().contains(lowerMatch)) {
                list.add(guest);
            }
        }
        return list;
    }

    @Override
    public String toString() {
        return "GuestsList{" +
                "guestsCapacity=" + guestsCapacity +
                ", guests=" + guests +
                ", waitingGuests=" + waitingGuests +
                '}';
    }
}
