public class GameCharacterYoung extends GameCharacter {

    public GameCharacterYoung() {
        super();
        setName("Young");
    }

    @Override
    public void playGuitar() {
        System.out.println("Young plays a guitar:");
        getGuitar().playGuitar();
    }

    @Override
    public void playSolo() {
        System.out.println("Young performs a solo:");
        getSolo().playSolo();
    }

}
