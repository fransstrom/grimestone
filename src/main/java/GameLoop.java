public class GameLoop {
  private   GameEngine gameEngine;
  private   InputProcessor inputProcessor;

    public GameLoop(GameEngine gameEngine){
       this.gameEngine = gameEngine;
       this.inputProcessor = new InputProcessor();
       while(!gameEngine.isGameOver()){
           getActivePlayer().drawCard();
           putCardOnTablePhase();
           actionPhase();
           getActivePlayer().setCardsOnTableToActive();
           gameEngine.switchActivePlayer();
       }
    }

    public void putCardOnTablePhase() {
        gameEngine.getGui().render();
        int choice;
        do{
            gameEngine.getGui().printPickACardToPlay();
            choice = this.inputProcessor.getInputInt();
        }while (!getActivePlayer().placeCardOnTable(choice));
    }

    public void actionPhase(){
        gameEngine.getGui().render();
        if(getActivePlayer().hasActiveCardsOnTable()){
            gameEngine.getGui().printChooseCardToAttackWith();
            int choice;
            Card pickedCard;
            do{
                choice = this.inputProcessor.getInputInt();
                pickedCard = getActivePlayer().pickCardFromTable(choice);
            }while (pickedCard == null || !((CreatureCard)pickedCard).isActive());
            gameEngine.getBattleLogic().setAttackingCard(pickedCard);
            gameEngine.attack();
        }
    }

    public Player getActivePlayer(){
        return this.gameEngine.getActivePlayer();
    }

    public void setInputProcessor(InputProcessor inputProcessor) {
        this.inputProcessor = inputProcessor;
    }
}
