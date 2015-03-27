package modules.gui_interface;

import main.Resources;
import model.User;
import modules.user_interface.MainMenu;
import modules.user_interface.UserHandler;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.math.BigInteger;

public class RegistrationForm extends JFrame{

    //region Components
    JTextField loginTF;
    JTextField onePassTF;
    JTextField twoPassTF;
    JLabel wrongPass;
    JButton okButton = new JButton("ОК");
    //endregion

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
        JLabel loginLabel = new JLabel(Resources.language.getENTER_LOGIN());
        loginLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        frameBox.add(loginLabel);
        loginTF = new JTextField(15);
        frameBox.add(loginTF);

        frameBox.add(Box.createVerticalStrut(10));
        JLabel onePassLabel = new JLabel(Resources.language.getENTER_PASSWORD());
        onePassLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        frameBox.add(onePassLabel);
        onePassTF = new JTextField(15);
        onePassTF.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                wrongPass.setVisible(false);
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                wrongPass.setVisible(false);
            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        });

        frameBox.add(onePassTF);
        frameBox.add(Box.createVerticalStrut(10));

        JLabel twoPassLabel = new JLabel(Resources.language.getREPEAT_PASSWORD());
        twoPassLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        frameBox.add(twoPassLabel);
        twoPassTF = new JTextField(15);
        twoPassTF.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                wrongPass.setVisible(false);
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                wrongPass.setVisible(false);
            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        });
        frameBox.add(twoPassTF);

        frameBox.add(Box.createVerticalStrut(10));
        initWrongPass();
        frameBox.add(wrongPass);
        frameBox.add(Box.createVerticalStrut(10));
        okButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        frameBox.add(okButton);
        frameBox.add(Box.createVerticalStrut(10));
        frameBox.setBorder(new EmptyBorder(5,5,5,5));
        return frameBox;


    }

    private void initWrongPass() {
        wrongPass = new JLabel(Resources.language.getPASS_EXCEPTION());
        wrongPass.setVisible(false);
        wrongPass.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        wrongPass.setForeground(Color.red);
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
                try {
                    registration();
                    MainWindow.main(false);
                }
                catch (IllegalArgumentException ex) {
                    wrongPass.setText(ex.getMessage());
                    wrongPass.setVisible(true);
                }
            }
        });

    }
    public void registration(){
        UserHandler.registration(loginTF.toString(), onePassTF.getText(), twoPassTF.getText());
    }
}
