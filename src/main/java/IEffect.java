public interface IEffect {

    void trigger();
    void trigger(Player player);
    void trigger(Card player);
    String getText();
}
