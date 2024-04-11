package appMathExams;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ButtonListener implements ActionListener {
    static Random random = new Random();

    FrameMenu frameMenu; // need to invoke setter
    FrameExam frameExam; // need to invoke setter
    FrameTask frameTask; // need to invoke setter
    String question = "";
    String rightAnswerStr = "";
    List<JButton> listOfButtons;

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
        rightAnswerStr = String.valueOf(rightAnswer);
        listOfButtons = frameTask.getListOfButtons();
        int wrongAnswer = rightAnswer + 10;
        int randomButton = random.nextInt(listOfButtons.size());

        for (JButton button : listOfButtons) {
            button.setText(String.valueOf(wrongAnswer));
            button.setActionCommand("");
            wrongAnswer += 10;
        }
        listOfButtons.get(randomButton).setText(rightAnswerStr); //set right answer to random button
        listOfButtons.get(randomButton).setActionCommand(rightAnswerStr); // can to check that buttons contains right answer
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
