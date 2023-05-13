package frsf.ia.search.pokemon;

import java.nio.file.FileSystemAlreadyExistsException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import frsf.cidisi.faia.state.EnvironmentState;
import frsf.ia.search.pokemon.classes.AtaqueEspecial;
import frsf.ia.search.pokemon.classes.Charmander;
import frsf.ia.search.pokemon.classes.Enemigo;
import frsf.ia.search.pokemon.classes.Pokebola;
import frsf.ia.search.pokemon.classes.PokemonMaestro;

public class PokemonEnvironmentState extends EnvironmentState{

	private Map<Integer, List<Object>> mapaMundial;    // key(nodo): lista de objetos: Primer elemento nodos adyacentes, Segundo elemento objeto que hay en el nodo, Tercer elemento la percepcion
	private Charmander charmander;
	private Integer cantCiclosDesdeUltimoUsoSatelite;
	private List<AtaqueEspecial> listaAtaquesEspeciales;
	
	
	public PokemonEnvironmentState(Map<Integer, List<Object>> m) {
	    mapaMundial = m;
	}
	
	public PokemonEnvironmentState() {
		mapaMundial = new HashMap<>();
		this.initState();
		
	}

	
	// inicia el estado del ambiente
	@Override
	public void initState() {

		
		this.charmander = new Charmander(1, 20, 20, 2, 1 , new HashMap<String, List<Integer>>());

		Enemigo enemigo1 = new Enemigo(1, 2, 5, 0);
		
		
		PokemonMaestro boss = new PokemonMaestro(20, 10);
		Pokebola pokebola1 = new Pokebola(1, 4, 10);


		cantCiclosDesdeUltimoUsoSatelite = 1;
		mapaMundial = new HashMap<Integer, List<Object>>();
		
		//falta inicializar Ataques especiales
		
		
				
		//Primer elemento nodos adyacentes, Segundo elemento objeto que hay en el nodo, Tercer elemento la percepcion
		//mapaMundial.put(1, List.of(List.of(2), charmander, PokemonPerception.EMPTY_PERCEPTION));
		mapaMundial.put(1, List.of(List.of(2), PokemonAgentState.VACIO, PokemonPerception.EMPTY_PERCEPTION));
		//mapaMundial.put(2, List.of(List.of(1, 3, 10), PokemonAgentState.VACIO, PokemonPerception.EMPTY_PERCEPTION));
		mapaMundial.put(2, List.of(List.of(1, 3, 10), enemigo1, PokemonPerception.ENEMIGO_PERCEPTION));
		mapaMundial.put(3, List.of(List.of(2, 4, 8), PokemonAgentState.VACIO, PokemonPerception.EMPTY_PERCEPTION));
		//mapaMundial.put(3, List.of(List.of(2, 4), enemigo1, PokemonPerception.ENEMIGO_PERCEPTION));
		mapaMundial.put(4, List.of(List.of(3, 5, 6, 9), pokebola1, PokemonPerception.POKEBOLA_PERCEPTION));
		//mapaMundial.put(4, List.of(List.of(3, 5, 9), PokemonAgentState.VACIO, PokemonPerception.EMPTY_PERCEPTION));
		mapaMundial.put(5, List.of(List.of(4, 7), PokemonAgentState.VACIO, PokemonPerception.EMPTY_PERCEPTION));
		mapaMundial.put(6, List.of(List.of(4, 7), PokemonAgentState.VACIO, PokemonPerception.EMPTY_PERCEPTION));
		mapaMundial.put(7, List.of(List.of(5, 6), PokemonAgentState.VACIO, PokemonPerception.EMPTY_PERCEPTION));
		mapaMundial.put(8, List.of(List.of(3, 9), PokemonAgentState.VACIO, PokemonPerception.EMPTY_PERCEPTION));
		mapaMundial.put(9, List.of(List.of(4, 8, 10, 15),  PokemonAgentState.VACIO, PokemonPerception.EMPTY_PERCEPTION));
		mapaMundial.put(10, List.of(List.of(2, 9, 11, 13), PokemonAgentState.VACIO, PokemonPerception.EMPTY_PERCEPTION));
		
		
		mapaMundial.put(11, List.of(List.of(10, 12 ,13), PokemonAgentState.VACIO, PokemonPerception.EMPTY_PERCEPTION));
		mapaMundial.put(12, List.of(List.of(11, 18), PokemonAgentState.VACIO, PokemonPerception.EMPTY_PERCEPTION));
		mapaMundial.put(13, List.of(List.of(10, 11, 14), PokemonAgentState.VACIO, PokemonPerception.EMPTY_PERCEPTION));
		mapaMundial.put(14, List.of(List.of(13, 16, 18), PokemonAgentState.VACIO, PokemonPerception.EMPTY_PERCEPTION));
		mapaMundial.put(15, List.of(List.of(9, 16), PokemonAgentState.VACIO, PokemonPerception.EMPTY_PERCEPTION));
		mapaMundial.put(16, List.of(List.of(14, 15, 17, 18), PokemonAgentState.VACIO, PokemonPerception.EMPTY_PERCEPTION));
		mapaMundial.put(17, List.of(List.of(16, 20), PokemonAgentState.VACIO, PokemonPerception.EMPTY_PERCEPTION));
		mapaMundial.put(18, List.of(List.of(12, 14, 16, 21, 22), PokemonAgentState.VACIO, PokemonPerception.EMPTY_PERCEPTION));
		mapaMundial.put(19, List.of(List.of(20, 22), PokemonAgentState.VACIO, PokemonPerception.EMPTY_PERCEPTION));
		mapaMundial.put(20, List.of(List.of(17, 19, 28), boss, PokemonPerception.POKEMON_MAESTRO_PERCEPTION));
		mapaMundial.put(21, List.of(List.of(18, 23, 24), PokemonAgentState.VACIO, PokemonPerception.EMPTY_PERCEPTION));
		mapaMundial.put(22, List.of(List.of(18, 19, 25, 26), PokemonAgentState.VACIO, PokemonPerception.EMPTY_PERCEPTION));
		mapaMundial.put(23, List.of(List.of(21), PokemonAgentState.VACIO, PokemonPerception.EMPTY_PERCEPTION));
		mapaMundial.put(24, List.of(List.of(21, 25), PokemonAgentState.VACIO, PokemonPerception.EMPTY_PERCEPTION));
		mapaMundial.put(25, List.of(List.of(22, 24, 27), PokemonAgentState.VACIO, PokemonPerception.EMPTY_PERCEPTION));
		mapaMundial.put(26, List.of(List.of(22, 28), PokemonAgentState.VACIO, PokemonPerception.EMPTY_PERCEPTION));
		mapaMundial.put(27, List.of(List.of(25, 29), PokemonAgentState.VACIO, PokemonPerception.EMPTY_PERCEPTION));
		mapaMundial.put(28, List.of(List.of(26, 29), PokemonAgentState.VACIO, PokemonPerception.EMPTY_PERCEPTION));
		mapaMundial.put(29, List.of(List.of(27, 28), PokemonAgentState.VACIO, PokemonPerception.EMPTY_PERCEPTION));
		
		
	}

