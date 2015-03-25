package modules.gui_interface;

import languages.Language;
import languages.Strings_EN;
import languages.Strings_RU;
import modules.user_interface.UserHandler;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Михаил on 17.03.2015.
 */
public class LoginWindow extends JFrame {
    public static Language language = new Strings_EN();
    private JTextField loginTextField;
    private JPasswordField passTextField;
    JLabel regLabelButton;
    JLabel wrongPassLabel = new JLabel("<html><font  color = red>WRONG PASSWORD OR LOGIN!</font>");
    JComboBox langComboBox;

    private JButton okButton = new JButton("Ok");
    private JButton guestButton = new JButton("Guest mode");
    private JButton guestButtonInfo = new JButton("?");


    public LoginWindow() {
        super("Login");   // В дальнейшем сделать константой
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(addComponentsToForm());
        pack();
        setResizable(false);
        setLocationRelativeTo(null);// По центру экрана
        addListener();


    }
    public static void main(){
        LoginWindow loginWindow = new LoginWindow();
        loginWindow.setVisible(true);
    }
    public String getLogin() {
        return loginTextField.getText();
    }

    public String getPassword() {
        return String.valueOf(passTextField.getPassword());
    }

    public Box addComponentsToForm() {
        Box boxLogin = Box.createHorizontalBox();
        JLabel logLabel = new JLabel("Login:");
        boxLogin.add(logLabel);
        boxLogin.add(Box.createHorizontalStrut(10));
        loginTextField = new JTextField(15);
        boxLogin.add(loginTextField);

        Box boxPass = Box.createHorizontalBox();
        JLabel passLabel = new JLabel("Password:");
        boxPass.add(passLabel);
        boxPass.add(Box.createHorizontalStrut(10));
        passTextField = new JPasswordField(15);
        boxPass.add(passTextField);

        Box languageBox = Box.createHorizontalBox();
        JLabel languageLabel = new JLabel("Language:");
        langComboBox = new JComboBox();
        langComboBox.setPreferredSize(passTextField.getPreferredSize());
        langComboBox.addItem("English");
        langComboBox.addItem("Русский");
        languageBox.add(languageLabel);
        languageBox.add(Box.createHorizontalStrut(10));
        languageBox.add(langComboBox);


        Box boxButtons = Box.createHorizontalBox();
        boxButtons.add(guestButton);
        boxButtons.add(Box.createHorizontalStrut(1));
        boxButtons.add(guestButtonInfo); //Как уменьшить размер кнопки
        guestButton.setToolTipText("В гостевом режиме нельзя создавать и изменять события. "  +
                "Для добавления личных чобытий пройдите регистрацию или авторизуйтесь.");
        boxButtons.add(Box.createHorizontalGlue());
        boxButtons.add(okButton);

        Box boxRegistration = Box.createHorizontalBox();
        JLabel regText = new JLabel("<html><font  color = black >Нет учетной записи?</font>");
        regLabelButton = new JLabel("<html><u><font  color = blue>Зарегистрируйся!</font></u>");
        regLabelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Box.createHorizontalGlue();
        boxRegistration.add(regText);
        boxRegistration.add(regLabelButton);

        Box wrongPassBox = Box.createHorizontalBox();
        wrongPassBox.add(Box.createHorizontalStrut(70));
        wrongPassBox.add(wrongPassLabel);
        wrongPassLabel.setVisible(false);


        logLabel.setPreferredSize(passLabel.getPreferredSize()); // чтобы рамка логина  не выступала за рамку  пароля
        languageLabel.setPreferredSize(passLabel.getPreferredSize());
        Box frameBox = Box.createVerticalBox();
        frameBox.setBorder(new EmptyBorder(6, 6, 6, 6));
        frameBox.add(boxLogin);
        frameBox.add(Box.createVerticalStrut(7));
        frameBox.add(boxPass);
        frameBox.add(Box.createVerticalStrut(7));
        frameBox.add(languageBox);
        frameBox.add(Box.createVerticalStrut(3));
        frameBox.add(wrongPassBox);
        frameBox.add(Box.createVerticalStrut(10));
        frameBox.add(boxButtons);
        frameBox.add(Box.createVerticalStrut(10));
        frameBox.add(boxRegistration);
        return frameBox;
    }

    public void addListener() {
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(UserHandler.authorizate(getLogin(),getPassword())){ if(langComboBox.getSelectedIndex()==1)
                    language = new Strings_EN(); else language = new Strings_RU();
                    wrongPassLabel.setText(",fkfkf "); // Здесь открываем новую форму и язык

                }
                else {wrongPassLabel.setVisible(true);}

            }
        });
        guestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            MainWindow.main();
            }
        });
        guestButtonInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(guestButtonInfo, "В гостевом режиме нельзя создавать и изменять события. " +"\n" +
                                                "Для добавления личных чобытий пройдите регистрацию или авторизуйтесь.", "Информация",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
        regLabelButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                RegistrationForm registrationForm = new RegistrationForm();
                registrationForm.setVisible(true);
                dispose();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
               regLabelButton.setText( "<html><u><font  color = red >Зарегистрируйся!</font></u>");

            }

            @Override
            public void mouseExited(MouseEvent e) {
                regLabelButton.setText( "<html><u><font color = blue >Зарегистрируйся!</font></u>");


            }
        });

    }

}
