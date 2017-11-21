public class Gremlin extends Monster {

    public Gremlin() {
        super("Gremlin", 70, 5, 15, 30, .8, .4, 20, 40);
    }

    @Override
    public void battleChoices(DungeonCharacter c) {

        Interface.println(this.getName() + " attacks you");
        attack(c);

    }
}