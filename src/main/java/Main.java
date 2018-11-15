public class Main {
 //   int rnd = RandomNumberGenerator.roll();
/*
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }
*/

    public static void main(String[] args) {
       GameEngine gameEngine = new GameEngine(new Player(), new Player(), new BattleLogic(), new InputProcessor());
       gameEngine.startGame();
    }
}
