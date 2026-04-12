
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * GymGUI provides the graphical user interface for managing gym members.
 * 
 * It enables users to input, view, and manage details of Regular and Premium members
 * including their personal information, membership plans, payment details, and attendance.
 * The interface includes labels, text fields, combo boxes, radio buttons, and buttons
 * to perform various operations like adding members, activating and deactivating memberships,
 * calculating discounts, upgrading plans, saving to and reading from files, and more.
 * 
 * The GUI layout is made using setLayout(null) and various
 * action listeners are attached to handle user interactions with buttons.
 * 
 * This class interacts with underlying member objects such as RegularMember and PremiumMember
 * to perform operations and display information.
 * 
 * @author Dhawa Tamu Gurung
 */
class GymGUI {
    JFrame frame = new JFrame("Gym Management System");
    private JLabel lbId, lbName, lbLocation, lbEmail, lbPhone, lbDOB, lbMembershipStartDate, lbPlan, lbReferralSource, 
    lbGender, lbPaidAmount, lbRemovalReason, lbTrainersName, lbRegularPlanPrice, lbPremiumPlanCharge, lbDiscountAmount;
    
    private JTextField txtId, txtName, txtLocation, txtEmail, txtPhone, txtReferralSource, txtPaidAmount, 
    txtRemovalReason, txtTrainersName, txtRegularPlanPrice, txtPremiumPlanCharge, txtDiscountAmount;
    
    private JComboBox<String> comboDOBMonth, comboDOBYear, comboDOBDay, comboMembershipDateYear, 
    comboMembershipDateMonth, comboMembershipDateDay, comboPlan;
    
    private JRadioButton maleButton, femaleButton;
    
    private ButtonGroup genderGroup;
    
