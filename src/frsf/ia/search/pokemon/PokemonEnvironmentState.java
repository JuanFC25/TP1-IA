package frsf.ia.search.pokemon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import frsf.cidisi.faia.state.EnvironmentState;
import frsf.ia.search.pokemon.classes.Charmander;
import frsf.ia.search.pokemon.classes.Enemigo;
import frsf.ia.search.pokemon.classes.Pokebola;
import frsf.ia.search.pokemon.classes.PokemonMaestro;

public class PokemonEnvironmentState extends EnvironmentState{

	private Map<Integer, List<Object>> mapaMundial;    // key(nodo): lista de objetos: Primer elemento nodos adyacentes, Segundo elemento objeto que hay en el nodo, Tercer elemento la percepcion
	private Charmander charmander;
	
	
	
	public PokemonEnvironmentState(Map<Integer, List<Object>> m) {
	    mapaMundial = m;
	}
	
	public PokemonEnvironmentState() {
		mapaMundial = new HashMap<>();
		this.initState();
		
	}

	
	
	@Override
	public void initState() {
		this.charmander = new Charmander(1, 20, 20, 2, 1 , new HashMap<Integer, List<Integer>>());
		Enemigo enemigo1 = new Enemigo(1, 3, 5, 0);
		PokemonMaestro boss = new PokemonMaestro(5, 10);
		Pokebola pokebola1 = new Pokebola(1, 4, 10);
		
		mapaMundial = new HashMap<Integer, List<Object>>();
		
		//falta inicializar Ataques especiales
		
		
		//Primer elemento nodos adyacentes, Segundo elemento objeto que hay en el nodo, Tercer elemento la percepcion
		mapaMundial.put(1, List.of(List.of(2), charmander, PokemonPerception.EMPTY_PERCEPTION));
		mapaMundial.put(2, List.of(List.of(1, 3), PokemonAgentState.VACIO, PokemonPerception.EMPTY_PERCEPTION));
		mapaMundial.put(3, List.of(List.of(2, 4), enemigo1, PokemonPerception.ENEMIGO_PERCEPTION));
		mapaMundial.put(4, List.of(List.of(3, 5), pokebola1, PokemonPerception.POKEBOLA_PERCEPTION));
		mapaMundial.put(5, List.of(List.of(4), boss, PokemonPerception.POKEMON_MAESTRO_PERCEPTION));
		
	}

	@Override
	public String toString() {
		return  "-----------------AMBIENTE------------- \\N" +
				charmander.toString() + "  \\n  Mapa Mundial: " + mapaMundial;
	}

	
	
	
	public Map<Integer, List<Object>> getMapaMundial() {
		return mapaMundial;
	}

	public void setMapaMundial(Map<Integer, List<Object>> mapaMundial) {
		this.mapaMundial = mapaMundial;
	}

	public Charmander getCharmander() {
		return charmander;
	}

	public void setCharmander(Charmander charmander) {
		this.charmander = charmander;
	}

	public Map<Integer, List<Object>> getNodosAdyacentes(Integer posicion) {
		
		
		List<Integer> nodosAdyacentes = (List<Integer>) mapaMundial.get(posicion).get(0);
		
		//key; nodo // lista: primer elemento es el objeto que hay en ese nodo y segundo elemento la percepcion
		
		Map<Integer, List<Object>> adyacencias = new HashMap<>();
		
		for (Integer nodo : nodosAdyacentes) {
			
			if( getMapaMundial().get(nodo).get(1) != PokemonAgentState.VACIO) {
				adyacencias.put(nodo,  List.of(getMapaMundial().get(nodo).get(1), getMapaMundial().get(nodo).get(2)));
			}
			
			adyacencias.put(nodo,  List.of(PokemonAgentState.VACIO, getMapaMundial().get(nodo).get(2)));
		}
		return adyacencias;
	}

public List<Object> getNodosAdyacentes2(Integer posicion) {
		
		
		List<Integer> nodosAdyacentes = (List<Integer>) mapaMundial.get(posicion).get(0);
		
		//key; nodo // lista: primer elemento es el objeto que hay en ese nodo y segundo elemento la percepcion
		
		List<Object> adyacencias = new 	ArrayList();
		
		for (Integer nodo : nodosAdyacentes) {
			
			if( getMapaMundial().get(nodo).get(1) != PokemonAgentState.VACIO) {
				adyacencias.add(List.of(nodo, getMapaMundial().get(nodo).get(1), getMapaMundial().get(nodo).get(2)));
				//adyacencias.L(nodo,  List.of(getMapaMundial().get(nodo).get(1), getMapaMundial().get(nodo).get(2)));
			}
			adyacencias.add(List.of(nodo, PokemonAgentState.VACIO, getMapaMundial().get(nodo).get(2)));
			//adyacencias.put(nodo,  List.of(null, getMapaMundial().get(nodo).get(2)));
		}
		return adyacencias;
	}

public void modificarPosicionCharmander(Integer nodoActual, Charmander charmander2) {
	this.mapaMundial.replace(nodoActual, List.of(mapaMundial.get(nodoActual).get(0),PokemonAgentState.VACIO ,mapaMundial.get(nodoActual).get(2)));
	Integer nodoNuevo = charmander2.getPosicion();
	this.mapaMundial.replace(nodoNuevo, List.of(mapaMundial.get(nodoNuevo).get(0),charmander2 ,mapaMundial.get(nodoNuevo).get(2)));
}

public void eliminarEnemigo(Integer nodo) {
	mapaMundial.replace(nodo, List.of(mapaMundial.get(nodo), PokemonAgentState.VACIO,  PokemonPerception.EMPTY_PERCEPTION));
}
	
	
	
	
}
