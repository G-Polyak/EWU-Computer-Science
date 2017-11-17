public class Skeleton extends Monster {


    public Skeleton() {
        super("Skeleton", 100, 3, 30, 60, .8, .3, 30, 50);
    }

    @Override
    public void battleChoices(DungeonCharacter c) {

        Interface.println(this.getName() + " attacks you");
        attack(c);

    }

}