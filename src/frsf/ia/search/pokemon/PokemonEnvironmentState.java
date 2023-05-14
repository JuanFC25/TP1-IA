package frsf.ia.search.pokemon;

import java.nio.file.FileSystemAlreadyExistsException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.text.StyledEditorKit.ForegroundAction;

import frsf.cidisi.faia.state.EnvironmentState;
import frsf.ia.search.pokemon.classes.AtaqueEspecial;
import frsf.ia.search.pokemon.classes.Charmander;
import frsf.ia.search.pokemon.classes.Enemigo;
import frsf.ia.search.pokemon.classes.Pokebola;
import frsf.ia.search.pokemon.classes.PokemonMaestro;

public class PokemonEnvironmentState extends EnvironmentState{

	private Map<Integer, List<Object>> mapaMundial;    // key(nodo): lista de objetos: Primer elemento nodos adyacentes, Segundo elemento objeto que hay en el nodo, Tercer elemento la percepcion
	private Charmander charmander;
	public Integer cantCiclosDesdeUltimoUsoSatelite;
	private List<AtaqueEspecial> listaAtaquesEspeciales;
	private Integer ciclosSinMoverseEnemigos;
	private Integer ciclosSatelite;
	
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
		Enemigo enemigo2 = new Enemigo(2, 10, 10, 0);
		
		PokemonMaestro boss = new PokemonMaestro(7, 35);
		
		Pokebola pokebola1 = new Pokebola(1, 8, 20);
		
		
		//Pokebola pokebola2 = new Pokebola(2, 11, 10);
		
		
		/*
		Pokebola pokebola3 = new Pokebola(3, 19, 10);
		Pokebola pokebola4 = new Pokebola(4, 21, 10);
		Pokebola pokebola5 = new Pokebola(5, 29, 10);
		 */

		ciclosSatelite = getNumeroRandom(5, 10);
		
		cantCiclosDesdeUltimoUsoSatelite = ciclosSatelite;
		ciclosSinMoverseEnemigos = 0;
		mapaMundial = new HashMap<Integer, List<Object>>();
		
		//falta inicializar Ataques especiales
		
		
				
		//Primer elemento nodos adyacentes, Segundo elemento objeto que hay en el nodo, Tercer elemento la percepcion
		//mapaMundial.put(1, List.of(List.of(2), charmander, PokemonPerception.EMPTY_PERCEPTION));
		mapaMundial.put(1, List.of(List.of(2), PokemonAgentState.VACIO, PokemonPerception.EMPTY_PERCEPTION));
		//mapaMundial.put(2, List.of(List.of(1, 3, 10), PokemonAgentState.VACIO, PokemonPerception.EMPTY_PERCEPTION));
		mapaMundial.put(2, List.of(List.of(1, 3, 10), enemigo1, PokemonPerception.ENEMIGO_PERCEPTION));
		//mapaMundial.put(2, List.of(List.of(1, 3, 10), PokemonAgentState.VACIO, PokemonPerception.EMPTY_PERCEPTION));
		mapaMundial.put(3, List.of(List.of(2, 4, 8), PokemonAgentState.VACIO, PokemonPerception.EMPTY_PERCEPTION));
		//mapaMundial.put(3, List.of(List.of(2, 4), enemigo1, PokemonPerception.ENEMIGO_PERCEPTION));
		mapaMundial.put(4, List.of(List.of(3, 5, 6, 9), PokemonAgentState.VACIO, PokemonPerception.EMPTY_PERCEPTION));
		//mapaMundial.put(4, List.of(List.of(3, 5, 9), PokemonAgentState.VACIO, PokemonPerception.EMPTY_PERCEPTION));
		mapaMundial.put(5, List.of(List.of(4, 7), PokemonAgentState.VACIO, PokemonPerception.EMPTY_PERCEPTION));
		mapaMundial.put(6, List.of(List.of(4, 7), PokemonAgentState.VACIO, PokemonPerception.EMPTY_PERCEPTION));
		mapaMundial.put(7, List.of(List.of(5, 6), boss, PokemonPerception.POKEMON_MAESTRO_PERCEPTION));
		mapaMundial.put(8, List.of(List.of(3, 9),pokebola1, PokemonPerception.POKEBOLA_PERCEPTION));
		mapaMundial.put(9, List.of(List.of(4, 8, 10),  PokemonAgentState.VACIO, PokemonPerception.EMPTY_PERCEPTION));
		mapaMundial.put(10, List.of(List.of(2, 9), enemigo2, PokemonPerception.ENEMIGO_PERCEPTION));
		
