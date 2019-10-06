package scripts;

import org.tribot.api.General;
import org.tribot.api2007.Login;
import org.tribot.api2007.Skills;
import org.tribot.script.Script;
import scripts.framework.Action;
import scripts.framework.Goal;
import scripts.framework.Task;
import scripts.framework.goals.SkillGoal;
import scripts.woodcutting.ChopAction;

import java.util.Stack;

public class Main extends Script {

    Stack<Task> taskStack = new Stack<>();
    boolean end = false;

    @Override
    public void run() {
        init();
        while(!end){
            if(Login.getLoginState().equals(Login.STATE.INGAME)){
                if(taskStack.isEmpty())
                    break;
                Task currentTask = taskStack.peek();
                if(currentTask == null) {
                    end = true;
                } else {
                    if(!currentTask.completed()){
                        currentTask.execute();
                    } else {
                        currentTask.end();
                        taskStack.pop();
                    }
                }
            } else {
                Login.login();
            }

            General.sleep(10,20);
        }
    }

    private void init(){
        Goal woodcuttingGoal = new SkillGoal(Skills.SKILLS.WOODCUTTING, 10);
        Action woodcuttingAction = new ChopAction();
        Task woodcuttingTask = new Task(woodcuttingAction,woodcuttingGoal);
        taskStack.push(woodcuttingTask);
    }
}
