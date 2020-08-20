import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread;
import javax.swing.border.LineBorder;

public class Main {

    private TypingThread TypingThread;
    private SpamThread SpamThread;
    private boolean TypingThreadToggle = true;
    private boolean SpamThreadToggle = true;

    private ImageIcon scaledDown = new ImageIcon(new ImageIcon("Logo.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));

    public static void main(String[] args) {
        UIManager.put("ToolTip.background", new Color(39,40,34));

        UIManager.put("ToolTip.border", Color.black);

        UIManager.put("ToolTip.font", new Font("Tahoma",Font.BOLD,10));

        UIManager.put("ToolTip.foreground", new Color(85,85,255));

        new Main();
    }

    private Main() {
        JFrame MainFrame = new JFrame();

        MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MainFrame.setSize(400,350);

        MainFrame.setTitle("Discord tools");

        MainFrame.setResizable(false);

        MainFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("Logo.png")));

        JPanel ParentPanel = new JPanel();

        ParentPanel.setLayout(new GridLayout(7,1));

        ParentPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JLabel Title = new JLabel("Discord tools");

        Title.setFont(new Font("Impact", Font.BOLD, 20));

        Color gray = new Color(40, 40, 40);

        Title.setForeground(gray);

        JPanel TitlePanel = new JPanel();

        TitlePanel.setLayout(new FlowLayout());

        TitlePanel.add(Title, SwingConstants.CENTER);

        ParentPanel.add(TitlePanel);

        JTextField TextField = new JTextField(12);

        Color navy = new Color(36, 48, 88);

        TextField.setBorder(new LineBorder(navy,3,false));

        TextField.setText("");

        TextField.addActionListener(e -> {
            String Input = TextField.getText();
            TextField.setText("");
            String[] parts = Input.split(" ");
            int len = parts.length;
            String copyMeh = "";
            StringBuilder sb = new StringBuilder(copyMeh);

            for (int i = 0 ; i < len ; i++) {
                sb.append(parts[i]);
                if (i != (len - 1)) sb.append("\n");
            }

            StringSelection sel = new StringSelection(sb.toString());
            Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
            clip.setContents(sel, sel);

            windowsFeel();

            swingFeel();
        });

        TextField.setToolTipText("String to be split into words and copied to clipboard");

        TextField.setFont(new Font("Segoe UI Black", Font.PLAIN, 10));

        TextField.setSelectionColor(new Color(204,153,0));

        TextField.setHorizontalAlignment(JTextField.CENTER);

        TextField.setCaretColor(gray);

        TextField.setPreferredSize(new Dimension(80,25));

        JPanel WordPanel = new JPanel();

        WordPanel.setLayout(new FlowLayout());

        WordPanel.add(TextField, SwingConstants.CENTER);

        JLabel strings = new JLabel("<html><font color=rgb(15,15,15)>Strings:</html>");

        strings.setFont(new Font("Arial Black", Font.BOLD, 10));

        strings.setForeground(gray);

        WordPanel.add(strings, SwingConstants.CENTER);

        ParentPanel.add(WordPanel);

        JTextField CharField = new JTextField(12);

        CharField.setBorder(new LineBorder(navy,3,false));

        CharField.setText("");

        CharField.addActionListener(e -> {
            String Input = CharField.getText().replace(" ","");
            CharField.setText("");
            char[] chars = Input.toCharArray();
            int len = chars.length;
            String copyMeh = "";
            StringBuilder sb = new StringBuilder(copyMeh);

            for (int i = 0 ; i < len ; i++) {
                sb.append(chars[i]);
                if (i != (len - 1)) sb.append("\n");
            }

            StringSelection sel = new StringSelection(sb.toString());
            Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
            clip.setContents(sel, sel);

            windowsFeel();

            swingFeel();
        });

        CharField.setToolTipText("String to be split into characters and copied to clipboard");

        CharField.setFont(new Font("Segoe UI Black", Font.PLAIN, 10));

        CharField.setSelectionColor(new Color(204,153,0));

        CharField.setHorizontalAlignment(JTextField.CENTER);

        CharField.setCaretColor(gray);

        CharField.setPreferredSize(new Dimension(80,25));

        JPanel BottomWordPanel = new JPanel();

        BottomWordPanel.setLayout(new FlowLayout());

        JLabel chars = new JLabel("<html><font color=rgb(15,15,15)>Chars:</html>");

        chars.setFont(new Font("Arial Black", Font.BOLD, 10));

        chars.setForeground(gray);

        BottomWordPanel.add(CharField, SwingConstants.CENTER);

        BottomWordPanel.add(chars, SwingConstants.CENTER);

        ParentPanel.add(BottomWordPanel);

        JTextField SpamField = new JTextField(12);

        SpamField.setBorder(new LineBorder(navy,3,false));

        SpamField.setText("");

        SpamField.addActionListener(e -> {
            windowsFeel();

            JOptionPane.showMessageDialog(null,"Make sure to enter in a delay in MS and a valid string to spam then press\n" +
                    "the start spam button.", "Not quite there buddy", JOptionPane.ERROR_MESSAGE,scaledDown);

            swingFeel();
        });

        SpamField.setToolTipText("String to be spammed every ms delay period");

        SpamField.setFont(new Font("Segoe UI Black", Font.PLAIN, 10));

        SpamField.setSelectionColor(new Color(204,153,0));

        SpamField.setHorizontalAlignment(JTextField.CENTER);

        SpamField.setCaretColor(gray);

        SpamField.setPreferredSize(new Dimension(80,25));

        JPanel SpamFieldPanel = new JPanel();

        SpamFieldPanel.setLayout(new FlowLayout());

        JLabel SpamLabel = new JLabel("<html><font color=rgb(15,15,15)>Spam:</html>");

        SpamLabel.setFont(new Font("Arial Black", Font.BOLD, 10));

        SpamLabel.setForeground(gray);

        SpamFieldPanel.add(SpamField, SwingConstants.CENTER);

        SpamFieldPanel.add(SpamLabel, SwingConstants.CENTER);

        ParentPanel.add(SpamFieldPanel);

        JTextField SpamDelay = new JTextField(12);

        SpamDelay.setBorder(new LineBorder(navy,3,false));

        SpamDelay.setText("");

        SpamDelay.addActionListener(e -> {
            windowsFeel();

            JOptionPane.showMessageDialog(null,"Make sure to enter in a delay in MS and a valid string to spam then press\n" +
                    "the start spam button.", "Not quite there buddy", JOptionPane.ERROR_MESSAGE,scaledDown);

            swingFeel();
        });

        SpamDelay.setToolTipText("MS delay for spam intervals");

        SpamDelay.setFont(new Font("Segoe UI Black", Font.PLAIN, 10));

        SpamDelay.setSelectionColor(new Color(204,153,0));

        SpamDelay.setHorizontalAlignment(JTextField.CENTER);

        SpamDelay.setCaretColor(gray);

        SpamDelay.setPreferredSize(new Dimension(80,25));

        JPanel SpamDelayPanel = new JPanel();

        SpamDelayPanel.setLayout(new FlowLayout());

        JLabel SpamDelayLabel = new JLabel("<html><font color=rgb(15,15,15)>Spam delay:</html>");

        SpamDelayLabel.setFont(new Font("Arial Black", Font.BOLD, 10));

        SpamDelayLabel.setForeground(gray);

        SpamDelayPanel.add(SpamDelay, SwingConstants.CENTER);

        SpamDelayPanel.add(SpamDelayLabel, SwingConstants.CENTER);

        ParentPanel.add(SpamDelayPanel);

        JButton Start = new JButton("Start Always Typing");

        Start.setFocusPainted(false);

        Start.setFont(new Font("Segoe UI Black", Font.BOLD, 10));

        Start.setBackground(new Color(138,118,231));

        Start.setForeground(Color.black);

        Start.addActionListener(e -> {
            if (TypingThreadToggle) {
                TypingThreadToggle = false;
                Start.setText("Stop Always Typing");
                TypingThread = new TypingThread(false, 4000);
            }

            else {
                TypingThreadToggle = true;
                Start.setText("Start Always Typing");
                TypingThread.kill();
            }
        });

        Start.setPreferredSize(new Dimension(200,25));

        Start.setBorder(new LineBorder(navy,3,false));

        JPanel StartPanel = new JPanel();

        StartPanel.setLayout(new FlowLayout());

        StartPanel.add(Start, SwingConstants.CENTER);

        ParentPanel.add(StartPanel);

        JButton StringSpam = new JButton("Start String Spam");

        StringSpam.setFocusPainted(false);

        StringSpam.setFont(new Font("Segoe UI Black", Font.BOLD, 10));

        StringSpam.setBackground(new Color(138,118,231));

        StringSpam.setForeground(Color.black);

        StringSpam.addActionListener(e -> {
            if (SpamThreadToggle) {
                SpamThreadToggle = false;
                StringSpam.setText("Stop String Spam");
                SpamThread = new SpamThread(false, Integer.parseInt(SpamDelay.getText()), SpamField.getText());
            }

            else {
                SpamThreadToggle = true;
                StringSpam.setText("Start String Spam");
                SpamThread.kill();
            }
        });

        StringSpam.setPreferredSize(new Dimension(200,25));

        StringSpam.setBorder(new LineBorder(navy,3,false));

        JPanel StringSpamPanel = new JPanel();

        StringSpamPanel.setLayout(new FlowLayout());

        StringSpamPanel.add(StringSpam, SwingConstants.CENTER);

        ParentPanel.add(StringSpamPanel);

        ParentPanel.setBorder(new LineBorder(navy,10,false));

        MainFrame.add(ParentPanel);

        MainFrame.setLocationRelativeTo(null);

        MainFrame.repaint();

        MainFrame.revalidate();

        MainFrame.setVisible(true);
    }

