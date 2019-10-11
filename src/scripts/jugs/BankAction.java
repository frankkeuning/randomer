package scripts.jugs;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Inventory;
import scripts.framework.Action;

import java.util.function.BooleanSupplier;

public class BankAction extends Action {
    @Override
    public Action execute() {
        if(Banking.isBankScreenOpen()){
            if(Inventory.getCount("Jug of water") > 0){
                Banking.depositAll();
                General.sleep(General.randomSD(40,4000,600,300));
            } else {
                Banking.withdraw(28,"Jug");
                General.sleep(General.randomSD(40,4000,600,300));
                Banking.close();
                General.sleep(General.randomSD(40,4000,600,300));
            }
        } else {
            if(Banking.openBank()){
                Timing.waitCondition(new BooleanSupplier() {
                    @Override
                    public boolean getAsBoolean() {
                        General.sleep(10,20);
                        return Banking.isBankScreenOpen();
                    }
                }, General.random(750,1000));
            }
        }
        return null;
    }


    @Override
    public boolean completed() {
        return Inventory.getCount("Jug") == 28;
    }
}
