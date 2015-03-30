package modules.gui_interface;

import main.Resources;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SearchWindow extends JFrame {
    static final String IMG_SRC = "resources/img/lupa-32x32.png";

    private JLabel logoLable;
    private JLabel searchParam;
    private JComboBox searchComboBox;
    private JComboBox andSearchComboBox = new JComboBox();
    private JButton okButton;
    private JButton andButton;
    private String[] searchItem = {
            Resources.language.getSEARCH_BY_DATE(),
            Resources.language.getSEARCH_BY_DATE_INTERVAL(),
            Resources.language.getSUBSTRING(),
            Resources.language.getREGULAR()
    };
    private String[] searchDateItem = {
            Resources.language.getSEARCH_BY_DATE(),
            Resources.language.getSEARCH_BY_DATE_INTERVAL()
    };
    private String[] searchStringItem = {
            Resources.language.getSUBSTRING(),
            Resources.language.getREGULAR()
    };

    public SearchWindow(int flag_num, int prev_num){
        super(Resources.language.getSEARCH_MENU_BAR());
        setContentPane(addComponentsToForm(flag_num, prev_num));
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        addListener();
    }

    public static void main(final int flag_num, final int prev_num){
        SearchWindow searchWindow = new SearchWindow(flag_num, prev_num);
        searchWindow.setVisible(true);
    }

    public static void main() {
        SearchWindow searchWindow = new SearchWindow(0, 0);
        searchWindow.setVisible(true);
    }

    private Box addComponentsToForm(int flag_num, int prev_num){
        //while(true){
        logoLable = new JLabel();
        logoLable.setIcon(new ImageIcon(IMG_SRC));
        logoLable.setText(Resources.language.getSEARCH_MENU_BAR());
        Font font = new Font("Verdana", Font.PLAIN, 22);
        logoLable.setFont(font);

        Box logoBox = Box.createHorizontalBox();
        //logoBox.setFont(font);
        logoBox.add(logoLable);

        searchComboBox = new JComboBox(searchItem);
        searchComboBox.setVisible(true);

        Box comboBox = Box.createHorizontalBox();
        searchParam = new JLabel(Resources.language.getCHOOSE_SEARCH_PARAM()); //const
        comboBox.add(searchParam);
        comboBox.add(searchComboBox);

        andButton = new JButton(Resources.language.getAND_LABEL());

        okButton = new JButton(Resources.language.getSEARCH_MENU_BAR());

        Box frameBox = Box.createVerticalBox();
        frameBox.setBorder(new EmptyBorder(6, 6, 6, 6));
        frameBox.add(logoBox);
        frameBox.add(Box.createVerticalStrut(7));
        frameBox.add(comboBox);
        frameBox.add(Box.createVerticalStrut(7));
        frameBox.add(searchComboBox);
        searchComboBox.setSelectedIndex(prev_num);
        frameBox.add(Box.createVerticalStrut(7));
        andButton.setEnabled(false);
        frameBox.add(andButton);
        frameBox.add(Box.createVerticalStrut(7));
        switch (flag_num){
            case 1: {
                andSearchComboBox = new JComboBox(searchStringItem);
                frameBox.add(andSearchComboBox);
                frameBox.add(Box.createVerticalStrut(7));
                break;
            }
            case 2: {
                andSearchComboBox = new JComboBox(searchDateItem);
                frameBox.add(andSearchComboBox);
                frameBox.add(Box.createVerticalStrut(7));
                break;
            }
        }
        frameBox.add(okButton);
        return frameBox;
    }

    private void addListener(){
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //int num_prev = 0;
                switch (searchComboBox.getSelectedIndex()){
                    case 0: {
                        switch (andSearchComboBox.getSelectedIndex()) {
                            case 0: {
                                AdditionalSearchWindow.main(4);
                                break;
                            }
                            case 1: {
                                AdditionalSearchWindow.main(5);
                                break;
                            }
                            default:
                                AdditionalSearchWindow.main(0);
                                break;
                        }
                        setVisible(false);
                        dispose();
                        //AdditionalSearchWindow.main(0);
                        break;
                    }
                    case 1: {
                        switch (andSearchComboBox.getSelectedIndex()) {
                            case 0: {
                                AdditionalSearchWindow.main(6);
                                break;
                            }
                            case 1: {
                                AdditionalSearchWindow.main(7);
                                break;
                            }
                            default:
                                AdditionalSearchWindow.main(1);
                                break;
                        }
                        setVisible(false);
                        dispose();
                        break;
                    }
                    case 2: {
                        switch (andSearchComboBox.getSelectedIndex()) {
                            case 0: {
                                AdditionalSearchWindow.main(4);
                                break;
                            }
                            case 1: {
                                AdditionalSearchWindow.main(6);
                                break;
                            }
                            default:
                                AdditionalSearchWindow.main(2);
                                break;
                        }
                        setVisible(false);
                        dispose();
                        break;
                    }
                    case 3: {
                        switch (andSearchComboBox.getSelectedIndex()) {
                            case 0: {
                                AdditionalSearchWindow.main(5);
                                break;
                            }
                            case 1: {
                                AdditionalSearchWindow.main(7);
                                break;
                            }
                            default:
                                AdditionalSearchWindow.main(3);
                                break;
                        }
                        setVisible(false);
                        dispose();
                        break;
                    }
                    default: {
                        break;
                    }
                }
            }
        });
        andButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch(searchComboBox.getSelectedIndex()) {
                    case 0: {
                        SearchWindow.main(1, 0);
                        //setVisible(false);
                        //dispose();
                        break;
                    }
                    case 1: {
                        SearchWindow.main(1, 1);
                        //setVisible(false);
                        //dispose();
                        break;
                    }
                    case 2: {
                        //setVisible(false);
                        //dispose();
                        SearchWindow.main(2, 2);
                        break;
                    }
                    case 3: {
                        //setVisible(false);
                        //dispose();
                        SearchWindow.main(2, 3);
                        break;
                    }
                    default:
                        break;
                }
                setVisible(false);
                dispose();
            }
        });

        andButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                andButton.setEnabled(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

    }
}