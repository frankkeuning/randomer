package scripts.woodcutting;

import org.tribot.api2007.Banking;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.WebWalking;
import scripts.framework.Action;

public class WalkToBankAction extends Action {
    @Override
    public Action execute() {
        WebWalking.walkToBank();
        if(Banking.isInBank() && Inventory.isFull()){
            return new BankAction();
        } else{
            return null;
        }
    }

    @Override
    public boolean completed() {
        return Banking.isInBank();
    }
}
