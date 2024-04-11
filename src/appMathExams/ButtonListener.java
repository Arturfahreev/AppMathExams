package appMathExams;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListener implements ActionListener {
    FrameMenu frameMenu;
    FrameExam frameExam;
    FrameTask frameTask;
    String question = "";

    public ButtonListener() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == frameMenu.getButtonExam()) {
            if (frameExam != null) {
                frameExam.setVisible(true);
            }
            return;
        }

        if (frameExam != null && frameTask != null) {
            for (JButton button : frameExam.getListButtons() ) {
                if (e.getSource() == button) {
                    question = button.getText();

                    frameTask.setVisible(false);
                    frameTask.getLabelTask().removeAll();
                    frameTask.getLabelTask().setText(question);

                    int rightAnswer = frameExam.getMapTask().get(question);
                    this.setNewButtons(rightAnswer);
                    frameTask.setVisible(true);
                    return;
                }
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

    private void setNewButtons(int rightAnswer) {
        String rightAnswerStr = String.valueOf(rightAnswer);

        frameTask.getjButton1().setText(rightAnswerStr); // set text (number) of right button
        frameTask.getjButton1().setActionCommand(rightAnswerStr); // remember in button right answer

        int numberRandom = rightAnswer + 10;
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
