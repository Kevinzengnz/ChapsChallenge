package nz.ac.vuw.ecs.swen225.gp22.domain;

import nz.ac.vuw.ecs.swen225.gp22.app.Model;

/**
 * Represents Info Tile in the game.
 * Displays game instructions when moved onto by a Player.
 *
 * @author Alicia Robinson - 300560663
 */
public class InfoTile extends Tile {
    /**
     * Game instructions to be displayed.
     */
    private String infoText;
    /**
     * Sound effect that can be run when InfoTile is interacted with.
     */
    public Runnable soundEffect;

    /**
     * Creates InfoTile at specified point.
     *
     * @param point InfoTiles position
     */
    protected InfoTile(Point point) {
        super("INFO", point);
    }

    /**
     * Sets the info text of the Tile.
     *
     * @param infoText instruction text
     */
    public void setText(String infoText) {
        if (infoText == null || infoText.isEmpty()) {
            throw new IllegalArgumentException("Text cannot be empty");
        }
        this.infoText = infoText;
    }

    /**
     * Returns game instructions.
     *
     * @return infoText
     */
    public String getText(){
        return infoText;
    }

    @Override
    public void setSoundEffect(Runnable soundEffect) {
        if (soundEffect == null) {
            throw new IllegalArgumentException("Sound Effect is Null");
        }
        this.soundEffect = soundEffect;
    }

    @Override
    public void doAction(Model model, Player player, Point point) {
        soundEffect.run();
    }

    @Override
    public int getDepth() {
        return 1;
    }
}
