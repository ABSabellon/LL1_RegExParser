package io;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class TxtFilter extends FileFilter {
    @Override
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }

        String extension = getExtension(f);
        if (extension != null) {
            if (extension.equals("txt")) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 && i < s.length() - 1) {
            ext = s.substring(i + 1).toLowerCase();
        }
        return ext;
    }

    @Override
    public String getDescription() {
        return "*.txt";
    }
}
