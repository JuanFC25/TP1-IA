package frsf.ia.search.pokemon.actions;

import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.solver.search.Search;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;
import frsf.ia.search.pokemon.PokemonAgentState;
import frsf.ia.search.pokemon.PokemonEnvironmentState;
import frsf.ia.search.pokemon.classes.Charmander;

public class NoPelear extends SearchAction{

	@Override
	public SearchBasedAgentState execute(SearchBasedAgentState s) {
PokemonAgentState pokemonState = (PokemonAgentState) s;
		
		Charmander charmander = pokemonState.getCharmander();
		Integer nodoActual = charmander.getPosicion();
		return null;
	}

	@Override
	public Double getCost() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EnvironmentState execute(AgentState ast, EnvironmentState est) {
		PokemonAgentState pokemonState = (PokemonAgentState) ast;
		PokemonEnvironmentState pokemonEnvironmentState = (PokemonEnvironmentState) est;
		return null;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	

}
