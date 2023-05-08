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
		
		//Primer elemento nodos adyacentes, Segundo elemento objeto que hay en el nodo, Tercer elemento la percepcion
		mapaMundial.put(1, List.of(List.of(2), charmander, PokemonPerception.EMPTY_PERCEPTION));
		mapaMundial.put(2, List.of(List.of(1, 3), null, PokemonPerception.EMPTY_PERCEPTION));
		mapaMundial.put(3, List.of(List.of(2, 4), enemigo1, PokemonPerception.ENEMIGO_PERCEPTION));
		mapaMundial.put(4, List.of(List.of(3, 5), pokebola1, PokemonPerception.POKEBOLA_PERCEPTION));
		mapaMundial.put(5, List.of(List.of(4), boss, PokemonPerception.POKEMON_MAESTRO_PERCEPTION));
		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
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
			
			if( getMapaMundial().get(nodo).get(1) != null) {
				adyacencias.put(nodo,  List.of(getMapaMundial().get(nodo).get(1), getMapaMundial().get(nodo).get(2)));
			}
			
			adyacencias.put(nodo,  List.of(null, getMapaMundial().get(nodo).get(2)));
		}
		return adyacencias;
	}

	
	
	
	
}
