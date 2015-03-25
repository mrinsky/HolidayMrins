/*
package modules.gui_interface;

import com.toedter.calendar.JDateChooser;
import main.Resources;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

*/
/**
 * Created by root on 22.03.15.
 *//*

public class AdditionalSearchWindow extends JFrame {
    private JTextField holidayTextField;
    private JTextField countryTextField;
    private JTextArea descriptionTextField;
    private JTextField regTextField;
    private JDateChooser dateChooserField;
    private JDateChooser getDateChooserField1;
    private JDateChooser getDateChooserField2;
    private JLabel logoLable;
    private JLabel enterHolidayLabel;
    private JLabel enterCountryLabel;
    private JLabel enterDescriptionLabel;
    private JLabel enterRegEx;
    private JLabel lable1;
    private JLabel fromLabel;
    private JLabel toLabel;
    //private String[] chooseItem;
    private JButton addButton;
    private JButton okButton;
    //private JulianCalendar calendar;
    private JComboBox chooseComboBox;

    public AdditionalSearchWindow(int param_num){
        super(Resources.language.getSEARCH_TEXT());   // В дальнейшем сделать константой
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(addComponentsToForm(param_num));
        pack();
        setResizable(false);
        setLocationRelativeTo(null);// По центру экрана
    }

    public static void main(final int param_num) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdditionalSearchWindow(param_num).setVisible(true);
            }
        });

    }

    private Box addComponentsToForm(int param_num){
        logoLable = new JLabel();
        logoLable.setIcon(new ImageIcon("resources/img/lupa-32x32.png"));
        logoLable.setText(Resources.language.getSEARCH_TEXT());
        Font font = new Font("Verdana", Font.PLAIN, 22);
        logoLable.setFont(font);

        Box logoBox = Box.createHorizontalBox();
        logoBox.add(logoLable);

        dateChooserField = new JDateChooser();
        Box dateBox = Box.createHorizontalBox();
        dateBox.add(dateChooserField);

        getDateChooserField1 = new JDateChooser();
        getDateChooserField2 = new JDateChooser();
        lable1 = new JLabel("       ");

        String[] chooseItem = {
                "And",
                "Or"
        };
        chooseComboBox = new JComboBox(chooseItem);
        addButton = new JButton("Add");

        fromLabel = new JLabel("From:");
        Box leftDateBox = Box.createVerticalBox();
        leftDateBox.add(fromLabel);
        leftDateBox.add(getDateChooserField1);
        //leftDateBox.add(chooseComboBox);

        toLabel = new JLabel("To:");
        Box rightDateBox = Box.createVerticalBox();
        rightDateBox.add(toLabel);
        rightDateBox.add(getDateChooserField2);
        //rightDateBox.add(addButton);

        Box bigDateBox = Box.createHorizontalBox();
        bigDateBox.add(leftDateBox);
        bigDateBox.add(lable1);
        bigDateBox.add(rightDateBox);

        enterHolidayLabel = new JLabel("Enter holiday:");
        enterCountryLabel = new JLabel("Enter country:");
        enterDescriptionLabel = new JLabel(Resources.language.getENTER_DESCRIPTION());
        holidayTextField = new JTextField();
        countryTextField = new JTextField();
        descriptionTextField = new JTextArea();
        Box maskSearchBox = Box.createVerticalBox();
        maskSearchBox.add(enterHolidayLabel);
        maskSearchBox.add(holidayTextField);
        maskSearchBox.add(enterCountryLabel);
        maskSearchBox.add(countryTextField);
        maskSearchBox.add(enterDescriptionLabel);
        maskSearchBox.add(descriptionTextField);

        okButton = new JButton(Resources.language.getSEARCH_TEXT());

        regTextField = new JTextField();
        enterRegEx = new JLabel("Enter regular:");
        Box regularSearchBox = Box.createVerticalBox();
        regularSearchBox.add(enterRegEx);
        regularSearchBox.add(regTextField);

        Box frameBox = Box.createVerticalBox();
        frameBox.setBorder(new EmptyBorder(6, 6, 6, 6));
        frameBox.add(logoBox);
        switch (param_num){
            case 0: {
                frameBox.add(dateBox);
                break;
            }
            case 1: {
                frameBox.add(bigDateBox);
                break;
            }
            case 2: {
                frameBox.add(maskSearchBox);
                break;
            }
            case 3: {
                frameBox.add(regularSearchBox);
                break;
            }
            case 4: {
                frameBox.add(dateBox);
                frameBox.add(Box.createVerticalStrut(7));
                frameBox.add(maskSearchBox);
                break;
            }
            case 5: {
                frameBox.add(dateBox);
                frameBox.add(Box.createVerticalStrut(7));
                frameBox.add(regularSearchBox);
                break;
            }
            case 6: {
                frameBox.add(bigDateBox);
                frameBox.add(Box.createVerticalStrut(7));
                frameBox.add(maskSearchBox);
                break;
            }
            case 7: {
                frameBox.add(bigDateBox);
                frameBox.add(Box.createVerticalStrut(7));
                frameBox.add(regularSearchBox);
                break;
            }
            default:
                break;
        }
        frameBox.add(Box.createVerticalStrut(7));
        frameBox.add(okButton);
        //frameBox.add(maskSearchBox);
        //frameBox.add(Box.createVerticalStrut(7));
        return frameBox;
    }

    private void addListener() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdditionalSearchWindow.main(1);
            }
        });
    }
}
*/
