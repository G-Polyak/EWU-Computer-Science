
public class GuitarHero {

    public static void main(String[] args) {

        GameCharacter player1 = new GameCharacterSlash(); //note that constructor could be designed to accept initial behaviors
        GameCharacter player2 = new GameCharacterHendrix();
        player1.setGuitar(new FenderTelecaster());
        player1.setSolo(new JumpOffStage());
        player2.setGuitar(new GibsonFlyingV());
        player2.setSolo(new PutGuitarOnFire());
        player1.playGuitar();
        player2.playGuitar();
        player1.playSolo();
        player2.playSolo();

        //add code below to show the swapping of behaviors
        System.out.println("\n");
        player1 = new GameCharacterYoung();
        player1.setGuitar(new GibsonFlyingV());
        player1.setSolo(new PutGuitarOnFire());

        player2.setGuitar(new GibsonSG());
        player2.setSolo(new SmashGuitar());
        player1.playGuitar();
        player2.playGuitar();
        player1.playSolo();
        player2.playSolo();

    }

}