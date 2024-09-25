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
    List<JButtonColor> listOfTaskButtons;
    List<JButton> listOfResultButtons;
    List<JButtonColor> listOfExamButtons;
    JButtonColor currentPushedExamButton;
    @Override
    public void actionPerformed(ActionEvent e) {
        // set FrameExam when EXAM button was pushed
        if (e.getSource() == frameMenu.getButtonExam()) {
            setFrameExamWhenExamButtonPushed();
            return;
        }
        // showing TaskFrame with questions and answers
        //if (frameExam != null && frameTask != null) {
//            if (listOfTaskButtons == null) {
//                listOfTaskButtons = frameTask.getListOfButtons();
//            }
            for (JButtonColor buttonExam : listOfExamButtons ) {
                if (e.getSource() == buttonExam) {
                    currentPushedExamButton = buttonExam;
                    frameTask.setVisible(false);
                    frameTask.getLabelTask().removeAll();
                    frameTask.getLabelTask().setText(buttonExam.getText());

                    int rightAnswer = Integer.parseInt(buttonExam.getRightAnswer()); // receive right answer
                    int wrongAnswer = rightAnswer + 10;
                    int randomButton = random.nextInt(listOfTaskButtons.size());

                    for (JButtonColor buttonTask : listOfTaskButtons) {
                        buttonTask.setText(String.valueOf(wrongAnswer)); //set random answer to all buttons
                        wrongAnswer += 10;
                        if (listOfTaskButtons.indexOf(buttonTask) == randomButton) { // ???
                            buttonTask.setText(buttonExam.getRightAnswer());
                            buttonTask.setRightAnswer(buttonExam.getRightAnswer());
                        }
                    }
                    //this.setNewButtons(rightAnswer); //set right answer to random button
                    frameTask.setVisible(true);
                    return;
                }
            }
        //}

        //checking whether pushed button right or wrong answer
        for (JButtonColor buttonTask : listOfTaskButtons) {
            if (e.getSource() == buttonTask) {
                int answer = JOptionPane.showConfirmDialog(null, "Are you sure?", "Caution!", JOptionPane.YES_NO_OPTION);
                if (answer == JOptionPane.YES_OPTION) {
                    if (buttonTask.getText().equals(buttonTask.getRightAnswer())) {
                        currentPushedExamButton.setEnabled(false);
                        currentPushedExamButton.setBackground(Color.BLACK);
                        currentPushedExamButton.setOpaque(true);
                        currentPushedExamButton.setColor(new Color(0, 170, 0));
                        currentPushedExamButton.setUserAnswer(buttonTask.getText());
                        frameResult.setRightAnswers(frameResult.getRightAnswers() + 1); // need to nullify
                        checkFrameResult(); // checking is it end of exam ?
                    } else {
                        currentPushedExamButton.setEnabled(false);
                        currentPushedExamButton.setBackground(Color.BLACK);
                        currentPushedExamButton.setOpaque(true);
                        currentPushedExamButton.setColor(new Color(250, 0, 0));
                        currentPushedExamButton.setUserAnswer(buttonTask.getText());
                        frameResult.setWrongAnswers(frameResult.getWrongAnswers() + 1);
                        checkFrameResult(); // checking is it end of exam ?
                    }
                    frameTask.setVisible(false);
                    return;
                }
                return;
            }
        }
        // sets interaction between buttons of FrameExam and buttons of FrameResult
        //if (listOfResultButtons != null) {
            for (JButton button : listOfResultButtons) {
                if (e.getSource() == button) {
                    int index = listOfResultButtons.indexOf(button);
                    JButtonColor textButton = listOfExamButtons.get(index);
                    JLabel label = frameResult.getLabel();
                    JLabel labelAnswer = frameResult.getLabelAnswer();
                    label.removeAll();
                    label.setText(textButton.getText());
                    labelAnswer.setText("<html>" +  "RIGHT answer: " + textButton.getRightAnswer() + "<br />" + "Your answer: " + textButton.getUserAnswer() +  "</html>");
                    labelAnswer.setForeground(textButton.getColor());
                }
            }
        //}
    }

    private void setFrameExamWhenExamButtonPushed() {
        if (frameExam != null && frameTask != null && frameResult != null) {
            frameExam.setTimer();
            frameExam.setVisible(true);
            frameExam.setLanguage(frameMenu.getLanguage()); // need to make global variable of language
            frameTask.setLanguage(frameMenu.getLanguage());
            frameResult.setLanguage(frameMenu.getLanguage());
            frameResult.setWrongAnswers(0);
            frameResult.setRightAnswers(0);
        }
        return;
    }

    public void setListsOfButtons() {
        if (listOfTaskButtons == null && frameTask != null) {
            listOfTaskButtons = frameTask.getListOfButtons();
        }
        if (listOfResultButtons == null && frameResult != null) {
            listOfResultButtons = frameResult.getListOfButtons();
        }
        if (listOfExamButtons == null && frameExam != null) {
            listOfExamButtons = frameExam.getListButtons();
        }
    }

    // checking the end of exam and needing of showing FrameResult
    public void checkFrameResult() {
        if (frameExam.checkEnableButtons() == true) { // if there is at least one enable FrameExam button then exam continues
            return;
        } else {
            frameTask.setVisible(false);
            setColorOfResultButtons();
            if (frameResult.getWrongAnswers() > 0) {
                JOptionPane.showMessageDialog(null, "Exam is FAILED. Count of wrong answers: " + frameResult.getWrongAnswers(), "WARNING!", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Exam is PASSED. Count of right answers: " + frameResult.getRightAnswers(), "Congratulations!", JOptionPane.INFORMATION_MESSAGE);
            }
            frameResult.getLabel().setText("Choose the Task");
            frameResult.getLabelAnswer().setText(" ");
            frameResult.setVisible(true);
        }
    }

    private void setColorOfResultButtons() {
        for (int i = 0; i < listOfResultButtons.size(); i++) {
            JButton buttonResult = listOfResultButtons.get(i);
            JButton buttonExam = listOfExamButtons.get(i);
            if (buttonExam instanceof JButtonColor jButtonColor) {
                buttonResult.setBackground(jButtonColor.getColor());
            }
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
