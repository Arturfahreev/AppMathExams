package appMathExams;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class MainFrame extends JFrame implements ActionListener {

    JLabel labelMain = new JLabel();
    JButton buttonExam = new JButton("EXAM");



    public static final int ROWS = 4;
    public static final int COLUMNS = 4;
    JFrame frameExam;
    JFrame frameTask;
    JPanel panelMain = new JPanel();
    ImageIcon imageBackground = new ImageIcon("Background.png");
    //JButton[] arrButton = new JButton[20];
    Random random = new Random();
    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    java.util.List<JButton> listButtons;
    JLabel labelTask = new JLabel();

    MainFrame() {
        labelMain.setLayout(null);
        labelMain.setIcon(new ImageIcon("Background.png"));

        buttonExam.setBounds(50, 50, 100, 50);
        buttonExam.setFont(new Font(null, Font.BOLD, 20));
        buttonExam.addActionListener(this);
        labelMain.add(buttonExam);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("MATH EXAMS");
        this.setResizable(false);
        this.add(labelMain);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void setFrameExam() {
        frameExam = new JFrame();
        frameExam.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frameExam.setLayout(new GridLayout(ROWS, COLUMNS));
        frameExam.setSize(screen.width, screen.height);
        frameExam.setResizable(false);
        frameExam.addWindowListener(new ExamWindowAdapter());

        setButtonsOnExamFrame();
        //setTasks();
    }

    private String setTasks(JButton button) {
        int intOperation = random.nextInt(3);
        int intOne = random.nextInt(100);
        int intTwo = random.nextInt(100);
        int result = 0;
        String resultStr = "";

            switch (intOperation) {
                case 0 : result = intOne * intTwo;
                resultStr = intOne + " * " + intTwo + " = ?";
                break;

                case 1 : result = intOne + intTwo;
                resultStr = intOne + " + " + intTwo + " = ?";
                break;

                case 2 : result = intOne - intTwo;
                resultStr = intOne + " - " + intTwo + " = ?";
                break;
            }
        button.setActionCommand(String.valueOf(result));
        return resultStr;
    }


    private void setButtonsOnExamFrame() {
        listButtons = new ArrayList<>();

        String name = "";
        String actionCommand = "";
        JButton button;

        for (int i = 0; i < (ROWS * COLUMNS); i++) {
            button = new JButton();
            button.setText(setTasks(button));
            //button.setActionCommand("Button" + (i + 1));
            button.addActionListener(this);

            listButtons.add(button);
            frameExam.add(button);
        }

    }

    private void setFrameTask() {
        frameTask = new JFrame();
        frameTask.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameTask.setLayout(new FlowLayout());
        frameTask.setSize(screen.width / 2, screen.height / 2);
        frameTask.setResizable(false);
        frameTask.setLocationRelativeTo(null);
        //frameTask.setAlwaysOnTop(true);
        frameTask.setVisible(false);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonExam) {
            setFrameExam();
            setFrameTask();
            //frameExam.setAlwaysOnTop(true);
            frameExam.setVisible(true);
            return;
        }

        for (JButton button : listButtons ) {
            if (e.getSource() == button) {
                frameTask.setVisible(false);
                frameTask.getContentPane().removeAll();
                labelTask.removeAll();

                labelTask.setText(button.getText());
                labelTask.setPreferredSize(new Dimension(frameTask.getWidth(), frameTask.getHeight() / 3));
                labelTask.setFont(new Font(null, Font.BOLD, 40));
                labelTask.setHorizontalAlignment(JLabel.CENTER);
                //labelTask.setHorizontalTextPosition(SwingConstants.CENTER);

                JButton jButton1 = new JButton();
                jButton1.setText(button.getActionCommand());
                jButton1.setActionCommand(button.getActionCommand());

                JButton jButton2 = new JButton();
                jButton2.setText(String.valueOf(Integer.parseInt(button.getActionCommand()) + random.nextInt(10)));
                jButton2.setActionCommand("");

                JButton jButton3 = new JButton();
                jButton3.setText(String.valueOf(Integer.parseInt(button.getActionCommand()) + random.nextInt(10)));
                jButton3.setActionCommand("");

                jButton1.addActionListener(this);
                jButton2.addActionListener(this);
                jButton3.addActionListener(this);

                frameTask.add(labelTask);
                frameTask.add(jButton1, FlowLayout.CENTER);
                frameTask.add(jButton2, FlowLayout.CENTER);
                frameTask.add(jButton3, FlowLayout.CENTER);
                frameTask.setVisible(true);
                return;
            }
        }

        if (e.getSource() instanceof JButton button) {
            System.out.println(button.getText());
            System.out.println(button.getActionCommand());

            if (button.getText().equals(button.getActionCommand())) {
                JOptionPane.showMessageDialog(null, "RIGHT answer!", "Answer", JOptionPane.INFORMATION_MESSAGE );
            } else {
                JOptionPane.showMessageDialog(null, "WRONG answer!", "Answer", JOptionPane.ERROR_MESSAGE );
            }
        }

    }

    private class ExamWindowAdapter extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            int answer = JOptionPane.showConfirmDialog(null, "Are sure to exit exam?", "Caution!", JOptionPane.YES_NO_OPTION);
            if (answer == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(null, "Exam is failed!", "Press Ok to exit", JOptionPane.WARNING_MESSAGE );
                frameExam.setVisible(false);
                //frameExam = null;
            }
        }
    }
}
