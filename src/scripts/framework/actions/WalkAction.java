package scripts.framework.actions;

import org.tribot.api2007.Player;
import org.tribot.api2007.WebWalking;
import org.tribot.api2007.types.RSTile;
import scripts.framework.Action;

public class WalkAction extends Action {

    private RSTile destination;

    public WalkAction(RSTile destination){
        this.destination = destination;
    }


    @Override
    public Action execute() {
        WebWalking.walkTo(destination);
        return null;
    }

    @Override
    public boolean completed() {
        return Player.getRSPlayer().getPosition().distanceTo(destination) < 5;
    }
}
