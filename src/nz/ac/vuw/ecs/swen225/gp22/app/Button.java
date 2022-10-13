package nz.ac.vuw.ecs.swen225.gp22.app;

import javax.swing.*;
import java.awt.event.ActionListener;

public class Button extends JButton {
  public Button(String text, ActionListener action) {
    super(text);
    addActionListener(action);
    setFocusable(false);
  }
}
