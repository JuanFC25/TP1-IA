package frsf.ia.search.pokemon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.ia.search.pokemon.classes.AtaqueEspecial;
import frsf.ia.search.pokemon.classes.Charmander;
import frsf.ia.search.pokemon.classes.Enemigo;
import frsf.ia.search.pokemon.classes.Pokebola;
import frsf.ia.search.pokemon.classes.PokemonMaestro;

public class PokemonAgentState  extends SearchBasedAgentState{

	private Charmander charmander;
	//Primer elemento nodos adyacentes, Segundo elemento objeto que hay en el nodo, Tercer elemento la percepcion
	private Map<Integer, List<Object>> mapaAgente; //igual que en ambiente, es la representacion interna del agente
	private List<AtaqueEspecial> listaAtaquesEspeciales;
	private Integer cantidadMovimientosTotales;
	
	public Integer cantidadMovimientosSinPerderEnergia;
	
	public final static Integer VACIO = 1000;
	private Boolean vencioPokemonMaestro = false;
	
	public PokemonAgentState() {
		initState();
	}


	

	public PokemonAgentState(Charmander charmander, Map<Integer, List<Object>> mapaAgente) {
		super();
		this.charmander = charmander;
		this.mapaAgente = mapaAgente;
		

		AtaqueEspecial ataque1 = new AtaqueEspecial(2, 20, "Scary Face");
		AtaqueEspecial ataque2 = new AtaqueEspecial(3, 30, "Slash");
		AtaqueEspecial ataque3 = new AtaqueEspecial(4, 50, "Fire Fang");
		listaAtaquesEspeciales = new ArrayList<>();
		listaAtaquesEspeciales.add(ataque1);
		listaAtaquesEspeciales.add(ataque2);
		listaAtaquesEspeciales.add(ataque3);
		
		cantidadMovimientosTotales=0;

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
		
		if(mapaAgenteObj.equals(this.mapaAgente) && charmanderObj.equals(this.charmander)
				&& ((PokemonAgentState) obj).cantidadMovimientosSinPerderEnergia == this.cantidadMovimientosSinPerderEnergia) return true;
		
		return false;
	}
	
	
	
	/**
     * This method clones the state of the agent. It's used in the search
     * process, when creating the search tree.
     */
	@Override
	public SearchBasedAgentState clone() {
		Map<Integer, List<Object>> newMapaAgente = new HashMap<>();
				newMapaAgente.putAll(this.mapaAgente);
				
		Map<String, List<Integer>>	newAtaquesEspeciales = new HashMap<>();
		newAtaquesEspeciales.putAll(this.charmander.getAtaquesDisponibles());
				
		Charmander newCharmander = new Charmander(charmander.getPosicion(), charmander.getEnergiaActual(),
				charmander.getEnergiaInicial(), charmander.getCantidadAdversarios(), charmander.getNivel(), newAtaquesEspeciales, charmander.getPuedeMoverse());
		
		
		PokemonAgentState newState = new PokemonAgentState(newCharmander, newMapaAgente);
		newState.cantidadMovimientosSinPerderEnergia = this.cantidadMovimientosSinPerderEnergia;
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

		//hacer lo de actualizar lista de enemigos.
		 
		Set<Integer> nodosAdyacentes = pokemonPerception.getNodosAdyacentes().keySet();
		
		if (pokemonPerception.getMapaMundial() == null) {
			for ( Integer nodo : nodosAdyacentes) {
				
				Object contenido = pokemonPerception.getNodosAdyacentes().get(nodo).get(0);
				Integer percepcion = (Integer) pokemonPerception.getNodosAdyacentes().get(nodo).get(1);
		
				if (contenido != VACIO) {
					mapaAgente.put(nodo, List.of(mapaAgente.get(nodo).get(0), contenido, percepcion));
				} else {
					mapaAgente.put(nodo, List.of(mapaAgente.get(nodo).get(0), VACIO, percepcion));
				}
			}
		} else {
			this.setMapaAgente(pokemonPerception.getMapaMundial());
		}
		
		
		
	}

	

	
	/**
     * This method is optional, and sets the initial state of the agent.
     */
	@Override
	public void initState() {
		// ver si hay que poner el mapa de nodos completos
		mapaAgente = new HashMap<>();
		vencioPokemonMaestro = false;
	
		charmander = new Charmander(1,20,20,2,1, null);
		cantidadMovimientosSinPerderEnergia = 0;
		cantidadMovimientosTotales = 0;
		
		AtaqueEspecial ataque1 = new AtaqueEspecial(2, 20, "Scary Face");
		AtaqueEspecial ataque2 = new AtaqueEspecial(3, 30, "Slash");
		AtaqueEspecial ataque3 = new AtaqueEspecial(4, 50, "Fire Fang");
		listaAtaquesEspeciales = new ArrayList<>();
		listaAtaquesEspeciales.add(ataque1);
		listaAtaquesEspeciales.add(ataque2);
		listaAtaquesEspeciales.add(ataque3);
	}
	

