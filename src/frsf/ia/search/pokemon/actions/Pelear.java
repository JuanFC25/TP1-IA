package frsf.ia.search.pokemon.actions;

import java.util.List;
import java.util.Map;

import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;
import frsf.ia.search.pokemon.PokemonAgentState;
import frsf.ia.search.pokemon.PokemonEnvironmentState;
import frsf.ia.search.pokemon.PokemonPerception;
import frsf.ia.search.pokemon.classes.Charmander;
import frsf.ia.search.pokemon.classes.Enemigo;

public class Pelear extends SearchAction{

	
	
	@Override
	public SearchBasedAgentState execute(SearchBasedAgentState s) {
		PokemonAgentState pokemonState = (PokemonAgentState) s;
		
		Charmander charmander = pokemonState.getCharmander();
		Integer nodoActual = charmander.getPosicion();
		Map<Integer, List<Object>> mapaAgente = pokemonState.getMapaAgente();
		
		if((Integer) mapaAgente.get(charmander.getPosicion()).get(2) == PokemonPerception.ENEMIGO_PERCEPTION) {
			Enemigo enemigo = ((Enemigo) mapaAgente.get(charmander.getPosicion()).get(1));
			if( charmander.getEnergiaActual() >  enemigo.getEnergia()) {
				pokemonState.eliminarEnemigo(nodoActual);
				Integer energia = (int) (charmander.getEnergiaActual() - enemigo.getEnergia() + enemigo.getEnergia() * 0.2);
				charmander.setEnergiaActual(energia);
				charmander.setCantidadAdversarios(charmander.getCantidadAdversarios()-1);
				charmander.setPuedeMoverse(true);
				
				pokemonState.setCharmander(charmander);
				
				//hacer subir de nivel
				//hacer contadores para ataques
				
				//ver que pasa si lo unico que se ejecuta es ir a n
				
				return pokemonState;
			}
		}
		
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
		
		Charmander charmander = pokemonEnvironmentState.getCharmander();
		Integer nodoActual = charmander.getPosicion();
		Map<Integer, List<Object>> mapaAmbiente = pokemonEnvironmentState.getMapaMundial();
		
		if((Integer) mapaAmbiente.get(charmander.getPosicion()).get(2) == PokemonPerception.ENEMIGO_PERCEPTION) {
			Enemigo enemigo = ((Enemigo) mapaAmbiente.get(charmander.getPosicion()).get(1));
			if( charmander.getEnergiaActual() >  enemigo.getEnergia()) {
				pokemonState.eliminarEnemigo(nodoActual);
				Integer energia = (int) (charmander.getEnergiaActual() - enemigo.getEnergia() + enemigo.getEnergia() * 0.2);
				charmander.setEnergiaActual(energia);
				charmander.setCantidadAdversarios(charmander.getCantidadAdversarios()-1);
				charmander.setPuedeMoverse(true);
				pokemonState.setCharmander(charmander);
				
				pokemonEnvironmentState.eliminarEnemigo(nodoActual);
				pokemonEnvironmentState.setCharmander(charmander);
				
				return pokemonEnvironmentState;
			}
		}
		
		
		
		return null;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
