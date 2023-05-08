package frsf.ia.search.pokemon;

import java.util.List;
import java.util.Map;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;



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
		
		return null;
	}


	private Map<Integer, List<Object>> getNodosAdyacentes(Integer posicion) {	
		return ((PokemonEnvironmentState) this.environmentState).getNodosAdyacentes(posicion);
	}
	
	

}
