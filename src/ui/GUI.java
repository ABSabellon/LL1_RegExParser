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

