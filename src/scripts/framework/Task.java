package scripts.framework;

import java.util.Stack;

public class Task {

    /*
        the action stack is filled by the initial action for a task
     */
    private Stack<Action> actionStack = new Stack<>();
    private Goal goal;

    public Task(Action action, Goal goal){
        actionStack.push(action);
        this.goal = goal;
    }

    public boolean completed(){
        return goal.completed();
    }

    public void execute(){
        Action currentAction = actionStack.peek();
        if(currentAction == null)
            return;
        if(!currentAction.completed()){
            Action response = currentAction.execute();
            if(response != null){
                actionStack.push(response);
            }
        } else {
            actionStack.pop();
        }
    }

    public void end(){
        actionStack = null;
    }

}
