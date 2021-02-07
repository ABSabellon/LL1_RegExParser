package ui.fields;

import ui.util.GUIInterface;

import javax.swing.*;
import java.awt.*;

public class ParserTabPanel extends JPanel implements GUIInterface {
    private JTextArea jTextArea;

    public ParserTabPanel(){
        setLayout(new BorderLayout(0, 0));
        jTextArea = new JTextArea(30, 35);
        jTextArea.setEditable(false);
        jTextArea.setLineWrap(true);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(jTextArea);
        add(scrollPane, BorderLayout.CENTER);
    }

    @Override
    public void clear() {
        jTextArea.setText(null);
    }
}
