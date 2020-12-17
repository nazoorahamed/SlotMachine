package sample;



import javafx.scene.control.Alert;



import javax.sound.sampled.*;
import java.io.IOException;



/**
 * Created by nazoorahamed on 11/13/17.
 */
public class MachineView   {





    private static int wins;
    private static int losses;
    private static int matches;


    private static boolean play;
    private static int currentBet = 0;
    private static int currentcredits = 10;
    private static int totalbets =0;
    private static int totalcredits=0;
    private static int winningcredits=0;

    public static Reel.Newreel reel1;
    public static Reel.Newreel reel2;
    public static Reel.Newreel reel3;

    private static Symbol rolll1;
    private static Symbol rolll2;
    private static Symbol rolll3;



    //checking the reel is working status if not the reel will check the bet amount nd begin to spin
    public static void spin() {

        if (play==true) {

            if (!reel1.isAlive()&& !reel2.isAlive() && !reel3.isAlive()) {
                startspin();

            }
        }
        else {

            startspin();
        }
    }
    //spinning action
    private static void startspin(){
        if (currentBet > 0) {

            Main.addcoin.setDisable(true);
            Main.betone.setDisable(true);
            Main.betmax.setDisable(true);
            Main.reset.setDisable(true);
            Main.spin.setDisable(true);
            Main.back.setDisable(true);

            alertlable();
            play=true;
            reel1 = new Reel.Newreel(Main.roll1);
            reel2 = new Reel.Newreel(Main.roll2);
            reel3 = new Reel.Newreel(Main.roll3);

            reel1.start();
            reel2.start();
            reel3.start();
        } else {

            Alert betAlert = setalert("Error","Bet First !",Alert.AlertType.WARNING);
            betAlert.showAndWait();
        }
    }
    //Calling alerts for illegal action from the user
    public static Alert setalert(String title, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);

        return alert;
    }




    //this method will assign to addcoin button to increase the Current amount
    public static void addCoin() {
        currentcredits +=1;
        viewscore();
    }

    //this method will increase the bet amount by one if amount is not enough Alert messege will display
    public static void betOne() {
        if (currentcredits > 0) {

            currentcredits-=1;
            currentBet +=1;
            totalbets+=1;
            viewscore();
        }else {
            Alert betAlert = setalert("Error !", "No enough credit to spin ! Add coins to play !",Alert.AlertType.WARNING);
            betAlert.showAndWait();
        }
    }
    //this method will allow to bet amount by 3 ! if currentcredit is less than 3 alert will display
    public static void betMax() {
        if (currentcredits > 2) {
            currentBet +=3;
            totalbets+=3;
            currentcredits -=3;
            viewscore();
            Main.betmax.setDisable(true);
        }else{
            Alert betAlert = setalert("Error !", "No enough credit to spin ! Add coins to play !",Alert.AlertType.WARNING);
            betAlert.showAndWait();
        }
    }
    //method to change the bet amount he bets before he spin
    public static void reset() {
      if(currentBet>0){
          currentcredits +=currentBet;
          currentBet = 0;
          viewscore();
          Main.betmax.setDisable(false);
      }else {

          Alert resetalert = setalert("Error !", "No reset needed ! Bet is already 0 !", Alert.AlertType.WARNING);
          resetalert.showAndWait();
      }
    }
    //this method will call when the user click the reel image
    protected static void stopReel() {
        if (currentBet>0) {

            alertlableoff();
            if ((reel1.isAlive()&& reel2.isAlive() && reel3.isAlive())) {

                reel1.stop();
                reel2.stop();
                reel3.stop();
                rolll1 = reel1.getreel();
                rolll2 = reel2.getreel();
                rolll3 = reel3.getreel();

                matchsymbol();
                Main.addcoin.setDisable(false);
                Main.betone.setDisable(false);
                Main.betmax.setDisable(false);
                Main.reset.setDisable(false);
                Main.spin.setDisable(false);
                Main.back.setDisable(false);


            }
        }
    }
    protected  static  void stopreel1(){
        if (currentBet>0){
            if(reel1.isAlive()) {
                reel1.stop();
                rolll1 = reel1.getreel();

                if (!reel2.isAlive() && !reel3.isAlive()){
                    stopandmatch();
                }
            }
        }
    }
    protected  static  void stopreel2(){
        if (currentBet>0){
            if(reel2.isAlive()) {
                reel2.stop();
                rolll2 = reel2.getreel();

                if (!reel3.isAlive() && !reel1.isAlive()){
                    stopandmatch();
                }


            }
        }
    }
    protected  static  void stopreel3(){
        if (currentBet>0){

            if(reel3.isAlive()) {
                reel3.stop();
                rolll3 = reel3.getreel();
               if (!reel2.isAlive() && !reel1.isAlive()){
                   stopandmatch();
               }
            }
        }
    }

    protected  static  void stopandmatch(){

            alertlableoff();
            matchsymbol();
            Main.addcoin.setDisable(false);
            Main.betone.setDisable(false);
            Main.betmax.setDisable(false);
            Main.reset.setDisable(false);
            Main.spin.setDisable(false);
            Main.back.setDisable(false);

    }


    //Check the symbols for match
    protected static void matchsymbol() {

        if (rolll1.equals(rolll2) || rolll1.equals(rolll3) ) {

            won(rolll1);
        }
        else if (rolll2.equals(rolll3)) {

            won(rolll2);
        }
        else {
            lost();
        }
        currentBet = 0;
        viewscore();
    }
    //add the winning amount with the current credits and show the amount win in alert message
    private static void won(Symbol symbol) {
         int winamount = currentBet * symbol.getValue();
        currentcredits += winamount;
        winningcredits+=winamount;
        wins++;
        matches++;
        Alert wonAlert = setalert("Congratulation !", "You won " + winamount + " credits", Alert.AlertType.INFORMATION);
        wonAlert.show();
    }
    //increase the lost matches
    private static void lost() {
        losses++;
        matches++;
        Alert wonAlert = setalert("Try Again", "You Lost the Game !", Alert.AlertType.INFORMATION);
        wonAlert.show();
    }
    //view the current bet and current credits in textfield
    private static void viewscore(){
        Main.Creditarea.setText(String.format("%02d",currentcredits));
        Main.betarea.setText(String.format("%02d",currentBet));

    }
    //label messege for click the image to stop
    private static void alertlable(){
        Main.alretlabel.setText(String.format("*** Click Any Image To Stop The Reel ***"));
    }
    //label messege will off
    private static void alertlableoff(){
        Main.alretlabel.setText(String.format(""));
    }


    //Whenthe back button is pressed while playing the game the amount of current bet and current credit balance will change to 0,10 respectively
    public static void backamoutn(){
        currentBet = 0;
        currentcredits =10;
        Main.addcoin.setDisable(false);
        Main.betone.setDisable(false);
        Main.betmax.setDisable(false);
        Main.reset.setDisable(false);
        Main.spin.setDisable(false);
        Main.back.setDisable(false);

    }



    public static synchronized void playSound(final String bgsound) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(getClass().getResource("/sample/extra/coinsound.wav" + bgsound));
                    clip.open(inputStream);
                    clip.start();
                } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }






    public static float averagecredits(){
        if (matches==0){
            return 0;
        }
        float average = (winningcredits-totalbets)/matches;
        return average;
    }
    public static int getWins() {
        return wins;
    }

    public static int getLosses() {
        return losses;
    }

    public static int getMatches(){
        return matches;
    }





}
