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
import frsf.ia.search.pokemon.classes.PokemonMaestro;

public class Pelear extends SearchAction{

	
	
	@Override
	public SearchBasedAgentState execute(SearchBasedAgentState s) {
		PokemonAgentState pokemonState = (PokemonAgentState) s;
		
		Charmander charmander = pokemonState.getCharmander();
		Integer nodoActual = charmander.getPosicion();
		Map<Integer, List<Object>> mapaAgente = pokemonState.getMapaAgente();
		
		
		if (charmander.getEnergiaActual() > 0) {         // se fija si el pokemon agente esta vivo
			if((Integer) mapaAgente.get(charmander.getPosicion()).get(2) == PokemonPerception.ENEMIGO_PERCEPTION) {
				Enemigo enemigo = ((Enemigo) mapaAgente.get(charmander.getPosicion()).get(1));
				if( charmander.getEnergiaActual() >  enemigo.getEnergia()) {
					pokemonState.eliminarEnemigo(nodoActual);
					Integer energia = (int) (charmander.getEnergiaActual() - enemigo.getEnergia() + enemigo.getEnergia() * 0.2);
					charmander.setEnergiaActual(energia);
					charmander.setCantidadAdversarios(charmander.getCantidadAdversarios()-1);
					charmander.setPuedeMoverse(true);
					charmander.incrementarContadoresAtaques();
					charmander.evaluarSubirDeNivel();
					
					pokemonState.setCharmander(charmander);

					return pokemonState;
				}
			} else if ((Integer) mapaAgente.get(charmander.getPosicion()).get(2) == PokemonPerception.POKEMON_MAESTRO_PERCEPTION){
				PokemonMaestro boss = ((PokemonMaestro) mapaAgente.get(charmander.getPosicion()).get(1));
				if( charmander.getEnergiaActual() >  boss.getEnergia()) {
					
					Integer energia = (int) (charmander.getEnergiaActual() - boss.getEnergia() + boss.getEnergia() * 0.2);
					charmander.setEnergiaActual(energia);
					charmander.setCantidadAdversarios(charmander.getCantidadAdversarios()-1);
					charmander.setPuedeMoverse(true);
					charmander.incrementarContadoresAtaques();
					charmander.evaluarSubirDeNivel();
					
					pokemonState.setCharmander(charmander);
					
					pokemonState.vencerPokemonFinal(boss, nodoActual);

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
			if((Integer) mapaAmbiente.get(charmander.getPosicion()).get(2) == PokemonPerception.ENEMIGO_PERCEPTION) {
				Enemigo enemigo = ((Enemigo) mapaAmbiente.get(charmander.getPosicion()).get(1));
				if( charmander.getEnergiaActual() >  enemigo.getEnergia()) {
					
					Integer energia = (int) (charmander.getEnergiaActual() - enemigo.getEnergia() + enemigo.getEnergia() * 0.2);
					charmander.setEnergiaActual(energia);
					charmander.setCantidadAdversarios(charmander.getCantidadAdversarios()-1);
					charmander.setPuedeMoverse(true);
					charmander.incrementarContadoresAtaques();
					charmander.evaluarSubirDeNivel();
					
					
					pokemonState.eliminarEnemigo(nodoActual);
					pokemonState.setCharmander(charmander);
					
					pokemonEnvironmentState.eliminarEnemigo(nodoActual);
					pokemonEnvironmentState.setCharmander(charmander);
				
					
					
			
					return pokemonEnvironmentState;
				}
			} else if((Integer) mapaAmbiente.get(charmander.getPosicion()).get(2) == PokemonPerception.POKEMON_MAESTRO_PERCEPTION) {
				PokemonMaestro boss = ((PokemonMaestro) mapaAmbiente.get(charmander.getPosicion()).get(1));
				if( charmander.getEnergiaActual() >  boss.getEnergia()) {
					boss.setEnergia(0);
					
					Integer energia = (int) (charmander.getEnergiaActual() - boss.getEnergia() + boss.getEnergia() * 0.2);
					charmander.setEnergiaActual(energia);
					charmander.setCantidadAdversarios(charmander.getCantidadAdversarios()-1);
					charmander.setPuedeMoverse(true);
					charmander.incrementarContadoresAtaques();
					
					charmander.evaluarSubirDeNivel();
					
					pokemonState.setCharmander(charmander);
					pokemonState.vencerPokemonFinal(boss, nodoActual);
					
					pokemonEnvironmentState.setCharmander(charmander);
					pokemonEnvironmentState.vencerPokemonFinal(boss, nodoActual);
					
					return pokemonEnvironmentState;
				}
			}
		}
	
		return null;
	}

	@Override
	public String toString() {
		return "PELEAR";
	}

}
