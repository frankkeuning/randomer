package scripts.woodcutting;

import org.tribot.api.DynamicClicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Objects;
import org.tribot.api2007.Player;
import org.tribot.api2007.Walking;
import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSObject;
import scripts.framework.Action;
import scripts.framework.actions.WalkAction;

import java.util.function.BooleanSupplier;

public class ChopAction extends Action {



    @Override
    public Action execute() {
        if(Player.getAnimation() != -1 || Player.isMoving())
            return null;
        if(!Areas.treeArea.contains(Player.getPosition())){
            return new WalkAction(Areas.treeArea.getRandomTile());
        }
        if(Inventory.isFull()){
            return new WalkToBankAction();
        }
        chop();
        return null;
    }

    void chop(){
        RSObject[] trees = Objects.findNearest(20,"Tree");
        if(trees.length > 0){
            RSObject tree = trees[0];
            if(tree.isClickable() && tree.isOnScreen()){
                if(DynamicClicking.clickRSObject(tree,"Chop down")){
                    Timing.waitCondition(new BooleanSupplier() {
                        @Override
                        public boolean getAsBoolean() {
                            return Player.getAnimation() != -1;
                        }
                    }, General.random(1250,2000));
                }
            } else {
                if(Walking.walkTo(new RSArea(tree.getPosition(),3).getRandomTile())){
                    Timing.waitCondition(new BooleanSupplier() {
                        @Override
                        public boolean getAsBoolean() {
                            return tree.isOnScreen();
                        }
                    }, General.random(750,1000));
                }

            }

        }
    }

    /*
        ChopAction is the base action for woodcutting, this action will never be completed
     */
    @Override
    public boolean completed() {
        return false;
    }
}
