package nz.ac.vuw.ecs.swen225.gp22.domain;
import nz.ac.vuw.ecs.swen225.gp22.app.Model;
/**
 * @author Alicia Robinson - 300560663
 */
public class InfoTile extends Tile{
    private String infoText;
    protected Runnable soundEffect;
    protected InfoTile(Point point) {
        super("INFO", point);
    }
    public void setText(String infoText){
        if(infoText.isEmpty()){
            throw new IllegalArgumentException("Text cannot be empty");
        }
        this.infoText = infoText;
    }
    public void setSoundEffect(Runnable soundEffect){
        if(soundEffect == null){
            throw new IllegalArgumentException("Sound Effect is Null");
        }
        this.soundEffect = soundEffect;
    }
    public String getText(){
        return infoText;
    }
    @Override
    public void doAction(Model model, Player player, Point point) {
        soundEffect.run();
        //TODO InfoTile doAction
    }
}
