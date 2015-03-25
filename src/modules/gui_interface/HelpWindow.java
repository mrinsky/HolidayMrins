package modules.gui_interface;

import main.Resources;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class HelpWindow extends JFrame {

    private JTextArea helpArea;

    public HelpWindow(String path)
    {
        this.setTitle(Resources.language.getHELP());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setBounds(400, 100, 500, 600);
        this.setResizable(true);
        initComponents(path);
        setMinimumSize(new Dimension(500,600));
    }

    public static void main(final String path) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HelpWindow(path).setVisible(true);
            }
        });
    }

    private void initComponents(String path) {
        this.setLayout(new BorderLayout());
        helpArea = new JTextArea();
        File helpFile = new File(path);
        String message = "";
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(helpFile));
            while (fileReader.ready()) helpArea.append(fileReader.readLine()+"\n");
        } catch (FileNotFoundException e) {
            message = Resources.language.getIO_ERROR();
        } catch (IOException e) {
            message = Resources.language.getIO_ERROR();
        }
        if(!(message.isEmpty())) JOptionPane.showMessageDialog(null, message);
        this.add(helpArea, BorderLayout.CENTER);
        this.add(new JScrollPane(helpArea));
    }
}
