package frsf.ia.search.pokemon.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author juank
 *
 */
public class Charmander {

	
	private Integer posicion;
	private Integer energiaActual;
	private Integer energiaInicial;
	private Integer cantidadAdversarios;
	private Integer nivel;
	private Boolean puedeMoverse;

	private Map<String, List<Integer>> ataquesDisponibles; //clave(es el nombre de ataque), lista con porcentaje de energia que aumenta el ataque y cantidad de ciclos desde ultimo uso
	private List<AtaqueEspecial> listaAtaquesEspeciales;

	
	
	public Charmander() {
		super();
		setListaAtaquesEspeciales();
	}
	
	public Charmander(Integer posicion, Integer energiaActual, Integer energiaInicial, Integer cantidadAdversarios,
			Integer nivel, Map<String, List<Integer>> ataquesDisponibles) {
		super();
		this.posicion = posicion;
		this.energiaActual = energiaActual;
		this.energiaInicial = energiaInicial;
		this.cantidadAdversarios = cantidadAdversarios;
		this.nivel = nivel;
		this.ataquesDisponibles = ataquesDisponibles;
		this.puedeMoverse = true;
		setListaAtaquesEspeciales();
	}

	
	public Charmander(Integer posicion, Integer energiaActual, Integer energiaInicial, Integer cantidadAdversarios,
			Integer nivel, Map<String, List<Integer>> ataquesDisponibles, Boolean puedeMoverse) {
		super();
		this.posicion = posicion;
		this.energiaActual = energiaActual;
		this.energiaInicial = energiaInicial;
		this.cantidadAdversarios = cantidadAdversarios;
		this.nivel = nivel;
		this.ataquesDisponibles = ataquesDisponibles;
		this.puedeMoverse = puedeMoverse;
		setListaAtaquesEspeciales();

	}
	
	public Integer getPosicion() {
		return posicion;
	}
	
	public void setPosicion(Integer posicion) {
		this.posicion = posicion;
	}
	
	public Integer getEnergiaActual() {
		return energiaActual;
	}
	
	public void setEnergiaActual(Integer energiaActual) {
		this.energiaActual = energiaActual;
	}
	
	public Integer getEnergiaInicial() {
		return energiaInicial;
	}
	
	public void setEnergiaInicial(Integer energiaInicial) {
		this.energiaInicial = energiaInicial;
	}
	
	public Integer getCantidadAdversarios() {
		return cantidadAdversarios;
	}
	
	public void setCantidadAdversarios(Integer cantidadAdversarios) {
		this.cantidadAdversarios = cantidadAdversarios;
	}
	
	public Integer getNivel() {
		return nivel;
	}
	
	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}

	public Map<String, List<Integer>> getAtaquesDisponibles() {
		return ataquesDisponibles;
	}

	


	public void setAtaquesDisponibles(Map<String, List<Integer>> ataquesDisponibles) {
		this.ataquesDisponibles = ataquesDisponibles;
	}
	
	public Boolean getPuedeMoverse() {
		return puedeMoverse;
	}
	
	public void setPuedeMoverse(Boolean puedeMoverse) {
		this.puedeMoverse = puedeMoverse;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "CHARMANDER(Posición, energía, nivel, puede moverse): " + "[" + posicion + ", " + energiaActual + ", " + nivel  + ", " + puedeMoverse + "]" + "\n" +
				"Ataques Disponibles(Porcentaje aumento energía, cantidad de ciclos): " + ataquesDisponibles;
	}
	

	
	
	public void evaluarSubirDeNivel() {
        Integer energia = this.getEnergiaActual();
        Integer energiaInicial = this.getEnergiaInicial();
        
        switch (getNivel()) {
        case 1: {
            if(energia >= energiaInicial * 1.25) {
          
                this.ataquesDisponibles.put(listaAtaquesEspeciales.get(0).getNombre(), List.of(listaAtaquesEspeciales.get(0).getPorcentajeAumentoEnergia(),3));
                this.setNivel(2);
  
            }
        }
        case 2: {
            if(energia >= energiaInicial * 1.75) {
            	this.ataquesDisponibles.put(listaAtaquesEspeciales.get(1).getNombre(), List.of(listaAtaquesEspeciales.get(1).getPorcentajeAumentoEnergia(),3));
                this.setNivel(3);
          
            }
        }
        case 3: {
            if(energia >= energiaInicial * 2.2) {
            	this.ataquesDisponibles.put(listaAtaquesEspeciales.get(2).getNombre(), List.of(listaAtaquesEspeciales.get(2).getPorcentajeAumentoEnergia(),3));
                this.setNivel(4);
               
    
            }
        }
        }
        
    }
	public List<AtaqueEspecial> getListaAtaquesEspeciales() {
		return listaAtaquesEspeciales;
	}
	public void setListaAtaquesEspeciales() {
		AtaqueEspecial ataque1 = new AtaqueEspecial(2, 20, "Scary Face");
		AtaqueEspecial ataque2 = new AtaqueEspecial(3, 30, "Slash");
		AtaqueEspecial ataque3 = new AtaqueEspecial(4, 50, "Fire Fang");
		listaAtaquesEspeciales = new ArrayList<>();
		listaAtaquesEspeciales.add(ataque1);
		listaAtaquesEspeciales.add(ataque2);
		listaAtaquesEspeciales.add(ataque3);
	}
	public void reiniciarContador(String ataque) {
		ataquesDisponibles.replace(ataque,List.of(ataquesDisponibles.get(ataque).get(0), 0));
	}

	

	public void incrementarContadoresAtaques() {
		Set<String> ataques = this.getAtaquesDisponibles().keySet();
		for (String ataque : ataques) {
			ataquesDisponibles.replace(ataque,List.of(ataquesDisponibles.get(ataque).get(0), ataquesDisponibles.get(ataque).get(1) + 1));
		}
	}

}
