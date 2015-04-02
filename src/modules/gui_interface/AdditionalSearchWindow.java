
package modules.gui_interface;

import main.Resources;
import model.Holiday;
import model.Tradition;
import modules.functional.DateLabelFormatter;
import modules.functional.Search;
import modules.user_interface.MainMenu;
import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


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

    private UtilDateModel modelFrom;
    private UtilDateModel modelTo;

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
        addListener();
    }

    public static void main(final int param_num) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdditionalSearchWindow(param_num).setVisible(true);
            }
        });

    }

    private void initCalendar(UtilDateModel dateModel, JDatePanelImpl jDatePanel, JDatePickerImpl calendar) {
        //Box dateBox = Box.createHorizontalBox();
        //date = new JPanel();
        dateModel = new UtilDateModel();
        Properties pr = new Properties();
        pr.put("text.today", "Today");
        pr.put("text.month", "Month");
        pr.put("text.year", "Year");
        jDatePanel = new JDatePanelImpl(dateModel, pr);
        calendar = new JDatePickerImpl(jDatePanel, new DateLabelFormatter());
        //dateBox.add((Component) jCalendar);
        //dateBox.setBorder(BorderFactory.createTitledBorder(Resources.language.getDATE()));
        //return dateBox;
    }

    private Box addComponentsToForm(int param_num){
        logoLable = new JLabel();
        logoLable.setIcon(new ImageIcon(SearchWindow.IMG_SRC));
        logoLable.setText(Resources.language.getSEARCH_MENU_BAR());
        Font font = new Font("Verdana", Font.PLAIN, 22);
        logoLable.setFont(font);

        Box logoBox = Box.createHorizontalBox();
        logoBox.add(logoLable);

        initCalendar(model, datePanel, calendar);
        Box dateBox = Box.createHorizontalBox();
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

        initCalendar(modelFrom, datePanelFrom, calendarFrome);
        leftDateBox.add(fromLabel);
        leftDateBox.add(calendarFrome);

        toLabel = new JLabel("To:");
        Box rightDateBox = Box.createVerticalBox();

        initCalendar(modelTo, datePanelTo, calendarTo);
        rightDateBox.add(toLabel);
        rightDateBox.add(calendarTo);

        Box bigDateBox = Box.createHorizontalBox();
        bigDateBox.add(leftDateBox);
        bigDateBox.add(lable1);
        bigDateBox.add(rightDateBox);

        Box holidayBox = Box.createHorizontalBox();
        holidayTextField = new JTextField(1);
        holidayBox.add(holidayTextField);
        holidayBox.setBorder(BorderFactory.createTitledBorder("Enter holiday:"));

        Box countryBox = Box.createHorizontalBox();
        countryTextField = new JTextField(15);
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
                //frameBox.add(dateBox);
                frameBox.add(Box.createVerticalStrut(7));
                frameBox.add(maskSearchBox);
                break;
            }
            case 5: {
                //frameBox.add(dateBox);
                frameBox.add(Box.createVerticalStrut(7));
                frameBox.add(regularSearchBox);
                break;
            }
            case 6: {
                //frameBox.add(bigDateBox);
                frameBox.add(Box.createVerticalStrut(7));
                frameBox.add(maskSearchBox);
                break;
            }
            case 7: {
                //frameBox.add(bigDateBox);
                frameBox.add(Box.createVerticalStrut(7));
                frameBox.add(regularSearchBox);
                break;
            }
            default:
                break;
        }
        frameBox.add(Box.createVerticalStrut(7));

        Container containerOkButton = getContentPane();
        containerOkButton.setLayout(new BorderLayout());
        okButton = new JButton(Resources.language.getSEARCH_MENU_BAR());
        containerOkButton.add(okButton, BorderLayout.EAST);

        frameBox.add(containerOkButton);
        return frameBox;
    }

    private Date initDate(){
        GregorianCalendar calendarValue = (GregorianCalendar) calendar.getJFormattedTextField().getValue();

        Date result = calendarValue.getTime();

        try {
            Integer month = calendarValue.get(calendarValue.MONTH) + 1;
            result = Holiday.dateFormat.parse(calendarValue.get(calendarValue.DAY_OF_MONTH) + "." + month);
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        System.out.println(result);
        return result;
    }

    private static void searchDate(Date dateValue) {
        if (Search.getDateHolidays(dateValue).size() != 0) {
            LinkedList<Holiday> holidays = Search.getDateHolidays(dateValue);

            ArrayList<Tradition> traditions = new ArrayList<Tradition>();
            for (Holiday item : holidays) {
                for (Tradition tradition : Search.getTraditions(item)) {
                    traditions.add(tradition);
                }
            }

            Resources.traditions = traditions;
        } else {
            Resources.out.println(Resources.language.getNOT_FOUND());
        }
    }

    private ArrayList<Date> getDaysBetweenDates(Date startdate, Date enddate) {
        ArrayList<Date> dates = new ArrayList<Date>();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(startdate);

        while (calendar.getTime().before(enddate))
        {
            Date result = calendar.getTime();
            dates.add(result);
            calendar.add(Calendar.DATE, 1);
        }
        return dates;
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
                        Date dateValue = initDate();
                        searchDate(dateValue);

                            for (Tradition item : Resources.traditions) {
                                System.out.println(item);
                            }

                        break;
                    case 1:
                        Date dateFrom = initDate();
                        Date dateTo = initDate();
                        ArrayList<Date> interval = getDaysBetweenDates(dateFrom, dateTo);
                        for (Date item : interval) {
                            searchDate(item);
                        }

                        for (Tradition item : Resources.traditions) {
                            System.out.println(item);
                        }
                        break;
                    case 2:
                        System.out.println(holidayTextField.getText() + " " + countryTextField.getText() + " " + descriptionTextField.getText());
                        Resources.traditions = Search.maskSearch(holidayTextField.getText(), countryTextField.getText(), descriptionTextField.getText(),
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