package ui.util;
import java.awt.event.ActionEvent;


public class GuiMenuInterFace {

    public interface MenuFileListener {
        public void setNewFileListener(ActionEvent event);
        public void setOpenFileListener(ActionEvent event);
        public void setSaveFileListener(ActionEvent event);
        void setExitListener(ActionEvent event);
    }

    public interface MenuRunListener {
        public void setRunListener(ActionEvent event);
        public void setDebugListener(ActionEvent event);
    }

    public interface MenuHelpListener {
        public void setAboutListener(ActionEvent event);
    }
}
