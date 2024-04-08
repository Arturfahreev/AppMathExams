package appMathExams;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListener implements ActionListener {
    JButton jButton1 = new JButton();
    JButton jButton2 = new JButton();
    JButton jButton3 = new JButton();

    public ButtonListener() {
        jButton1.addActionListener(this);
        jButton2.addActionListener(this);
        jButton3.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == FrameMenu.buttonExam) {
            Main.frameExam.setVisible(true);
            Main.frameTask.add(FrameTask.labelTask);
            Main.frameTask.add(jButton1, FlowLayout.CENTER);
            Main.frameTask.add(jButton2, FlowLayout.CENTER);
            Main.frameTask.add(jButton3, FlowLayout.CENTER);
            return;
        }

        for (JButton button : FrameExam.listButtons ) {
            if (e.getSource() == button) {
                Main.frameTask.setVisible(false);
                FrameTask.labelTask.removeAll();
                FrameTask.labelTask.setText(button.getText());

                jButton1.setText(button.getActionCommand());
                jButton1.setActionCommand(button.getActionCommand());
                jButton2.setText(String.valueOf(Integer.parseInt(button.getActionCommand()) + FrameExam.random.nextInt(10)));
                jButton2.setActionCommand("");
                jButton3.setText(String.valueOf(Integer.parseInt(button.getActionCommand()) + FrameExam.random.nextInt(10)));
                jButton3.setActionCommand("");

                Main.frameTask.setVisible(true);
                return;
            }
        }

        if (e.getSource() instanceof JButton button) {
            System.out.println(button.getText());
            System.out.println(button.getActionCommand());

            if (button.getText().equals(button.getActionCommand())) {
                JOptionPane.showMessageDialog(null, "RIGHT answer!", "Answer", JOptionPane.INFORMATION_MESSAGE );
            } else {
                JOptionPane.showMessageDialog(null, "WRONG answer!", "Answer", JOptionPane.ERROR_MESSAGE );
            }
        }
    }
}
