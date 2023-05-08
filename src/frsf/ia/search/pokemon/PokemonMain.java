package frsf.ia.search.pokemon;

import frsf.cidisi.faia.exceptions.PrologConnectorException;
import frsf.cidisi.faia.simulator.SearchBasedAgentSimulator;

public class PokemonMain {

	public static void main(String[] args) throws PrologConnectorException {
		
		PokemonEnvironment pokemonEnvironment = new PokemonEnvironment();
		PokemonAgent pokemonAgent = new PokemonAgent();
		
		  SearchBasedAgentSimulator simulator =
	                new SearchBasedAgentSimulator(pokemonEnvironment, pokemonAgent);
	        
	        simulator.start();
	}
}
