package appMathExams;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        Runnable runnable = () -> {

            ButtonListener buttonListener = new ButtonListener();

            FrameExam frameExam = new FrameExam(buttonListener);
            FrameMenu frameMenu = new FrameMenu(buttonListener, frameExam);
            FrameTask frameTask = new FrameTask(buttonListener);
            FrameResult frameResult = new FrameResult(buttonListener);

            buttonListener.setFrameMenu(frameMenu);
            buttonListener.setFrameExam(frameExam);
            buttonListener.setFrameTask(frameTask);
            buttonListener.setFrameResult(frameResult);
            buttonListener.setListsOfButtons();
        };
        SwingUtilities.invokeLater(runnable);
    }
}
