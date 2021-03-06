package modules.gui_interface;

import languages.Strings_EN;
import languages.Strings_RU;
import main.Resources;
import model.Tradition;
import modules.functional.Remove;
import modules.functional.Search;
import modules.functional.UserData;
import modules.user_interface.UserHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class MainWindow extends JFrame {
    //region MENU
    private JMenuBar mainMenu;

    private JMenu editMenu;
    private JMenuItem addMenu;

    private JMenuItem removeThis;
    private JMenuItem removeAllMarked;

    private JMenu styleMenu;
    private JMenu helpMenu;
    private JMenu searchMenu;
    private JMenu restartMenu;
    private JMenuItem searchDate;
    private JMenuItem searchDateInterval;
    private JMenuItem searchSubstring;
    private JMenuItem searchRegEx;
    private JMenuItem readHelpItem;
    private JTextField searchField;

    private  JPopupMenu popup;
    private JMenuItem removeThisPopup;
    private JMenuItem removeAllMarkedPopup;
    private JMenuItem showOrEdit;
//endregion

    TraditionalTableModel tableModel;
    private static JTable traditionTable;
    private static String[] columnNamesEN = {"HOLIDAY", "COUNTRY", "DATE", "TYPE", "CHOOSE"};
    private static String[] columnNamesRU = {"ПРАЗДНИК","СТРАНА","ДАТА","ТИП","ВЫБРАТЬ"};

    private boolean isGuestMode = false;

    public MainWindow(final boolean isGuestMode) {
        this.isGuestMode = isGuestMode;

        this.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent windowEvent) {
                if (!isGuestMode) {
                    if (Resources.language instanceof Strings_RU) UserData.currentUser.saveAllRU();
                    else UserData.currentUser.saveAllEN();
                    UserData.saveData();
                }
                LoginWindow.main();
                super.windowClosing(windowEvent);
            }
        });

        this.setBounds(200, 200, 600, 400);
        this.setResizable(false);
        initComponents(); //
    }

    public static void main(final boolean check) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow(check).setVisible(true);
            }
        });
    }

    private void initComponents() {
        initMenuBar();
        this.setLayout(new BorderLayout());
        initTable();
    }

    private void initMenuBar() {
        mainMenu = new JMenuBar();
        initEditMenu();
        initSearchMenu();
        initStyleMenu();
        initHelpMenu();
        JButton update = new JButton(new ImageIcon("resources/img/update32x32.png"));
        update.setPreferredSize(new Dimension(32,32));
        mainMenu.add(update);
        update.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                refreshTable();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }
