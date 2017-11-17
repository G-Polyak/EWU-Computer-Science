import java.util.Random;

public abstract class Hero extends DungeonCharacter {

    protected double chanceToBlock;
    protected int remainingTurns;

    public Hero(String name, int hp, int attackSpeed, int minDamage,
                int maxDamage, double chanceHit, double chanceToBlock) {

        super(name, hp, attackSpeed, minDamage, maxDamage, chanceHit);
        this.chanceToBlock = chanceToBlock;
        readName();

    }

    public void readName() {

        Interface.print("Enter character name: ");
        this.name = Interface.readString();

    }

    public void block(int dmg) {

        Random randGen = new Random();
        boolean block = randGen.nextDouble() <= this.chanceToBlock;
        if (block) {
            Interface.println(this.name + " BLOCKED the attack!");
        } else {
            super.getHit(dmg);
        }

    }

    public void battleChoices(DungeonCharacter c) {

        this.remainingTurns = (this.attackSpeed / c.getAttackSpeed());
        if (this.remainingTurns == 0) {
            this.remainingTurns += 1;
        }
        Interface.println("There are " + this.remainingTurns + " turns left in this round");

    }

    public abstract void specSkill(DungeonCharacter c);

    public double getChanceToBlock() {
        return chanceToBlock;
    }

    public void setChanceToBlock(double chanceToBlock) {
        this.chanceToBlock = chanceToBlock;
    }

    public int getRemainingTurns() {
        return remainingTurns;
    }

    public void setRemainingTurns(int remainingTurns) {
        this.remainingTurns = remainingTurns;
    }
}