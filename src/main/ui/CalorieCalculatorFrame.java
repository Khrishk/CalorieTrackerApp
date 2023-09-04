package ui;

import model.Event;
import model.EventLog;
import model.User;
import model.exception.LogException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalorieCalculatorFrame extends JDialog implements LogPrinter {
    private JTextField tfHeight;
    private JLabel heightLabel;
    private JLabel weightLabel;
    private JTextField tfWeight;
    private JLabel ageLabel;
    private JTextField tfAge;
    private JLabel sexLabel;
    private JComboBox tfCombo;
    private JPanel calculatorPane;
    private JLabel goalLabel;
    private JButton calculateCalorieGoalButton;
    private JComboBox tfSex;
    private JTextField calGoalDisplay;
    private JButton homeButton;
    private double calGoal;


    // EFFECTS: Constructs a JFrame for the Calorie Calculator panel
    public CalorieCalculatorFrame() {
        startHelper();
        calculateCalorieGoalButton.addActionListener(new ActionListener() {

            //  EFFECTS: Calls calculate calorie on button press
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateCalorie();
            }
        });
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {

            // EFFECTS: Prints the EventLog and quits the system
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                printerHelper();
            }
        });

        setVisible(true);
        homeButton.addActionListener(new ActionListener() {

            //  EFFECTS: Closes this frame and opens Main Frame
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new MainFrame();
            }
        });
    }


    //  EFFECTS: Calculates calorie goal of user using given inputs
    //  MODIFIES: this
    private void calculateCalorie() {
        double height = Double.parseDouble(tfHeight.getText());
        double weight = Double.parseDouble(tfWeight.getText());
        int age = Integer.parseInt(tfAge.getText());
        int sex = tfSex.getSelectedIndex();
        int goal = tfCombo.getSelectedIndex();

        User user = new User(height, weight, age, sex, goal);

        this.calGoal = user.getCalorieGoal();
        calGoalDisplay.setText(String.valueOf(this.calGoal) + " Calories");
        reset();
    }

    // EFFECTS: Resets all textFields
    public void reset() {
        tfHeight.setText("");
        tfAge.setText("");
        tfWeight.setText("");
        tfSex.setSelectedIndex(0);
        tfCombo.setSelectedIndex(0);
    }

    // EFFECTS: Prints the EventLog
    @Override
    public void printLog(EventLog el) throws LogException {
        for (Event next : el) {
            System.out.println(next.getDescription());
        }
    }

    // EFFECTS: Helper to print EventLog once system is closed
    public void printerHelper() {
        try {
            printLog(EventLog.getInstance());
        } catch (LogException e) {
            throw new RuntimeException(e);
        }
        System.exit(0);
    }

    // EFFECTS: Helper to start the frame
    public void startHelper() {
        setContentPane(calculatorPane);
        setTitle("Calculator");
        setSize(550, 450);
    }
}
