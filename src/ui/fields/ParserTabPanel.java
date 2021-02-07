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
        jTextArea.setLineWrap(false);

        JScrollPane scrollPane = new JScrollPane(jTextArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane, BorderLayout.CENTER);
    }

    @Override
    public void clear() {
        jTextArea.setText(null);
    }

    @Override
    public String getText() {
        return jTextArea.getText();
    }

    @Override
    public void setText(String s) {
        jTextArea.setText(s);
    }

    public byte[] getTextWithByte(){
        jTextArea.getText().getBytes();
        return new byte[0];
    }

}
