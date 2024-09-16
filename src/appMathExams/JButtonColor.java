package appMathExams;

import javax.swing.*;
import java.awt.*;

class JButtonColor extends JButton {
    private Color color;
    private String rightAnswer = " ";
    private String userAnswer = " ";

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }
}
