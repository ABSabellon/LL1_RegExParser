package ui.util;

import java.awt.*;

import ui.util.GUIMenuInterFace.MenuFileListener;
import ui.util.GUIMenuInterFace.MenuHelpListener;

public class GUIMenu extends  MenuBar{

    private MenuItem fileNew, fileOpen, fileSave, fileExit;
    private MenuItem runRun, runFile;
    private MenuItem helpAbout;

    public GUIMenu(){
        Menu menuFile = new Menu("File");
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

        helpAbout = new MenuItem("About");
        menuHelp.add(helpAbout);

        add(menuFile);add(menuHelp);
    }

    public void addMenuFileListener(MenuFileListener menuFileListener){
        fileNew.addActionListener(menuFileListener::setNewFileListener);
        fileOpen.addActionListener(menuFileListener::setOpenFileListener);
        fileSave.addActionListener(menuFileListener::setSaveFileListener);
        fileExit.addActionListener(menuFileListener::setExitListener);
    }

    public void addMenuHelpListener(MenuHelpListener menuHelpListener){
        helpAbout.addActionListener(menuHelpListener::setAboutListener);
    }
}
