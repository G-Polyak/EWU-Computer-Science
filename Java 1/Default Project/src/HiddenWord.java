/**
 * Created by Georgie on 5/2/2016.
 */
public class HiddenWord {

    private String hWord;

    public HiddenWord(String hWord) {
        this.hWord = hWord;
    }

    public String getHint(String guess) {

        char[] h = new char[guess.length()];
        int count = 0;

       for(int i = 0; i < guess.length(); i++) {
           if(guess.charAt(i) == this.hWord.charAt(i)) {
               h[i] = guess.charAt(i);
           }
           for (int j = 0; j < this.hWord.length(); j++) {
               if(guess.charAt(i) == this.hWord.charAt(j) && j != i) {
                   h[i] = '+';
               }
               if(guess.charAt(i) != this.hWord.charAt(j)) {
                   count++;
               }
           }
           if(count == guess.length()) {
               h[i] = '*';
           }
           count = 0;
       }

        String hint = "";
        for(int i = 0; i < h.length; i++) {
            hint = hint + h[i];
        }
        return hint;

    }

}
