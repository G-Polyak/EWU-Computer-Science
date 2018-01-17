public class GameCharacterHendrix extends GameCharacter {

    public GameCharacterHendrix() {
        super();
        setName("Hendrix");
    }

    @Override
    public void playGuitar() {
        System.out.println("Hendrix plays a guitar:");
        getGuitar().playGuitar();
    }

    @Override
    public void playSolo() {
        System.out.println("Hendrix performs a solo:");
        getSolo().playSolo();
    }

}
