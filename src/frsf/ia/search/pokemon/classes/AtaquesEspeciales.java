package frsf.ia.search.pokemon.classes;
import java.util.List;
import java.util.Map;

public class AtaquesEspeciales {

	private Integer nivel;   //es el nivel con el que se desbloquea el ataque
	private Integer porcentajeAumentoEnergia;
	
	
	
	
	public AtaquesEspeciales() {
		super();
	}



	public AtaquesEspeciales(Integer nivel, Integer porcentajeAumentoEnergia) {
		super();
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
	
	
	
	
}
