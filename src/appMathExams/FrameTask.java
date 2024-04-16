package appMathExams;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class FrameTask extends JFrame {
    static Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    private JLabel labelTask = new JLabel();
    private ActionListener actionListener;
    private JPanel panelTask = new JPanel();
    private JPanel panelAnswers = new JPanel();

    private java.util.List<JButton> listOfButtons = new ArrayList<>();

    public FrameTask(ActionListener actionListener) {
        this.actionListener = actionListener;
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setLayout(new FlowLayout());
        this.setSize(screen.width / 2, screen.height / 2);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        labelTask.setBounds(0, 0, this.getWidth(), this.getHeight() / 2);
        labelTask.setFont(new Font(null, Font.BOLD, 40));
        labelTask.setHorizontalAlignment(JLabel.CENTER);
        labelTask.setOpaque(true);
        labelTask.setBackground(Color.BLACK);
        labelTask.setForeground(Color.WHITE);

        panelTask.setBounds(0, 0, this.getWidth(), this.getHeight() / 2 - 20);
        panelTask.setLayout(null);
        panelTask.add(labelTask);

        panelAnswers.setBounds(0, this.getHeight() / 2 - 15, this.getWidth(), this.getHeight() / 2 - 10);
        panelAnswers.setLayout(new GridLayout(2, 3, 10, 10));

        addButtonsOnPanelAnswers();

        this.setLayout(null);
        this.add(panelTask);
        this.add(panelAnswers);
        this.setVisible(false);
    }

    private void addButtonsOnPanelAnswers() {
        JButton button;
        for (int i = 0; i < 8; i++) {
            button = new JButtonColor();
            button.addActionListener(actionListener);
            button.setFont(new Font(null, Font.BOLD, 30));
            listOfButtons.add(button);
            panelAnswers.add(button);
        }
    }

    public JLabel getLabelTask() {
        return labelTask;
    }

    public List<JButton> getListOfButtons() {
        return listOfButtons;
    }
}
