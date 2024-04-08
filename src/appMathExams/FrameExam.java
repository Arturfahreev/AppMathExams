package appMathExams;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;

public class FrameExam extends JFrame {

    public static final int ROWS = 4;
    public static final int COLUMNS = 4;
    static java.util.List<JButton> listButtons = new ArrayList<>();
    static Random random = new Random();
    static java.util.Map<String, Integer> mapTask = new HashMap<>();

    public FrameExam(ActionListener actionListener) {
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setLayout(new GridLayout(ROWS, COLUMNS));
        this.setSize(Main.screen.width, Main.screen.height);
        this.setResizable(false);
        this.addWindowListener(new ExamWindowAdapter());

        setTasksAndButtons(actionListener);
        //setButtonsOnExamFrame(actionListener);
    }

//    private void setButtonsOnExamFrame(ActionListener actionListener) {
//        JButton button;
//
//        for (int i = 0; i < (ROWS * COLUMNS); i++) {
//            button = new JButton();
//            //button.setText(mapTask.());
//            button.addActionListener(actionListener);
//            listButtons.add(button);
//            this.add(button);
//        }
//    }

    private void setTasksAndButtons(ActionListener actionListener) {
        JButton button;
        int intOperation = 0;
        int intOne = 0;
        int intTwo = 0;
        int resultInt = 0;
        String resultStr = "";

        for (int i = 0; i < (ROWS * COLUMNS); i++) {
            intOperation = random.nextInt(3);
            intOne = random.nextInt(100);
            intTwo = random.nextInt(100);
            button = new JButton();
            button.addActionListener(actionListener);

            switch (intOperation) {
                case 0 : resultInt = intOne * intTwo;
                    resultStr = intOne + " * " + intTwo + " = ?";
                    break;
                case 1 : resultInt = intOne + intTwo;
                    resultStr = intOne + " + " + intTwo + " = ?";
                    break;
                case 2 : resultInt = intOne - intTwo;
                    resultStr = intOne + " - " + intTwo + " = ?";
                    break;
            }

            button.setText(resultStr);
            listButtons.add(button);
            this.add(button);
            mapTask.put(resultStr, resultInt);
        }

    }

    private static class ExamWindowAdapter extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            int answer = JOptionPane.showConfirmDialog(null, "Are sure to exit exam?", "Caution!", JOptionPane.YES_NO_OPTION);
            if (answer == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(null, "Exam is failed!", "Press Ok to exit", JOptionPane.WARNING_MESSAGE );
                Main.frameExam.setVisible(false);
            }
        }
    }

}
