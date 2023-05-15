package frsf.ia.search.pokemon;

import frsf.cidisi.faia.solver.search.IEstimatedCostFunction;
import frsf.cidisi.faia.solver.search.NTree;

public class Heuristic implements IEstimatedCostFunction{

	@Override
	public double getEstimatedCost(NTree node) {
		PokemonAgentState pokemonState = (PokemonAgentState) node.getAgentState();

		return pokemonState.getEnergiaFaltanteVencerPokemonFinal();
	}

}
