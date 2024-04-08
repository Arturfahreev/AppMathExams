package appMathExams;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Random;

public class FrameExam extends JFrame {

    public static final int ROWS = 4;
    public static final int COLUMNS = 4;
    static java.util.List<JButton> listButtons = new ArrayList<>();
    static Random random = new Random();

    public FrameExam(ActionListener actionListener) {
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setLayout(new GridLayout(ROWS, COLUMNS));
        this.setSize(Main.screen.width, Main.screen.height);
        this.setResizable(false);
        this.addWindowListener(new ExamWindowAdapter());
        System.out.println();
        setButtonsOnExamFrame(actionListener);
    }

    private void setButtonsOnExamFrame(ActionListener actionListener) {
        String name = "";
        JButton button;

        for (int i = 0; i < (ROWS * COLUMNS); i++) {
            button = new JButton();
            name = setTasks(button);
            button.setText(name);
            button.addActionListener(actionListener);
            listButtons.add(button);
            this.add(button);
        }
    }

    private String setTasks(JButton button) {
        int intOperation = random.nextInt(3);
        int intOne = random.nextInt(100);
        int intTwo = random.nextInt(100);
        int result = 0;
        String resultStr = "";

        switch (intOperation) {
            case 0 : result = intOne * intTwo;
                resultStr = intOne + " * " + intTwo + " = ?";
                break;
            case 1 : result = intOne + intTwo;
                resultStr = intOne + " + " + intTwo + " = ?";
                break;
            case 2 : result = intOne - intTwo;
                resultStr = intOne + " - " + intTwo + " = ?";
                break;
        }
        button.setActionCommand(String.valueOf(result));
        return resultStr;
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
