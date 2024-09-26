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
    private JMenuItem menuItemSetTimer10Minutes = new JMenuItem("Set Timer 10 min");
    private JMenuItem menuItemSetTimer20Minutes = new JMenuItem("Set Timer 20 min");
    private JMenu subMenuSetTimer = new JMenu("Set Timer");
    private ActionListener actionListener;
    private FrameExam frameExam;

    public FrameMenu(ActionListener actionListener, FrameExam frameExam) {
        this.frameExam = frameExam;
        this.actionListener = actionListener;

        labelMain.setLayout(null);
        labelMain.setIcon(new ImageIcon("Background.png"));

        buttonExam.setBounds(30, 50, 150, 50);
        buttonExam.setFont(new Font(null, Font.BOLD, 20));
        buttonExam.addActionListener(actionListener);
        labelMain.add(buttonExam);

        menuItemChangeLanguage.addActionListener(this);
        //menuItemSetTimer.addActionListener(this);
        menuItemSetTimer10Minutes.addActionListener(this);
        menuItemSetTimer20Minutes.addActionListener(this);
        subMenuSetTimer.add(menuItemSetTimer10Minutes);
        subMenuSetTimer.add(menuItemSetTimer20Minutes);
        menuSettings.add(menuItemChangeLanguage);
        menuSettings.add(subMenuSetTimer);
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
        if (e.getSource() == menuItemSetTimer10Minutes) {
            frameExam.setCountTimer(599);
            return;
        }
        if (e.getSource() == menuItemSetTimer20Minutes) {
            frameExam.setCountTimer(1199);
            return;
        }
    }

    public String getLanguage() {
        return language;
    }
}

enum Language {ENGLISH, RUSSIAN}
