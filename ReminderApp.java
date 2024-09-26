import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.*;
import java.util.Calendar;

public class ReminderApp extends JFrame {

    private JComboBox<String> dayComboBox;
    private JComboBox<String> timeComboBox;
    private JComboBox<String> activityComboBox;
    private JButton setReminderButton;

    public ReminderApp() {
        setLayout(new FlowLayout());

        // Day of the week combo box
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        dayComboBox = new JComboBox<>(days);

        // Time combo box
        String[] times = new String[24];
        for (int i = 0; i < 24; i++) {
            times[i] = String.format("%02d:00", i);
        }
        timeComboBox = new JComboBox<>(times);

        // Activity combo box
        String[] activities = {"Wake up", "Go to gym", "Breakfast", "Meetings", "Lunch", "Quick nap", "Go to library", "Dinner", "Go to sleep"};
        activityComboBox = new JComboBox<>(activities);

        setReminderButton = new JButton("Set Reminder");

        add(new JLabel("Day:"));
        add(dayComboBox);
        add(new JLabel("Time:"));
        add(timeComboBox);
        add(new JLabel("Activity:"));
        add(activityComboBox);
        add(setReminderButton);

        setReminderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setReminder();
            }
        });

        setSize(400, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void setReminder() {
        String day = (String) dayComboBox.getSelectedItem();
        String time = (String) timeComboBox.getSelectedItem();
        String activity = (String) activityComboBox.getSelectedItem();

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, getDayOfWeek(day));
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(time.split(":")[0]));
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                playSound();
                JOptionPane.showMessageDialog(null, "Reminder: " + activity);
            }
        }, calendar.getTimeInMillis() - System.currentTimeMillis());

        JOptionPane.showMessageDialog(null, "Reminder set successfully");
    }

    private int getDayOfWeek(String day) {
        switch (day) {
            case "Monday":
                return Calendar.MONDAY;
            case "Tuesday":
                return Calendar.TUESDAY;
            case "Wednesday":
                return Calendar.WEDNESDAY;
            case "Thursday":
                return Calendar.THURSDAY;
            case "Friday":
                return Calendar.FRIDAY;
            case "Saturday":
                return Calendar.SATURDAY;
            case "Sunday":
                return Calendar.SUNDAY;
            default:
                return Calendar.MONDAY;
        }
    }

    private void playSound() {
        Toolkit.getDefaultToolkit().beep();
    }

    public static void main(String[] args) {
        new ReminderApp();
    }
}