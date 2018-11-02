import java.util.ArrayList;

public class GUI {

    private GameEngine gameEngine;

    public GUI(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    public void render() {
        System.out.println("------------------------------------------------------------------------------------------------------------------------------");
        printPlayerHud(gameEngine.getInactivePlayer());
        System.out.printf("\n");
        printPlayerCardsInHand(gameEngine.getInactivePlayer());
        System.out.printf("\n\n");
        printCardsOnTable();
        System.out.printf("\n\n");
        printPlayerCardsInHand(gameEngine.getActivePlayer());
        System.out.printf("\n\n");
        printPlayerHud(gameEngine.getActivePlayer());
        System.out.printf("\n------------------------------------------------------------------------------------------------------------------------------");
    }

    public void printPlayerHP(Player player) {
        String num = "00";
        if (player.getHp() < 9) {
            num = "0" + player.getHp();
        } else {
            num = Integer.toString(player.getHp());
        }
        System.out.printf("| Player HP: " + num + " |");
    }

    public void printPlayerCardsInHand(Player player) {
        if (player.isActive()) {
            ArrayList<Card> cardsInHandForActivePlayer = player.getHand();
            printSpaceBetweenCardsAndBoard(cardsInHandForActivePlayer);
            printCards(cardsInHandForActivePlayer, false);
        } else {
            ArrayList<Card> cardsInHandForInactivePlayer = player.getHand();
            printSpaceBetweenCardsAndBoard(cardsInHandForInactivePlayer);
            printCards(cardsInHandForInactivePlayer, true);
        }
    }

    public void printPlayerHud(Player player) {
        printNumberOfGraveyardCards(player);
        System.out.printf("                             ");
        printPlayerHP(player);
        System.out.printf("                                 ");
        printRemainingCards(player);
    }

    public void printRemainingCards(Player player) {
        String num = "00";
        if (player.getDeck().size() < 9) {
            num = "0" + player.getDeck().size();
        } else {
            num = Integer.toString(player.getDeck().size());
        }
        System.out.printf("| Cards in Deck: " + num + " |");
    }

    public void printNumberOfGraveyardCards(Player player) {
        String num = "00";
        if (player.getGraveyard().size() < 9) {
            num = "0" + player.getGraveyard().size();
        } else {
            num = Integer.toString(player.getGraveyard().size());
        }
        System.out.printf("| Cards in Graveyard: " + num + " |");
    }

    public void printCardsOnTable() {
        ArrayList<Card> activePlayerTable = gameEngine.getActivePlayer().getTable();
        ArrayList<Card> inActivePlayerTable = gameEngine.getInactivePlayer().getTable();
        printSpaceBetweenCardsAndBoard(inActivePlayerTable);
        printCards(inActivePlayerTable, false);
        System.out.printf("\n\n");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------");
        printSpaceBetweenCardsAndBoard(activePlayerTable);
        printCards(activePlayerTable, false);
    }

    public void printCards(ArrayList<Card> cardList, boolean hidden) {
        if (cardList.size() == 0) {
            System.out.println();
        }
        if (!hidden) {
            int cardPlacement = 1;
            for (Card card : cardList) {
                if (card instanceof CreatureCard) {
                    if (!((CreatureCard) card).isActive()) {
                        System.out.printf("\u001B[31m | Card " + cardPlacement + " HP: " + ((CreatureCard) card).getHp() + " | ");
                    } else {
                        System.out.printf(" | Card " + cardPlacement + " HP: " + ((CreatureCard) card).getHp() + " | ");
                    }
                }
            }
        } else {
            int cardPlacement = 1;
            for (Card card : cardList) {
                if (card instanceof CreatureCard) {
                    System.out.printf(" |    Card " + cardPlacement + "    | ");
                }
                cardPlacement++;

            }
        }
    }

    public void printSpaceBetweenCardsAndBoard(ArrayList<Card> cardList) {
        switch (cardList.size()) {
            case 5:
                System.out.printf("\n                  ");
                break;
            case 4:
                System.out.printf("\n                           ");
                break;
            case 3:
                System.out.printf("\n                                    ");
                break;
            case 2:
                System.out.printf("\n                                             ");
                break;
            case 1:
                System.out.printf("\n                                                      ");
                break;
        }
    }

    public void printGameOverMenu() {
        String gameOverMenuDesign = "------------------------------------------------------------------------------------------------------------------------------\n"
                + "|                                                                                                                            |\n"
                + "|                                                                                                                            |\n"
                + "|                                                                                                                            |\n"
                + "|                                                                                                                            |\n"
                + "|                                                         Game over!                                                         |\n"
                + "|                                                                                                                            |\n"
                + "|                                                       1. Play Again                                                        |\n"
                + "|                                                       2. Quit                                                              |\n"
                + "|                                                                                                                            |\n"
                + "|                                                                                                                            |\n"
                + "|                                                                                                                            |\n"
                + "|                                                                                                                            |\n"
                + "|                                                                                                                            |\n"
                + "|                                                                                                                            |\n"
                + "|                                                                                                                            |\n"
                + "------------------------------------------------------------------------------------------------------------------------------";
        System.out.println(gameOverMenuDesign);
    }

    public void printStartMenu() {
        String startMenuDesign = "------------------------------------------------------------------------------------------------------------------------------\n"
                + "|                                                                                                                            |\n"
                + "|                                                                                                                            |\n"
                + "|                                                                                                                            |\n"
                + "|                                                                                                                            |\n"
                + "|                                                      * GRIME STONE! *                                                      |\n"
                + "|                                                                                                                            |\n"
                + "|                                                         1. Play                                                            |\n"
                + "|                                                         2. Quit                                                            |\n"
                + "|                                                                                                                            |\n"
                + "|                                                                                                                            |\n"
                + "|                                                                                                                            |\n"
                + "|                                                                                                                            |\n"
                + "|                                                                                                                            |\n"
                + "|                                                                                                                            |\n"
                + "|                                                                                                                            |\n"
                + "------------------------------------------------------------------------------------------------------------------------------";
        System.out.println(startMenuDesign);
    }

    public void printPickACardToPlay(){
        System.out.println("Choose a card from your hand to play.");
    }
    public void invalidChoiceOfCard(){
        System.out.println("Invalid choice of card.");
    }
    public void printChooseCardToAttackWith(){
        System.out.println("Choose a card to attack with!");
    }

}