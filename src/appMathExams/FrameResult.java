package appMathExams;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FrameResult extends JFrame {
    static Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    private JPanel panel1 = new JPanel();
    private JPanel panel2 = new JPanel();
    private JLabel label = new JLabel();
    private JLabel labelAnswer = new JLabel();
    private int rightAnswers = 0;
    private int wrongAnswers = 0;
    private ActionListener actionListener;
    private java.util.List<JButton> listOfButtons = new ArrayList<>();

    public FrameResult(ActionListener actionListener) {
        this.actionListener = actionListener;
        this.setSize((int) (screen.width * 0.6), (int)(screen.height * 0.75));
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(null);

        panel1.setBounds(0, 0, (int) (this.getWidth() * 0.2), this.getHeight() - 20);
        panel1.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        panel1.setLayout(new GridLayout(16, 1, 0, 5));

        panel2.setBounds((int) (this.getWidth() * 0.2), 0, (int) (this.getWidth() * 0.8), this.getHeight() - 20);
        panel2.setLayout(new GridLayout(2,1, 0, 0));

        label.setLayout(new FlowLayout());
        label.setBackground(Color.GRAY);
        label.setForeground(Color.WHITE);
        label.setFont(new Font(null, Font.BOLD, 30));
        label.setOpaque(true);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        labelAnswer.setFont(new Font(null, Font.BOLD, 30));
        labelAnswer.setOpaque(true);
        labelAnswer.setVerticalAlignment(JLabel.CENTER);
        labelAnswer.setHorizontalAlignment(JLabel.CENTER);

        panel2.add(label);
        panel2.add(labelAnswer);

        setListOfButtons();

        this.add(panel1);
        this.add(panel2);
        this.setVisible(false);
    }

    public void setListOfButtons() {
        JButton button;
        for (int i = 1; i < FrameExam.COLUMNS * FrameExam.ROWS + 1; i++) {
            button = new JButton();
            button.setText("Task " + i);
            button.addActionListener(actionListener);
            button.setOpaque(true);
            panel1.add(button);
            listOfButtons.add(button);
        }
    }
    public int getRightAnswers() {
        return rightAnswers;
    }

    public int getWrongAnswers() {
        return wrongAnswers;
    }

    public void setRightAnswers(int rightAnswers) {
        this.rightAnswers = rightAnswers;
    }

    public void setWrongAnswers(int wrongAnswers) {
        this.wrongAnswers = wrongAnswers;
    }

    public List<JButton> getListOfButtons() {
        return listOfButtons;
    }

    public JLabel getLabel() {
        return label;
    }

    public JLabel getLabelAnswer() {
        return labelAnswer;
    }
}
