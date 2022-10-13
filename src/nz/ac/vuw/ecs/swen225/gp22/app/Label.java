package nz.ac.vuw.ecs.swen225.gp22.app;

import javax.swing.*;
import java.awt.*;

/**
 * Label class which extends JLabel.
 *
 * @author Kevin Zeng
 *         ID: 300563468
 */
public class Label extends JLabel {
  private static final Font DEFAULT_FONT = new Font("Verdana", Font.PLAIN, 20);

  /**
   * Constructor that takes in just a text field.
   * Font is set to the DEFAULT_FONT.
   * Labels are by default set to not focusable.
   *
   * @param text   text to be displayed on the label
   */
  public Label(String text) {
    super(text, SwingConstants.CENTER);
    setFont(DEFAULT_FONT);
    setBackground(Color.BLACK);
    setForeground(Color.WHITE);
    setFocusable(false);
  }
}
