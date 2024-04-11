package appMathExams;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        Runnable runnable = () -> {

            ButtonListener buttonListener = new ButtonListener();

            FrameMenu frameMenu = new FrameMenu(buttonListener);
            FrameExam frameExam = new FrameExam(buttonListener);
            FrameTask frameTask = new FrameTask(buttonListener);

            buttonListener.setFrameMenu(frameMenu);
            buttonListener.setFrameExam(frameExam);
            buttonListener.setFrameTask(frameTask);
        };
        SwingUtilities.invokeLater(runnable);
    }
}
