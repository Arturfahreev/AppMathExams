package appMathExams;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;

public class ButtonListener implements ActionListener {
    static Random random = new Random();
    FrameMenu frameMenu; // need to invoke setter
    FrameExam frameExam; // need to invoke setter
    FrameTask frameTask; // need to invoke setter
    FrameResult frameResult; // need to invoke setter
    List<JButtonColor> listOfTaskButtons;
    List<JButtonColor> listOfResultButtons;
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
       for (JButtonColor buttonExam : listOfExamButtons ) {
           if (e.getSource() == buttonExam) {
               showingTaskFrameWithQuestionsAndAnswers(buttonExam);
               return;
           }
       }
       //checking whether pushed button right or wrong answer
        for (JButtonColor buttonTask : listOfTaskButtons) {
            if (e.getSource() == buttonTask) {
                checkingPushedButtonRightOrWrongAnswer(buttonTask);
                return;
            }
        }
        // sets interaction between buttons of FrameExam and buttons of FrameResult
        for (JButton button : listOfResultButtons) {
            if (e.getSource() == button) {
                setInteractionBetweenButtonsFrameExamAndFrameResult(button);
                return;
            }
        }
    }
    private void setInteractionBetweenButtonsFrameExamAndFrameResult(JButton button) {
        int index = listOfResultButtons.indexOf(button);
        JButtonColor textButton = listOfExamButtons.get(index);
        frameResult.getLabel().removeAll();
        frameResult.getLabel().setText(textButton.getText());
        frameResult.getLabelAnswer().setText("<html>" +  "RIGHT answer: " + textButton.getRightAnswer() + "<br />" + "Your answer: " + textButton.getUserAnswer() +  "</html>");
        frameResult.getLabelAnswer().setForeground(textButton.getColor());
    }

    private void checkingPushedButtonRightOrWrongAnswer(JButtonColor buttonTask) {
        int answer = JOptionPane.showConfirmDialog(null, "Are you sure?", "Caution!", JOptionPane.YES_NO_OPTION);
        if (answer == JOptionPane.YES_OPTION) {
            currentPushedExamButton.setEnabled(false);
            currentPushedExamButton.setBackground(Color.BLACK);
            currentPushedExamButton.setOpaque(true);
            currentPushedExamButton.setUserAnswer(buttonTask.getText());
            if (buttonTask.getText().equals(buttonTask.getRightAnswer())) {
                currentPushedExamButton.setColor(new Color(0, 170, 0));
                frameResult.setRightAnswers(frameResult.getRightAnswers() + 1); // need to nullify
            } else {
                currentPushedExamButton.setColor(new Color(250, 0, 0));
                frameResult.setWrongAnswers(frameResult.getWrongAnswers() + 1);
            }
            frameTask.setVisible(false);
            checkEnableButtons(); // checking is it end of exam ?
        }
    }

    private void showingTaskFrameWithQuestionsAndAnswers(JButtonColor buttonExam) {
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
            if (listOfTaskButtons.indexOf(buttonTask) == randomButton) {
                buttonTask.setText(buttonExam.getRightAnswer());
                buttonTask.setRightAnswer(buttonExam.getRightAnswer());
            }
        }
        frameTask.setVisible(true);
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

    // checking the end of exam and needing of showing FrameResult
    public void checkEnableButtons() {
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
            JButtonColor buttonResult = listOfResultButtons.get(i);
            JButtonColor buttonExam = listOfExamButtons.get(i);
            buttonResult.setBackground(buttonExam.getColor());
        }
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
