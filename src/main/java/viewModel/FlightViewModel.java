package main.java.viewModel;

public class FlightViewModel {
    public String Destination;
    public String Departure;
    public String DepartureTime;
    public String ArrivalTime;
    public String Price;
    public String FlightNumber;

    public FlightViewModel(String Destination, String Departure, String DepartureTime, String ArrivalTime, String Price,
            String FlightNumber) {
        this.Destination = Destination;
        this.Departure = Departure;
        this.DepartureTime = DepartureTime;
        this.ArrivalTime = ArrivalTime;
        this.Price = Price;
        this.FlightNumber = FlightNumber;
    }

}
