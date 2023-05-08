package frsf.ia.search.pokemon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.ia.search.pokemon.classes.Charmander;
import frsf.ia.search.pokemon.classes.PokemonMaestro;

public class PokemonAgentState  extends SearchBasedAgentState{

	private Charmander charmander;
	//Primer elemento nodos adyacentes, Segundo elemento objeto que hay en el nodo, Tercer elemento la percepcion
	private Map<Integer, List<Object>> mapaAgente; //igual que en ambiente, es la representacion interna del agente
	
	public final static Integer VACIO = 1000;
	
	
	public PokemonAgentState() {
		initState();
	}


	

	public PokemonAgentState(Charmander charmander, Map<Integer, List<Object>> mapaAgente) {
		super();
		this.charmander = charmander;
		this.mapaAgente = mapaAgente;

	}




	/**
     * This method is used in the search process to verify if the node already
     * exists in the actual search.
     */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof PokemonAgentState))
			return false;
	
		Map<Integer, List<Object>> mapaAgenteObj = ((PokemonAgentState) obj).getMapaAgente();
		Charmander charmanderObj = ((PokemonAgentState) obj).getCharmander();
		
		if(mapaAgenteObj.equals(this.mapaAgente) && charmanderObj.equals(this.charmander)) return true;
		
		return false;
	}
	
	
	
	/**
     * This method clones the state of the agent. It's used in the search
     * process, when creating the search tree.
     */
	@Override
	public SearchBasedAgentState clone() {
		Map<Integer, List<Object>> newMapaAgente = this.mapaAgente;
		Charmander newCharmander = new Charmander(charmander.getPosicion(), charmander.getEnergiaActual(),
				charmander.getEnergiaInicial(), charmander.getCantidadAdversarios(), charmander.getNivel(), charmander.getAtaquesDisponibles(), charmander.getPuedeMoverse());
		
		
		PokemonAgentState newState = new PokemonAgentState(newCharmander, newMapaAgente);
		return newState;
	}

	
	/**
     * This method is used to update the Pacman State when a Perception is
     * received by the Simulator.
     */
	@Override
	public void updateState(Perception p) {
		PokemonPerception pokemonPerception = (PokemonPerception) p;
		
		//actualizo el pokemon agente con los datos del environment
		charmander = pokemonPerception.getCharmander();

		//List<Object> nodosAdyacentes = pokemonPerception.getNodosAdyacentes2();
		 
		Set<Integer> nodosAdyacentes = pokemonPerception.getNodosAdyacentes().keySet();
		
		for ( Integer nodo : nodosAdyacentes) {
		
			Object contenido = pokemonPerception.getNodosAdyacentes().get(nodo).get(0);
			Integer percepcion = (Integer) pokemonPerception.getNodosAdyacentes().get(nodo).get(1);
			
			if (contenido != VACIO) {
				mapaAgente.put(nodo, List.of(mapaAgente.get(nodo).get(0), contenido, percepcion));
			} else {
				mapaAgente.put(nodo, List.of(mapaAgente.get(nodo).get(0), VACIO, percepcion));
			}
		}
		
	}

	
	/**
     * This method returns the String representation of the agent state.
     */
	@Override
	public String toString() {
		return charmander.toString() + "\n" + "Mapa agente: " + mapaAgente;
	}

	
	/**
     * This method is optional, and sets the initial state of the agent.
     */
	@Override
	public void initState() {
		// ver si hay que poner el mapa de nodos completos
		
		mapaAgente = inicializarMapa();
		charmander = new Charmander(1,20,20,2,1, null);
		
	}



	private Map<Integer, List<Object>> inicializarMapa() {
		Map<Integer, List<Object>>  mapa = new HashMap<Integer, List<Object>>();
		mapa.put(1, List.of(List.of(2), VACIO, PokemonPerception.EMPTY_PERCEPTION));
		mapa.put(2, List.of(List.of(1, 3, 10), VACIO, PokemonPerception.UNKNOWN_PERCEPTION));
		mapa.put(3, List.of(List.of(2, 4), VACIO, PokemonPerception.UNKNOWN_PERCEPTION));
		mapa.put(4, List.of(List.of(3, 5, 9), VACIO, PokemonPerception.UNKNOWN_PERCEPTION));
		mapa.put(5, List.of(List.of(4), VACIO, PokemonPerception.UNKNOWN_PERCEPTION));
		
		mapa.put(10, List.of(List.of(2, 9), VACIO, PokemonPerception.UNKNOWN_PERCEPTION));
		
		mapa.put(9, List.of(List.of(4, 10), VACIO, PokemonPerception.UNKNOWN_PERCEPTION));
		return mapa;
	}



	public boolean isInMasterPokemonPosition() {
		return (getCharmander().getPosicion() == 5);
	}



	public Map<Integer, List<Object>> getMapaAgente() {
		return mapaAgente;
	}



	public void setMapaAgente(Map<Integer, List<Object>> mapaAgente) {
		this.mapaAgente = mapaAgente;
	}



	public Charmander getCharmander() {
		return charmander;
	}



	public void setCharmander(Charmander charmander) {
		this.charmander = charmander;
	}



	public void modificarPosicionCharmander(Integer nodoActual, Charmander charmander2) {
		this.mapaAgente.replace(nodoActual, List.of(mapaAgente.get(nodoActual).get(0),VACIO ,mapaAgente.get(nodoActual).get(2)));
		
		Integer nodoNuevo = charmander2.getPosicion();
		
		this.mapaAgente.replace(nodoNuevo, List.of(mapaAgente.get(nodoNuevo).get(0),charmander2 ,mapaAgente.get(nodoNuevo).get(2)));
		
	}




	public void eliminarEnemigo(Integer nodo) {
		mapaAgente.replace(nodo, List.of(mapaAgente.get(nodo), VACIO,  PokemonPerception.EMPTY_PERCEPTION));
		
	}




	public boolean isMasterPokemonAlive() {
		if(((PokemonMaestro) (mapaAgente.get(5).get(1))).getEnergia() == 0)return true;
		return false;
	}

	
}
