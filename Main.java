import java.util.*;
public class Main {

    public static void bookTicket(Passanger p){                    // To book ticket
        TicketBooker booker = new TicketBooker();

        if(TicketBooker.availWaitingList == 0){                    //  if waiting list is already full just tell no ticket available
            System.out.print("No ticket available");
            return;
        }

        if((p.berthPref.equals("L") && TicketBooker.availLowerBerths > 0) ||          
            (p.berthPref.equals("M") && TicketBooker.availMiddleBerths > 0) ||
            (p.berthPref.equals("U") && TicketBooker.availUpperBerths > 0))
            {                                                                                     // checking for choosen berth
                System.out.println("Preferred Berth Available");
                
                if(p.berthPref.equals("L")){
                    System.out.println("Lower Berth Given");

                    booker.bookTicket(p,(TicketBooker.lowerBerthsPos.get(0)),"L");

                    TicketBooker.lowerBerthsPos.remove(0);
                    TicketBooker.availLowerBerths--;
                }

                else if(p.berthPref.equals("M")){
                    System.out.println("Middle Berth Given");

                    booker.bookTicket(p,(TicketBooker.middleBerthsPos.get(0)),"L");

                    TicketBooker.middleBerthsPos.remove(0);
                    TicketBooker.availMiddleBerths--;
                }

                else if(p.berthPref.equals("U")){
                    System.out.println("Upper Berth Given");

                    booker.bookTicket(p,(TicketBooker.upperBerthsPos.get(0)),"L");

                    TicketBooker.upperBerthsPos.remove(0);
                    TicketBooker.availUpperBerths--;
                }
            } 
            else if(TicketBooker.availLowerBerths > 0){                                              // if ticket is not available for prefered berth
                System.out.println("Lower Berth Given");
                booker.bookTicket(p,(TicketBooker.lowerBerthsPos.get(0)),"L");

                TicketBooker.lowerBerthsPos.remove(0);
                TicketBooker.availLowerBerths--;
            }

            else if(TicketBooker.availMiddleBerths > 0){
                System.out.println("Middle Berth Given");
                booker.bookTicket(p,(TicketBooker.middleBerthsPos.get(0)),"L");

                TicketBooker.middleBerthsPos.remove(0);
                TicketBooker.availMiddleBerths--;
            }

            else if(TicketBooker.availUpperBerths > 0){
                System.out.println("Upper Berth Given");
                booker.bookTicket(p,(TicketBooker.upperBerthsPos.get(0)),"L");

                TicketBooker.upperBerthsPos.remove(0);
                TicketBooker.availUpperBerths--;
            }

            else if(TicketBooker.availRacTickets > 0){                                                //  if the ticket is full in  L,U,M then book in rac
                System.out.println("Rac available");
                booker.addToRac(p,(TicketBooker.racPos.get(0)),"RAC");
            }

            else if(TicketBooker.availWaitingList > 0){                                                // if there is no ticket then goes to waiting list
                System.out.println("Added to waiting List");
                booker.addToWaitingList(p,(TicketBooker.waitingListPos.get(0)),"WL");
            }
    }

    public static void cancelTicket(int id){
        TicketBooker booker = new TicketBooker();                     // cancel the ticket

        if(!booker.passengers.containsKey(id)){
            System.out.println("Passanger detail unknown");
        }

        else{
            booker.cancelTicket(id);
        }
    }


    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
        boolean loop = true;

        while(loop){
            System.out.println(" 1. Book Ticket \n 2. Cancel Ticket \n 3. Available Ticket \n 4. Booked Tickets \n 5. Exit ");
            int choice = sc.nextInt();

            switch(choice){

                case 1 :{
                    System.out.println("Enter Passanger name,age and berth preference (L,M or U)");
                    String name = sc.next();
                    sc.nextLine();
                    int age = sc.nextInt();
                    sc.nextLine();
                    String berthPref = sc.next();

                    Passanger p = new Passanger(name,age,berthPref);

                    bookTicket(p);
                }
                break;

                case 2 :{

                    System.out.println("Enter passenger Id to cancel");
                    int id = sc.nextInt();
                    cancelTicket(id);
                }
                break;

                case 3:{
                    TicketBooker booker = new TicketBooker();
                    booker.printAvailable();
                }
                break;

                case 4:{
                    TicketBooker booker = new TicketBooker();
                    booker.printPassangers();
                }
                break;

                case 5:{
                    loop = false;
                }
                break;

                default :
                    break;
            }
        }
    }
}
