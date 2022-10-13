package nz.ac.vuw.ecs.swen225.gp22.app;

import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Button class which extends JButton.
 *
 * @author Kevin Zeng
 *         ID: 300563468
 */
public class Button extends JButton {

  /**
   * Constructor takes in a text field, and an actionListener.
   * Buttons are by default set to not focusable.
   *
   * @param text   text of the button
   * @param action ActionListener to be added
   */
  public Button(String text, ActionListener action) {
    super(text);
    addActionListener(action);
    setFocusable(false);
  }
}
