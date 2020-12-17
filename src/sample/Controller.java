package sample;

import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.Node;
import javafx.util.Duration;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static sample.Main.fadealert;

public class Controller {
    public static void saveStats() {

        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd_HH.mm.ss");
        Date date = new Date();
        String filename = String.valueOf(dateFormat.format(date));

        try {
            FileWriter filewriter = new FileWriter(filename+".txt");
            PrintWriter printWriter = new PrintWriter(filewriter);
            printWriter.println("Total Spins  : " + MachineView.getMatches());
            printWriter.println("Win Spins    : " + MachineView.getWins());
            printWriter.println("Losses spins : " + MachineView.getLosses());
            printWriter.println("Average net credit per spin : " + MachineView.averagecredits());

            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        fadealert.setText("\tFile Saved !\n"+filename+".txt");
        FadeTransition fader = createFader(fadealert);

        SequentialTransition fade = new SequentialTransition(fadealert,fader);

        fade.play();

    }
    private static FadeTransition createFader(Node node) {
        FadeTransition fade = new FadeTransition(Duration.seconds(5), node);
        fade.setFromValue(1);
        fade.setToValue(0);

        return fade;
    }
}


