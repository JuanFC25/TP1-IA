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
import frsf.ia.search.pokemon.classes.Pokebola;

public class RecogerEnergiaPokebola extends SearchAction{

	@Override
	public SearchBasedAgentState execute(SearchBasedAgentState s) {
		PokemonAgentState pokemonState = (PokemonAgentState) s;
		
		Charmander charmander = pokemonState.getCharmander();
		Integer nodoActual = charmander.getPosicion();
		Map<Integer, List<Object>> mapaAgente = pokemonState.getMapaAgente();
		
		
		if (charmander.getEnergiaActual() > 0) {         // se fija si el pokemon agente esta vivo
			if((Integer) mapaAgente.get(charmander.getPosicion()).get(2) == PokemonPerception.POKEBOLA_PERCEPTION) { // me fijo si hay una pokebola
				Pokebola pokebola = ((Pokebola) mapaAgente.get(charmander.getPosicion()).get(1));
				Integer energia = charmander.getEnergiaActual() + pokebola.getEnergia();
				//ver si hay que armar funcion eliminarPokebola
				pokemonState.eliminarPokebola(nodoActual);
				charmander.setEnergiaActual(energia);
				charmander.setPuedeMoverse(true);
				charmander.incrementarContadoresAtaques();
				charmander.evaluarSubirDeNivel();
				pokemonState.setCharmander(charmander);
				
				
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
		if (charmander.getEnergiaActual() > 0) {         // se fija si el pokemon agente esta vivo
			if((Integer) mapaAmbiente.get(charmander.getPosicion()).get(2) == PokemonPerception.POKEBOLA_PERCEPTION) {
				Pokebola pokebola = ((Pokebola) mapaAmbiente.get(charmander.getPosicion()).get(1));
				Integer energia = charmander.getEnergiaActual() + pokebola.getEnergia();
				//ver si hay que armar funcion eliminarPokebola
				charmander.setEnergiaActual(energia);
				charmander.setPuedeMoverse(true);
				
				pokemonState.eliminarPokebola(nodoActual);
				charmander.incrementarContadoresAtaques();
				charmander.evaluarSubirDeNivel();
				pokemonState.setCharmander(charmander);
				
				pokemonEnvironmentState.setCharmander(charmander);
				pokemonEnvironmentState.eliminarPokebola(nodoActual);
				
				
				return pokemonEnvironmentState;
			}
		
		}		
		
		return null;
	}

	@Override
	public String toString() {
	
		return "RECOJO POKEBOLA";
	}
	
	
}
