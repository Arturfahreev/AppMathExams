package appMathExams;

import javax.swing.*;
import java.awt.*;

public class FrameTask extends JFrame {
    static JLabel labelTask = new JLabel();


    public FrameTask() {
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setLayout(new FlowLayout());
        this.setSize(Main.screen.width / 2, Main.screen.height / 2);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(false);

        labelTask.setPreferredSize(new Dimension((int) (this.getWidth()), (int) this.getHeight() / 2));
        labelTask.setFont(new Font(null, Font.BOLD, 40));
        labelTask.setHorizontalAlignment(JLabel.CENTER);

        this.add(labelTask);
    }

}
