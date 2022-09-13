package nz.ac.vuw.ecs.swen225.gp22.renderer;

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

    public void update(int newX, int newY) {
        tileX = newX;
        tileY = newY;
        if (animation != null) animation.update();
    }


    public int getX() {
        if (animation.isRunning()) return animation.getX();
        return tileX  * tileSize;
    }
    public int getY() {
        if (animation.isRunning()) return animation.getY();
        return tileY * tileSize;
    }
    public int getTileX() {
        return tileX;
    }
    public int getTileY() {
        return tileY;
    }

    public void addAnimation(Animation newAnimation) {
        if (animation != null && animation.isRunning()) throw new IllegalStateException("Camera already has an animation");
        this.animation = newAnimation;
    }

}
