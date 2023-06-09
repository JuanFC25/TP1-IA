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

		Integer ciclosUltimouso = getEnvironmentState().getCantCiclosDesdeUltimoUsoSatelite();
		Integer ciclosSatelite = getEnvironmentState().getCiclosSatelite();
		
		if(getEnvironmentState().getCiclosSinMoverseEnemigos() == 2) {
			getEnvironmentState().moverEnemigos();
			getEnvironmentState().setCiclosSinMoverseEnemigos(0);
		} else {
			getEnvironmentState().setCiclosSinMoverseEnemigos(getEnvironmentState().getCiclosSinMoverseEnemigos() + 1);
		}
		
		if( ciclosUltimouso == ciclosSatelite ) {
			getEnvironmentState().setCantCiclosDesdeUltimoUsoSatelite(0);
			getEnvironmentState().setCiclosSatelite(getEnvironmentState().getNumeroRandom(5, 10));
			perception.setMapaMundial(this.getMapaMundial());
			
		} else {
			getEnvironmentState().setCantCiclosDesdeUltimoUsoSatelite(getEnvironmentState().getCantCiclosDesdeUltimoUsoSatelite() + 1);
			perception.setMapaMundial(null);
		}
		
		
		
		perception.setNodosAdyacentes(this.getNodosAdyacentes(posicion));

		perception.setCharmander(this.getCharmander());
		
		
		
		return perception;
	}

	
	
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

	
	//no anda
	@Override
    public boolean agentFailed(Action actionReturned) {

        PokemonEnvironmentState pokemonEnvironmentState =
                this.getEnvironmentState();

        int agentEnergy = pokemonEnvironmentState.getCharmander().getEnergiaActual();

        // FIXME: The pacman agent always has the same energy
        // If the agent has no energy, he failed
        if (agentEnergy <= 0)
            return true;

        return false;
    }
	
}
