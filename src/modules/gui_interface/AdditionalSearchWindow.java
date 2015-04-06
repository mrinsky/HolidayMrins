
package modules.gui_interface;

import main.Resources;
import model.Holiday;
import model.Tradition;
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
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.ParseException;
import java.util.*;


/**
 * Created by root on 22.03.15.
 */

public class AdditionalSearchWindow extends JFrame {
    static final String IMG_SRC = "resources/img/lupa-32x32.png";
    private ArrayList<Tradition> defaultTradtion = Resources.traditions;

    private JTextField holidayTextField;
    private JTextField countryTextField;
    private JTextArea descriptionTextField;
    private JTextField regTextField;

    private JLabel logoLable;
    private JLabel lable1;
    private JLabel fromLabel;
    private JLabel toLabel;

    private JScrollPane descriptionScroll;

    private JDatePickerImpl calendar;
    private JDatePickerImpl calendarFrom;
    private JDatePanelImpl datePanel;
    private JDatePanelImpl datePanelFrom;
    private UtilDateModel model;
    private UtilDateModel modelFrom;
    private UtilDateModel modelTo;
    private JDatePanelImpl datePanelTo;
    private JDatePickerImpl calendarTo;

    private final int PARAM_NUM = 1;
    private final int FROM_PARAM_NUM = 2;
    private final int TO_PARAM_NUM = 3;
    //private JButton addButton;
    private JButton okButton;
    //private JComboBox chooseComboBox;

    private static int currentParamNum = 0;

    public AdditionalSearchWindow(int param_num){
        super(Resources.language.getSEARCH_MENU_BAR());
        currentParamNum = param_num;
        setContentPane(addComponentsToForm(param_num));
        pack();
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

    private void initCalendar(UtilDateModel dateModel, JDatePanelImpl jDatePanel, int param_num) {
        dateModel = new UtilDateModel();
        Properties pr = new Properties();
        pr.put("text.today", "Today");
        pr.put("text.month", "Month");
        pr.put("text.year", "Year");
        jDatePanel = new JDatePanelImpl(dateModel, pr);
        switch (param_num) {
            case 1 :
                calendar = new JDatePickerImpl(jDatePanel, new DateLabelFormatter());
                break;
            case 2:
                calendarFrom = new JDatePickerImpl(jDatePanel, new DateLabelFormatter());
                break;
            case 3:
                calendarTo = new JDatePickerImpl(jDatePanel, new DateLabelFormatter());
                break;
            default:
                break;
        }
    }

    private Box initLogo() {
        logoLable = new JLabel();
        logoLable.setIcon(new ImageIcon(IMG_SRC));
        logoLable.setText(Resources.language.getSEARCH_MENU_BAR());
        Font font = new Font("Verdana", Font.PLAIN, 22);
        logoLable.setFont(font);

        Box result = Box.createHorizontalBox();
        result.add(logoLable);
        return  result;
    }

    private Box initDateBox() {
        initCalendar(model, datePanel, PARAM_NUM);
        Box result = Box.createHorizontalBox();
        result.add(calendar);
        result.setBorder(BorderFactory.createTitledBorder(Resources.language.getDATE()));
        return result;
    }

    private Box initLeftDateBox() {
        fromLabel = new JLabel(Resources.language.getFROM_LABEL());
        Box result = Box.createVerticalBox();

        initCalendar(modelFrom, datePanelFrom, FROM_PARAM_NUM);
        result.add(fromLabel);
        result.add(calendarFrom);
        return result;
    }

    private Box initRightDateBox() {
        toLabel = new JLabel(Resources.language.getTO_LABEL());
        Box result = Box.createVerticalBox();

        initCalendar(modelTo, datePanelTo, TO_PARAM_NUM);
        result.add(toLabel);
        result.add(calendarTo);
        return result;
    }

    private Box initBigDateBox() {
        lable1 = new JLabel("       ");
        /*
        String[] chooseItem = {
                "And",
                "Or"
        };
        chooseComboBox = new JComboBox(chooseItem);
        addButton = new JButton("Add");
        */
        Box leftDateBox = initLeftDateBox();
        Box rightDateBox = initRightDateBox();

        Box result = Box.createHorizontalBox();
        result.add(leftDateBox);
        result.add(lable1);
        result.add(rightDateBox);
        return result;
    }

    private Box initHolidayBox() {
        Box result = Box.createHorizontalBox();
        holidayTextField = new JTextField(1);
        result.add(holidayTextField);
        result.setBorder(BorderFactory.createTitledBorder(Resources.language.getHOLIDAY_ITEM()));
        return result;
    }

    private Box initCountryBox() {
        Box result = Box.createHorizontalBox();
        countryTextField = new JTextField(15);
        result.add(countryTextField);
        result.setBorder(BorderFactory.createTitledBorder(Resources.language.getCOUNTRY_ITEM()));
        return result;
    }

    private Box initMaskSearchBox(Box holidayBox, Box countryBox) {
        descriptionTextField = new JTextArea();
        descriptionTextField.setLineWrap(true);
        descriptionTextField.setWrapStyleWord(true);
        descriptionScroll = new JScrollPane(descriptionTextField);
        descriptionScroll.setPreferredSize(new Dimension(225, 75));
        descriptionScroll.setBorder(BorderFactory.createTitledBorder(Resources.language.getENTER_DESCRIPTION()));
        Box result = Box.createVerticalBox();
        result.add(holidayBox);
        result.add(countryBox);
        result.add(descriptionScroll);
        return result;
    }

    private Box initRegularQueryBox() {
        regTextField = new JTextField(20);
        Box result = Box.createVerticalBox();
        result.add(regTextField);
        result.setBorder(BorderFactory.createTitledBorder(Resources.language.getREGULAR()));
        return result;
    }

    private Container initOkButton() {
        Container result = getContentPane();
        result.setLayout(new BorderLayout());
        okButton = new JButton(Resources.language.getSEARCH_MENU_BAR());
        result.add(okButton, BorderLayout.EAST);
        return result;
    }

    private Box addComponentsToForm(int param_num){
        Box logoBox = initLogo();
        Box dateBox = initDateBox();
        Box bigDateBox = initBigDateBox();
        Box holidayBox = initHolidayBox();
        Box countryBox = initCountryBox();
        Box maskSearchBox = initMaskSearchBox(holidayBox, countryBox);
        Box regularSearchBox = initRegularQueryBox();

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
            default:
                break;
        }
        frameBox.add(Box.createVerticalStrut(7));
        Container containerOkButton = initOkButton();
        frameBox.add(containerOkButton);
        return frameBox;
    }

