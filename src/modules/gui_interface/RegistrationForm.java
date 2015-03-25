package modules.gui_interface;

import model.User;
import modules.user_interface.MainMenu;
import modules.user_interface.UserHandler;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.math.BigInteger;

/**
 * Created by Михаил on 19.03.2015.
 */
public class RegistrationForm extends JFrame{
   JTextField loginTF;
    JTextField onePassTF;
    JTextField twoPassTF;
    JLabel wrongPass = new JLabel("<html><u><font  color = red >Пароли не совпадают!</font></u>");
    JButton okButton = new JButton("ОК");



    public RegistrationForm(){
        super("Registration");

        setContentPane(addComponents());
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        addListener();
    }
    public Box addComponents(){
        Box frameBox = Box.createVerticalBox();
        Box.createHorizontalBox();
        JLabel loginLabel = new JLabel("Введите логин: ");
        loginLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        frameBox.add(loginLabel);
        loginTF = new JTextField(15);
        frameBox.add(loginTF);
        frameBox.add(Box.createVerticalStrut(10));
        JLabel onePassLabel = new JLabel("Введите пароль: ");
        onePassLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        frameBox.add(onePassLabel);
        onePassTF = new JTextField(15);
        frameBox.add(onePassTF);
        frameBox.add(Box.createVerticalStrut(10));
        JLabel twoPassLabel = new JLabel("Повторите пароль: ");
        twoPassLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        frameBox.add(twoPassLabel);
        twoPassTF = new JTextField(15);
        frameBox.add(twoPassTF);
        frameBox.add(Box.createVerticalStrut(10));
        frameBox.add(wrongPass);
        frameBox.add(okButton);

        frameBox.setBorder(new EmptyBorder(5,5,5,5));
        return frameBox;


    }
    public void addListener(){
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                LoginWindow loginWindow = new LoginWindow();
                loginWindow.setVisible(true);
                dispose();


            }
        });
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registration();
            }
        });

    }
    public void registration(){
        UserHandler.registration(loginTF.toString(), onePassTF.toString(), twoPassTF.toString());
    }
}
