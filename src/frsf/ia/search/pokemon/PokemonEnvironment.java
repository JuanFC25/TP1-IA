package frsf.ia.search.pokemon;

import java.util.List;
import java.util.Map;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;
import frsf.ia.search.pokemon.classes.Charmander;



public class PokemonEnvironment extends Environment {


	public PokemonEnvironment() {
        // Create the environment state
        this.environmentState = new PokemonEnvironmentState();
    }


	@Override
    public PokemonEnvironmentState getEnvironmentState() {
        return (PokemonEnvironmentState) super.getEnvironmentState();
    }

	
	
	/**
     * This method is called by the simulator. Given the Agent, it creates
     * a new perception reading, for example, the agent position.
     * @param agent
     * @return A perception that will be given to the agent by the simulator.
     */
	@Override
	public Perception getPercept() {
		
		PokemonPerception perception = new PokemonPerception();
		
		Integer posicion = getEnvironmentState().getCharmander().getPosicion();
		
		perception.setNodosAdyacentes(this.getNodosAdyacentes(posicion));
		perception.setNodosAdyacentes2(this.getNodosAdyacentes2(posicion));
		perception.setCharmander(this.getCharmander());
		
		return perception;
	}


	private Charmander getCharmander() {
		return ((PokemonEnvironmentState) this.environmentState).getCharmander();
	}


	private List<Object> getNodosAdyacentes2(Integer posicion) {
		return ((PokemonEnvironmentState) this.environmentState).getNodosAdyacentes2(posicion);
	}


	private Map<Integer, List<Object>> getNodosAdyacentes(Integer posicion) {	
		return ((PokemonEnvironmentState) this.environmentState).getNodosAdyacentes(posicion);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return environmentState.toString();
	}

}
