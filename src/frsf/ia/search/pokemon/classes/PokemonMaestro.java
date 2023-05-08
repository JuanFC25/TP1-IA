package frsf.ia.search.pokemon.classes;

public class PokemonMaestro {

	
	private Integer posicion;
	private Integer energia;
	
	
	
	public PokemonMaestro() {
		super();
	}
	
	
	public PokemonMaestro(Integer posicion, Integer energia) {
		super();
		this.posicion = posicion;
		this.energia = energia;
	}
	
	
	public Integer getPosicion() {
		return posicion;
	}
	public void setPosicion(Integer posicion) {
		this.posicion = posicion;
	}
	public Integer getEnergia() {
		return energia;
	}
	public void setEnergia(Integer energia) {
		this.energia = energia;
	}
	
	
	@Override
	public String toString() {
		return "Pokemon Maestro[Pos, energia]: " + this.posicion + ", " + this.energia;
	}
	
}
