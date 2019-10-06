package scripts.framework;

public abstract class Action {

    /*
        return a new action if a new action is required, (e.g walking)
        return null if no new action is required.
     */
    public abstract Action execute();

    /*
        return true if the action has been completed,
        return false otherwise.
     */
    public abstract boolean completed();

}
