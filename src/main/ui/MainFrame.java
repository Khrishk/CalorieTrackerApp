package ui;

import model.Event;
import model.EventLog;
import model.FoodList;
import model.exception.LogException;
import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame implements LogPrinter  {
    private JButton calorieCalculatorButton;
    private JButton calorieTrackerButton;
    private JPanel mainPanel;
    private JLabel calcIcon;

    //  EFFECTS: Constructs a JFrame for the Main panel
    public MainFrame() {
        startFrame();
        calorieCalculatorButton.addActionListener(new ActionListener() {
            //  EFFECTS: Closes this frame and starts a CalorieCalculatorFrame
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new CalorieCalculatorFrame();
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {

            // EFFECTS: Prints the EventLog and quits the system
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                printLogHelper();
            }
        });

        calorieTrackerButton.addActionListener(new ActionListener() {
            //  EFFECTS: Closes this frame and starts a CalorieTrackerFrame
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new CalorieTrackerFrame();
            }
        });
        setVisible(true);

    }

    // EFFECTS: Prints the EventLog
    @Override
    public void printLog(EventLog el) throws LogException {
        for (Event next : el) {
            System.out.println(next.getDescription());
        }
    }

    // EFFECTS: Helper to start the frame
    public void startFrame() {
        setContentPane(mainPanel);
        setTitle("Main");
        setSize(550, 450);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    }

    // EFFECTS: Helper for printing the EventLog on close
    public void printLogHelper() {
        try {
            printLog(EventLog.getInstance());
        } catch (LogException e) {
            throw new RuntimeException(e);
        }
        System.exit(0);
    }
}


