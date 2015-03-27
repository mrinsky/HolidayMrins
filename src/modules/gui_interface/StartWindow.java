/*
package modules.gui_interface;

import main.Resources.Strings_EN;
import languages.Strings_RU;
import main.Resources;
import modules.functional.FileWorking;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class StartWindow extends JFrame {

    private JComboBox localeList;
    private JButton chooseButton;

    public StartWindow() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Language");
        this.setBounds(200, 200, 220, 60);
        this.setResizable(false);
        initComponents();
    }

    public static void main() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StartWindow().setVisible(true);
            }
        });
    }

    private void initComponents() {
        this.setLayout(new BorderLayout());
        initLocaleList();
        initButton();
    }

    private void initLocaleList() {
        localeList = new JComboBox();
        localeList.setPreferredSize(new Dimension(150, 60));
        for (String s : Resources.locales) localeList.addItem(s);
        this.getContentPane().add(localeList, BorderLayout.WEST);
    }

    private void initButton() {
        chooseButton = new JButton("OK");
        chooseButton.setPreferredSize(new Dimension(50, 60));
        this.getContentPane().add(chooseButton, BorderLayout.EAST);

        chooseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int choice = localeList.getSelectedIndex();

                Container container = chooseButton;
                String message = "";

                do {
                    container = container.getParent();
                } while (!(container instanceof StartWindow));

                switch (choice) {
                    case 0:
                        Resources.language = new Strings_RU();
                        try {
                            FileWorking.readAllRU();
                        } catch (ClassNotFoundException e) {
                            message = Resources.language.getIO_ERROR();
                        } catch (IOException e) {
                            message = Resources.language.getIO_ERROR();
                        }
                        if (!message.isEmpty()) JOptionPane.showMessageDialog(null, message);

                        processEvent(new WindowEvent((StartWindow) container, WindowEvent.WINDOW_CLOSING));
                        MainWindow.main();
                        break;
                    case 1:
                        Resources.language = new Strings_EN();
                        try {
                            FileWorking.readAllEN();
                        } catch (ClassNotFoundException e) {
                            Resources.out.println(Resources.language.getIO_ERROR());
                        } catch (IOException e) {
                            Resources.out.println(Resources.language.getIO_ERROR());
                        }
                        processEvent(new WindowEvent((StartWindow) container, WindowEvent.WINDOW_CLOSING));
                        MainWindow.main();
                        break;
                }
            }
        });
    }


}
*/
