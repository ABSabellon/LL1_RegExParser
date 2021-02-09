package ui.fields;

import ui.util.GUIInterface;

import javax.swing.*;
import javax.swing.text.DefaultEditorKit;
import java.awt.*;


public class InputFields extends JPanel implements GUIInterface {

    private JTextArea jTextArea;


    public InputFields(){

        setBorder(BorderFactory.createLineBorder(Color.black));

        jTextArea = new JTextArea(30, 30);
        jTextArea.getDocument().putProperty(DefaultEditorKit.EndOfLineStringProperty, "\r\n");

        JScrollPane scrollPane = new JScrollPane(jTextArea);
        scrollPane.setAutoscrolls(true);
        add(scrollPane);

    }

    @Override
    public void clear() {
        jTextArea.setText(null);
    }

    @Override
    public String getText() {
        return jTextArea.getText().replace("\n", System.getProperty("line.separator"));
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
