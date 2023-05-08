package frsf.ia.search.pokemon.classes;

public class Pokebola {

	private Integer id;
	private Integer posicion;
	private Integer energia;
	


	public Pokebola() {
		super();
	}



	public Pokebola(Integer id, Integer posicion, Integer energia) {
		super();
		this.id = id;
		this.posicion = posicion;
		this.energia = energia;
	}
	
	
	

	public Integer getId() {
		return id;
	}





	public void setId(Integer id) {
		this.id = id;
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
		return "Pokebola[id, pos, energia]: "+this.id + ", " + this.posicion + ", " + this.energia;
	}
}
