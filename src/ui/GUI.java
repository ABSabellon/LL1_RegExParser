package ui;

//all Java Imports
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import ui.fields.InputFields;
import ui.fields.LexAnTabPanel;
import ui.fields.ParserTabPanel;
import ui.util.FileUtil;
import ui.util.GUIMenu;
import ui.util.GUIMenuInterFace;

import javax.swing.*;

import static javax.swing.JOptionPane.INFORMATION_MESSAGE;

public class GUI extends JPanel {
    private JFrame frame;

    private JPanel jInputPanel;
    private JPanel buttonPanel;

    private JTabbedPane tabbedPane;

    private JFileChooser fileChooser;

    public String currentContent;

    private InputFields inputTextPanel;
    private LexAnTabPanel lexAnTab;
    private ParserTabPanel parserTab;


    public GUI(JFrame frame) {

        inputTextPanel = new InputFields();
        lexAnTab = new LexAnTabPanel();
        parserTab = new ParserTabPanel();

        fileChooser = new JFileChooser("./etc/TestCases");

        this.frame = frame;
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        //Input
        jInputPanel = new JPanel();
        jInputPanel.setLayout(new BoxLayout(jInputPanel, BoxLayout.Y_AXIS));
        this.add(inputTextPanel);

        //tabbed
        tabbedPane = new JTabbedPane();

        tabbedPane.add("Token Recognizer ", lexAnTab);
        this.add(tabbedPane);

        tabbedPane.add("Parser", parserTab);
        this.add(tabbedPane);

        GUIMenu guiMenuBar = new GUIMenu();
        frame.setMenuBar(guiMenuBar);

        guiMenuBar.addMenuFileListener(new GUIMenuInterFace.MenuFileListener() {
            @Override
            public void setNewFileListener(ActionEvent event) {
                inputTextPanel.clear();
            }

            @Override
            public void setOpenFileListener(ActionEvent event) {

            }

            @Override
            public void setSaveFileListener(ActionEvent event) {
                //check if default name is duplicated
                File file = new File("Output.txt");
                while (FileUtil.isDuplicate(file, fileChooser.getCurrentDirectory())) {
                    String tem = file.getName().split("\\.")[0];
                    file = new File("Output" + (Integer.parseInt(tem.substring(8, tem.length())) + 1) + ".txt");
                }
                fileChooser.setSelectedFile(file);

                int i = fileChooser.showSaveDialog(GUI.this);
                if (i == JFileChooser.APPROVE_OPTION) {
                    File f = fileChooser.getSelectedFile();
                    //check if the file is already existed
                    try {
                        FileOutputStream out = new FileOutputStream(f);
                        out.write(inputTextPanel.getTextWithByte());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

            }

            @Override
            public void setExitListener(ActionEvent event) {
                inputTextPanel.clear();
            }
        });

        guiMenuBar.addMenuHelpListener(new GUIMenuInterFace.MenuHelpListener() {
            @Override
            public void setAboutListener(ActionEvent event) {
                JOptionPane.showMessageDialog(GUI.this, "@Author: Sabellon, Aileen \n@Date 2021-02-05", "CMPILER: REGEX PARSER", INFORMATION_MESSAGE);
            }
        });

    }

    private String readCodeToString(String filePath) throws IOException {
        StringBuffer fileData = new StringBuffer();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        char[] buf = new char[1024];
        int numRead = 0;
        while ((numRead = reader.read(buf)) != -1) {
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
            buf = new char[1024];
        }
        reader.close();
        return fileData.toString();
    }

    private class RunActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}

