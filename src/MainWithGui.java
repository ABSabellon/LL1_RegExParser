import ui.GUI;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class MainWithGui {

    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("CMPILER commandList.Interpreter");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(new GUI(frame));
                frame.pack();
                frame.setResizable(false);
                frame.setVisible(true);
            }
        });
    }
}
