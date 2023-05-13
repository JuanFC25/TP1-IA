package frsf.ia.search.pokemon;

import java.util.List;
import java.util.Map;

import frsf.cidisi.faia.agent.Action;
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
		
		perception.setMapaMundial(this.getMapaMundial());
		
		perception.setNodosAdyacentes(this.getNodosAdyacentes(posicion));

		perception.setCharmander(this.getCharmander());
		
		
		
		return perception;
	}

	/*
	@Override
    public boolean agentFailed(Action actionReturned) {

        PokemonEnvironmentState pokemonEnvironmentState =
                this.getEnvironmentState();

        int agentEnergy = pokemonEnvironmentState.getCharmander().getEnergiaActual();


        if (agentEnergy < 1)
            return true;

        return false;
    }
*/
	
	private Charmander getCharmander() {
		return ((PokemonEnvironmentState) this.environmentState).getCharmander();
	}




	private Map<Integer, List<Object>> getNodosAdyacentes(Integer posicion) {	
		return ((PokemonEnvironmentState) this.environmentState).getNodosAdyacentes(posicion);
	}
	
	
	//aca se usa el satelite
	private Map<Integer, List<Object>> getMapaMundial() {	
		return ((PokemonEnvironmentState) this.environmentState).usarSatelite();
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return environmentState.toString();
	}

	
	
}
