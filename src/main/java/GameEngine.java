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
        if (battleLogic.getDefendingPlayer().getTable().isEmpty()) {
            battleLogic.cardVsPlayer();
        } else {
            int choice;
            Card defendingCard;
            System.out.println("Choose card to attack!");
            choice = scanner.nextInt();
            defendingCard = getInactivePlayer().pickCardFromTable(choice);
            while (!(getInactivePlayer().getTable().size() >= choice)) {
                System.out.println("Choose card to attack!");
                choice = scanner.nextInt();
                defendingCard = getInactivePlayer().pickCardFromTable(choice);
            }
            battleLogic.setDefendingCard(defendingCard);
            battleLogic.cardVsCard();

        }
    }

    public void startGame() {
        player1.drawInitialHand();
        player2.drawInitialHand();
        randomGenerateFirstActivePlayer();

        while (!isGameOver()) {
            getActivePlayer().drawCard();
            getActivePlayer().setCardsOnTableToActive();
            playerChoicePhase();
            switchActivePlayer();

        }
    }

    public void playerChoicePhase() {
        int choice;
        int cardIndex;

        while (!getActivePlayer().hasPassedTurn()) {
            String resolvePlay = "";
            gui.render();
            System.out.println("\033[1;31mDin tur!\n\033[0;93m1. Spela ett kort\n2. Attackera\n3. Passa\033[0m");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    gui.printPickACardToPlay();
                    cardIndex = scanner.nextInt();
                    resolvePlay = getActivePlayer().playCard(cardIndex);
                    break;
                case 2:
                    actionPhase();
                    break;
                case 3:
                    getActivePlayer().passTurn(true);
                    break;
                default:
                    System.out.println("\033[0;101m\033[1;97mOgiltigt val!\033[0m");
                    sleep(1000);
            }
            moveAllDeadCardsToGraveYard();
            resolveEffect(resolvePlay);
        }

    }




    public void resolveEffect(String effect) {
        String[] effectComponents = effect.split("_");
        switch (effectComponents[0]) {
            case "HEAL":
                if (effectComponents[1].equals("PLAYER")) {
                    int currentHp = getActivePlayer().getHp();
                    int healAmount = Math.abs(Integer.parseInt(effectComponents[2]));
                    getActivePlayer().setHp(currentHp + healAmount);
                }
        }
    }

    public void actionPhase() {
        if (getActivePlayer().hasActiveCardsOnTable() && !getActivePlayer().hasPassedTurn()) {
            gui.render();
            gui.printChooseCardToAttackWith();
            int choice;
            Card pickedCard;
            do {
                choice = scanner.nextInt();
                if (choice == 0) {
                    getActivePlayer().passTurn(true);
                    return;
                }
                pickedCard = getActivePlayer().pickCardFromTable(choice);
            } while (pickedCard == null || !((CreatureCard) pickedCard).isActive());
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

    public void switchActivePlayer() {
        if (getActivePlayer().hasPassedTurn()) {
            getActivePlayer().passTurn(false);
        }
        player1.setActive(!player1.isActive());
        player2.setActive(!player2.isActive());
        gui.nextTurn();
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

    private void moveAllDeadCardsToGraveYard() {
        getActivePlayer().moveDeadCardToGraveyard();
        getInactivePlayer().moveDeadCardToGraveyard();
    }
    private void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

