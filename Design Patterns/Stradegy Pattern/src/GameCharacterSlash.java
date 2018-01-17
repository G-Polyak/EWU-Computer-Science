public class GameCharacterSlash extends GameCharacter {

    public GameCharacterSlash() {
        super();
        setName("Slash");
    }

    @Override
    public void playGuitar() {
        System.out.println("Slash plays a guitar:");
        getGuitar().playGuitar();
    }

    @Override
    public void playSolo() {
        System.out.println("Slash performs a solo:");
        getSolo().playSolo();
    }

}
