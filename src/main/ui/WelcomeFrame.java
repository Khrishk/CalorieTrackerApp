package ui;

import model.Event;
import model.EventLog;
import model.exception.LogException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeFrame extends JFrame implements LogPrinter {
    private JPanel welcomePanel;
    private JButton letsGoButton;

    //  EFFECTS: Constructs a JFrame for the Welcome panel (splash screen)
    public WelcomeFrame() {
        setContentPane(welcomePanel);
        setTitle("Welcome");
        setSize(550, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        addWindowListener(new java.awt.event.WindowAdapter() {

            // EFFECTS: Prints the EventLog and quits the system
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                try {
                    printLog(EventLog.getInstance());
                } catch (LogException e) {
                    throw new RuntimeException(e);
                }
                //THEN you can exit the program
                System.exit(0);
            }
        });

        letsGoButton.addActionListener(new ActionListener() {

            //  EFFECTS: Closes this frame and starts a MainFrame
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new MainFrame();
            }
        });
    }

    // EFFECTS: Prints the EventLog
    @Override
    public void printLog(EventLog el) throws LogException {
        for (Event next : el) {
            System.out.println(next.getDescription());
        }
    }

    //  EFFECTS: Runs the program, calls a new WelcomeFrame (Main Method)
    public static void main(String[] args) {
        WelcomeFrame welcome = new WelcomeFrame();
    }
}
