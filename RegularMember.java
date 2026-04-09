/**
 * This class has attributes and methods of regular member 
 * This class inherits the attributes and methods of GymMember class
 * 
 * @author Dhawa Tamu Gurung
 */
 public class RegularMember extends GymMember {
    private final int attendanceLimit = 30;
    private boolean isEligibleForUpgrade;
    private String removalReason;
    private String referralSource;
    private String plan;
    private double price;

    /**
     * Constructs a RegularMember object with specified details.
     *
     * @param id the id of the member
     * @param name the name of the member
     * @param location the location of the member
     * @param phone the phone of the member
     * @param email the email of the member
     * @param gender the gender of the member
     * @param DOB the date of birth of the member
     * @param membershipStartDate the membership start date of the member
     * @param referralSource the referal source of the gym
     */
    public RegularMember(int id, String name, String location, String phone, String email, String gender, String DOB, 
                        String membershipStartDate, String referralSource) {
        super(id, name, location, phone, email, gender, DOB, membershipStartDate);
        this.isEligibleForUpgrade = false;
        this.removalReason = "";
        this.referralSource = referralSource;
        this.plan = "basic";
        this.price = 6500;
    }

    public int getAttendanceLimit() { 
        return attendanceLimit;
    }
    
    public boolean isEligibleForUpgrade() { 
        return isEligibleForUpgrade;
    }
    
    public String getRemovalReason() { 
        return removalReason;
    }
    
    public String getReferralSource() { 
        return referralSource;
    }
    
    public String getPlan() { 
        return plan;
    }
    
    public double getPrice() { 
        return price;
    }

    /**
     * This method marks attendance for the member and updates loyalty points.
     * Also checks for eligibility to upgrade the plan.
     */
    @Override
    public void markAttendance() {
        if (activeStatus) {
            attendance++;
            loyaltyPoints += 5;
            
            if (attendance >= attendanceLimit) {
                isEligibleForUpgrade = true;
            }
        }
    }

    public double getPlanPrice(String plan) {
        switch (plan.toLowerCase()) {
            case 
            "basic": 
            return 6500;
            
            case 
            "standard": 
            return 12500;
            
            case 
            "deluxe": 
            return 18500;
            
            default: 
            return -1;
        }
    }

    /**
     * This method upgrades the members current plan.
     *
     * @param newPlan The new plan to upgrade to
     * @return A message indicating the result of the upgrade 
     */
    public String upgradePlan(String newPlan) {
        if (!activeStatus) {
            return "Membership is not active. Cannot upgrade plan.";
        }
        
        if (!isEligibleForUpgrade) {
            return "Member is not eligible for upgrade. Attendance must be at least " + attendanceLimit;
        }
        
        if (newPlan.equalsIgnoreCase(plan)) {
            return "Member is already on the " + plan + " plan.";
        }
        
        double newPrice = getPlanPrice(newPlan);
        if (newPrice == -1) {
            return "Invalid plan selected.";
        }
        
        this.plan = newPlan;
        this.price = newPrice;
        return "Plan upgraded successfully to " + newPlan + " with price " + newPrice;
    }

    /**
     * This method resets the members data and reverts them to the default plan.
     *
     * @param removalReason The reason for reverting the member
     */
    public void revertRegularMember(String removalReason) {
        super.resetMember();
        this.isEligibleForUpgrade = false;
        this.plan = "basic";
        this.price = 6500;
        this.removalReason = removalReason;
    }

    /**
     * Displays the member's details along with plan specific information.
     */
    @Override
    public void display() {
        super.display();
        System.out.println("Plan: " + plan);
        System.out.println("Price: " + price);
        if (!removalReason.isEmpty()) {
            System.out.println("Removal Reason: " + removalReason);
        }
    }
}