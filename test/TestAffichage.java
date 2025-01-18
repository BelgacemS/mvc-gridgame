package test;

import model.Agent8;
import model.Joyaux;
import view.GameView;

public class TestAffichage {

    public static void main(String[] args) {
        
        Agent8 agent = Agent8.getInstance();
        
        Joyaux diamant = new Joyaux("DIAMANT");
        agent.getSac().add(diamant);
        
        GameView view = new GameView();
        view.afficherAgent(agent);
    }
}
