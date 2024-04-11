package appMathExams;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;
import java.util.List;

public class FrameExam extends JFrame {
    static final int ROWS = 4;
    static final int COLUMNS = 4;
    static Random random = new Random();
    static Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

    private ActionListener actionListener;

    private java.util.List<JButton> listButtons = new ArrayList<>();
    private java.util.Map<String, Integer> mapTask = new HashMap<>();

    public FrameExam(ActionListener actionListener) {
        this.actionListener = actionListener;

        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setLayout(new GridLayout(ROWS, COLUMNS));
        this.setSize(screen.width, screen.height);
        this.setResizable(false);
        this.addWindowListener(new ExamWindowAdapter());
        this.setVisible(false);

        setTasksAndButtons();
    }

    private void setButtons () {

    }

    public void setTasksAndButtons() { //sets Tasks and Buttons on Frame Exam
        listButtons.clear(); // clear list of buttons
        mapTask.clear(); // clear map of Tasks

        JButton button;
        int intOperation = 0;
        int intOne = 0;
        int intTwo = 0;
        int rightAnswer = 0;
        String question = "";

        for (int i = 0; i < (ROWS * COLUMNS); i++) {
            intOperation = random.nextInt(3);
            intOne = random.nextInt(100);
            intTwo = random.nextInt(100);
            button = new JButton();
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
            button.setText(question); // set text of Task on button
            listButtons.add(button); // add buttons to list
            this.add(button); // add button of Task on Frame Exam
            mapTask.put(question, rightAnswer); // put Task and right answer to map
        }

    }

    private class ExamWindowAdapter extends WindowAdapter {
        public void windowClosing(WindowEvent e) { // if close Exam Frame
            int answer = JOptionPane.showConfirmDialog(null, "Are sure to exit exam?", "Caution!", JOptionPane.YES_NO_OPTION);
            if (answer == JOptionPane.YES_OPTION) {
                FrameExam.this.setVisible(false);
                FrameExam.this.getContentPane().removeAll(); // remove all from Exam Frame
                FrameExam.this.setTasksAndButtons(); // set new Tasks and Buttons before user push Exam button
            }
        }
    }

    public List<JButton> getListButtons() {
        return listButtons;
    }

    public Map<String, Integer> getMapTask() {
        return mapTask;
    }
}
