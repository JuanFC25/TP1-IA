package frsf.ia.search.pokemon;

import java.util.List;
import java.util.Map;
import java.util.Set;

import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;
import frsf.ia.search.pokemon.classes.Charmander;
import frsf.ia.search.pokemon.classes.Enemigo;
import frsf.ia.search.pokemon.classes.Pokebola;
import frsf.ia.search.pokemon.classes.PokemonMaestro;

public class PokemonPerception extends Perception{

	public static int UNKNOWN_PERCEPTION = -1;
    public static int EMPTY_PERCEPTION = 0;
    public static int ENEMIGO_PERCEPTION = 1;
    public static int POKEMON_MAESTRO_PERCEPTION = 2;
    public static int POKEBOLA_PERCEPTION = 3;
    public static int SATELITE_PERCEPTION = 4;
	
    Charmander charmander; //pokemon agente
    Map<Integer, List<Object>> nodosAdyacentes; //map de los nodos adyacentes de un nodo. Compuesto por el nodo adyacente
    //como key y luego una lista de objetos(primer elemento el objeto que hay, puede ser null y luego la percepcion)
    Map<Integer, List<Object>> mapaMundial;    // key(nodo): lista de objetos: Primer elemento nodos adyacentes, Segundo elemento objeto que hay en el nodo, Tercer elemento la percepcion
   
    public PokemonPerception() {
        System.out.println("Se crea la percepcion" );
    }
    
    
    public PokemonPerception(Agent agent, Environment environment) {
        super(agent, environment);
    }
    
    
    
    
    
    //esta no se usa nunca creo
    /**
     * This method is used to setup the perception.
     */
    @Override
	public void initPerception(Agent agent, Environment environment) {
		PokemonAgent pokemonAgent = (PokemonAgent) agent;
		PokemonEnvironment pokemonEnvironment = (PokemonEnvironment) environment;
		PokemonEnvironmentState environmentState = pokemonEnvironment.getEnvironmentState();
		
		Integer posicion = environmentState.getCharmander().getPosicion();
		
		this.setCharmander(environmentState.getCharmander());
		this.setNodosAdyacentes(environmentState.getNodosAdyacentes(posicion));
		
		Map<Integer, List<Object>> mapaSatelite = (environmentState.usarSatelite());
		if(mapaSatelite != null) {
			this.setMapaMundial(mapaSatelite);
		} else {
			this.setMapaMundial(null);
		}
		
	}


    
    
	public Map<Integer, List<Object>> getNodosAdyacentes() {
		return nodosAdyacentes;
	}


	public void setNodosAdyacentes(Map<Integer, List<Object>> nodosAdyacentes) {
		this.nodosAdyacentes = nodosAdyacentes;
	}



	public Charmander getCharmander() {
		return charmander;
	}



	public void setCharmander(Charmander charmander) {
		this.charmander = charmander;
	}


	
	
    public Map<Integer, List<Object>> getMapaMundial() {
		return mapaMundial;
	}


	public void setMapaMundial(Map<Integer, List<Object>> mapaMundial) {
		this.mapaMundial = mapaMundial;
	}


	@Override
    public String toString() {
    	Boolean seUsaSatelite;
    	if (mapaMundial == null) seUsaSatelite = false;
    	else seUsaSatelite = true;
    	
    	String adyacentes = new String();
		
		Set<Integer> nodos = nodosAdyacentes.keySet(); 
		for (Integer nodo: nodos) {
			switch ((Integer)nodosAdyacentes.get(nodo).get(1)){
			case 0: {
				adyacentes = adyacentes + "Nodo N° " + nodo + " Contenido: VACIO  Percepcion: " + nodosAdyacentes.get(nodo).get(1) + "\n";
				break;
			}
			case 3: {
				Pokebola p =(Pokebola) nodosAdyacentes.get(nodo).get(0);
				adyacentes = adyacentes + "Nodo N° " + nodo + " Contenido: " + p + "   Percepcion: " + nodosAdyacentes.get(nodo).get(1) + "\n";
				break;
			}
			case 1: {
				Enemigo e =(Enemigo) nodosAdyacentes.get(nodo).get(0);
				adyacentes = adyacentes + "Nodo N° " + nodo +  " Contenido: " + e + "   Percepcion: " + nodosAdyacentes.get(nodo).get(1) + "\n";
				break;
			}
			case 2: {
				PokemonMaestro pm =(PokemonMaestro) nodosAdyacentes.get(nodo).get(0);
				adyacentes = adyacentes + "Nodo N° " + nodo +  " Contenido: " + pm + "   Percepcion: " + nodosAdyacentes.get(nodo).get(1) + "\n";
				break;
			}
			}
		}
    	
		return "\n" + "------------PERCEPCION------------"   + "\n"  +
    			charmander.toString() + "\n" + "Nodos adyacentes(nodo, contenido, percepcion): "  + "\n" + adyacentes + "Se usa satelite: " + seUsaSatelite + "\n";
    			
    }
    
}
