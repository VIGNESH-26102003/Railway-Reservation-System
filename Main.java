package RESERVATION;

import java.util.*;

public class Main {

    //functions for booking ticket
    public static void bookTicket(Passenger p)
    {
        Ticketbooker booker=new Ticketbooker();

        if(Ticketbooker.availableWaitingList==0)
        {
            System.out.println("Sorry!..No Tickets Available");
            return;
        }
        if((p.berthPreference.equals("L") && Ticketbooker.availableLowerBerths > 0)||
        (p.berthPreference.equals("M") && Ticketbooker.availableMiddleBerths > 0)||
        (p.berthPreference.equals("U") && Ticketbooker.availableUpperBerths >0))
        {
            System.out.println("Prefered Berth Available");
            if(p.berthPreference.equals("U")){
                System.out.println("Upper Bearth given");

                //call booking function in the TicketBooker class
                booker.bookTicket(p,(Ticketbooker.upperBreathPositions.get(0)),"L");
                //remove the booked function in the TicketBooker class
                //particular type

                Ticketbooker.upperBreathPositions.remove(0);
                Ticketbooker.availableUpperBerths--;
            }
            else if(p.berthPreference.equals("M"))
            {
                System.out.println("Middle Berth Given");

                //call booking function in the TicketBookeer class
                booker.bookTicket(p,Ticketbooker.middleBreathPositions.get(0) , "M");
                
                //remove the booked position from available positions and also decrease available seats of that
                //particular type

                Ticketbooker.middleBreathPositions.remove(0);
                Ticketbooker.availableMiddleBerths--;



            }
            else if(p.berthPreference.equals("L")){
                System.out.println("Lower Bearth given");

                booker.bookTicket(p,Ticketbooker.lowerBreathPositions.get(0), "L");

                Ticketbooker.middleBreathPositions.remove(0);
                Ticketbooker.availableLowerBerths--;

            }

            //if no berth available go to RAC
            else if(Ticketbooker.availableRacTickets >0)
        {
            System.out.println("RAC available ");
            booker.addTORAC(p,(Ticketbooker.racPositions.get(0)),"RAC");

        }
        // if no RAC available go to RAC  
        else if(Ticketbooker.availableWaitingList > 0) {
            System.out.println("RAC available");
            booker.addTOWaitingList(p,Ticketbooker.waitingListPositions.get(0) , "WL");
        }
    }
    }

    //cancel ticket function
    public static void cancelTicket(int id)
    {
        Ticketbooker booker =new Ticketbooker();

        //check if passenger id is valid
        if(!booker.passengers.containsKey(id))
        {
            System.out.println("Passenger detail unknown");

        }
        else
        booker.cancelTicket(id);
            


    }
    public void main(String[] args)
    {
        Scanner s= new Scanner(System.in);
        boolean loop = true;

        //loop to get choices from user until he stops 
        while (loop) {
            System.out.println("1. Book Ticket \n 2.Cancel Ticket \n3. Available Tickets \n4. Booked Tickets \n5.Exit");
            int choice = s.nextInt();
            switch(choice)
            {

                //book ticket
                case 1:
                {
                    //get details from Passenger 
                    System.out.println("Enter passenger name,age and berth preference (L,M or U)");
                    String name = s.next();
                    int age = s.nextInt();

                    //get berth preference (L,U,M)
                    String berthPreference = s.next();

                    //create a passenger obj

                    Passenger p=new Passenger(name, age, berthPreference);

                    //booking
                    bookTicket(p);
                }
                break;

                case 2:
                {
                    //get passenger id to cancel
                    System.out.println("Enter Passenger ID to cancel");
                    int id=s.nextInt();
                    cancelTicket(id);
                }

                //available tickets print

                case 3:
                {
                    Ticketbooker booker=new Ticketbooker();
                    booker.printAvailable();
                }
                break;

                //occupied tickets print
                case 4:
                {
                    Ticketbooker booker= new Ticketbooker();
                    booker.printPassengers();
                }
                break;

                case 5:
                {
                    loop = false;
                }
                break;
               
                
                
            }
        }
    
    

}
}




    


    



        
        

    
    

