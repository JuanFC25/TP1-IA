package frsf.ia.search.pokemon;

import java.util.List;
import java.util.Map;

import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

public class PokemonPerception extends Perception{

	public static int UNKNOWN_PERCEPTION = -1;
    public static int EMPTY_PERCEPTION = 0;
    public static int ENEMIGO_PERCEPTION = 1;
    public static int POKEMON_MAESTRO_PERCEPTION = 2;
    public static int POKEBOLA_PERCEPTION = 3;
    public static int SATELITE_PERCEPTION = 4;
	
    Map<Integer, List<Object>> nodosAdyacentes; //map de los nodos adyacentes de un nodo. Compuesto por el nodo adyacente
    //como key y luego una lista de objetos(primer elemento el objeto que hay, puede ser null y luego la percepcion)
    
    
    /**
     * This method is used to setup the perception.
     */
    @Override
	public void initPerception(Agent agent, Environment environment) {
		PokemonAgent pokemonAgent = (PokemonAgent) agent;
		PokemonEnvironment pokemonEnvironment = (PokemonEnvironment) environment;
		PokemonEnvironmentState environmentState = pokemonEnvironment.getEnvironmentState();
		
		Integer posicion = environmentState.getCharmander().getPosicion();
		
		nodosAdyacentes = environmentState.getNodosAdyacentes(posicion);
		
		
	}


    
    
	public Map<Integer, List<Object>> getNodosAdyacentes() {
		return nodosAdyacentes;
	}


	public void setNodosAdyacentes(Map<Integer, List<Object>> nodosAdyacentes) {
		this.nodosAdyacentes = nodosAdyacentes;
	}
	
	
    
    
}
