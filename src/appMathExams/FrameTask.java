package appMathExams;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class FrameTask extends JFrame {
    static Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    private JLabel labelTask = new JLabel();
    private ActionListener actionListener;

    private JButton jButton1 = new JButton();
    private JButton jButton2 = new JButton();
    private JButton jButton3 = new JButton();
    private JButton jButton4 = new JButton();


    public FrameTask(ActionListener actionListener) {
        this.actionListener = actionListener;

        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setLayout(new FlowLayout());
        this.setSize(screen.width / 2, screen.height / 2);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        labelTask.setPreferredSize(new Dimension((this.getWidth()), this.getHeight() / 2));
        labelTask.setFont(new Font(null, Font.BOLD, 40));
        labelTask.setHorizontalAlignment(JLabel.CENTER);

        jButton1.addActionListener(actionListener);
        jButton2.addActionListener(actionListener);
        jButton3.addActionListener(actionListener);
        jButton4.addActionListener(actionListener);

        this.add(labelTask);
        this.add(jButton1, FlowLayout.CENTER);
        this.add(jButton2, FlowLayout.CENTER);
        this.add(jButton3, FlowLayout.CENTER);
        this.add(jButton4, FlowLayout.CENTER);

        this.setVisible(false);
    }

    public JLabel getLabelTask() {
        return labelTask;
    }

    public JButton getjButton1() {
        return jButton1;
    }

    public JButton getjButton2() {
        return jButton2;
    }

    public JButton getjButton3() {
        return jButton3;
    }

    public JButton getjButton4() {
        return jButton4;
    }
}
