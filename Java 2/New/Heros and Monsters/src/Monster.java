import java.util.Random;

public abstract class Monster extends DungeonCharacter {

    protected double chanceToHeal;
    protected int minHeal;
    protected int maxHeal;

    public Monster(String name, int hp, int attackSpeed, int minDamage, int maxDamage,
                   double chanceHit, double chanceToHeal, int minHeal, int maxHeal) {

        super(name, hp, attackSpeed, minDamage, maxDamage, chanceHit);
        this.chanceToHeal = chanceToHeal;
        this.minHeal = minHeal;
        this.maxHeal = maxHeal;
        getMonsterName();

    }

    private void getMonsterName() {

        if (this.name.equalsIgnoreCase("Ogre")) {
            getOgreName();
        } else if (this.name.equalsIgnoreCase("Skeleton")) {
            getSkeletonName();
        } else {
            getGremlinName();
        }

    }

    public abstract void battleChoices(DungeonCharacter c);

    public void getOgreName() {

        Random randGen = new Random();
        int index = randGen.nextInt(6);
        String[] names = new String[]{"Owen", "Oliver", "Otis", "Oscar", "Omar", "Odin"};
        this.name = names[index] + " the Ogre";

    }

    public void getSkeletonName() {

        Random randGen = new Random();
        int index = randGen.nextInt(6);
        String[] names = new String[]{"Stephen", "Sheldon", "Sebastian", "Shawn", "Stanley", "Solomon"};
        this.name = names[index] + " the Skeleton";

    }

    public void getGremlinName() {

        Random randGen = new Random();
        int index = randGen.nextInt(6);
        String[] names = new String[]{"Gabriel", "Garrett", "Graham", "Gregory", "Greg", "Gerald"};
        this.name = names[index] + " the Gremlin";

    }

    public double getchanceToHeal() {
        return chanceToHeal;
    }

    public void setchanceToHeal(double chanceToHeal) {
        this.chanceToHeal = chanceToHeal;
    }

}