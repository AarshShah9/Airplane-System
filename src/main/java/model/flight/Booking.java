package model.flight;

import java.util.Date;

/**
 * Represents a booking made by a user for a flight.
 */
public class Booking {
    private int bookingId;
    private String username;
    private int flightId;
    private int seatId;
    private double price;
    private Date bookingTime;
    private Boolean insurance;

    /**
     * Constructs a Booking object with the provided attributes.
     *
     * @param bookingId   The unique identifier for the booking.
     * @param flightId    The ID of the flight associated with the booking.
     * @param username    The username of the user making the booking.
     * @param seatId      The ID of the seat booked.
     * @param insurance   Whether insurance is included in the booking.
     * @param price       The total price of the booking.
     * @param bookingTime The timestamp when the booking was made.
     */
    public Booking(int bookingId, int flightId, String username, int seatId, Boolean insurance, double price,
                   Date bookingTime) {
        this.bookingId = bookingId;
        this.username = username;
        this.flightId = flightId;
        this.seatId = seatId;
        this.price = price;
        this.bookingTime = bookingTime;
        this.insurance = insurance;
    }

    /**
     * Gets the booking data as an array of strings.
     *
     * @return An array of strings containing booking data.
     */
    public String[] getBookingData() {
        String[] data = new String[6];
        data[0] = Integer.toString(bookingId);
        data[1] = Integer.toString(flightId);
        data[2] = Integer.toString(seatId);
        data[3] = insurance.toString();
        data[4] = Double.toString(price);
        data[5] = bookingTime.toString();
        return data;
    }

    // Getters and Setters

    public int getBookingId() {
        return bookingId;
    }

    public String getUsername() {
        return username;
    }

    public int getFlightId() {
        return flightId;
    }

    public int getSeatId() {
        return seatId;
    }

    public double getPrice() {
        return price;
    }

    public Date getBookingTime() {
        return bookingTime;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setBookingTime(Date bookingTime) {
        this.bookingTime = bookingTime;
    }

    public Boolean getInsurance() {
        return insurance;
    }

    public void setInsurance(Boolean insurance) {
        this.insurance = insurance;
    }
}