    private void swingFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());

            Frame bodgeFrame = new JFrame();

            SwingUtilities.updateComponentTreeUI(bodgeFrame);
        }

        catch (Exception ex) {
            windowsFeel();

            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);

            JOptionPane.showMessageDialog(null,"An unfortunate error occured.\nMaybe Nathan is an idiot?\n" +
                    "We won't know unless you email him the following error at: NathanJavaDevelopment@gmail.com\nError: " + sw.toString(), "Error", JOptionPane.ERROR_MESSAGE,scaledDown);

            swingFeel();
        }
    }

    private void windowsFeel() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

            JFrame bodgeFrame = new JFrame();

            SwingUtilities.updateComponentTreeUI(bodgeFrame);
        }

        catch (Exception ex) {
            windowsFeel();

            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);

            JOptionPane.showMessageDialog(null,"An unfortunate error occured.\nMaybe Nathan is an idiot?\n" +
                    "We won't know unless you email him the following error at: NathanJavaDevelopment@gmail.com\nError: " + sw.toString(), "Error", JOptionPane.ERROR_MESSAGE,scaledDown);

            swingFeel();
        }
    }
}

class TypingThread  {

    private boolean exit;
    private boolean toggle = true;
    private int delayInMS;
    private Robot rob; {
        try {
            rob = new Robot();
        }
        catch (AWTException e) {
            e.printStackTrace();
        }
    }

