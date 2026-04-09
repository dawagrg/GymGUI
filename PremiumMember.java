/**
 * This class has attributes and methods of premium member
 * This class inherits attributes and methods of GymMember class
 * 
 * @author Dhawa Tamu Gurung
 */
public class PremiumMember extends GymMember {
    private final double premiumCharge = 50000;
    private String personalTrainer;
    private boolean isFullPayment;
    private double paidAmount;
    private double discountAmount;

    /**
     * Constructs a PremiumMember object with specified details.
     *
     * @param id the id of the member
     * @param name the name of the member
     * @param location the location of the member
     * @param phone the phone of the member
     * @param email the email of the member
     * @param gender the gender of the member
     * @param DOB the date of birth of the member
     * @param membershipStartDate the membership start date of the member
     * @param personalTrainer the assigned trainers name
     */
    public PremiumMember(int id, String name, String location, String phone,String email, String gender, String DOB, String membershipStartDate, 
    String personalTrainer) {
        super(id, name, location, phone, email, gender, DOB, membershipStartDate);
        this.personalTrainer = personalTrainer;
        this.isFullPayment = false;
        this.paidAmount = 0;
        this.discountAmount = 0;
    }

    public double getPremiumCharge() { 
        return premiumCharge;
    }

    public String getPersonalTrainer() { 
        return personalTrainer; 
    }

    public boolean IsFullPayment() { 
        return isFullPayment;
    }

    public double getPaidAmount() { 
        return paidAmount;
    }

    public double getDiscountAmount() { 
        return discountAmount;
    }

    /**
     * This method marks the attendance of premium members
     * Increases attendance by 1 and loyalty points by 10
     */
    @Override
    public void markAttendance() {
        if (activeStatus) {
            attendance++;
            loyaltyPoints += 10; 
        }
    }

     /**
     * This method accepts and processes a payment of the premium membership.
     * It checks for valid amounts, ensures not to exceed the premium charge, and updates payment status.
     *
     * @param amount The amount being paid by the member
     * @return A message indicating the result of the payment
     */
    public String payDueAmount(double amount) {
        if (isFullPayment) {
            return "Payment already completed. No dues remaining.";
        }

        if (amount <= 0) {
            return "Invalid payment amount.";
        }

        if (paidAmount + amount > premiumCharge) {
            return "Payment exceeds the premium charge. Maximum acceptable amount: " + (premiumCharge - paidAmount);
        }

        paidAmount += amount;
        if (paidAmount == premiumCharge) {
            isFullPayment = true;
            calculateDiscount();
            return "Payment completed successfully. Membership fully paid.";
        }

        double remaining = premiumCharge - paidAmount;
        return "Payment of " + amount + " received. Remaining amount: " + remaining;
    }

    /**
     * This method calculates the discount if the member has completed full payment.
     * The discount is 10% of the premium charge.
     */
    public void calculateDiscount() {
        if (isFullPayment) {
            discountAmount = premiumCharge * 0.10;
            System.out.println("Discount of " + discountAmount + " applied (10% of premium charge).");
        } else {
            discountAmount = 0;
        }
    }

    /**
     * This method resets the premium member to initial state.
     * It clears payment, discount, and personal trainer details.
     */
    public void revertPremiumMember() {
        super.resetMember();
        personalTrainer = "";
        isFullPayment = false;
        paidAmount = 0;
        discountAmount = 0;
    }

    /**
     * This method displays all details of the premium member, including payment and trainer information.
     */
    @Override
    public void display() {
        super.display();
        System.out.println("Personal Trainer: " + personalTrainer);
        System.out.println("Paid Amount: " + paidAmount);
        System.out.println("Full Payment Status: " + (isFullPayment ? "Yes" : "No"));
        System.out.println("Remaining Amount: " + (premiumCharge - paidAmount));
        if (isFullPayment) {
            System.out.println("Discount Amount: " + discountAmount);
        }
    }
}