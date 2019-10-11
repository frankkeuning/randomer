package scripts.jugs;

import org.tribot.api.Clicking;
import org.tribot.api.DynamicClicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Objects;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSItem;
import org.tribot.api2007.types.RSObject;
import scripts.framework.Action;

import java.util.function.BooleanSupplier;

public class FillAction extends Action {

    int waterCount = 0;

    @Override
    public Action execute() {
        Timing.waitCondition(new BooleanSupplier() {
            @Override
            public boolean getAsBoolean() {
                General.sleep(10,20);
                return Player.getAnimation() != -1;
            }
        },General.random(500,1000));
        if(Player.getAnimation() != -1 || Player.isMoving())
            return null;
        if(Inventory.getCount("Jug") == 0){
            waterCount = 0;
            return new BankAction();
        }
        fill();
        return null;

    }


    private void fill(){
        RSObject[] fountains = Objects.findNearest(10,"Fountain");
        if(fountains.length > 0){
            RSObject fountain = fountains[0];
            if(fountain.isOnScreen() && fountain.isClickable()){
                RSItem[] jugs = Inventory.find("Jug");
                if(Clicking.click(jugs[0])){
                    General.sleep(General.randomSD(100,1000,400,200));
                }
                if(DynamicClicking.clickRSObject(fountain,"Use Jug")){
                    Timing.waitCondition(new BooleanSupplier() {
                        @Override
                        public boolean getAsBoolean() {
                            return Player.getAnimation() != -1;
                        }
                    }, General.random(1250,2500));
                }
            }
        }
    }

    @Override
    public boolean completed() {
        return false;
    }
}
