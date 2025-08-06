import java.util.*;
public class TicketBooker {
    static int availLowerBerths = 5;
    static int availMiddleBerths = 5;
    static int availUpperBerths = 5;
    static int availRacTickets = 2;
    static int availWaitingList = 1;

    static Queue<Integer> waitingList = new LinkedList<>();
    static Queue<Integer> racList  = new LinkedList<>();
    static List<Integer> bookedTicketList = new ArrayList<>();

    static List<Integer> lowerBerthsPos = new ArrayList<>(Arrays.asList(1,2,3,4,5));
    static List<Integer> upperBerthsPos = new ArrayList<>(Arrays.asList(1,2,3,4,5));
    static List<Integer> middleBerthsPos = new ArrayList<>(Arrays.asList(1,2,3,4,5));
    static List<Integer> racPos = new ArrayList<>(Arrays.asList(1,2));
    static List<Integer> waitingListPos = new ArrayList<>(Arrays.asList(1));

    static Map<Integer,Passanger> passengers = new HashMap<>();

    public void bookTicket(Passanger p , int berthInfo, String allotedBerth){
        p.number = berthInfo;
        p.alloted = allotedBerth;
        passengers.put(p.passangerId,p);
        bookedTicketList.add(p.passangerId);
        System.out.println("-------------------------------------Booked Successfully");
    }

    public void addToRac(Passanger p , int racInfo, String allotedRac){
        p.number = racInfo;
        p.alloted = allotedRac;
        passengers.put(p.passangerId, p);
        racList.add(p.passangerId);
        availRacTickets--;
        racPos.remove(0);
        System.out.println("-------------------------------------added to Rac Successfully");
    }

    public void addToWaitingList(Passanger p , int waitingListInfo, String allotedWl){
        p.number = waitingListInfo;
        p.alloted  = allotedWl;
        passengers.put(p.passangerId,p);
        waitingList.add(p.passangerId);
        availWaitingList--;
        waitingListPos.remove(0);
        System.out.println("------------------------------------added to waiting List successfully");

    }

    public void cancelTicket(int passangerId){
        Passanger p = passengers.get(passangerId);
        passengers.remove(Integer.valueOf(passangerId));
        bookedTicketList.remove(Integer.valueOf(passangerId));
        int posBooked = p.number;
        System.out.println("------------------------------------Cancelled Successfully");

        if(p.alloted.equals("L")){
            availLowerBerths++;
            lowerBerthsPos.add(posBooked);
        }

        else if(p.alloted.equals("M")){
            availMiddleBerths++;
            middleBerthsPos.add(posBooked);
        }

        else if(p.alloted.equals("U")){
            availUpperBerths++;
            upperBerthsPos.add(posBooked);
        }

        if(racList.size() > 0){
            Passanger passangerFromRac =  passengers.get(racList.poll());
            int positionRac = passangerFromRac.number;
            racPos.add(positionRac);
            racList.remove(Integer.valueOf(passangerFromRac.passangerId));
            availRacTickets++;

            if(waitingList.size() > 0){

                Passanger passangerFromWaitingList = passengers.get(waitingList.poll());
                int posWl = passangerFromWaitingList.number;
                waitingListPos.add(posWl);
                waitingList.remove(Integer.valueOf(passangerFromWaitingList.passangerId));
                
                passangerFromWaitingList.number = racPos.get(0);
                passangerFromWaitingList.alloted = "RAC";
                racPos.remove(0);
                racList.add(passangerFromWaitingList.passangerId);

                availWaitingList++;
                availRacTickets--;
            }
            Main.bookTicket(passangerFromRac);
        }
    }

    public void printAvailable(){
        System.out.println("Available Lower Berths " + availLowerBerths);
        System.out.println("Available Middle Berths " + availMiddleBerths);
        System.out.println("Available Upper Berths " + availUpperBerths);
        System.out.println("Available RACs " + availRacTickets);
        System.out.println("Available Waiting list " + availWaitingList);
    }

    public void printPassangers(){
        if(passengers.size() == 0){
            System.out.println("No details of passanger");
            return ;
        }

        for(Passanger p : passengers.values()){
            System.out.println(" Passanger Id " + p.passangerId);
            System.out.println(" Name " + p.name);
            System.out.println(" Age " + p.age);
            System.out.println(" Status " + p.number + p. alloted);
            System.out.println("---------------------------------");
        }
    }


}
