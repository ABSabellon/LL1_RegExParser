package ui.fields;

import ui.util.GUIInterface;

import javax.swing.*;
import java.awt.*;


public class InputFields extends JPanel implements GUIInterface {

    private JTextArea jTextArea;


    public InputFields(){

        setBorder(BorderFactory.createLineBorder(Color.black));

        jTextArea = new JTextArea(30, 40);

        JScrollPane scrollPane = new JScrollPane(jTextArea);
        scrollPane.setAutoscrolls(true);
        add(scrollPane);

    }

    @Override
    public void clear() {
        jTextArea.setText(null);
    }

    @Override
    public void getText() {
        jTextArea.getText();
    }

    @Override
    public void setText() {

    }

    public byte[] getTextWithByte(){
        jTextArea.getText().getBytes();
        return new byte[0];
    }
}
