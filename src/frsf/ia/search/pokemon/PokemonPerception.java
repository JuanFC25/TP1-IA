package frsf.ia.search.pokemon;

import java.util.List;
import java.util.Map;

import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;
import frsf.ia.search.pokemon.classes.Charmander;

public class PokemonPerception extends Perception{

	public static int UNKNOWN_PERCEPTION = -1;
    public static int EMPTY_PERCEPTION = 0;
    public static int ENEMIGO_PERCEPTION = 1;
    public static int POKEMON_MAESTRO_PERCEPTION = 2;
    public static int POKEBOLA_PERCEPTION = 3;
    public static int SATELITE_PERCEPTION = 4;
	
    Charmander charmander; //pokemon agente
    Map<Integer, List<Object>> nodosAdyacentes; //map de los nodos adyacentes de un nodo. Compuesto por el nodo adyacente
    //como key y luego una lista de objetos(primer elemento el objeto que hay, puede ser null y luego la percepcion)
    List<Object> nodosAdyacentes2; //prueba(creo que es mas facil que con map)
    
   
    public PokemonPerception() {
        System.out.println("Se crea la percepcion" );
    }
    
    
    public PokemonPerception(Agent agent, Environment environment) {
        super(agent, environment);
    }
    
    /**
     * This method is used to setup the perception.
     */
    @Override
	public void initPerception(Agent agent, Environment environment) {
		PokemonAgent pokemonAgent = (PokemonAgent) agent;
		PokemonEnvironment pokemonEnvironment = (PokemonEnvironment) environment;
		PokemonEnvironmentState environmentState = pokemonEnvironment.getEnvironmentState();
		
		Integer posicion = environmentState.getCharmander().getPosicion();
		
		this.setCharmander(environmentState.getCharmander());
		this.setNodosAdyacentes(environmentState.getNodosAdyacentes(posicion));
		//nodosAdyacentes = environmentState.getNodosAdyacentes(posicion);
		this.setNodosAdyacentes2(environmentState.getNodosAdyacentes2(posicion));
		//nodosAdyacentes2 = environmentState.getNodosAdyacentes2(posicion);
		
		
	}


    
    
	public Map<Integer, List<Object>> getNodosAdyacentes() {
		return nodosAdyacentes;
	}


	public void setNodosAdyacentes(Map<Integer, List<Object>> nodosAdyacentes) {
		this.nodosAdyacentes = nodosAdyacentes;
	}



	public Charmander getCharmander() {
		return charmander;
	}



	public void setCharmander(Charmander charmander) {
		this.charmander = charmander;
	}




	public List<Object> getNodosAdyacentes2() {
		return nodosAdyacentes2;
	}


	public void setNodosAdyacentes2(List<Object> nodosAdyacentes2) {
		this.nodosAdyacentes2 = nodosAdyacentes2;
	}
	
	
    @Override
    public String toString() {
    	return charmander.toString() + "\n" + "Nodos adyacentes(nodo, contenido, percepcion): " + nodosAdyacentes2;
    }
    
}
