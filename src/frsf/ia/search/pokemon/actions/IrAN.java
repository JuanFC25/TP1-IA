package frsf.ia.search.pokemon.actions;

import java.util.List;

import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;
import frsf.ia.search.pokemon.PokemonAgentState;
import frsf.ia.search.pokemon.PokemonEnvironmentState;
import frsf.ia.search.pokemon.PokemonPerception;
import frsf.ia.search.pokemon.classes.Charmander;


public class IrAN extends SearchAction{

	private Integer nodo;

	public IrAN(Integer nodo) {
		super();
		this.nodo = nodo;
	}

	
	
	 /**
     * This method updates a tree node state when the search process is running.
     * It does not updates the real world state.
     */
	@Override
	public SearchBasedAgentState execute(SearchBasedAgentState s) {
		
		PokemonAgentState pokemonState = (PokemonAgentState) s;
		
		Charmander charmander = pokemonState.getCharmander();
		Integer nodoActual = charmander.getPosicion();
		
		List<Integer> nodosAdyacentes = (List<Integer>) pokemonState.getMapaAgente().get(nodoActual).get(0);
		
		//primero pregunto si puede moverse, ya que si se movio antes debe hacer otra accion salvo que el nodo este vacio
		if(charmander.getPuedeMoverse() ||((int) (pokemonState.getMapaAgente().get(nodoActual).get(2))) == PokemonPerception.EMPTY_PERCEPTION) {
			if(nodosAdyacentes != null && nodosAdyacentes.contains(nodo)) {
				charmander.setPosicion(nodo);
				charmander.setPuedeMoverse(false);
				pokemonState.setCharmander(charmander);
				
				//pokemonState.modificarPosicionCharmander(nodoActual, charmander); //metodo que actualiza la posicion de charmander en pokemon Agent State
				//ver si sirve
				
				return pokemonState;
			}
		}
		
		
		
		return null;
	}


	
	
	/**
     * This method updates the agent state and the real world state.
     */
	@Override
	public EnvironmentState execute(AgentState ast, EnvironmentState est) {
		PokemonAgentState pokemonState = (PokemonAgentState) ast;
		PokemonEnvironmentState pokemonEnvironmentState = (PokemonEnvironmentState) est;
		
		Charmander charmander = pokemonEnvironmentState.getCharmander();
		Integer nodoActual =  charmander.getPosicion();
		
		List<Integer> nodosAdyacentes = (List<Integer>) pokemonEnvironmentState.getMapaMundial().get(nodoActual).get(0);
		
		if(charmander.getPuedeMoverse() ||((int) (pokemonEnvironmentState.getMapaMundial().get(nodoActual).get(2))) == PokemonPerception.EMPTY_PERCEPTION) {
			if(nodosAdyacentes != null && nodosAdyacentes.contains(nodo)) {
				charmander.setPosicion(nodo);
				pokemonState.setCharmander(charmander);
				charmander.setPuedeMoverse(false);
				//ver si sirve
				//pokemonState.modificarPosicionCharmander(nodoActual, charmander);
		
				
				pokemonEnvironmentState.setCharmander(charmander);
				//ver si sirve
				//pokemonEnvironmentState.modificarPosicionCharmander(nodoActual, charmander);
				
				return pokemonEnvironmentState;
			}
				
		}
	
		return null;
	}

	
	/**
     * This method returns the action cost.
     */
	@Override
	public Double getCost() {
		// TODO Auto-generated method stub
		return null;
	}
	
    /**
     * This method is not important for a search based agent, but is essensial
     * when creating a calculus based one.
     */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "IR A NODO N°" + nodo;
	}
	
	
	

	
	
}
