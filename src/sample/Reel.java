package sample;



import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Arrays;

import java.util.List;
import java.util.Random;



/**
 * Created by nazoorahamed on 11/13/17.
 */
public class Reel  {
    private static Image sevens;
    private static Image bells;
    private static Image watermelons;
    private static Image plums;
    private static Image lemons;
    private static Image cherrys;

    private static Symbol symbolseven;
    private static Symbol symbolbell;
    private static Symbol symbolwatermelon;
    private static Symbol symbolplum;
    private static Symbol symbollemon;
    private static Symbol symbolcherry;

    static List<Symbol> symbolarray;


    static void loadimage(){
        try {
            sevens = new Image("sample/Images/redseven.png");
            bells = new Image("sample/Images/bell.png");
            watermelons = new Image("sample/Images/watermelon.png");
            plums = new Image("sample/Images/plum.png");
            lemons = new Image("sample/Images/lemon.png");
            cherrys = new Image("sample/Images/cherry.png");


            symbolseven = new Symbol(7, sevens);
            symbolbell = new Symbol(6, bells);
            symbolwatermelon = new Symbol(5, watermelons);
            symbolplum = new Symbol(4, plums);
            symbollemon = new Symbol(3, lemons);
            symbolcherry = new Symbol(2, cherrys);

            symbolarray = Arrays.asList(symbolseven, symbolbell, symbolwatermelon, symbolplum, symbollemon, symbolcherry);
        }catch (Exception e){
            e.printStackTrace();
            Main.alretlabel.setText("***some images are Missing in the reel***\n\t\tSpin is unavailable!");
            Main.back.setDisable(false);
            Main.roll1.setDisable(true);
            Main.roll2.setDisable(true);
            Main.roll3.setDisable(true);

        }
    }

    //selecting randomly
    protected static int Randomnumber(int number) {
        Random random = new Random();
        int x = random.nextInt(number);
        return x;
    }

    //image thread reel changes the array list randomly
    static class Newreel extends Thread{


        private ImageView view;
        private Symbol reel;

        public Newreel(ImageView view){
            loadimage();
            this.view = view;
        }

        public Symbol getreel(){
            if (symbolarray ==null){
             throw new NullPointerException("Symbols can not be null");
            }
            return this.reel;
        }

        @Override
        public void run() {

            Symbol temp;

            while (true) {
                try {
                    temp = Reel.symbolarray.get(Randomnumber(6));
                    System.out.println(Thread.currentThread().getId() + "|" + temp.getValue());
                    view.setImage(temp.getImage());
                    reel = temp;
                    try {
                        Thread.sleep(70);
                    } catch (InterruptedException | NullPointerException e) {

                        e.printStackTrace();
                    }
                }catch (NullPointerException e){
                    MachineView.reel1.stop();
                    MachineView.reel2.stop();
                    MachineView.reel3.stop();
                   // e.printStackTrace();
                    System.out.println("reel can not be null");

                }

            }
        }
    }

}