//
            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        mainMenu.add(Box.createHorizontalGlue());
        initSearchField();
        setJMenuBar(mainMenu);
    }
    private void refreshTable()    {
        TraditionalTableModel tableRestart;
        if(Resources.language.getClass() == Strings_EN.class) {
            tableRestart = new TraditionalTableModel(initData(columnNamesEN), columnNamesEN);
        }else tableRestart = new TraditionalTableModel(initData(columnNamesRU), columnNamesRU );
        traditionTable.setModel(tableRestart);
    }

    private void initSearchMenu() {
        searchMenu = new JMenu(Resources.language.getSEARCH_MENU_BAR());
        searchDate = new JMenuItem(Resources.language.getSEARCH_BY_DATE());
        searchDateInterval = new JMenuItem(Resources.language.getSEARCH_BY_DATE_INTERVAL());
        searchSubstring = new JMenuItem(Resources.language.getSUBSTRING());
        searchRegEx = new JMenuItem(Resources.language.getREGULAR());

        searchDate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AdditionalSearchWindow.main(0);
            }
        });
        searchDateInterval.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AdditionalSearchWindow.main(1);
            }
        });
        searchSubstring.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AdditionalSearchWindow.main(2);
            }
        });
        searchRegEx.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AdditionalSearchWindow.main(3);
            }
        });

        searchMenu.add(searchDate);
        searchMenu.add(searchDateInterval);
        searchMenu.add(searchSubstring);
        searchMenu.add(searchRegEx);
        mainMenu.add(searchMenu);
    }

    private void initStyleMenu() {
        styleMenu = new JMenu(Resources.language.getSTYLE_MENU());
        final UIManager.LookAndFeelInfo[] styles = UIManager.getInstalledLookAndFeels();
        for (int i = 0; i < styles.length; i++) {
            JMenuItem current = new JMenuItem(styles[i].getName());
            current.addActionListener(new styleListener(this, styles[i]));
            styleMenu.add(current);
        }
        mainMenu.add(styleMenu);
    }

    private void initEditMenu() {
        editMenu = new JMenu(Resources.language.getEDIT_MENU());
        initAddMenu();
        //initSearchMenu();
        initRemoveMenu();
        mainMenu.add(editMenu);
    }

    private void initAddMenu() {
        addMenu = new JMenuItem(Resources.language.getADD());

        addMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AddWindow.main();
            }
        });

        editMenu.add(addMenu);
        if (isGuestMode) addMenu.setEnabled(false);
    }

    private void initRemoveMenu() {
        removeThis = new JMenuItem(Resources.language.getREMOVE());
        removeAllMarked = new JMenuItem(Resources.language.getREMOVE_MARKED());
        editMenu.add(removeThis);
        editMenu.add(removeAllMarked);
        if (isGuestMode) {
            removeAllMarked.setEnabled(false);
            removeThis.setEnabled(false);
        }
        removeAllMarked.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                initTable();
            }
        });
        removeThis.addActionListener(new removeListener());

    }

    private void initSearchField() {
        final ArrayList<Tradition> defaultTradtion = Resources.traditions;

        searchField = new JTextField(Resources.language.getSEARCH(), 20);
        searchField.setMaximumSize(searchField.getPreferredSize());
        searchField.addKeyListener((KeyListener) new KeyAdapter() {

            public void keyReleased(KeyEvent e) {
                if ("".equals(searchField.getText())) {
                    Resources.traditions = defaultTradtion;
                }
                else {
                    Resources.traditions = Search.search(searchField.getText(), Resources.traditions);
                }
                restart();
                /*
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (!"".equals(searchField.getText())) {
                        Resources.traditions = Search.search(searchField.getText(), Resources.traditions);
                    }
                    else {
                        Resources.traditions = defaultTradtion;
                    }
                    restart();
                }
                */
            }
        });
        searchField.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                searchField.setText("");
                //Resources.traditions = Search.search(searchField.getText(), Resources.traditions);
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                /*
                if ("".equals(searchField.getText())) {
                    initSearchMenu();
                }
                */
            }
        });

        mainMenu.add(searchField);
    }

    private void initHelpMenu() {
        helpMenu = new JMenu(Resources.language.getHELP());
        readHelpItem = new JMenuItem(Resources.language.getREAD());

        readHelpItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (Resources.language.getClass() == Strings_EN.class) HelpWindow.main("./resources/helps/help_en.txt");
                else HelpWindow.main("./resources/helps/help_ru.txt");
            }
        });

        helpMenu.add(readHelpItem);
        mainMenu.add(helpMenu);
    }

    public void initTable() {

        if(Resources.language.getClass() == Strings_EN.class) {
            tableModel = new TraditionalTableModel(initData(columnNamesEN), columnNamesEN);
        }else tableModel = new TraditionalTableModel(initData(columnNamesRU), columnNamesRU );
        traditionTable = new JTable(tableModel);
        traditionTable.add(new JScrollPane());
        this.add(traditionTable, BorderLayout.WEST);
        this.add(new JScrollPane(traditionTable));
        traditionTable.setCellSelectionEnabled(true);
        popup = new JPopupMenu();
        showOrEdit = new JMenuItem(Resources.language.getSHOW());
        removeThisPopup = new JMenuItem(Resources.language.getREMOVE());
        removeAllMarkedPopup = new JMenuItem(Resources.language.getREMOVE_MARKED());
        popup.add(showOrEdit);
        popup.add(removeThisPopup);
        popup.add(removeAllMarkedPopup);
        if(isGuestMode){
            removeThisPopup.setEnabled(false);
            removeAllMarkedPopup.setEnabled(false);
        }
        traditionTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e))
                    popup.show(traditionTable, e.getX(), e.getY());

                if ((tableModel.isCellEditable(e)) && (!isGuestMode)) {
                    AddWindow.main(Resources.traditions.get(traditionTable.getSelectedRow()), traditionTable.getSelectedRow());
                }
            }
        });
        showOrEdit.addActionListener(new descriptListener());
        removeThisPopup.addActionListener(new removeListener());
    }

    private class styleListener implements ActionListener {
        private UIManager.LookAndFeelInfo styleInfo;
        private Frame parent;

        public styleListener(Frame parent, UIManager.LookAndFeelInfo styleInfo) {
            this.parent = parent;
            this.styleInfo = styleInfo;
        }

        public void actionPerformed(ActionEvent event) {
            String message = "";
            try {
                UIManager.setLookAndFeel(styleInfo.getClassName());
                SwingUtilities.updateComponentTreeUI(parent);
            } catch (ClassNotFoundException ex) {
                message = "Error: " + styleInfo.getClassName() + " not found";
            } catch (InstantiationException ex) {
                message = "Error: InstantiationException";
                ;
            } catch (IllegalAccessException ex) {
                message = "Error: IllegalAccessException";
                ;
            } catch (UnsupportedLookAndFeelException ex) {
                message = "Error: UnsupportedLookAndFeelException";
                ;
            }
            if (!(message.isEmpty())) JOptionPane.showMessageDialog(null, message);
        }
    }

    private class removeListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            int i = 0;
            int count = traditionTable.getRowCount();
            int index = traditionTable.getSelectedRow();
           Remove.removeTraditionGui(index,Resources.traditions);
            /*for (i =0; i < count; ) {
              // boolean tr = Boolean.parseBoolean(traditionTable.getValueAt(i,4));
                if(traditionTable.getValueAt(i,4)==Boolean.TRUE){Remove.removeTraditionGui(i,Resources.traditions);
                    i--;
                    count--;
                }else  i++;

            }

*/
            restart();
        }
    }

    private static Object[][] initData(String[] columnNames) {
        Object[][] data =  new Object[Resources.traditions.size()][columnNames.length];
        for (int j = 0; j < Resources.traditions.size(); j++) {
            Tradition tr = Resources.traditions.get(j);

            data[j][0] = tr.getHoliday().getName();
            data[j][1] = tr.getCountry().getName();
            data[j][2] = tr.getHoliday().getStartDate();
            data[j][3] = tr.getHoliday().getType().toString();
            data[j][4] = Boolean.FALSE;
        }
        return data;
    }

    private class descriptListener implements ActionListener {

        private String description = "";

        public descriptListener() {}

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            int index = traditionTable.getSelectedRow();
            if (index < Resources.traditions.size()) this.description = Resources.traditions.get(index).getDescription();
            if (description.isEmpty()) description = Resources.language.getNOT_FOUND_DESCRIPTION();

            JOptionPane optionPane = new JOptionPane();
            optionPane.setMessage(description);
            optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
            JDialog dialog = optionPane.createDialog(null,Resources.language.getTRADITION_ITEM());
            dialog.setVisible(true);
        }
    }

    public static void restart(){
        TraditionalTableModel tableRestart;
        if(Resources.language.getClass() == Strings_EN.class) {
            tableRestart = new TraditionalTableModel(initData(columnNamesEN), columnNamesEN);
        }else tableRestart = new TraditionalTableModel(initData(columnNamesRU), columnNamesRU );
        traditionTable.setModel(tableRestart);
    }

    public static void frontWindow() {

    }
}
