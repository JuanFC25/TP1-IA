package frsf.ia.search.pokemon.classes;
import java.util.List;
import java.util.Map;

public class AtaqueEspecial {

	private Integer nivel;   //es el nivel con el que se desbloquea el ataque
	private Integer porcentajeAumentoEnergia;
	private String nombre;
	
	
	
	public AtaqueEspecial() {
		super();
	}



	public AtaqueEspecial(Integer nivel, Integer porcentajeAumentoEnergia, String nombre) {
		super();
		this.nombre = nombre;
		this.nivel = nivel;
		this.porcentajeAumentoEnergia = porcentajeAumentoEnergia;
	}
	
	
	
	public Integer getNivel() {
		return nivel;
	}
	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}
	public Integer getPorcentajeAumentoEnergia() {
		return porcentajeAumentoEnergia;
	}
	public void setPorcentajeAumentoEnergia(Integer porcentajeAumentoEnergia) {
		this.porcentajeAumentoEnergia = porcentajeAumentoEnergia;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public String toString() {
		return nombre;
	}
	
	
}
