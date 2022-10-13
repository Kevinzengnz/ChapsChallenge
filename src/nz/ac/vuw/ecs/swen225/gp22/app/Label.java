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
   * Constructor takes in a text field, and a font.
   * Labels are by default set to not focusable.
   *
   * @param text   text to be displayed on the label
   * @param font   desired font for this component
   */
  public Label(String text, Font font) {
    super(text);
    setFont(font);
    setFocusable(false);
  }

  /**
   * Constructor that takes in just a text field.
   * Font is set to the DEFAULT_FONT.
   * Labels are by default set to not focusable.
   *
   * @param text   text to be displayed on the label
   */
  public Label(String text) {
    super(text);
    setFont(DEFAULT_FONT);
    setFocusable(false);
  }
}
