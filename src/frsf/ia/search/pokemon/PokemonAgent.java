package frsf.ia.search.pokemon;



import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.Problem;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgent;
import frsf.cidisi.faia.solver.search.BreathFirstSearch;
import frsf.cidisi.faia.solver.search.DepthFirstSearch;
import frsf.cidisi.faia.solver.search.Search;
import frsf.ia.search.pokemon.actions.IrAN;
import frsf.ia.search.pokemon.actions.NoPelear;
import frsf.ia.search.pokemon.actions.Pelear;
import frsf.ia.search.pokemon.actions.PelearConAtaqueEspecial;
import frsf.ia.search.pokemon.actions.RecogerEnergiaPokebola;


public class PokemonAgent extends SearchBasedAgent{

	public PokemonAgent() {

		//GOAL
		PokemonGoal goal = new PokemonGoal();
		
		PokemonAgentState pokemonState = new PokemonAgentState();
		this.setAgentState(pokemonState);
		
		//crear Operadores
		Vector<SearchAction> operators = new Vector<SearchAction>();
		operators.add(new Pelear());
		operators.add(new NoPelear());
		operators.add(new PelearConAtaqueEspecial());
		operators.add(new RecogerEnergiaPokebola());
		for(int i=1; i<=29 ;  i++) {
			operators.add(new IrAN(i));
		}
		
		

		 // Create the Problem which the Pacman will resolve
        Problem problem = new Problem(goal, pokemonState, operators);
        this.setProblem(problem);
	}
	

    /**
     * This method is executed by the simulator to ask the agent for an action.
     */
    @Override
    public Action selectAction() {

        // Create the search strategy
        //DepthFirstSearch strategy = new DepthFirstSearch();
       BreathFirstSearch strategy = new BreathFirstSearch();
        /**
         * Another search strategy examples:
         * 
         * Depth First Search:
         * DepthFirstSearch strategy = new DepthFirstSearch();
         * 
         * Breath First Search:
         * BreathFirstSearch strategy = new BreathFirstSearch();
         * 
         * Uniform Cost:
         * IStepCostFunction costFunction = new CostFunction();
         * UniformCostSearch strategy = new UniformCostSearch(costFunction);
         * 
         * A Star Search:
         * IStepCostFunction cost = new CostFunction();
         * IEstimatedCostFunction heuristic = new Heuristic();
         * AStarSearch strategy = new AStarSearch(cost, heuristic);
         * 
         * Greedy Search:
         * IEstimatedCostFunction heuristic = new Heuristic();
         * GreedySearch strategy = new GreedySearch(heuristic);
         */

        // Create a Search object with the strategy
        Search searchSolver = new Search(strategy);

        /* Generate an XML file with the search tree. It can also be generated
         * in other formats like PDF with PDF_TREE */
        searchSolver.setVisibleTree(Search.EFAIA_TREE);

        // Set the Search searchSolver.
        this.setSolver(searchSolver);

        // Ask the solver for the best action
        Action selectedAction = null;
        try {
            selectedAction =
                    this.getSolver().solve(new Object[]{this.getProblem()});
        } catch (Exception ex) {
        	Logger.getLogger(PokemonAgent.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Return the selected action
        return selectedAction;
    }

    /**
     * This method is executed by the simulator to give the agent a perception.
     * Then it updates its state.
     * @param p
     */
    @Override
    public void see(Perception p) {
        this.getAgentState().updateState(p);
    }




}
