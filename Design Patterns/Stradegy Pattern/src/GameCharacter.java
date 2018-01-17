public abstract class GameCharacter {

    private Guitar guitar;
    private SoloAct solo;
    private String name;

    public abstract void playGuitar();

    public abstract void playSolo();

    public Guitar getGuitar() {
        return guitar;
    }

    public void setGuitar(Guitar guitar) {
        this.guitar = guitar;
    }

    public SoloAct getSolo() {
        return solo;
    }

    public void setSolo(SoloAct solo) {
        this.solo = solo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
