package appMathExams;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class FrameMenu extends JFrame {
    JLabel labelMain = new JLabel();
    static JButton buttonExam = new JButton("EXAM");

    ActionListener actionListener;


    public FrameMenu(ActionListener actionListener) {
        this.actionListener = actionListener;

        labelMain.setLayout(null);
        labelMain.setIcon(new ImageIcon("Background.png"));

        buttonExam.setBounds(50, 50, 100, 50);
        buttonExam.setFont(new Font(null, Font.BOLD, 20));
        buttonExam.addActionListener(actionListener);
        labelMain.add(buttonExam);


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("MATH EXAMS");
        this.setResizable(false);
        this.add(labelMain);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