    private Date initDate(int paramNum){
        GregorianCalendar calendarValue = (GregorianCalendar) calendar.getJFormattedTextField().getValue();
        switch (paramNum) {
            case 1:
                break;
            case 2:
                calendarValue = (GregorianCalendar) calendarFrom.getJFormattedTextField().getValue();
                break;
            case 3:
                calendarValue = (GregorianCalendar) calendarTo.getJFormattedTextField().getValue();
                break;
            default:
                break;
        }

        Date result = calendarValue.getTime();

        try {
            Integer month = calendarValue.get(calendarValue.MONTH) + 1;
            result = Holiday.dateFormat.parse(calendarValue.get(calendarValue.DAY_OF_MONTH) + "." + month);
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        return result;
    }

    private void searchDate(Date dateValue) {
        try {
            if (Search.getDateHolidays(dateValue).size() != 0) {
                LinkedList<Holiday> holidays = Search.getDateHolidays(dateValue);

                ArrayList<Tradition> traditions = Search.getTraditions(holidays.get(0));
                for (Holiday item : holidays) {
                    for (Tradition tradition : Search.getTraditions(item)) {
                        traditions.add(tradition);
                    }
                }
                traditions.remove(0);
                Resources.traditions = traditions;
            } else {
                Resources.out.println(Resources.language.getNOT_FOUND());
            }
        }
        catch (IndexOutOfBoundsException exc) {
            Resources.out.println("!!!");
        }
    }

    private ArrayList<Date> getDaysBetweenDates(Date startDate, Date endDate) {
        ArrayList<Date> dates = new ArrayList<Date>();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(startDate);

        while (calendar.getTime().before(endDate))
        {
            Date result = calendar.getTime();
            dates.add(result);
            calendar.add(Calendar.DATE, 1);
        }
        return dates;
    }

    private void addListener() {
        /*addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdditionalSearchWindow.main(1);
            }
        });*/
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final int PARAM_NUM = 1;
                final int FROM_PARAM_NUM = 2;
                switch (currentParamNum){
                    case 0:
                        Date dateValue = initDate(PARAM_NUM);
                        searchDate(dateValue);
                        break;
                    case 1:
                        Date dateFrom = initDate(FROM_PARAM_NUM);
                        final int TO_PARAM_NUM = 3;
                        Date dateTo = initDate(TO_PARAM_NUM);
                        ArrayList<Date> interval = getDaysBetweenDates(dateFrom, dateTo);
                        for (Date item : interval) {
                            searchDate(item);
                        }
                        break;
                    case 2:
                        Resources.traditions = Search.maskSearch(holidayTextField.getText(), countryTextField.getText(), descriptionTextField.getText(),
                                Resources.traditions);
                        break;
                    case 3:
                        Resources.traditions = Search.regularSearch(regTextField.toString(), Resources.traditions);
                        break;
                }
                MainWindow.restart();
            }
        });
        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                Resources.traditions = defaultTradtion;
                MainWindow.restart();
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }
//
            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
    }
}