package nz.ac.vuw.ecs.swen225.gp22.renderer;

import nz.ac.vuw.ecs.swen225.gp22.domain.Point;

/**
 * Class to represent the camera. Has support for animation.
 * @author Oliver Silk
 * ID: 300564261
 */
public class Camera {
    protected final int visionSize = 9;
    protected final int visionDistance = (visionSize-1)/2;

    private final int tileSize = 64;

    private int tileX;
    private int tileY;

    private Animation animation;

    protected Camera(int tileX, int tileY) {
        this.tileX = tileX;
        this.tileY = tileY;
    }

    public void update(Point position) {
        tileX = position.x();
        tileY = position.y();
        if (animation != null) animation.ping();
    }


    public int getX() {
        if (animation != null && !animation.isFinished()) return animation.getX();
        return tileX  * tileSize;
    }
    public int getY() {
        if (animation != null && !animation.isFinished()) return animation.getY();
        return tileY * tileSize;
    }
    public int getTileX() {
        return tileX;
    }
    public int getTileY() {
        return tileY;
    }

    public void addAnimation(Animation newAnimation) {
        this.animation = newAnimation;
    }

    public void removeAnimation() {
        this.animation = null;
    }

}
