/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testeModels;


/**
 *
 * @author JoaoRocha
 */
import java.io.File;
import java.io.IOException;


public class Desktop {
    // hide the constructor.

    Desktop() {
    }
    // Created the appropriate instance

    public static Desktop getDesktop() {
        String os = System.getProperty("os.name").toLowerCase();
        Desktop desktop = new Desktop();         // This uf/elseif/else code is used only once: here
        if (os.indexOf("windows") != -1 || os.indexOf("nt") != -1) {
            desktop = new WindowsDesktop();
        } else if (os.equals("windows 95") || os.equals("windows 98")) {
            desktop = new Windows9xDesktop();
        } else if (os.indexOf("mac") != -1) {
            desktop = new OSXDesktop();
        } else if (os.indexOf("linux") != -1) {
            //&& isGnome()
            //String KDE = System.getProperty("KDEDIR");
            //if (KDE == null || KDE.isEmpty()) {
            //    desktop = new GnomeDesktop();
            //} else {
            desktop = new KdeDesktop();
            //}

        } else {
            throw new UnsupportedOperationException(String.format("The platform %s is not supported ", os));
        }
        return desktop;
    }    // default implementation :(

    public void open(File file) {
        throw new UnsupportedOperationException();
    }    // default implementation :(

    public void open(String endereco) {
        throw new UnsupportedOperationException();
    }    // default implementation :(

    public void imaginaryAction(File file) {
        throw new UnsupportedOperationException();
    }
}
// One subclass per platform below:
// Each one knows how to handle its own platform

class GnomeDesktop extends Desktop {

    public void open(File file) {
        try {
            // Runtime.getRuntime().exec: execute gnome-open <file>
            Runtime.getRuntime().exec("gnome-open " + file.getPath());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void open(String endereco) {
        try {
            Runtime.getRuntime().exec("gnome-open " + endereco);
        } catch (Exception ex) {
           ex.printStackTrace();
        }
    }
}

class KdeDesktop extends Desktop {

    public void open(File file) {
        try {
            Runtime.getRuntime().exec("kfmclient exec " + file.getPath());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void open(String endereco) {
        try {
            Runtime.getRuntime().exec("kfmclient exec " + endereco);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

class OSXDesktop extends Desktop {

    public void open(File file) {
        try {
            Runtime.getRuntime().exec("open " + file.getPath());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void open(String endereco) {
        try {
            Runtime.getRuntime().exec("open " + endereco);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

class WindowsDesktop extends Desktop {

    public void open(File file) {
        try {
            Runtime.getRuntime().exec("cmd /c start " + file.getPath());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void open(String endereco) {
        try {
            Runtime.getRuntime().exec("cmd /c start " + endereco);
        } catch (Exception ex) {
           ex.printStackTrace();
        }
    }
}

class Windows9xDesktop extends Desktop {

    public void open(File file) {
        try {
            Runtime.getRuntime().exec("command.com /C start " + file.getPath());
        } catch (IOException ex) {
           ex.printStackTrace();
        }
    }
}