    TypingThread(boolean ex, int delay) {
        exit = ex;
        delayInMS = delay;
        Thread tread = new Thread(() -> {
            while (!exit) {
                try {
                    if (toggle) {
                        rob.keyPress(KeyEvent.VK_SEMICOLON);
                        rob.keyRelease(KeyEvent.VK_SEMICOLON);
                        Thread.sleep(delayInMS);
                        toggle = !toggle;
                    }

                    else {
                        rob.keyPress(KeyEvent.VK_BACK_SPACE);
                        rob.keyRelease(KeyEvent.VK_BACK_SPACE);
                        Thread.sleep(delayInMS);
                        toggle = !toggle;
                    }
                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(null, "Error stack trace: " + exc, "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        tread.start();
    }

    void kill() {
        exit = true;
    }
}

class SpamThread  {

    private boolean exit;
    private String spam;
    private boolean toggle = true;
    private int delayInMS;
    private Robot rob; {
        try {
            rob = new Robot();
        }
        catch (AWTException e) {
            e.printStackTrace();
        }
    }

    SpamThread(boolean ex, int delay, String spm) {
        exit = ex;
        spam = spm;
        delayInMS = delay;
        Thread tread = new Thread(() -> {
            while (!exit) {
                try {
                    if (toggle) {
                        Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
                        StringSelection strSel = new StringSelection(spam);
                        clip.setContents(strSel, null);
                        rob.keyPress(KeyEvent.VK_CONTROL);
                        rob.keyPress(KeyEvent.VK_V);
                        rob.keyRelease(KeyEvent.VK_V);
                        rob.keyRelease(KeyEvent.VK_CONTROL);
                        toggle = !toggle;
                    }

                    else {
                        rob.keyPress(KeyEvent.VK_ENTER);
                        rob.keyRelease(KeyEvent.VK_ENTER);
                        Thread.sleep(delayInMS);
                        toggle = !toggle;
                    }
                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(null, "Error stack trace: " + exc, "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        tread.start();
    }

    void kill() {
        exit = true;
    }
}