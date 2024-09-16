package appMathExams;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameMenu extends JFrame implements ActionListener {
    private JLabel labelMain = new JLabel();
    private JButton buttonExam = new JButton("EXAM");
    private String language = "English";
    private JMenuBar menuBar = new JMenuBar();
    private JMenu menuSettings = new JMenu("Settings");
    private JMenuItem menuItemChangeLanguage = new JMenuItem("Change to Russian");
    private ActionListener actionListener;

    public FrameMenu(ActionListener actionListener) {
        this.actionListener = actionListener;

        labelMain.setLayout(null);
        labelMain.setIcon(new ImageIcon("Background.png"));

        buttonExam.setBounds(50, 50, 100, 50);
        buttonExam.setFont(new Font(null, Font.BOLD, 20));
        buttonExam.addActionListener(actionListener);
        labelMain.add(buttonExam);

        menuItemChangeLanguage.addActionListener(this);
        menuSettings.add(menuItemChangeLanguage);
        menuBar.add(menuSettings);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("MATH EXAMS");
        this.setResizable(false);
        this.add(labelMain);
        this.setJMenuBar(menuBar);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public JButton getButtonExam() {
        return buttonExam;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menuItemChangeLanguage) {
            if (language.equals("English")) {
                language = "Russian";
                menuSettings.setText("Настройки");
                menuItemChangeLanguage.setText("Поменять на английский");
                buttonExam.setText("Экзамен");
                return;
            }
            if (language.equals("Russian")) {
                language = "English";
                menuSettings.setText("Settings");
                menuItemChangeLanguage.setText("Change to Russian");
                buttonExam.setText("EXAM");
                return;
            }
        }
    }

    public String getLanguage() {
        return language;
    }
}
