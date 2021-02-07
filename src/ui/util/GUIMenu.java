package ui.util;

import java.awt.*;

import ui.util.GuiMenuInterFace.MenuFileListener;
import ui.util.GuiMenuInterFace.MenuHelpListener;
import ui.util.GuiMenuInterFace.MenuRunListener;

public class GUIMenu extends  MenuBar{

    private MenuItem fileNew, fileOpen, fileSave, fileExit;
    private MenuItem runRun, runDebug;
    private MenuItem helpAbout;

    public GUIMenu(){
        Menu menuFile = new Menu("File");
        Menu menuRun = new Menu("Run");
        Menu menuHelp = new Menu("Help");

        fileNew = new MenuItem("New File");
        fileOpen = new MenuItem("Open File");
        fileSave = new MenuItem("Save File");
        fileExit = new MenuItem("Exit");

        menuFile.add(fileNew);
        menuFile.add(fileOpen);
        menuFile.addSeparator();
        menuFile.add(fileSave);
        menuFile.addSeparator();
        menuFile.add(fileExit);

        menuRun.add(runRun);
        menuRun.add(runDebug);

        helpAbout = new MenuItem("About");
        menuHelp.add(helpAbout);

        add(menuFile);add(menuRun);add(menuHelp);
    }

    public void addMenuFileListener(MenuFileListener menuFileListener) {
        fileNew.addActionListener(menuFileListener::setNewFileListener);
        fileOpen.addActionListener(menuFileListener::setOpenFileListener);
        fileSave.addActionListener(menuFileListener::setSaveFileListener);
        fileExit.addActionListener(menuFileListener::setExitListener);

    }
    public void addMenuRunListener(MenuRunListener menuRunListener){
        runRun.addActionListener(menuRunListener::setRunListener);
        runDebug.addActionListener(menuRunListener::setDebugListener);
    }

    public void addMenuHelpListener(MenuHelpListener menuHelpListener){
        helpAbout.addActionListener(menuHelpListener::setAboutListener);
    }
}
