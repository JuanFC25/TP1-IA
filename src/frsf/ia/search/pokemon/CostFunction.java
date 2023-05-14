package frsf.ia.search.pokemon;

import frsf.cidisi.faia.solver.search.IStepCostFunction;
import frsf.cidisi.faia.solver.search.NTree;

public class CostFunction implements IStepCostFunction{

	@Override
	public double calculateCost(NTree node) {
		return ((PokemonAgentState) node.getAgentState()).getCantidadMovimientosTotales();
	}

}
