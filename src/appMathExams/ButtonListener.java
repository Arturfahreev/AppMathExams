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
    List<JButton> listOfExamButtons;
    JButton currentPushedExamButton;

    @Override
    public void actionPerformed(ActionEvent e) {

        // set FrameExam when EXAM button was pushed
        if (e.getSource() == frameMenu.getButtonExam()) {
            if (frameExam != null) {
                frameExam.setVisible(true);
                listOfExamButtons = frameExam.getListButtons();
                frameResult.setWrongAnswers(0);
                frameResult.setRightAnswers(0);
            }
            return;
        }

        // showing TaskFrame with questions and answers
        if (frameExam != null && frameTask != null) {
            if (listOfTaskButtons == null) {
                listOfTaskButtons = frameTask.getListOfButtons();
            }
            for (JButton button : listOfExamButtons ) {
                if (e.getSource() == button) {
                    currentPushedExamButton = button;
                    question = button.getText();

                    frameTask.setVisible(false);
                    frameTask.getLabelTask().removeAll();
                    frameTask.getLabelTask().setText(question);

                    if (button instanceof JButtonColor jButtonColor) {
                        int rightAnswer = Integer.parseInt(jButtonColor.getRightAnswer()); // receive right answer
                        int wrongAnswer = rightAnswer + 10;
                        int randomButton = random.nextInt(listOfTaskButtons.size());

                        for (JButton buttonTask : listOfTaskButtons) {
                            if (buttonTask instanceof JButtonColor jButtonColorTask) {
                                jButtonColorTask.setText(String.valueOf(wrongAnswer)); //set random answer to all buttons
                                wrongAnswer += 10;
                                if (listOfTaskButtons.indexOf(jButtonColorTask) == randomButton) {
                                    jButtonColorTask.setText(jButtonColor.getRightAnswer());
                                    jButtonColorTask.setRightAnswer(jButtonColor.getRightAnswer());
                                }
                            }
                        }
                        //this.setNewButtons(rightAnswer); //set right answer to random button
                        frameTask.setVisible(true);
                    }
                    return;
                }
            }
        }

        //checking whether pushed button right or wrong answer
        for (JButton button : listOfTaskButtons) {
            if (e.getSource() == button) {

                if (button instanceof JButtonColor jButtonColorTask) {

                    if (jButtonColorTask.getText().equals(jButtonColorTask.getRightAnswer())) { // if button.gettext() equals button.getActionCommand then right button was pushed
                        JOptionPane.showMessageDialog(null, "RIGHT answer!", "Answer", JOptionPane.INFORMATION_MESSAGE );

                        currentPushedExamButton.setEnabled(false);
                        currentPushedExamButton.setBackground(Color.BLACK);
                        currentPushedExamButton.setOpaque(true);

                        if (currentPushedExamButton instanceof JButtonColor jButtonColor) {
                            jButtonColor.setColor(new Color(0, 170, 0));
                            jButtonColor.setUserAnswer(button.getText());
                        }

                        frameResult.setRightAnswers(frameResult.getRightAnswers() + 1); // need to nullify
                        checkFrameResult(); // checking is it end of exam ?

                    } else {
                        JOptionPane.showMessageDialog(null, "WRONG!\n" + "Right answer: " + rightAnswerStr , "Answer", JOptionPane.ERROR_MESSAGE );

                        currentPushedExamButton.setEnabled(false);
                        currentPushedExamButton.setBackground(Color.BLACK);
                        currentPushedExamButton.setOpaque(true);

                        if (currentPushedExamButton instanceof JButtonColor jButtonColor) {
                            jButtonColor.setColor(new Color(250, 0, 0));
                            jButtonColor.setUserAnswer(button.getText());
                        }

                        frameResult.setWrongAnswers(frameResult.getWrongAnswers() + 1);
                        checkFrameResult(); // checking is it end of exam ?
                    }
                    frameTask.setVisible(false);
                    return;
                }
            }
        }

        // sets interaction between buttons of FrameExam and buttons of FrameResult
        for (JButton button : listOfResultButtons) {
            if (e.getSource() == button) {
                int index = listOfResultButtons.indexOf(button);
                JButton textButton = listOfExamButtons.get(index);

                JLabel label = frameResult.getLabel();
                JLabel labelAnswer = frameResult.getLabelAnswer();

                label.removeAll();
                label.setText(textButton.getText());

                if (textButton instanceof JButtonColor jButtonColor) {
                    labelAnswer.setText("<html>" +  "RIGHT answer: " + jButtonColor.getRightAnswer() + "<br />" + "Your answer: " + jButtonColor.getUserAnswer() +  "</html>");
                }
            }
        }
    }
    //sets new text of buttons with random right answer
//    private void setNewButtons(int rightAnswer) {
//        rightAnswerStr = String.valueOf(rightAnswer);
//        int wrongAnswer = rightAnswer + 10;
//        int randomButton = random.nextInt(listOfTaskButtons.size());
//
//        for (JButton button : listOfTaskButtons) {
//            button.setText(String.valueOf(wrongAnswer)); //set random answer to all buttons
//            button.setActionCommand(""); //this means that button is wrong
//            wrongAnswer += 10;
//        }
//        listOfTaskButtons.get(randomButton).setText(rightAnswerStr); //set right answer to random button
//        listOfTaskButtons.get(randomButton).setActionCommand(rightAnswerStr); // can to check that buttons contains right answer
//    }
    // checking the end of exam and needing of showing FrameResult
    public void checkFrameResult() {
        if (frameExam.checkEnableButtons() == true) { // if there is at least one enable FrameExam button then exam continues
            return;
        } else {
            frameTask.setVisible(false);
            listOfResultButtons = frameResult.getListOfButtons();
            setColorOfResultButtons();

            if (frameResult.getWrongAnswers() > 2) {
                JOptionPane.showMessageDialog(null, "Exam is FAILED", "WARNING!", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Exam is PASSED", "Congratulations!", JOptionPane.INFORMATION_MESSAGE);
            }
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
