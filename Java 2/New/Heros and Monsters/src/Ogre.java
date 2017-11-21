public class Ogre extends Monster {

    public Ogre() {
        super("Ogre", 200, 2, 30, 60, .6, .1, 30, 60);
    }

    @Override
    public void battleChoices(DungeonCharacter c) {

        Interface.println(this.getName() + " attacks you");
        attack(c);

    }

}