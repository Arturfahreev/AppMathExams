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
    FrameResult frameResult; // need to invoke setter
    String question = "";
    String rightAnswerStr = "";
    List<JButton> listOfTaskButtons;
    List<JButton> listOfResultButtons;
    List<JButton> listOfExameButtons;
    JButton currentPushedExamButton;

    @Override
    public void actionPerformed(ActionEvent e) {

        // set FrameExam when EXAM button was pushed
        if (e.getSource() == frameMenu.getButtonExam()) {
            if (frameExam != null) {
                frameExam.setVisible(true);
                listOfExameButtons = frameExam.getListButtons();
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
                //currentPushedExamButton.setText(""); // erasing text on one of tasks in FrameExam
                if (button.getText().equals(button.getActionCommand())) {
                    JOptionPane.showMessageDialog(null, "RIGHT answer!", "Answer", JOptionPane.INFORMATION_MESSAGE );
                    currentPushedExamButton.setText("<html>" + currentPushedExamButton.getText() + "<br />" +  "RIGHT answer: " + rightAnswerStr + "<br />" + "Your answer: " + button.getText() +  "</html>");
                    currentPushedExamButton.setEnabled(false);
                    currentPushedExamButton.setBackground(Color.GREEN);
                    currentPushedExamButton.setOpaque(true);
                    frameResult.setRightAnswers(frameResult.getRightAnswers() + 1);
                    checkFrameResult();

                } else {
                    JOptionPane.showMessageDialog(null, "WRONG!\n" + "Right answer: " + rightAnswerStr , "Answer", JOptionPane.ERROR_MESSAGE );
                    currentPushedExamButton.setText("<html>" + currentPushedExamButton.getText() + "<br />" +  "RIGHT answer: " + rightAnswerStr + "<br />" + "Your answer: " + button.getText() +  "</html>");
                    currentPushedExamButton.setEnabled(false);
                    currentPushedExamButton.setBackground(Color.PINK);
                    currentPushedExamButton.setOpaque(true);
                    frameResult.setWrongAnswers(frameResult.getWrongAnswers() + 1);
                    checkFrameResult();
                }
                frameTask.setVisible(false);
                return;
            }
        }

        for (JButton button : listOfResultButtons) {
            if (e.getSource() == button) {
                System.out.println(button.getText());
                int index = listOfResultButtons.indexOf(button);
                JButton textButton = listOfExameButtons.get(index);
                frameResult.getLabel().removeAll();
                frameResult.getLabel().setText(textButton.getText());
                frameResult.getLabel().setBackground(textButton.getBackground());

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

    public void checkFrameResult() {
        if (frameExam.checkEnableButtons() == true) {
            return;
        } else {
            frameTask.setVisible(false);
            frameResult.setListOfTasks(frameExam.getListButtons());
            if (frameResult.getWrongAnswers() > 2) {
                JOptionPane.showMessageDialog(null, "Exam is FAILED", "WARNING!", JOptionPane.ERROR_MESSAGE);
            } else JOptionPane.showMessageDialog(null, "Exam is PASSED", "Congratulations!", JOptionPane.INFORMATION_MESSAGE);

            frameResult.setVisible(true);
            listOfResultButtons = frameResult.getListOfButtons();
            setColorOfResultButtons();
        }

    }

    private void setColorOfResultButtons() {
        for (int i = 0; i < listOfResultButtons.size(); i++) {
            JButton buttonResult = listOfResultButtons.get(i);
            JButton buttonExam = listOfExameButtons.get(i);
            buttonResult.setBackground(buttonExam.getBackground());
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

    public void setFrameResult(FrameResult frameResult) {
        this.frameResult = frameResult;
    }
}
