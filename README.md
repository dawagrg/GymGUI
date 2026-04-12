# Gym Management System (Java)

## Overview

This project is a "Gym Management System" developed using "Java and Swing GUI".
It allows gym staff to manage gym members easily through a graphical interface.

The system can store and manage information for two types of members:

* Regular Members
* Premium Members

Users can add members, track attendance, manage membership status, calculate payments, and save member details to a file.

## Features

* Add **Regular Member**
* Add **Premium Member**
* Activate and deactivate membership
* Record member attendance
* Upgrade membership plan
* Calculate discount for premium members
* Display member information
* Save member details to a file
* Read member details from a file
* Simple **Java Swing GUI interface**


## Technologies Used

* Java
* Java Swing (GUI)
* File Handling (Read/Write)
* Object-Oriented Programming (OOP)


## Project Structure

GymGUI/
│
├── GymGUI.java
├── GymMember.java
├── RegularMember.java
├── PremiumMember.java
└── package.bluej

## Classes Description

### GymMember.java

This is the "abstract base class" for all gym members.
It stores common information such as:

* Member ID
* Name
* Date of Birth
* Phone number
* Email
* Location
* Gender
* Membership start date
* Attendance
* Loyalty points
* Membership status

Both RegularMember and PremiumMember inherit from this class.


### RegularMember.java

This class represents a "regular gym member".

Main features:

* Default membership plan
* Tracks attendance
* Can upgrade membership after reaching attendance limit
* Stores referral source
* Allows membership removal with a reason


### PremiumMember.java

This class represents a "premium gym member".

Main features:

* Fixed premium charge
* Personal trainer assignment
* Tracks payment status
* Calculates discount
* Stores paid amount


### GymGUI.java

This class creates the **graphical user interface (GUI)** of the system using "Java Swing".

The GUI includes:

* Text fields
* Combo boxes
* Radio buttons
* Buttons
* Text area for displaying information

Users can interact with the system through buttons to perform operations like adding members, updating attendance, saving data, and reading files.


## How to Run the Project

1. Install "Java JDK" on your system.
2. Open the project in "BlueJ or any Java IDE" (IntelliJ, Eclipse, NetBeans).
3. Compile all Java files.
4. Run the "GymGUI.java" file.
5. The Gym Management System interface will open.

## Learning Concepts Used

This project demonstrates:

* Object-Oriented Programming
* Inheritance
* Abstract Classes
* Encapsulation
* Java Swing GUI
* File Handling in Java
* Event Handling


## Author

Name: Dhawa Tamu Gurung

## License

This project is created for "educational purposes".