	@Override
	public String toString() {
		return  "-----------------AMBIENTE-------------" + "\n" +
				charmander.toString()  + "\n" + "Mapa Mundial: " + mapaMundial;
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

	
	//cada vez que la percepcion llama a esta funcion es porque paso un ciclo
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
		cantCiclosDesdeUltimoUsoSatelite +=1;
		return adyacencias;
	}


/*
	public void modificarPosicionCharmander(Integer nodoActual, Charmander charmander2) {
		this.mapaMundial.replace(nodoActual, List.of(mapaMundial.get(nodoActual).get(0),PokemonAgentState.VACIO ,mapaMundial.get(nodoActual).get(2)));
		Integer nodoNuevo = charmander2.getPosicion();
		this.mapaMundial.replace(nodoNuevo, List.of(mapaMundial.get(nodoNuevo).get(0),charmander2 ,mapaMundial.get(nodoNuevo).get(2)));
	}
	*/
	
	public void eliminarEnemigo(Integer nodo) {
		mapaMundial.replace(nodo, List.of(mapaMundial.get(nodo).get(0), PokemonAgentState.VACIO,  PokemonPerception.EMPTY_PERCEPTION));
		
	}
	
	
	public Map<Integer, List<Object>> usarSatelite() {
		
		if (cantCiclosDesdeUltimoUsoSatelite <1) {
			return null;
		}
		cantCiclosDesdeUltimoUsoSatelite = 0;
		return mapaMundial;
	
	}

	public void vencerPokemonFinal(PokemonMaestro boss, Integer nodoActual) {
		this.mapaMundial.replace(nodoActual, List.of(mapaMundial.get(nodoActual).get(0), boss, mapaMundial.get(nodoActual).get(2)));
		
	}

	public void eliminarPokebola(Integer nodoActual) {
		this.mapaMundial.replace(nodoActual, List.of(mapaMundial.get(nodoActual).get(0), PokemonAgentState.VACIO, PokemonPerception.EMPTY_PERCEPTION));
	}
	
	
	
	
}
