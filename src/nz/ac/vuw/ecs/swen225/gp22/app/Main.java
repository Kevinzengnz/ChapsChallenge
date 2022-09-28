package nz.ac.vuw.ecs.swen225.gp22.app;

import javax.swing.*;

/**
 * Main Java class
 * Creates a new instance of Chaps Challenge and runs it.
 * @author Kevin Zeng
 * ID: 300563468
 */
public class Main {
    public static void main(String[]a){
        SwingUtilities.invokeLater(ChapsChallenge::new);
    }
}
