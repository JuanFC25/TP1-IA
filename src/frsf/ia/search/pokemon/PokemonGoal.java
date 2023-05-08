package frsf.ia.search.pokemon;

import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;



public class PokemonGoal extends GoalTest{
	@Override
	public boolean isGoalState(AgentState agentState) {
        if (((PokemonAgentState) agentState).isInMasterPokemonPosition()) {
            return true;
        }
        return false;
	}
}
