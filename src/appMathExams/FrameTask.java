package appMathExams;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class FrameTask extends JFrame {
    JLabel labelTask = new JLabel();
    ActionListener actionListener;

    JButton jButton1 = new JButton();
    JButton jButton2 = new JButton();
    JButton jButton3 = new JButton();


    public FrameTask() {
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setLayout(new FlowLayout());
        this.setSize(Main.screen.width / 2, Main.screen.height / 2);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(false);

        labelTask.setPreferredSize(new Dimension((int) (this.getWidth()), (int) this.getHeight() / 2));
        labelTask.setFont(new Font(null, Font.BOLD, 40));
        labelTask.setHorizontalAlignment(JLabel.CENTER);

        this.add(labelTask);
        this.add(jButton1, FlowLayout.CENTER);
        this.add(jButton2, FlowLayout.CENTER);
        this.add(jButton3, FlowLayout.CENTER);
    }

    public void setActionListener(ActionListener actionListener) {
        this.actionListener = actionListener;

        jButton1.addActionListener(actionListener);
        jButton2.addActionListener(actionListener);
        jButton3.addActionListener(actionListener);
    }

}