    private String[] day = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26",
                            "27","28","29","30","31"};
    private String[] month = {"January","Feburary","March","April","May","June","July","August","September","October","November","December"};
    private String[] year = new String[41];
    private ArrayList<GymMember> members = new ArrayList<>();

    
    private JButton btnAddRegular, btnAddPremium, btnActivate, btnDeactivate, btnMarkAttendance, 
    btnUpgradePlan, btnCalculateDiscount, btnRevertRegular, btnRevertPremium, 
    btnPayDue, btnDisplay, btnClear, btnSaveToFile, btnReadFromFile;

    public GymGUI() {
        for (int i = 0; i < 41; i++) {
            year[i] = Integer.toString(1990 + i);
        }

        GUI();
    }

    public void GUI() {
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        lbId = new JLabel("ID:");
        lbId.setBounds(20,60,100,30);
        frame.add(lbId);

        txtId = new JTextField();
        txtId.setBounds(180,60,190,30);
        frame.add(txtId);

        lbName = new JLabel("Name:");
        lbName.setBounds(400,60,100,30);
        frame.add(lbName);

        txtName = new JTextField();
        txtName.setBounds(520,60,190,30);
        frame.add(txtName);

        lbLocation = new JLabel("Location:");
        lbLocation.setBounds(20,100,100,30);
        frame.add(lbLocation);

        txtLocation = new JTextField();
        txtLocation.setBounds(180,100,190,30);
        frame.add(txtLocation);
        
        lbPhone = new JLabel("Phone:");
        lbPhone.setBounds(400,100,100,30);
        frame.add(lbPhone);

        txtPhone = new JTextField();
        txtPhone.setBounds(520,100,190,30);
        frame.add(txtPhone);

        lbEmail = new JLabel("Email:");
        lbEmail.setBounds(20,140,120,30);
        frame.add(lbEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(180,140,190,30);
        frame.add(txtEmail);

        lbGender = new JLabel("Gender:");
        lbGender.setBounds(400,140,120,30);
        frame.add(lbGender);

        maleButton = new JRadioButton("Male");
        maleButton.setBounds(520,140,80,30);
        femaleButton = new JRadioButton("Female");
        femaleButton.setBounds(600,140,80,30);

        genderGroup = new ButtonGroup();
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);

        frame.add(maleButton);
        frame.add(femaleButton);

        lbDOB = new JLabel("Date of Birth:");
        lbDOB.setBounds(20,180,120,30);
        frame.add(lbDOB);

        comboDOBDay = new JComboBox<>(day);
        comboDOBDay.setBounds(180,180,40,30);
        frame.add(comboDOBDay);

        comboDOBMonth = new JComboBox<>(month);
        comboDOBMonth.setBounds(220,180,90,30);
        frame.add(comboDOBMonth);

        comboDOBYear = new JComboBox<>(year);
        comboDOBYear.setBounds(310,180,60,30);
        frame.add(comboDOBYear);
        
        lbPlan = new JLabel("Plan:");
        lbPlan.setBounds(400,180,120,30);
        frame.add(lbPlan);

        String[] plans = {"Basic", "Standard", "Deluxe"};
        comboPlan = new JComboBox<>(plans);
        comboPlan.setBounds(520,180,190,30);
        comboPlan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedPlan = comboPlan.getSelectedItem().toString().toLowerCase();
                switch(selectedPlan) {
                    case "basic":
                        txtRegularPlanPrice.setText("6500");
                        break;
                    case "standard":
                        txtRegularPlanPrice.setText("12500");
                        break;
                    case "deluxe":
                        txtRegularPlanPrice.setText("18500");
                        break;
                }
            }
        });
        frame.add(comboPlan);

        lbMembershipStartDate = new JLabel("Membership Start:");
        lbMembershipStartDate.setBounds(20,220,160,30);
        frame.add(lbMembershipStartDate);

        comboMembershipDateDay = new JComboBox<>(day);
        comboMembershipDateDay.setBounds(180,220,40,30);
        frame.add(comboMembershipDateDay);

        comboMembershipDateMonth = new JComboBox<>(month);
        comboMembershipDateMonth.setBounds(220,220,90,30);
        frame.add(comboMembershipDateMonth);

        comboMembershipDateYear = new JComboBox<>(year);
        comboMembershipDateYear.setBounds(310,220,60,30);
        frame.add(comboMembershipDateYear);

        lbReferralSource = new JLabel("Referral Source:");
        lbReferralSource.setBounds(400,220,160,30);
        frame.add(lbReferralSource);

        txtReferralSource = new JTextField();
        txtReferralSource.setBounds(520,220,190,30);
        frame.add(txtReferralSource);

        lbPaidAmount = new JLabel("Paid Amount:");
        lbPaidAmount.setBounds(20,260,160,30);
        frame.add(lbPaidAmount);

        txtPaidAmount = new JTextField();
        txtPaidAmount.setBounds(180,260,190,30);
        frame.add(txtPaidAmount);
        
        lbRemovalReason = new JLabel("Removal Reason:");
        lbRemovalReason.setBounds(400,260,160,30);
        frame.add(lbRemovalReason);

        txtRemovalReason = new JTextField();
        txtRemovalReason.setBounds(520,260,190,30);
        frame.add(txtRemovalReason);

        lbTrainersName = new JLabel("Trainer's Name:");
        lbTrainersName.setBounds(20,300,160,30);
        frame.add(lbTrainersName);

        txtTrainersName = new JTextField();
        txtTrainersName.setBounds(180,300,190,30);
        frame.add(txtTrainersName);

        lbRegularPlanPrice = new JLabel("Regular Plan Price:");
        lbRegularPlanPrice.setBounds(400,300,150,30);
        frame.add(lbRegularPlanPrice);

        txtRegularPlanPrice = new JTextField("6500");
        txtRegularPlanPrice.setEditable(false);
        txtRegularPlanPrice.setBounds(520,300,190,30);
        frame.add(txtRegularPlanPrice);

        lbPremiumPlanCharge = new JLabel("Premium Plan Charge:");
        lbPremiumPlanCharge.setBounds(20,340,150,30);
        frame.add(lbPremiumPlanCharge);

        txtPremiumPlanCharge = new JTextField("50000");
        txtPremiumPlanCharge.setEditable(false);
        txtPremiumPlanCharge.setBounds(180,340,190,30);
        frame.add(txtPremiumPlanCharge);

        lbDiscountAmount = new JLabel("Discount Amount:");
        lbDiscountAmount.setBounds(400,340,150,30);
        frame.add(lbDiscountAmount);

        txtDiscountAmount = new JTextField("0");
        txtDiscountAmount.setEditable(false);
        txtDiscountAmount.setBounds(520,340,190,30);
        frame.add(txtDiscountAmount);

        btnAddRegular = new JButton("Add Regular Member");
        btnAddRegular.setBounds(40,420,180,30);
        btnAddRegular.addActionListener(new AddRegularListener());
        frame.add(btnAddRegular);

        btnAddPremium = new JButton("Add Premium Member");
        btnAddPremium.setBounds(260,420,180,30);
        btnAddPremium.addActionListener(new AddPremiumListener());
        frame.add(btnAddPremium);

        btnActivate = new JButton("Activate Membership");
        btnActivate.setBounds(480,420,180,30);
        btnActivate.addActionListener(new ActivateMembershipListener());
        frame.add(btnActivate);

        btnDeactivate = new JButton("Deactivate Membership");
        btnDeactivate.setBounds(40,460,180,30);
        btnDeactivate.addActionListener(new DeactivateMembershipListener());
        frame.add(btnDeactivate);

        btnMarkAttendance = new JButton("Mark Attendance");
        btnMarkAttendance.setBounds(260,460,180,30);
        btnMarkAttendance.addActionListener(new MarkAttendanceListener());
        frame.add(btnMarkAttendance);

        btnUpgradePlan = new JButton("Upgrade Plan");
        btnUpgradePlan.setBounds(480,460,180,30);
        btnUpgradePlan.addActionListener(new UpgradePlanListener());
        frame.add(btnUpgradePlan);

        btnCalculateDiscount = new JButton("Calculate Discount");
        btnCalculateDiscount.setBounds(40,500,180,30);
        btnCalculateDiscount.addActionListener(new CalculateDiscountListener());
        frame.add(btnCalculateDiscount);

        btnRevertRegular = new JButton("Revert Regular Member");
        btnRevertRegular.setBounds(260,500,180,30);
        btnRevertRegular.addActionListener(new RevertRegularListener());
        frame.add(btnRevertRegular);

        btnRevertPremium = new JButton("Revert Premium Member");
        btnRevertPremium.setBounds(480,500,180,30);
        btnRevertPremium.addActionListener(new RevertPremiumListener());
        frame.add(btnRevertPremium);

        btnPayDue = new JButton("Pay Due Amount");
        btnPayDue.setBounds(40,540,180,30);
        btnPayDue.addActionListener(new PayDueListener());
        frame.add(btnPayDue);

        btnDisplay = new JButton("Display");
        btnDisplay.setBounds(260,540,180,30);
        btnDisplay.addActionListener(new DisplayListener());
        frame.add(btnDisplay);

        btnClear = new JButton("Clear");
        btnClear.setBounds(480,540,180,30);
        btnClear.addActionListener(new ClearListener());
        frame.add(btnClear);

        btnSaveToFile = new JButton("Save to File");
        btnSaveToFile.setBounds(140,580,180,30);
        btnSaveToFile.addActionListener(new SaveToFileListener());
        frame.add(btnSaveToFile);

        btnReadFromFile = new JButton("Read from File");
        btnReadFromFile.setBounds(380,580,180,30);
        btnReadFromFile.addActionListener(new ReadFromFileListener());
        frame.add(btnReadFromFile);

        frame.setSize(800, 800);
        frame.setVisible(true);
    }

    /**
    * Listener for adding a regular gym member.
    * Validates ID uniqueness and parses form data to create a new RegularMember object,
    * then adds it to the members list.
    */
    private class AddRegularListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                int id = Integer.parseInt(txtId.getText());

                for (GymMember member : members) {
                    if (member.getId() == id) {
                        JOptionPane.showMessageDialog(frame, "Member ID already exists", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                String name = txtName.getText();
                String location = txtLocation.getText();
                String phone = txtPhone.getText();
                String email = txtEmail.getText();
                String gender = maleButton.isSelected() ? "Male" : "Female";

                String dob = comboDOBDay.getSelectedItem() + "-" + comboDOBMonth.getSelectedItem() + "-" + comboDOBYear.getSelectedItem();
                String startDate = comboMembershipDateDay.getSelectedItem() + "-" + comboMembershipDateMonth.getSelectedItem() + "-" + comboMembershipDateYear.getSelectedItem();
                String referral = txtReferralSource.getText();

                RegularMember newMember = new RegularMember(id, name, location, phone, email, gender, dob, startDate, referral);
                members.add(newMember);

                JOptionPane.showMessageDialog(frame, "Regular member added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearFields();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid ID format", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
    * Listener for adding a premium gym member.
    * Validates ID uniqueness and parses form data to create a new PremiumMember object,
    * then adds it to the members list.
    */
    private class AddPremiumListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                int id = Integer.parseInt(txtId.getText());

                for (GymMember member : members) {
                    if (member.getId() == id) {
                        JOptionPane.showMessageDialog(frame, "Member ID already exists", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                String name = txtName.getText();
                String location = txtLocation.getText();
                String phone = txtPhone.getText();
                String email = txtEmail.getText();
                String gender = maleButton.isSelected() ? "Male" : "Female";

                String dob = comboDOBDay.getSelectedItem() + "-" + comboDOBMonth.getSelectedItem() + "-" + comboDOBYear.getSelectedItem();
                String startDate = comboMembershipDateDay.getSelectedItem() + "-" + comboMembershipDateMonth.getSelectedItem() + "-" + comboMembershipDateYear.getSelectedItem();
                String trainer = txtTrainersName.getText();

                PremiumMember newMember = new PremiumMember(id, name, location, phone, email, 
                        gender, dob, startDate, trainer);
                members.add(newMember);

                JOptionPane.showMessageDialog(frame, "Premium member added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearFields();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid ID format", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
    * Listener for activating a member's gym membership.
    * Searches for the member by ID and activates their membership if found.
    */
    private class ActivateMembershipListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                int id = Integer.parseInt(txtId.getText());

                for (GymMember member : members) {
                    if (member.getId() == id) {
                        member.activateMembership();
                        JOptionPane.showMessageDialog(frame, "Membership activated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                }

                JOptionPane.showMessageDialog(frame, "Member not found", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid ID format", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
    * Listener for deactivating a member's gym membership.
    * Searches for the member by ID and deactivates their membership if found.
    */
    private class DeactivateMembershipListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                int id = Integer.parseInt(txtId.getText());

                for (GymMember member : members) {
                    if (member.getId() == id) {
                        member.deactivateMembership();
                        JOptionPane.showMessageDialog(frame, "Membership deactivated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                }

                JOptionPane.showMessageDialog(frame, "Member not found", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid ID format", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
    * Listener for marking attendance of a gym member.
    * Asksfor member ID and marks attendance if the member exists and is active.
    */
    private class MarkAttendanceListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String idInput = JOptionPane.showInputDialog(frame, "Enter Member ID:", "Mark Attendance", JOptionPane.QUESTION_MESSAGE);
            if (idInput != null && !idInput.trim().isEmpty()) {
                try {
                    int id = Integer.parseInt(idInput);
                    for (GymMember member : members) {
                        if (member.getId() == id) {
                            if (member.isActiveStatus()) {
                                member.markAttendance();
                                JOptionPane.showMessageDialog(frame, "Attendance marked successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(frame, "Member is not active", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                            return;
                        }
                    }
                    JOptionPane.showMessageDialog(frame, "Member not found", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid ID format", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    /**
    * Listener for upgrading the plan of a regular member.
    * Asks for member ID, checks if the member is a RegularMember, and upgrades their plan.
    * Updates the displayed plan price upon success.
    */
    private class UpgradePlanListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String idInput = JOptionPane.showInputDialog(frame, "Enter Member ID:", "Upgrade Plan", JOptionPane.QUESTION_MESSAGE);
            if (idInput != null && !idInput.trim().isEmpty()) {
                try {
                    int id = Integer.parseInt(idInput);
                    String newPlan = comboPlan.getSelectedItem().toString().toLowerCase();
                    for (GymMember member : members) {
                        if (member.getId() == id && member instanceof RegularMember) {
                            RegularMember regularMember = (RegularMember) member;
                            String result = regularMember.upgradePlan(newPlan);
                            JOptionPane.showMessageDialog(frame, result, "Upgrade Plan", 
                                result.contains("successfully") ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE);
                            txtRegularPlanPrice.setText(String.valueOf(regularMember.getPrice()));
                            return;
                        }
                    }
                    JOptionPane.showMessageDialog(frame, "Regular member not found", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid ID format", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    /**
    * Listener for calculating the discount for a premium member.
    * Asks for member ID, verifies the member, and updates the discount amount in the UI.
    */
    private class CalculateDiscountListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String idInput = JOptionPane.showInputDialog(frame, "Enter Member ID:", "Calculate Discount", JOptionPane.QUESTION_MESSAGE);
            if (idInput != null && !idInput.trim().isEmpty()) {
                try {
                    int id = Integer.parseInt(idInput);
                    for (GymMember member : members) {
                        if (member.getId() == id && member instanceof PremiumMember) {
                            PremiumMember premiumMember = (PremiumMember) member;
                            premiumMember.calculateDiscount();
                            txtDiscountAmount.setText(String.valueOf(premiumMember.getDiscountAmount()));
                            JOptionPane.showMessageDialog(frame, "Discount calculated: " + premiumMember.getDiscountAmount(), 
                                "Success", JOptionPane.INFORMATION_MESSAGE);
                            return;
                        }
                    }
                    JOptionPane.showMessageDialog(frame, "Premium member not found", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid ID format", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    /**
    * Listener for reverting a regular member's plan.
    * Asks for member ID and reason, verifies the member, and performs the revert operation.
    */
    private class RevertRegularListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JPanel panel = new JPanel();
            panel.setLayout(new java.awt.GridLayout(2, 2, 5, 5));
            
            JTextField idField = new JTextField(10);
            JTextField reasonField = new JTextField(10);
            
            panel.add(new JLabel("Member ID:"));
            panel.add(idField);
            panel.add(new JLabel("Removal Reason:"));
            panel.add(reasonField);
            
            int result = JOptionPane.showConfirmDialog(frame, panel, "Revert Regular Member", 
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                
            if (result == JOptionPane.OK_OPTION) {
                String idInput = idField.getText().trim();
                String reason = reasonField.getText().trim();
                
                if (idInput.isEmpty() || reason.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    int id = Integer.parseInt(idInput);
                    for (GymMember member : members) {
                        if (member.getId() == id && member instanceof RegularMember) {
                            RegularMember regularMember = (RegularMember) member;
                            regularMember.revertRegularMember(reason);
                            JOptionPane.showMessageDialog(frame, "Regular member reverted", "Success", JOptionPane.INFORMATION_MESSAGE);
                            txtRegularPlanPrice.setText("6500");
                            return;
                        }
                    }
                    JOptionPane.showMessageDialog(frame, "Regular member not found", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid ID format", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    /**
    * Listener for reverting a premium member.
    * Asks for member ID and resets any premium-specific features like discount or charges.
    */
    private class RevertPremiumListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String idInput = JOptionPane.showInputDialog(frame, "Enter Member ID:", "Revert Premium Member", JOptionPane.QUESTION_MESSAGE);
            if (idInput != null && !idInput.trim().isEmpty()) {
                try {
                    int id = Integer.parseInt(idInput);
                    for (GymMember member : members) {
                        if (member.getId() == id && member instanceof PremiumMember) {
                            PremiumMember premiumMember = (PremiumMember) member;
                            premiumMember.revertPremiumMember();
                            JOptionPane.showMessageDialog(frame, "Premium member reverted", "Success", JOptionPane.INFORMATION_MESSAGE);
                            txtDiscountAmount.setText("0");
                            return;
                        }
                    }
                    JOptionPane.showMessageDialog(frame, "Premium member not found", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid ID format", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    /**
    * Listener for processing due payment for a premium member.
    * Asks for member ID and amount, and updates the payment and discount info if valid.
    */
    private class PayDueListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JPanel panel = new JPanel();
            panel.setLayout(new java.awt.GridLayout(2, 2, 5, 5));
            
            JTextField idField = new JTextField(10);
            JTextField amountField = new JTextField(10);
            
            panel.add(new JLabel("Member ID:"));
            panel.add(idField);
            panel.add(new JLabel("Amount to Pay:"));
            panel.add(amountField);
            
            int result = JOptionPane.showConfirmDialog(frame, panel, "Pay Due Amount", 
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                String idInput = idField.getText().trim();
                String amountInput = amountField.getText().trim();
                
                if (idInput.isEmpty() || amountInput.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                try {
                    int id = Integer.parseInt(idInput);
                    double amount = Double.parseDouble(amountInput);
                    
                    for (GymMember member : members) {
                        if (member.getId() == id && member instanceof PremiumMember) {
                            PremiumMember premiumMember = (PremiumMember) member;
                            String result2 = premiumMember.payDueAmount(amount);
                            JOptionPane.showMessageDialog(frame, result2, "Pay Due", 
                                result2.contains("successfully") ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE);
                            txtDiscountAmount.setText(String.valueOf(premiumMember.getDiscountAmount()));
                            return;
                        }
                    }
                    JOptionPane.showMessageDialog(frame, "Premium member not found", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid ID or amount format", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    /**
    * Listener for displaying all gym member information.
    * Creates a new frame and lists details of all regular and premium members.
    */
    private class DisplayListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFrame displayFrame = new JFrame("Member Information");
            displayFrame.setLayout(null);
            displayFrame.setSize(600, 400);
            
            StringBuilder sb = new StringBuilder();
            sb.append("===== Gym Members =====\n\n");
    
            for (GymMember member : members) {
                if (member instanceof RegularMember) {
                    RegularMember regMember = (RegularMember) member;
                    sb.append("Regular Member:\n");
                    sb.append("ID: ").append(member.getId()).append("\n");
                    sb.append("Name: ").append(member.getName()).append("\n");
                    sb.append("Location: ").append(member.getLocation()).append("\n");
                    sb.append("Phone: ").append(member.getPhone()).append("\n");
                    sb.append("Email: ").append(member.getEmail()).append("\n");
                    sb.append("Gender: ").append(member.getGender()).append("\n");
                    sb.append("DOB: ").append(member.getDOB()).append("\n");
                    sb.append("Membership Start Date: ").append(member.getMembershipStartDate()).append("\n");
                    sb.append("Attendance: ").append(member.getAttendance()).append("\n");
                    sb.append("Loyalty Points: ").append(member.getLoyaltyPoints()).append("\n");
                    sb.append("Status: ").append(member.isActiveStatus() ? "Active" : "Inactive").append("\n");
                    sb.append("Plan: ").append(regMember.getPlan()).append("\n");
                    sb.append("Price: ").append(regMember.getPrice()).append("\n");
                    sb.append("\n");
                } else if (member instanceof PremiumMember) {
                    PremiumMember premMember = (PremiumMember) member;
                    sb.append("Premium Member:\n");
                    sb.append("ID: ").append(member.getId()).append("\n");
                    sb.append("Name: ").append(member.getName()).append("\n");
                    sb.append("Location: ").append(member.getLocation()).append("\n");
                    sb.append("Phone: ").append(member.getPhone()).append("\n");
                    sb.append("Email: ").append(member.getEmail()).append("\n");
                    sb.append("Gender: ").append(member.getGender()).append("\n");
                    sb.append("DOB: ").append(member.getDOB()).append("\n");
                    sb.append("Membership Start Date: ").append(member.getMembershipStartDate()).append("\n");
                    sb.append("Attendance: ").append(member.getAttendance()).append("\n");
                    sb.append("Loyalty Points: ").append(member.getLoyaltyPoints()).append("\n");
                    sb.append("Status: ").append(member.isActiveStatus() ? "Active" : "Inactive").append("\n");
                    sb.append("Personal Trainer: ").append(premMember.getPersonalTrainer()).append("\n");
                    sb.append("Paid Amount: ").append(premMember.getPaidAmount()).append("\n");
                    sb.append("Premium Charge: ").append(premMember.getPremiumCharge()).append("\n");
                    sb.append("\n");
                }
            }
    
            JTextArea textArea = new JTextArea(sb.toString());
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setBounds(20, 20, 540, 320);
            
            displayFrame.add(scrollPane);
            
            JButton closeButton = new JButton("Close");
            closeButton.setBounds(240, 340, 100, 30);
            closeButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    displayFrame.dispose();
                }
            });
            displayFrame.add(closeButton);
            displayFrame.setLocationRelativeTo(frame);
            displayFrame.setVisible(true);
        }
    }

    /**
    * Listener for clearing all input fields in the form.
    * Resets all text fields, selection inputs, and radio button groups to default values.
    */
    private class ClearListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            txtId.setText("");
            txtName.setText("");
            txtLocation.setText("");
            txtEmail.setText("");
            txtPhone.setText("");
            genderGroup.clearSelection();
            txtReferralSource.setText("");
            txtPaidAmount.setText("");
            txtRemovalReason.setText("");
            txtTrainersName.setText("");
            txtDiscountAmount.setText("0");
            comboPlan.setSelectedIndex(0);
        }
    }

    /**
    * Listener to handle saving member data to a file.
    * Writes all GymMember objects (Regular and Premium) into a formatted text file.
    * Displays a success or error message depending on the outcome.
    */
    private class SaveToFileListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                PrintWriter writer = new PrintWriter(new FileWriter("MemberDetails.txt"));
                writer.println(String.format("%-5s %-15s %-15s %-15s %-25s %-20s %-10s %-10s %-10s %-15s %-10s %-15s %-15s",
                    "ID", "Name", "Location", "Phone", "Email", "Start Date", "Plan", "Price", 
                    "Attend", "Loyalty", "Active", "Full Pay", "Discount"));
                for (GymMember member : members) {
                    if (member instanceof RegularMember) {
                        RegularMember regular = (RegularMember) member;
                        writer.println(String.format("%-5d %-15s %-15s %-15s %-25s %-20s %-10s %-10.2f %-10d %-15.2f %-10b %-15s %-15.2f",
                            regular.getId(), regular.getName(), regular.getLocation(), regular.getPhone(),
                            regular.getEmail(), regular.getMembershipStartDate(), regular.getPlan(), regular.getPrice(),
                            regular.getAttendance(), regular.getLoyaltyPoints(), regular.isActiveStatus(), "-", 0.0));
                    } else if (member instanceof PremiumMember) {
                        PremiumMember premium = (PremiumMember) member;
                        writer.println(String.format("%-5d %-15s %-15s %-15s %-25s %-20s %-10s %-10.2f %-10d %-15.2f %-10b %-15b %-15.2f",
                            premium.getId(), premium.getName(), premium.getLocation(), premium.getPhone(),
                            premium.getEmail(), premium.getMembershipStartDate(), "Premium", premium.getPremiumCharge(),
                            premium.getAttendance(), premium.getLoyaltyPoints(), premium.isActiveStatus(),
                            premium.IsFullPayment(), premium.getDiscountAmount()));
                    }
                }
                writer.close();
                JOptionPane.showMessageDialog(frame, "Data saved to file successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(frame, "Error saving to file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    