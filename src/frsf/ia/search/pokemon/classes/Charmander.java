package frsf.ia.search.pokemon.classes;

import java.util.List;
import java.util.Map;

public class Charmander {

	
	private Integer posicion;
	private Integer energiaActual;
	private Integer energiaInicial;
	private Integer cantidadAdversarios;
	private Integer nivel;
	private Map<Integer, List<Integer>> ataquesDisponibles; //clave(es el numero de ataque), lista con porcentaje de energia que aumenta el ataque y cantidad de ciclos desde ultimo uso
	
	
	
	public Charmander() {
		super();
	}
	public Charmander(Integer posicion, Integer energiaActual, Integer energiaInicial, Integer cantidadAdversarios,
			Integer nivel, Map<Integer, List<Integer>> ataquesDisponibles) {
		super();
		this.posicion = posicion;
		this.energiaActual = energiaActual;
		this.energiaInicial = energiaInicial;
		this.cantidadAdversarios = cantidadAdversarios;
		this.nivel = nivel;
		this.ataquesDisponibles = ataquesDisponibles;
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
	public Map<Integer, List<Integer>> getAtaquesDisponibles() {
		return ataquesDisponibles;
	}
	public void setAtaquesDisponibles(Map<Integer, List<Integer>> ataquesDisponibles) {
		this.ataquesDisponibles = ataquesDisponibles;
	}
	
	
	
	
}
