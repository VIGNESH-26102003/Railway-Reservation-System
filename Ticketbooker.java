package RESERVATION;

import java.util.*;

public class Ticketbooker {

    //63 berths (lower, upper, middle) + (18 RAC)
    //10 waiting list
    //63>-21u,21L,21M,18RAC,10WL
    static int availableUpperBerths =21;
    static int availableMiddleBerths =21;
    static int availableLowerBerths =21;
    static int availableRacTickets =18;
    static int availableWaitingList =10;

    static Queue<Integer> waitingList = new LinkedList<>(); //queue of wl
    static Queue<Integer> racList = new LinkedList<>(); //queue of rac;
    static List<Integer> bookedTicketList = new ArrayList<>(); //queue of booked passengerlist
    
    static List<Integer> lowerBreathPositions = new ArrayList<>(Arrays.asList(1));
    static List<Integer> middleBreathPositions = new ArrayList<>(Arrays.asList(1));
    static List<Integer> upperBreathPositions = new ArrayList<>(Arrays.asList(1));
    static List<Integer> racPositions = new ArrayList<>(Arrays.asList(1));
    static List<Integer> waitingListPositions = new ArrayList<>(Arrays.asList(1));

    static Map<Integer, Passenger> passengers = new HashMap<>(); //map of passenger ids


    //book ticket
    public void bookTicket(Passenger p, int berthInfo, String allotedBerth)
    {
        p.number = berthInfo;
        p.alloted = allotedBerth;

        passengers.put(p.passengerId,p);
        //add passenger id to the list of booked tickets
        bookedTicketList.add(p.passengerId);
        System.out.println("-----------------------------BOOKED SUCCESSFULLY");
    }
   
    //adding to RAC
    public void addTORAC(Passenger p,int racInfo,String allotedRAC)
    {
        //assign seat number and type(RAC)
        p.number=racInfo;
        p.alloted=allotedRAC;
    
       //add passenger to the map
        passengers.put(p.passengerId, p);

        //add passenger to the RAC
        racList.add(p.passengerId);
        //decrease available RAC tickets by 1
        availableRacTickets--;

        //remove the position that was alloted to the passenger
        racPositions.remove(0);

        System.out.println("added to RAC Successfully");


    }

    //adding to WL
    public void addTOWaitingList(Passenger p,int waitingListInfo,String allotedWL)
    {
        p.number= waitingListInfo;
        p.alloted= allotedWL;
        
        //add passenger to map
        passengers.put(p.passengerId, p);

        //add passenger to WL
        waitingList.add(p.passengerId);

        availableWaitingList--;

        waitingListPositions.remove(0);

        System.out.println("added to Waiting List Successfully");




    }
    //cancel ticket
    public void cancelTicket(int passengerId){

        //Remove the passenger from the map
        Passenger p = passengers.get(passengerId);
        passengers.remove(Integer.valueOf(passengerId));

        //remove the booked ticket from the list
        bookedTicketList.remove(Integer.valueOf(passengerId));

       //take the booked position which is now free
        int positionBooked = p.number;

        System.out.println("------------cancelled Successfully");

        //add the free position to the corresponding type of list (either L,M,U)
        if(p.alloted.equals("L"))
        {
            availableLowerBerths++;
            lowerBreathPositions.add(positionBooked);
        }
        else if(p.alloted.equals("M"))
        {
            availableMiddleBerths++;
            middleBreathPositions.add(positionBooked);

        }
        else if(p.alloted.equals("U")){
            availableUpperBerths++;
            upperBreathPositions.add(positionBooked);

        }

        //check if any RAC is there
        if(racList.size()>0){

            //take the passenger from RAC and increase free space in RAC and increase available tickets
            Passenger passengerFromRAC = passengers.get(waitingList.poll());
            int positionRac = passengerFromRAC.number;
            racPositions.add(positionRac);
            racList.remove(Integer.valueOf(passengerFromRAC.passengerId));
            
            //check if any WL is there

            //take the passenger from WL and add them to RAC, increase the free space in waiting list
            //increase available WL and decrease available RAC by 1
            if(waitingList.size()>0){
           Passenger passengerFromWaitingList.number = passengers.get(waitingList.poll());
           int positionWL = passengerFromWaitingList.number;
           waitingListPositions.add(positionWL);
           waitingList.remove(Integer.valueOf(passengerFromWaitingList.passengerId));

           passengerFromWaitingList.number = racPositions.get(0);
           passengerFromWaitingList.alloted = "RAC";
           racPositions.remove(0);
           racList.add(passengerFromWaitingList.passengerId);
            

            availableWaitingList++;
            availableRacTickets--;
        }

        //now we have a passenger from RAC to whom we can book a ticket,
        //so book the cancelled ticket to the  RAC passenger
        Main.bookTicket(passengerFromRAC);
    }
}

//print all available seats
public void printAvailable()
{
    System.out.println("Available Lower Berths " + availableLowerBerths);
    System.out.println("Available Upper Berths " + availableUpperBerths);
    System.out.println("Available Middle Berths "+ availableMiddleBerths);
    System.out.println("Available RACs "+ availableRacTickets);
    System.out.println("Available Waiting List"+ availableWaitingList);
    System.out.println("-----------------------------------------------");

}

//print all occupied passengers from all types including WL

public void printPassengers()
{
    if(passengers.size() == 0)
    {
        System.out.println("NO Details of Passengers");
        return;
    }
    for(Passenger p : passengers.values())
    {
    System.out.println("PASSENGER ID  " + p.passengerId);
    System.out.println("Name " + p.name);
    System.out.println("Age " +p.age);
    System.out.println("Status " +p.number + p.alloted);
    System.out.println("------------------------------");
}
}
}



        




    