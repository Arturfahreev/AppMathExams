package appMathExams;

import javax.swing.*;
import java.awt.*;

public class Main {
    static Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    static FrameMenu frameMenu;
    static FrameExam frameExam;
    //static FrameTask frameTask;

    public static void main(String[] args) {
        Runnable runnable = () -> {
            ButtonListener buttonListener = new ButtonListener();
            frameMenu = new FrameMenu(buttonListener);
            frameExam = new FrameExam(buttonListener);
            //frameTask = new FrameTask();
        };
        SwingUtilities.invokeLater(runnable);
    }
}
