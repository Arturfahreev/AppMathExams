package appMathExams;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListener implements ActionListener {
    FrameMenu frameMenu;
    FrameExam frameExam;
    FrameTask frameTask;
    String text = "";

    public ButtonListener() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == frameMenu.buttonExam) {
            frameExam.setVisible(true);
            return;
        }

        for (JButton button : frameExam.getListButtons() ) {
            if (e.getSource() == button) {
                text = button.getText();

                frameTask.setVisible(false);
                frameTask.getLabelTask().removeAll();
                frameTask.getLabelTask().setText(text);

                int result = frameExam.getMapTask().get(text);
                this.setNewButtons(result);
                frameTask.setVisible(true);
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

    private void setNewButtons(int result) {
        String number = String.valueOf(result);

        frameTask.getjButton1().setText(number); // set text (number) of right button
        frameTask.getjButton1().setActionCommand(number); // remember in button right answer

        int numberRandom = result + 10;
        int numberRandom2 = numberRandom + 10;

        frameTask.getjButton2().setText(String.valueOf(numberRandom));
        frameTask.getjButton2().setActionCommand(""); // it is wrong answer

        frameTask.getjButton3().setText(String.valueOf(numberRandom2));
        frameTask.getjButton3().setActionCommand(""); // it is wrong answer
    }

    public void setFrameMenu(FrameMenu frameMenu) {
        this.frameMenu = frameMenu;
    }

    public void setFrameExam(FrameExam frameExam) {
        this.frameExam = frameExam;
    }

    public void setFrameTask(FrameTask frameTask) {
        this.frameTask = frameTask;
    }
}
