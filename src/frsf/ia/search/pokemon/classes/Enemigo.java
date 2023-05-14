package frsf.ia.search.pokemon.classes;

public class Enemigo {

	private Integer id;
	private Integer posicion;
	private Integer energia;
	private Integer cantidadCiclosSinMoverse;
	
	
	
	public Enemigo() {
		super();
	}


	public Enemigo(Integer id, Integer posicion, Integer energia, Integer cantidadCiclosSinMoverse) {
		super();
		this.id = id;
		this.posicion = posicion;
		this.energia = energia;
		this.cantidadCiclosSinMoverse = cantidadCiclosSinMoverse;
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


	public Integer getCantidadCiclosSinMoverse() {
		return cantidadCiclosSinMoverse;
	}


	public void setCantidadCiclosSinMoverse(Integer cantidadCiclosSinMoverse) {
		this.cantidadCiclosSinMoverse = cantidadCiclosSinMoverse;
	}
	
	@Override
	public String toString() {
		return "Pokemon Enemigo(Id, posición, energía): [" + this.id + ", " + this.posicion + ", " + this.energia  + "]";
	}
	
	
	
}
