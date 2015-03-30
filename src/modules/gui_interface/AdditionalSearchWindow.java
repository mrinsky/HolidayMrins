
package modules.gui_interface;

import main.Resources;
import modules.functional.DateLabelFormatter;
import modules.functional.Search;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;


/**
 * Created by root on 22.03.15.
 */

public class AdditionalSearchWindow extends JFrame {
    private JTextField holidayTextField;
    private JTextField countryTextField;
    private JTextArea descriptionTextField;
    private JTextField regTextField;
    private JLabel logoLable;
    private JLabel lable1;
    private JLabel fromLabel;
    private JLabel toLabel;
    private JScrollPane descriptionScroll;

    private UtilDateModel model;
    private JDatePanelImpl datePanel;

    private JDatePickerImpl calendar;

    private JDatePanelImpl datePanelFrom;
    private JDatePickerImpl calendarFrome;

    private JDatePanelImpl datePanelTo;
    private JDatePickerImpl calendarTo;

    private JPanel date;
    //private String[] chooseItem;
    private JButton addButton;
    private JButton okButton;
    //private JulianCalendar calendar;
    private JComboBox chooseComboBox;

    private static int currentParamNum = 0;

    public AdditionalSearchWindow(int param_num){
        super(Resources.language.getSEARCH_MENU_BAR());
        currentParamNum = param_num;
        setContentPane(addComponentsToForm(param_num));
        pack();
        //setBounds(200, 200, 200, 220);
        setResizable(false);
        setLocationRelativeTo(null);
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
        logoLable.setIcon(new ImageIcon(SearchWindow.IMG_SRC));
        logoLable.setText(Resources.language.getSEARCH_MENU_BAR());
        Font font = new Font("Verdana", Font.PLAIN, 22);
        logoLable.setFont(font);

        Box logoBox = Box.createHorizontalBox();
        logoBox.add(logoLable);

        Box dateBox = Box.createHorizontalBox();
        date = new JPanel();
        model = new UtilDateModel();
        Properties pr = new Properties();
        pr.put("text.today", "Today");
        pr.put("text.month", "Month");
        pr.put("text.year", "Year");
        datePanel = new JDatePanelImpl(model, pr);
        calendar = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        dateBox.add(calendar);
        dateBox.setBorder(BorderFactory.createTitledBorder(Resources.language.getDATE()));

        lable1 = new JLabel("       ");

        String[] chooseItem = {
                "And",
                "Or"
        };
        chooseComboBox = new JComboBox(chooseItem);
        addButton = new JButton("Add");

        fromLabel = new JLabel("From:");
        Box leftDateBox = Box.createVerticalBox();

        //date.setBorder(BorderFactory.createTitledBorder(Resources.language.getDATE()));
        //model = new UtilDateModel();
        datePanelFrom = new JDatePanelImpl(model, pr);
        calendarFrome = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        leftDateBox.add(fromLabel);
        leftDateBox.add(calendarFrome);

        toLabel = new JLabel("To:");
        Box rightDateBox = Box.createVerticalBox();

        datePanelTo = new JDatePanelImpl(model, pr);
        calendarTo = new JDatePickerImpl(datePanelTo, new DateLabelFormatter());
        rightDateBox.add(toLabel);
        rightDateBox.add(calendar);

        Box bigDateBox = Box.createHorizontalBox();
        bigDateBox.add(leftDateBox);
        bigDateBox.add(lable1);
        bigDateBox.add(rightDateBox);

        Box holidayBox = Box.createHorizontalBox();
        holidayTextField = new JTextField(20);
        holidayBox.add(holidayTextField);
        holidayBox.setBorder(BorderFactory.createTitledBorder("Enter holiday:"));

        Box countryBox = Box.createHorizontalBox();
        countryTextField = new JTextField(20);
        //countryTextField.setBorder(BorderFactory.createTitledBorder("Enter country:"));
        countryBox.add(countryTextField);
        countryBox.setBorder(BorderFactory.createTitledBorder("Enter country:"));

        descriptionTextField = new JTextArea();
        descriptionTextField.setLineWrap(true);
        descriptionTextField.setWrapStyleWord(true);
        descriptionScroll = new JScrollPane(descriptionTextField);
        descriptionScroll.setPreferredSize(new Dimension(225, 75));
        descriptionScroll.setBorder(BorderFactory.createTitledBorder(Resources.language.getENTER_DESCRIPTION()));
        Box maskSearchBox = Box.createVerticalBox();
        maskSearchBox.add(holidayBox);
        maskSearchBox.add(countryBox);
        maskSearchBox.add(descriptionScroll);

        Container containerOkButton = getContentPane();
        containerOkButton.setLayout(new BorderLayout());
        okButton = new JButton(Resources.language.getSEARCH_MENU_BAR());
        containerOkButton.add(okButton, BorderLayout.EAST);

        regTextField = new JTextField();
        Box regularSearchBox = Box.createVerticalBox();
        regularSearchBox.add(regTextField);
        regularSearchBox.setBorder(BorderFactory.createTitledBorder(Resources.language.getREGULAR()));

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

        frameBox.add(containerOkButton);
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
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (currentParamNum){
                    case 0:
                        //Resources.traditions = Search.getDateHolidays();
                        //break;
                    case 1:
                        //break;
                    case 2:
                        Resources.traditions = Search.maskSearch(holidayTextField.toString(), countryTextField.toString(), descriptionTextField.toString(),
                                Resources.traditions);
                        break;
                    case 3:
                        Resources.traditions = Search.regularSearch(regTextField.toString(), Resources.traditions);
                        break;
                }
            }
        });
    }
}