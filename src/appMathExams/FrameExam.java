package appMathExams;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;
import java.util.List;
import java.util.Timer;

public class FrameExam extends JFrame {
    static final int ROWS = 4; // rows of Task on FrameExam
    static final int COLUMNS = 4; //columns of Task on FrameExam
    static Random random = new Random();
    private Timer timer;
    private Thread thread;
    boolean threadFlag = true;
    int count;
    private String language = "English";
    private JPanel panelExam = new JPanel();
    private JPanel panelTimer = new JPanel();
    private JLabel labelTimer = new JLabel();
    static Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    private ActionListener actionListener;
    private java.util.List<JButton> listButtons = new ArrayList<>(); // list of buttons Tasks on FrameExam

    public FrameExam(ActionListener actionListener) {
        this.actionListener = actionListener;

        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        //this.setLayout(new GridLayout(ROWS, COLUMNS));
        this.setSize(screen.width, screen.height);
        this.setResizable(false);
        this.addWindowListener(new ExamWindowAdapter());
        this.setVisible(false);
        this.setLayout(null);

        panelTimer.setBounds(0, 0, this.getWidth(), 30);
        panelExam.setBounds(0, 30, this.getWidth(), this.getHeight() - 40);
        panelExam.setLayout(new GridLayout(ROWS, COLUMNS));

        panelTimer.setLayout(new BorderLayout());

        labelTimer.setBackground(Color.BLACK);
        labelTimer.setOpaque(true);
        labelTimer.setForeground(Color.RED);
        labelTimer.setFont(new Font(null, Font.BOLD, 20));
        labelTimer.setText("Timer: ");
        labelTimer.setHorizontalAlignment(JLabel.CENTER);
        panelTimer.add(labelTimer);

        this.add(panelTimer);
        this.add(panelExam);

        setTasksAndButtons();
    }

    public void setTimer() {
        count = 1200;
        threadFlag = true;
        thread = new Thread() {
            @Override
            public void run() {
                int min = 0;
                int sec = 0;
                while (count > 0 && threadFlag == true) {

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    count--;
                    min = count / 60;
                    sec = count % 60;
                    labelTimer.setText("Timer " + min + ":" + sec);
                }
                if (count == 0) {
                    for (JButton button : listButtons) {
                        button.setEnabled(false);
                    }
                    if (FrameExam.this.isVisible()) {
                        if (FrameExam.this.getLanguage().equals("English")) {
                            JOptionPane.showMessageDialog(null, "Time is over! Exam FAILED");
                        } else {
                            JOptionPane.showMessageDialog(null, "Время вышло! Экзамен провален!");
                        }
                    }
                }
                this.interrupt();
            }
        };
        thread.start();
    }

    public void setTasksAndButtons() { //sets Tasks and Buttons on FrameExam
        //listButtons.clear(); // clear list of buttons

        JButtonColor button;
        int intOperation = 0;
        int intOne = 0;
        int intTwo = 0;
        int rightAnswer = 0;
        String question = "";

        for (int i = 0; i < (ROWS * COLUMNS); i++) {
            intOperation = random.nextInt(3);
            intOne = random.nextInt(100);
            intTwo = random.nextInt(100);
            button = new JButtonColor();
            button.setFont(new Font(null, Font.BOLD, 20));
            button.addActionListener(actionListener);

            switch (intOperation) {
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
            button.setText(question); // set text (question) of Task on button
            button.setRightAnswer(String.valueOf(rightAnswer));
            listButtons.add(button); // add buttons to list
            panelExam.add(button); // add button of Task on Frame Exam
        }
    }
    // checking if there are any enable buttons on FrameExam
    public boolean checkEnableButtons() {
        for (JButton button : listButtons) {
            if (button.isEnabled()) {
                return true;
            }
        }
        threadFlag = false;
        return false;
    }

    public List<JButton> getListButtons() {
        return listButtons;
    }

    public void setNewTasks() {
        int intOperation = 0;
        int intOne = 0;
        int intTwo = 0;
        int rightAnswer = 0;
        String question = "";
        JButtonColor button;

        for (int i = 0; i < (ROWS * COLUMNS); i++) {
            intOperation = random.nextInt(3);
            intOne = random.nextInt(100);
            intTwo = random.nextInt(100);
            button = (JButtonColor) listButtons.get(i);

            switch (intOperation) {
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
            button.setText(question); // set text (question) of Task on button
            button.setRightAnswer(String.valueOf(rightAnswer));
            button.setEnabled(true);
            button.setBackground(null);
            button.setOpaque(false);
        }
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }

    private class ExamWindowAdapter extends WindowAdapter {
        public void windowClosing(WindowEvent e) {// if close Exam Frame
            if (FrameExam.this.getLanguage().equals("English")) {
                int answer = JOptionPane.showConfirmDialog(null, "Are sure to exit exam?", "Caution!", JOptionPane.YES_NO_OPTION);
                if (answer == JOptionPane.YES_OPTION) {
                    FrameExam.this.setVisible(false);
                    FrameExam.this.setNewTasks();
                    threadFlag = false;
                }
                return;
            }
            if (FrameExam.this.getLanguage().equals("Russian")) {
                int answer = JOptionPane.showConfirmDialog(null, "Вы уверены завершить экзамен?", "Внимание!", JOptionPane.YES_NO_OPTION);
                if (answer == JOptionPane.YES_OPTION) {
                    FrameExam.this.setVisible(false);
                    FrameExam.this.setNewTasks();
                    threadFlag = false;
                }
            }
        }
    }
}

class JButtonColor extends JButton {
    private Color color;
    private String rightAnswer = " ";
    private String userAnswer = " ";

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }


}
