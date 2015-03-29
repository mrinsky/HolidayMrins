package modules.gui_interface;

import languages.Strings_EN;
import main.Resources;
import model.Tradition;
import modules.user_interface.UserHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainWindow extends JFrame {
    //region MENU
    private JMenuBar mainMenu;

    private JMenu editMenu;
    private JMenuItem addMenu;

    private JMenuItem removeThis;
    private JMenuItem removeAllMarked;

    private JMenu styleMenu;
    private JMenu helpMenu;
    private JMenuItem readHelpItem;
    private JTextField searchField;
//endregion

    private JTable traditionTable;
    private String[] columnNamesEN = {"HOLIDAY", "COUNTRY", "DATE", "TYPE", "CHOOSE"};
    private String[] columnNamesRU = {"ПРАЗДНИК","СТРАНА","ДАТА","ТИП","ВЫБРАТЬ"};

    private boolean isGuestMode = false;

    public MainWindow(final boolean isGuestMode) {
        this.isGuestMode = isGuestMode;

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                if (!isGuestMode) {
                    UserHandler.logOut();
                    LoginWindow.main();
                    dispose();
                }
                else dispose();
            }
        });

        this.setBounds(200, 200, 600, 400);
        this.setResizable(false);
        initComponents();
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
        initStyleMenu();
        initHelpMenu();

        mainMenu.add(Box.createHorizontalGlue());
        initSearchField();
        setJMenuBar(mainMenu);
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
    }

    private void initSearchField() {
        searchField = new JTextField(Resources.language.getSEARCH(), 20);
        searchField.setMaximumSize(searchField.getPreferredSize());
        searchField.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                searchField.setText("");
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
        if (Resources.language.getClass() == Strings_EN.class) traditionTable = new JTable(initData(columnNamesEN),columnNamesEN);
        else traditionTable = new JTable(initData(columnNamesRU),columnNamesRU);
        traditionTable.add(new JScrollPane());
        this.add(traditionTable, BorderLayout.WEST);
        this.add(new JScrollPane(traditionTable));
        traditionTable.setEnabled(false);
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

    private String[][] initData(String[] columnNames) {
        String[][] data = new String[Resources.traditions.size()][columnNames.length];
        for (int j = 0; j < Resources.traditions.size(); j++) {
            Tradition tr = Resources.traditions.get(j);

            data[j][0] = tr.getHoliday().getName();
            data[j][1] = tr.getCountry().getName();
            data[j][2] = tr.getHoliday().getStartDate();
            data[j][3] = tr.getHoliday().getType().toString();
            data[j][4] = "";
        }
        return data;
    }
}