		/*
		mapaMundial.put(11, List.of(List.of(10, 12 ,13), pokebola2, PokemonPerception.POKEBOLA_PERCEPTION));
		mapaMundial.put(12, List.of(List.of(11), PokemonAgentState.VACIO, PokemonPerception.EMPTY_PERCEPTION));
		mapaMundial.put(13, List.of(List.of(10, 11, 14), PokemonAgentState.VACIO, PokemonPerception.EMPTY_PERCEPTION));
		mapaMundial.put(14, List.of(List.of(13, 16), PokemonAgentState.VACIO, PokemonPerception.EMPTY_PERCEPTION));
		mapaMundial.put(15, List.of(List.of(9, 16), PokemonAgentState.VACIO, PokemonPerception.EMPTY_PERCEPTION));
		mapaMundial.put(16, List.of(List.of(14, 15), boss, PokemonPerception.POKEMON_MAESTRO_PERCEPTION));
		
		
		mapaMundial.put(17, List.of(List.of(16, 20), PokemonAgentState.VACIO, PokemonPerception.EMPTY_PERCEPTION));
		mapaMundial.put(18, List.of(List.of(12, 14, 16, 21, 22), PokemonAgentState.VACIO, PokemonPerception.EMPTY_PERCEPTION));
		mapaMundial.put(19, List.of(List.of(20, 22), pokebola3, PokemonPerception.POKEBOLA_PERCEPTION));
		mapaMundial.put(20, List.of(List.of(17, 19, 28), boss, PokemonPerception.POKEMON_MAESTRO_PERCEPTION));
		mapaMundial.put(21, List.of(List.of(18, 23, 24), pokebola4, PokemonPerception.POKEBOLA_PERCEPTION));
		mapaMundial.put(22, List.of(List.of(18, 19, 25, 26), PokemonAgentState.VACIO, PokemonPerception.EMPTY_PERCEPTION));
		mapaMundial.put(23, List.of(List.of(21), PokemonAgentState.VACIO, PokemonPerception.EMPTY_PERCEPTION));
		mapaMundial.put(24, List.of(List.of(21, 25), PokemonAgentState.VACIO, PokemonPerception.EMPTY_PERCEPTION));
		mapaMundial.put(25, List.of(List.of(22, 24, 27), PokemonAgentState.VACIO, PokemonPerception.EMPTY_PERCEPTION));
		mapaMundial.put(26, List.of(List.of(22, 28), PokemonAgentState.VACIO, PokemonPerception.EMPTY_PERCEPTION));
		mapaMundial.put(27, List.of(List.of(25, 29), PokemonAgentState.VACIO, PokemonPerception.EMPTY_PERCEPTION));
		mapaMundial.put(28, List.of(List.of(26, 29), PokemonAgentState.VACIO, PokemonPerception.EMPTY_PERCEPTION));
		mapaMundial.put(29, List.of(List.of(27, 28), pokebola5, PokemonPerception.POKEBOLA_PERCEPTION));
		*/
		
	}

	@Override
	public String toString() {	
		String mapa = new String();
		
		Set<Integer> nodos = mapaMundial.keySet(); 
		for (Integer nodo: nodos) {
			switch ((Integer)mapaMundial.get(nodo).get(2)){
			case 0: {
				mapa = mapa + "Nodo N° " + nodo + " | Adyacencias: " + mapaMundial.get(nodo).get(0) + " Contenido: VACIO  Percepcion: " + mapaMundial.get(nodo).get(2) + "\n";
				break;
			}
			case 3: {
				Pokebola p =(Pokebola) mapaMundial.get(nodo).get(1);
				mapa = mapa + "Nodo N° " + nodo + " | Adyacencias: " + mapaMundial.get(nodo).get(0) + " Contenido: " + p + "   Percepcion: " + mapaMundial.get(nodo).get(2) + "\n";
				break;
			}
			case 1: {
				Enemigo e =(Enemigo) mapaMundial.get(nodo).get(1);
				mapa = mapa + "Nodo N° " + nodo + " | Adyacencias: " + mapaMundial.get(nodo).get(0) + " Contenido: " + e + "   Percepcion: " + mapaMundial.get(nodo).get(2) + "\n";
				break;
			}
			case 2: {
				PokemonMaestro pm =(PokemonMaestro) mapaMundial.get(nodo).get(1);
				mapa = mapa + "Nodo N° " + nodo + " | Adyacencias: " + mapaMundial.get(nodo).get(0) + " Contenido: " + pm + "   Percepcion: " + mapaMundial.get(nodo).get(2) + "\n";
				break;
			}
			}
		}
		
		return "\n" +  "-----------------AMBIENTE-------------" + "\n" +
				charmander.toString()  + "\n" + "Mapa Mundial: " + "\n" + mapa;
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
			}else {
				adyacencias.put(nodo,  List.of(PokemonAgentState.VACIO, getMapaMundial().get(nodo).get(2)));
			}
		}
		
		return adyacencias;
	}


	
	public void moverEnemigos() {
		Set<Integer> nodos = mapaMundial.keySet();
		List<Enemigo> enemigos = new ArrayList<>();

		for (Integer nodo : nodos) {
			if((int)(mapaMundial.get(nodo).get(2)) == PokemonPerception.ENEMIGO_PERCEPTION) {
				Enemigo enemigo = (Enemigo) mapaMundial.get(nodo).get(1);
				enemigos.add(enemigo);
			}
		}
		
		for (Enemigo enemigo : enemigos) {
	
				Integer posicionActual = enemigo.getPosicion();
				List<Integer> nodosAdyacentes = (List<Integer>) mapaMundial.get(posicionActual).get(0); //obtengo los nodos adyacentes a los enemigos			
				List<Integer> nodosAdyacentesVacios = new ArrayList<>();
				for (Integer nodoAdyacente : nodosAdyacentes) {
					if((int)(mapaMundial.get(nodoAdyacente).get(2)) == PokemonPerception.EMPTY_PERCEPTION && nodoAdyacente != charmander.getPosicion()) { //si mi nodo adyacente esta vacio o no esta el pokemon agente entro
						nodosAdyacentesVacios.add(nodoAdyacente);
					}
				}
				if(nodosAdyacentesVacios.size() != 0) { //si tengo nodos adyacentes vacios entro
					Integer aux = getNumeroRandom(0, nodosAdyacentesVacios.size() -1);
					Integer posicion = nodosAdyacentesVacios.get(aux); //obtengo una posicion aleatoria de la lista de nodos adyacentes vacios
					enemigo.setPosicion(posicion);
					mapaMundial.replace(posicionActual, List.of(mapaMundial.get(posicionActual).get(0), PokemonAgentState.VACIO, PokemonPerception.EMPTY_PERCEPTION));
					mapaMundial.replace(posicion, List.of(mapaMundial.get(posicion).get(0), enemigo, PokemonPerception.ENEMIGO_PERCEPTION));
				}
			
		}
	}

	
	public void eliminarEnemigo(Integer nodo) {
		mapaMundial.replace(nodo, List.of(mapaMundial.get(nodo).get(0), PokemonAgentState.VACIO,  PokemonPerception.EMPTY_PERCEPTION));
		
	}
	
	
	public Map<Integer, List<Object>> usarSatelite() {
		
		return mapaMundial;
	}

	public void vencerPokemonFinal(PokemonMaestro boss, Integer nodoActual) {
		this.mapaMundial.replace(nodoActual, List.of(mapaMundial.get(nodoActual).get(0), boss, mapaMundial.get(nodoActual).get(2)));
		
	}

	public void eliminarPokebola(Integer nodoActual) {
		this.mapaMundial.replace(nodoActual, List.of(mapaMundial.get(nodoActual).get(0), PokemonAgentState.VACIO, PokemonPerception.EMPTY_PERCEPTION));
	}
	
	public Integer getNumeroRandom(Integer min, Integer max) {
		Random aleatorio = new Random();
		return min+aleatorio.nextInt( (max+1) - min);
	}

	public Integer getCiclosSinMoverseEnemigos() {
		return ciclosSinMoverseEnemigos;
	}

	public void setCiclosSinMoverseEnemigos(Integer ciclosSinMoverseEnemigos) {
		this.ciclosSinMoverseEnemigos = ciclosSinMoverseEnemigos;
	}

	public Integer getCantCiclosDesdeUltimoUsoSatelite() {
		return cantCiclosDesdeUltimoUsoSatelite;
	}

	public void setCantCiclosDesdeUltimoUsoSatelite(Integer cantCiclosDesdeUltimoUsoSatelite) {
		this.cantCiclosDesdeUltimoUsoSatelite = cantCiclosDesdeUltimoUsoSatelite;
	}

	public Integer getCiclosSatelite() {
		return ciclosSatelite;
	}

	public void setCiclosSatelite(Integer ciclosSatelite) {
		this.ciclosSatelite = ciclosSatelite;
	}
	
	
	
	
}
