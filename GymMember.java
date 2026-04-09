/**
 * This is a abstract class gym member with common attributes and behaviors.
 * This class serves as the base class for regular member and premium member.
 * 
 * @author Dhawa Tamu Gurung
 */
public abstract class GymMember {
    protected int id;
    protected String DOB;
    protected String name;
    protected String location;
    protected String phone;
    protected String email;
    protected String gender;
    protected String membershipStartDate;
    protected int attendance;
    protected double loyaltyPoints;
    protected boolean activeStatus;

    /**
     * Constructs a GymMember with basic personal and membership details.
     *
     * @param id the id of the member
     * @param name the name of the member
     * @param location the location of the member
     * @param phone it phone number of member
     * @param email the email of member
     * @param gender the gender of member
     * @param DOB date of birth of the member
     * @param membershipStartDate the date when membership started
     */
    public GymMember(int id, String name, String location, String phone, 
                    String email, String gender, String DOB, String membershipStartDate) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.DOB = DOB;
        this.membershipStartDate = membershipStartDate;
        this.attendance = 0;
        this.loyaltyPoints = 0;
        this.activeStatus = false;
    }

    
    public int getId() { 
        return id;
    }

    public String getDOB() { 
        return DOB;
    }

    public String getName() {
        return name; 
    }

    public String getLocation() {
        return location;
    }

    public String getPhone() { 
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() { 
        return gender; 
    }

    public String getMembershipStartDate() { 
        return membershipStartDate; 
    }

    public int getAttendance() { 
        return attendance;
    }

    public double getLoyaltyPoints() { 
        return loyaltyPoints;
    }

    public boolean isActiveStatus() { 
        return activeStatus;
    }

    /**
     * Abstract method to mark attendance.
     * Subclasses must provide implementation for this method.
     */
    public abstract void markAttendance();

    /**
     * It activates the membership of the members.
     */
    public void activateMembership() {
        activeStatus = true;
    }

    /**
     * It deactivates the membership of the members.
     */
    public void deactivateMembership() {
        if (activeStatus) {
            activeStatus = false;
        }
    }

    /**
     * It resets member information such as attendance, loyalty points, and status.
     */
    public void resetMember() {
        activeStatus = false;
        attendance = 0;
        loyaltyPoints = 0;
    }

    /**
     * It displays the details of the members
     */
    public void display() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Location: " + location);
        System.out.println("Phone: " + phone);
        System.out.println("Email: " + email);
        System.out.println("Gender: " + gender);
        System.out.println("Date of Birth: " + DOB);
        System.out.println("Membership Start Date: " + membershipStartDate);
        System.out.println("Attendance: " + attendance);
        System.out.println("Loyalty Points: " + loyaltyPoints);
        System.out.println("Active Status: " + (activeStatus ? "Active" : "Inactive"));
    }
}
