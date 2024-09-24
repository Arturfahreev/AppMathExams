package appMathExams;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;
import java.util.List;
import java.util.Timer;

/**
 * This class shows the main Frame of exam, which includes Rows and Columns of tasks
 */
public class FrameExam extends JFrame {

    // ----------------------------- FIELDS -----------------------------
    static final int ROWS = 4; // rows of Task on FrameExam
    static final int COLUMNS = 4; //columns of Task on FrameExam
    private JPanel panelExam = new JPanel(); // panel of Exam with tasks
    private JPanel panelTimer = new JPanel(); // panel for Timer
    private JLabel labelTimer = new JLabel(); // label for Timer
    private ActionListener actionListener;
    private java.util.List<JButtonColor> listButtons = new ArrayList<>(); // list of buttons Tasks on FrameExam
    static Random random = new Random();
    boolean threadFlag = true;
    Thread thread;
    private String language = "English";
    static Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    private int countTimer = 10;

    // ----------------------------- CONSTRUCTOR -----------------------------
        public FrameExam(ActionListener actionListener) {
        this.actionListener = actionListener;
        setFrameExamSettings();
        setPanelTimerSettings();
        setPanelExamSettings();
        this.add(panelTimer);
        this.add(panelExam);
        setTasksAndButtons();
    }

    private void setPanelExamSettings() {
        panelExam.setBounds(0, 30, this.getWidth(), this.getHeight() - 40);
        panelExam.setLayout(new GridLayout(ROWS, COLUMNS));
    }

    private void setPanelTimerSettings() {
        panelTimer.setBounds(0, 0, this.getWidth(), 30);
        panelTimer.setLayout(new BorderLayout());
        labelTimer.setBackground(Color.BLACK);
        labelTimer.setOpaque(true);
        labelTimer.setForeground(Color.RED);
        labelTimer.setFont(new Font(null, Font.BOLD, 20));
        labelTimer.setText("Timer: ");
        labelTimer.setHorizontalAlignment(JLabel.CENTER);
        panelTimer.add(labelTimer);
    }

    private void setFrameExamSettings() {
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        //this.setLayout(new GridLayout(ROWS, COLUMNS));
        this.setSize(screen.width, screen.height);
        this.setResizable(false);
        this.addWindowListener(new ExamWindowAdapter());
        this.setVisible(false);
        this.setLayout(null);
    }

    public void setTasksAndButtons() { //sets Tasks and Buttons on FrameExam
        setButtonsInFrameExam();
        setNewTaskForButtons();
    }

    private void setButtonsInFrameExam() {
        listButtons.clear();
        JButtonColor button;
        for (int i = 0; i < (ROWS * COLUMNS) ; i++) {
            button = new JButtonColor(); // it is need (ROWS * COLUMNS) new Buttons with tasks
            button.setFont(new Font(null, Font.BOLD, 20));
            button.addActionListener(actionListener);
            listButtons.add(button); // add buttons to list
            panelExam.add(button); // add button of Task on FrameExam
        }
    }

    private void setNewTaskForButtons() {
        int intOperation = 0; // it is operation between numbers, example: + - *
        int intOne = 0; // first int
        int intTwo = 0; // second int
        int rightAnswer = 0; // right answer
        String question = ""; // question in task

        for (int i = 0; i < (ROWS * COLUMNS); i++) {
            intOperation = random.nextInt(3); // 0 it is *, 1 it is +, 2 it is -
            intOne = random.nextInt(100);
            intTwo = random.nextInt(100);

            switch (intOperation){ // depends of number in Operation, creating question and right answer
                case 0 : rightAnswer = intOne * intTwo;
                    question = intOne + " * " + intTwo + " = ?";
                    break;
                case 1 : rightAnswer = intOne + intTwo;
                    question = intOne + " + " + intTwo + " = ?";
                    break;
                case 2 : rightAnswer = intOne - intTwo;
                    question = intOne + " - " + intTwo + " = ?";
                    break;
            }
            JButtonColor buttonColor = listButtons.get(i);
            buttonColor.setText(question); // set text (question) of Task on button
            buttonColor.setRightAnswer(String.valueOf(rightAnswer));
            buttonColor.setEnabled(true);
            buttonColor.setBackground(null);
            buttonColor.setOpaque(false);
        }
    }

    public boolean checkEnableButtons() {  // checking if there are any enable buttons on FrameExam
        for (JButton button : listButtons) {
            if (button.isEnabled()) {
                return true;
            }
        }
        threadFlag = false;
        return false;
    }

    private class ExamWindowAdapter extends WindowAdapter {
        public void windowClosing(WindowEvent e) {// if close FrameExam
            if (FrameExam.this.getLanguage().equals("English")) { // to make English public static in FrameMenu to all of classes ???
                int answer = JOptionPane.showConfirmDialog(null, "Are sure to exit exam?", "Caution!", JOptionPane.YES_NO_OPTION);
                if (answer == JOptionPane.YES_OPTION) {
                    FrameExam.this.setVisible(false);
                    FrameExam.this.setNewTaskForButtons();
                    threadFlag = false;
                    labelTimer.setText("Timer " + (countTimer / 60) + " : " + (countTimer % 60));
                }
                return;
            }
            if (FrameExam.this.getLanguage().equals("Russian")) {
                int answer = JOptionPane.showConfirmDialog(null, "Вы уверены завершить экзамен?", "Внимание!", JOptionPane.YES_NO_OPTION);
                if (answer == JOptionPane.YES_OPTION) {
                    FrameExam.this.setVisible(false);
                    FrameExam.this.setNewTaskForButtons();
                    threadFlag = false;
                    labelTimer.setText("Timer " + (countTimer / 60) + " : " + (countTimer % 60));
                }
            }
        }
    }

    public void setTimer() {
        threadFlag = true;
        labelTimer.setText("Timer " + (countTimer / 60) + " : " + (countTimer % 60));
        Thread thread = new Thread() {
            @Override
            public void run() {
                int countTimerLocal = countTimer;
                while (countTimerLocal > 0 && threadFlag == true) { // main countdown
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    countTimerLocal--;
                    labelTimer.setText("Timer " + (countTimerLocal / 60) + " : " + (countTimerLocal % 60));
                }
                if (countTimerLocal == 0) {
                    timeIsOverExitExam(); // if time is over
                }
                this.interrupt();
            }
        };
        thread.start();
    }

    private void timeIsOverExitExam() {
            for (JButton button : listButtons) {
                button.setEnabled(false);
            }
            if (FrameExam.this.isVisible()) { // maybe to remove if ?
                if (FrameExam.this.getLanguage().equals("English")) {
                    JOptionPane.showMessageDialog(null, "Time is over! Exam FAILED");
                    threadFlag = false;
                    labelTimer.setText("Timer " + 0 + ":" + 0 + "    TIME IS OVER! ");
                } else {
                    JOptionPane.showMessageDialog(null, "Время вышло! Экзамен провален!");
                    threadFlag = false;
                    labelTimer.setText("Timer " + 0 + ":" + 0 + "    ВРЕМЯ ВЫШЛО! ");
                }
            }
    }

    public List<JButtonColor> getListButtons() {
        return listButtons;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }
}