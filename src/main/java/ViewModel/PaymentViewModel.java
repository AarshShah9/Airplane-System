package ViewModel;

public class PaymentViewModel {
    private double seatPrice;
    private double flightPrice;
    private double tax;
    private boolean isMember;
    private double promotionDiscount; // As a percentage (e.g., 20 for 20%)

    // Constructor, getters and setters
    public PaymentViewModel(double seatPrice, double flightPrice, double tax, boolean isMember,
            double promotionDiscount) {
        this.seatPrice = seatPrice;
        this.flightPrice = flightPrice;
        this.tax = tax;
        this.isMember = isMember;
        this.promotionDiscount = promotionDiscount;
    }

    // getters and setters
    public double getSeatPrice() {
        return seatPrice;
    }

    public double getTax() {
        return tax;
    }

    public boolean getIsMember() {
        return isMember;
    }

    public double getPromotionDiscount() {
        return promotionDiscount;
    }

    public double getFlightPrice() {
        return flightPrice;
    }

    public void setSeatPrice(double seatPrice) {
        this.seatPrice = seatPrice;
    }

    public void setFlightPrice(double flightPrice) {
        this.flightPrice = flightPrice;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public void setIsMember(boolean isMember) {
        this.isMember = isMember;
    }

    public void setPromotionDiscount(double promotionDiscount) {
        this.promotionDiscount = promotionDiscount;
    }

}