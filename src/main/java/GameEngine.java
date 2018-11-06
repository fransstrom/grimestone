import java.util.Scanner;

public class GameEngine {

    private Player player1;
    private Player player2;
    private GUI gui;
    private BattleLogic battleLogic;
    private Scanner scanner;

    public GameEngine(Player p1, Player p2, BattleLogic battleLogic) {
        player1 = p1;
        player2 = p2;
        this.battleLogic = battleLogic;
        this.gui = new GUI(this);
        this.scanner = new Scanner(System.in);
    }

    public boolean isGameOver() {
        if (player1.getHp() < 1 || player1.noCardsLeftInDeck()) {
            System.out.println("Player1 lost");
            return true;
        }
        if (player2.getHp() < 1 || player2.noCardsLeftInDeck()) {
            System.out.println("Player2 lost");
            return true;
        }
        return false;
    }


    public void attack() {
        battleLogic.setDefendingPlayer(getInactivePlayer());
        if(battleLogic.getDefendingPlayer().getTable().isEmpty()){
            battleLogic.cardVsPlayer();
        }else {
            int choice;
            Card defendingCard;
            do{
                System.out.println("Choose card to attack!");
                choice = scanner.nextInt();
                defendingCard = getInactivePlayer().pickCardFromTable(choice);
            }while (!(getInactivePlayer().getTable().size() >= choice));
            battleLogic.setDefendingCard(defendingCard);
            battleLogic.cardVsCard();
            getInactivePlayer().moveDeadCardToGraveyard();
        }
    }

    public void startGame(){
        player1.drawInitialHand();
        player2.drawInitialHand();
        randomGenerateFirstActivePlayer();

        while(!isGameOver()){
            getActivePlayer().drawCard();
            putCardOnTablePhase();
            actionPhase();
            getActivePlayer().setCardsOnTableToActive();
            if(getActivePlayer().hasPassedTurn()){
                getActivePlayer().passTurn(false);
            }
            switchActivePlayer();
        }
    }

    public void putCardOnTablePhase() {
        gui.render();
        int choice;
        do{
            gui.printPickACardToPlay();
            choice = scanner.nextInt();
            if(choice == 0){
                getActivePlayer().passTurn(true);
                return;
            }
        }while (!getActivePlayer().placeCardOnTable(choice));
    }

    public void actionPhase(){
        if(getActivePlayer().hasActiveCardsOnTable() && !getActivePlayer().hasPassedTurn()){
            gui.render();
            gui.printChooseCardToAttackWith();
            int choice;
            Card pickedCard;
            do{
                choice = scanner.nextInt();
                if(choice == 0){
                    getActivePlayer().passTurn(true);
                    return;
                }
                pickedCard = getActivePlayer().pickCardFromTable(choice);
            }while (pickedCard == null || !((CreatureCard)pickedCard).isActive());
            battleLogic.setAttackingCard(pickedCard);
            attack();
        }
    }

    public Player getActivePlayer() {
        if (player1.isActive()) {
            return player1;
        } else {
            return player2;
        }
    }


    public Player getInactivePlayer() {
        if (!player1.isActive()) {
            return player1;
        } else {
            return player2;
        }
    }

    public void switchActivePlayer(){
        player1.setActive(!player1.isActive());
        player2.setActive(!player2.isActive());
    }

    private void randomGenerateFirstActivePlayer() {
        if (!player1.isActive() && !player2.isActive()) {
            int lottery = RandomNumberGenerator.roll();
            if ((lottery % 2) == 0) {
                player1.setActive(true);
            } else if ((lottery % 2) != 0) {
                player2.setActive(true);
            }
        }

    }
}

