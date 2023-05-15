package frsf.ia.search.pokemon.actions;

import java.util.List;
import java.util.Map;

import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.solver.search.Search;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;
import frsf.ia.search.pokemon.PokemonAgentState;
import frsf.ia.search.pokemon.PokemonEnvironmentState;
import frsf.ia.search.pokemon.PokemonPerception;
import frsf.ia.search.pokemon.classes.Charmander;
import frsf.ia.search.pokemon.classes.Enemigo;
import frsf.ia.search.pokemon.classes.PokemonMaestro;

public class NoPelear extends SearchAction{

	@Override
	public SearchBasedAgentState execute(SearchBasedAgentState s) {
PokemonAgentState pokemonState = (PokemonAgentState) s;
		
		Charmander charmander = pokemonState.getCharmander();
		Integer nodoActual = charmander.getPosicion();
		Map<Integer, List<Object>> mapaAgente = pokemonState.getMapaAgente();
		
		if (charmander.getEnergiaActual() > 0) {      // se fija si el pokemon agente esta vivo
			if((Integer) mapaAgente.get(charmander.getPosicion()).get(2) == PokemonPerception.ENEMIGO_PERCEPTION) {
				Enemigo enemigo = ((Enemigo) mapaAgente.get(charmander.getPosicion()).get(1));
				//esto preguntar.
				if( charmander.getEnergiaActual() <= enemigo.getEnergia()) {
					Integer energia = (int) (charmander.getEnergiaActual() - (enemigo.getEnergia()/4));
					charmander.setEnergiaActual(energia);
					charmander.setPuedeMoverse(true);
					charmander.incrementarContadoresAtaques();
					
					pokemonState.setCharmander(charmander);
					pokemonState.setEnergiaFaltanteVencerPokemonFinal(charmander.getEnergiaActual());
					//hacer subir de nivel
					//hacer contadores para ataques
					return pokemonState;
				}
			}
			else if((Integer) mapaAgente.get(charmander.getPosicion()).get(2) == PokemonPerception.POKEMON_MAESTRO_PERCEPTION){
				PokemonMaestro boss = ((PokemonMaestro) mapaAgente.get(charmander.getPosicion()).get(1));
				//esto preguntar.
				if( charmander.getEnergiaActual() <= boss.getEnergia()) {
					Integer energia = (int) (charmander.getEnergiaActual() - (boss.getEnergia()/4));
					charmander.setEnergiaActual(energia);
					charmander.setPuedeMoverse(true);
					charmander.incrementarContadoresAtaques();
					
					pokemonState.setCharmander(charmander);
					pokemonState.setEnergiaFaltanteVencerPokemonFinal(charmander.getEnergiaActual());
					//hacer subir de nivel
					//hacer contadores para ataques
					return pokemonState;
				}
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
			if((Integer) mapaAmbiente.get(charmander.getPosicion()).get(2) == PokemonPerception.ENEMIGO_PERCEPTION ) {  //  Se fija si hay un enemigo en esa posicion
				Enemigo enemigo = ((Enemigo) mapaAmbiente.get(charmander.getPosicion()).get(1));
				if( charmander.getEnergiaActual() <=  enemigo.getEnergia()) {   // se fija si el enemigo tiene mas energia que el pokemon agente
					Integer energia = (int) (charmander.getEnergiaActual() - (enemigo.getEnergia()/4));
					charmander.setEnergiaActual(energia);
					charmander.setPuedeMoverse(true);
					charmander.incrementarContadoresAtaques();
					
					
					pokemonState.setCharmander(charmander);
					pokemonState.setEnergiaFaltanteVencerPokemonFinal(charmander.getEnergiaActual());
					pokemonEnvironmentState.setCharmander(charmander);
					return pokemonEnvironmentState;
					
				}
			}else if ((Integer) mapaAmbiente.get(charmander.getPosicion()).get(2) == PokemonPerception.POKEMON_MAESTRO_PERCEPTION) {
				PokemonMaestro boss = ((PokemonMaestro) mapaAmbiente.get(charmander.getPosicion()).get(1));
				if( charmander.getEnergiaActual() <=  boss.getEnergia()) {   // se fija si el enemigo tiene mas energia que el pokemon agente
					Integer energia = (int) (charmander.getEnergiaActual() - (boss.getEnergia()/4));
					charmander.setEnergiaActual(energia);
					charmander.setPuedeMoverse(true);
					charmander.incrementarContadoresAtaques();
					
					pokemonState.setCharmander(charmander);
					pokemonState.setEnergiaFaltanteVencerPokemonFinal(charmander.getEnergiaActual());
					pokemonEnvironmentState.setCharmander(charmander);
					return pokemonEnvironmentState;
				}
		 }
		}
		return null;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "NO PELEAR";
	}
}


