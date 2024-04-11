package appMathExams;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class ButtonListener implements ActionListener {
    static Random random = new Random();

    FrameMenu frameMenu; // need to invoke setter
    FrameExam frameExam; // need to invoke setter
    FrameTask frameTask; // need to invoke setter
    String question = "";

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
        int number2 = rightAnswer + 10;
        int number3 = number2 + 10;
        int number4 = number3 + 10;
        int intOperation = random.nextInt(4);

        switch (intOperation) {
            case 0 :
                frameTask.getjButton1().setText(rightAnswerStr); // set text (number) of right button
                frameTask.getjButton1().setActionCommand(rightAnswerStr); // remember in button

                frameTask.getjButton2().setText(String.valueOf(number2));
                frameTask.getjButton2().setActionCommand(""); // it is wrong answer

                frameTask.getjButton3().setText(String.valueOf(number3));
                frameTask.getjButton3().setActionCommand(""); // it is wrong answer

                frameTask.getjButton4().setText(String.valueOf(number4));
                frameTask.getjButton4().setActionCommand(""); // it is wrong answer
                break;

            case 1 :
                frameTask.getjButton1().setText(String.valueOf(number2));
                frameTask.getjButton1().setActionCommand("");

                frameTask.getjButton2().setText(rightAnswerStr);
                frameTask.getjButton2().setActionCommand(rightAnswerStr);

                frameTask.getjButton3().setText(String.valueOf(number3));
                frameTask.getjButton3().setActionCommand("");

                frameTask.getjButton4().setText(String.valueOf(number4));
                frameTask.getjButton4().setActionCommand("");

                break;

            case 2 :
                frameTask.getjButton1().setText(String.valueOf(number2));
                frameTask.getjButton1().setActionCommand("");

                frameTask.getjButton2().setText(String.valueOf(number3));
                frameTask.getjButton2().setActionCommand("");

                frameTask.getjButton3().setText(rightAnswerStr);
                frameTask.getjButton3().setActionCommand(rightAnswerStr);

                frameTask.getjButton4().setText(String.valueOf(number4));
                frameTask.getjButton4().setActionCommand("");
                break;

            case 3 :
                frameTask.getjButton1().setText(String.valueOf(number2));
                frameTask.getjButton1().setActionCommand("");

                frameTask.getjButton2().setText(String.valueOf(number3));
                frameTask.getjButton2().setActionCommand("");

                frameTask.getjButton3().setText(String.valueOf(number4));
                frameTask.getjButton3().setActionCommand("");

                frameTask.getjButton4().setText(rightAnswerStr);
                frameTask.getjButton4().setActionCommand(rightAnswerStr);
                break;
        }
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
