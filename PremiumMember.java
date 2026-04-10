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

    