	public boolean isInMasterPokemonPosition() {
		//cambiar, no hardcodear
		
		if (getCharmander().getPosicion() == 7) {
			return true;
		}
		else return false;
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






	public void eliminarEnemigo(Integer nodo) {
		mapaAgente.replace(nodo, List.of(mapaAgente.get(nodo).get(0), VACIO,  PokemonPerception.EMPTY_PERCEPTION));
		
	}

	public boolean isMasterPokemonAlive() {
		
		return !vencioPokemonMaestro;
	}




	public void vencerPokemonFinal(PokemonMaestro boss, Integer nodoActual) {
		//seteo la condicion en el pokemon maestro para que se cumpla el goal
		this.mapaAgente.replace(nodoActual, List.of(mapaAgente.get(nodoActual).get(0), boss, mapaAgente.get(nodoActual).get(2)));
		this.vencioPokemonMaestro = true;
	}




	public void eliminarPokebola(Integer nodo) {
		mapaAgente.replace(nodo, List.of(mapaAgente.get(nodo).get(0), VACIO,  PokemonPerception.EMPTY_PERCEPTION));
	}

	
	/**
     * This method returns the String representation of the agent state.
     */
	@Override
	public String toString() {
String mapa = new String();
		
		Set<Integer> nodos = mapaAgente.keySet(); 
		for (Integer nodo: nodos) {
			switch ((Integer)mapaAgente.get(nodo).get(2)){
			case 0: {
				mapa = mapa + "Nodo N° " + nodo + " | Adyacencias: " + mapaAgente.get(nodo).get(0) + " Contenido: VACIO  Percepcion: " + mapaAgente.get(nodo).get(2) + "\n";
				break;
			}
			case 3: {
				Pokebola p =(Pokebola) mapaAgente.get(nodo).get(1);
				mapa = mapa + "Nodo N° " + nodo + " | Adyacencias: " + mapaAgente.get(nodo).get(0) + " Contenido: " + p + "   Percepcion: " + mapaAgente.get(nodo).get(2) + "\n";
				break;
			}
			case 1: {
				Enemigo e =(Enemigo) mapaAgente.get(nodo).get(1);
				mapa = mapa + "Nodo N° " + nodo + " | Adyacencias: " + mapaAgente.get(nodo).get(0) + " Contenido: " + e + "   Percepcion: " + mapaAgente.get(nodo).get(2) + "\n";
				break;
			}
			case 2: {
				PokemonMaestro pm =(PokemonMaestro) mapaAgente.get(nodo).get(1);
				mapa = mapa + "Nodo N° " + nodo + " | Adyacencias: " + mapaAgente.get(nodo).get(0) + " Contenido: " + pm + "   Percepcion: " + mapaAgente.get(nodo).get(2) + "\n";
				break;
			}
			}
		}
		return "\n" + "--------------AGENTE---------------" + "\n"
				+ charmander.toString() + "\n" + "Mapa agente: " +  "\n" + mapa;
	}




	public Integer getCantidadMovimientosTotales() {
		return cantidadMovimientosTotales;
	}

	public void aumentarCantidadMovimientosTotales() {
		this.cantidadMovimientosTotales += 1;
	}

}
