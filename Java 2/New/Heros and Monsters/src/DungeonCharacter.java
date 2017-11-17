import java.util.Random;

public abstract class DungeonCharacter {

    protected String name;
    protected int hp;
    protected int attackSpeed;
    protected int minDamage;
    protected int maxDamage;
    protected double chanceHit;

    public DungeonCharacter(String name, int hp, int attackSpeed,
                            int minDamage, int maxDamage, double chanceHit) {

        this.name = name;
        this.hp = hp;
        this.attackSpeed = attackSpeed;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.chanceHit = chanceHit;

    }

    public void attack(DungeonCharacter c) {

        Random randGen = new Random();
        boolean canAttack = randGen.nextDouble() <= this.chanceHit;

        if (canAttack) {

            int dmg = randGen.nextInt((this.maxDamage - this.minDamage) + 1) + this.minDamage;
            Interface.println(getName() + "'s attack on " + c.getName() + " succeeded!");
            c.getHit(dmg);

        } else {
            Interface.println(getName() + "'s attack on " + c.getName() + " failed!");
        }

    }

    public void getHit(int dmg) {

        if (dmg < 0) {
            Interface.println("Damage amount must be positive");
        } else if (dmg > 0) {

            this.hp -= dmg;
            if (this.hp < 0) {
                this.hp = 0;
            }

            Interface.println(getName() + " was hit for " + dmg + " points of damage");
            Interface.println(getName() + " now has " + getHp() + " HP");
            Interface.println("");

        }

        if (this.hp == 0) {
            Interface.println(this.name + " has been killed!");
        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAttackSpeed() {
        return attackSpeed;
    }

    public void setAttackSpeed(int attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public int getMinDamage() {
        return minDamage;
    }

    public void setMinDamage(int minDamage) {
        this.minDamage = minDamage;
    }

    public int getMaxDamage() {
        return maxDamage;
    }

    public void setMaxDamage(int maxDamage) {
        this.maxDamage = maxDamage;
    }

    public double getChanceHit() {
        return chanceHit;
    }

    public void setChanceHit(double chanceHit) {
        this.chanceHit = chanceHit;
    }


}