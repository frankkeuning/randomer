package scripts.framework.goals;

import org.tribot.api2007.Skills;
import scripts.framework.Goal;

public class SkillGoal extends Goal {

    protected Skills.SKILLS skill;
    protected int level;

    public SkillGoal(Skills.SKILLS skill, int level){
        this.skill = skill;
        this.level = level;
    }

    @Override
    public boolean completed() {
        return Skills.getCurrentLevel(skill) == level;
    }
}
