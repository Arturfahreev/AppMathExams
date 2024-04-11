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
    List<JButton> listOfTaskButtons;
    JButton currentPushedExamButton;

    @Override
    public void actionPerformed(ActionEvent e) {

        // set FrameExam when EXAM button was pushed
        if (e.getSource() == frameMenu.getButtonExam()) {
            if (frameExam != null) {
                frameExam.setVisible(true);
            }
            return;
        }

        // showing TaskFrame with questions and answers
        if (frameExam != null && frameTask != null) {
            if (listOfTaskButtons == null) {
                listOfTaskButtons = frameTask.getListOfButtons();
            }
            for (JButton button : frameExam.getListButtons() ) {
                if (e.getSource() == button) {
                    currentPushedExamButton = button;
                    question = button.getText();

                    frameTask.setVisible(false);
                    frameTask.getLabelTask().removeAll();
                    frameTask.getLabelTask().setText(question);

                    try {
                        int rightAnswer = frameExam.getMapTask().get(question); // receive right answer
                        this.setNewButtons(rightAnswer); //set right answer to random button
                        frameTask.setVisible(true);
                        return;
                    } catch (NullPointerException ex) {
                        return;
                    }

                }
            }
        }

        //checking whether pushed button right or wrong answer
        for (JButton button : listOfTaskButtons) {
            if (e.getSource() == button) {
                currentPushedExamButton.setText(""); // erasing text on one of tasks in FrameExam

                if (button.getText().equals(button.getActionCommand())) {
                    JOptionPane.showMessageDialog(null, "RIGHT answer!", "Answer", JOptionPane.INFORMATION_MESSAGE );
                } else {
                    JOptionPane.showMessageDialog(null, "WRONG answer!", "Answer", JOptionPane.ERROR_MESSAGE );
                }
                frameTask.setVisible(false);
                return;
            }
        }
    }

    private void setNewButtons(int rightAnswer) {
        rightAnswerStr = String.valueOf(rightAnswer);
        //listOfTaskButtons = frameTask.getListOfButtons();
        int wrongAnswer = rightAnswer + 10;
        int randomButton = random.nextInt(listOfTaskButtons.size());

        for (JButton button : listOfTaskButtons) {
            button.setText(String.valueOf(wrongAnswer));
            button.setActionCommand("");
            wrongAnswer += 10;
        }
        listOfTaskButtons.get(randomButton).setText(rightAnswerStr); //set right answer to random button
        listOfTaskButtons.get(randomButton).setActionCommand(rightAnswerStr); // can to check that buttons contains right answer
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
