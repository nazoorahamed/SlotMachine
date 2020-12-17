package sample;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.Group;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.util.Optional;
import static sample.MachineView.*;


public  class Main extends Application  {
    protected static Button start,stats,exit,back,nback,addcoin,betone,betmax,reset;
    Scene scene;
    Text balancecredit,betamount;
    protected static TextField Creditarea,betarea;
    protected static ImageView roll1,roll2,roll3;
    protected static Button spin;
    protected static Label alretlabel,fadealert;



    @Override
    public void start(Stage primaryStage) throws Exception{





        primaryStage.setTitle("Slot Machine");
        Group gro = new Group();
        StackPane roots = new StackPane();
        roots.setStyle("-fx-effect: dropshadow(three-pass-box, black, 30, 0.5, 0, 0);" +
                "-fx-background-color: #252525");
        scene = new Scene(roots,700,500);

        primaryStage.setScene(scene);
        primaryStage.show();






// All  CSS Styles button from internet
        String savebt = "-fx-background-color: \n" +
                "        linear-gradient(#ffd65b, #e68400),\n" +
                "        linear-gradient(#ffef84, #f2ba44),\n" +
                "        linear-gradient(#ffea6a, #efaa22),\n" +
                "        linear-gradient(#ffe657 0%, #f8c202 50%, #eea10b 100%),\n" +
                "        linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));\n" +
                "    -fx-background-radius: 30;\n" +
                "    -fx-background-insets: 0,1,2,3,0;\n" +
                "    -fx-text-fill: #654b00;\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-font-size: 14px;\n" +
                "    -fx-padding: 10 20 10 20;";

        String btstyle = "-fx-font:bold 15 arial; -fx-font-size:1.1em;  -fx-background-color: linear-gradient(from 0% 93% to 0% 100%," +
                " #a34313 0%, #903b12 100%),#9d4024,#d86e3a,radial-gradient(center 50% 50%, radius 100%, #ea7f4b, #c54e2c);" +
                "-fx-padding: 8 15 15 15;-fx-background-insets: 0,0 0 5 0, 0 6 0, 0 0 7 0;-fx-background-radius: 13;" +
                "-fx-effect: dropshadow( gaussian ,rgba(0,0,0,0.75) , 4,0,0,1) ;";

        String spinstyle = "-fx-background-radius: 7em;-fx-min-width: 80px;-fx-min-height: 80px;-fx-max-width: 80px;-fx-max-height: 80px;" +
                 "-fx-background-color: linear-gradient(from 0% 93% to 0% 100%, #a34313 0%, #903b12 100%),#9d4024,#67d800;" +
                "-fx-font:bold 15 arial; -fx-font-size:1.1em;";


        //Start Button
        start = new Button("Start The game");
        start.setText("    Start    ");
        start.setCursor(Cursor.HAND);
        start.setLayoutY(200);
        start.setLayoutX(550);
        gro.getChildren().add(start);
        start.setStyle(btstyle);

        //Statistic Button
        stats = new Button("Statistic of the game");
        stats.setText("Statistics");
        stats.setCursor(Cursor.HAND);
        stats.setLayoutY(250);
        stats.setLayoutX(550);
        gro.getChildren().add(stats);
        stats.setStyle(btstyle);

        //Exit Button
        exit = new Button("Exit the game");
        exit.setText("    Exit     ");
        exit.setCursor(Cursor.HAND);
        exit.setLayoutY(300);
        exit.setLayoutX(550);
        gro.getChildren().add(exit);
        exit.setStyle(btstyle);

        //Logo
        try {
            ImageView logo = new ImageView("sample/extra/logopng.png");
            logo.setLayoutX(400);
            logo.setLayoutY(50);
            logo.setPreserveRatio(true);
            logo.setFitHeight(250);
            logo.setFitWidth(300);
            gro.getChildren().add(logo);
        }catch (Exception c){
            System.out.println(" logo location fiel not found!");

        }


        //Side Image
        try {
            ImageView sidepng = new ImageView("sample/extra/pngimage.png");
            sidepng.setLayoutX(650);
            sidepng.setLayoutY(100);
            sidepng.setPreserveRatio(true);
            sidepng.setFitHeight(200);
            sidepng.setFitWidth(200);
            gro.getChildren().add(sidepng);
        }catch (Exception c){
            System.out.println(" logo location fiel not found!");

        }


        roots.getChildren().add(gro);

        //Exit button Action
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                //Confirmation Alert Message for exit the game
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Slot Machine");
                alert.setHeaderText("Exit the Game?");
                alert.setContentText("Are you sure ? You want to exit the Game ?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    Platform.exit();
                } else {

                }
            }
        });
        //Start Button Action
        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent mainevent) {




                //new scene for game platform
                Group startgro = new Group();
                StackPane startroot = new StackPane();
                Scene startscene = new Scene(startroot,700,500);
                startroot.setStyle("-fx-background-color:#252525");


                //back button design and action
                back = new Button("Back to menu");
                back.setText(" Back");
                back.setCursor(Cursor.HAND);
                back.setLayoutY(25);
                back.setLayoutX(25);
                startgro.getChildren().add(back);
                back.setStyle(btstyle);
                back.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {



                        //Confirmation for back from the game
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Slot Machine");
                        alert.setHeaderText("Quit the Game?");
                        alert.setContentText("Are you sure ? You want to Quit the Game ?");

                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.get() == ButtonType.OK){
                            backamoutn();

                            primaryStage.setScene(scene);
                            primaryStage.show();
                        } else {

                        }
                    }
                });

                //Alert label for stop the reel
                alretlabel = new Label("  ");
                alretlabel.setTextFill(Color.web("#FFFF00"));
                alretlabel.setAlignment(Pos.CENTER);
                alretlabel.setFont(new Font("Arial",18));
                alretlabel.setStyle("-fx-font-weight: bold");
                alretlabel.setLayoutY(65);
                alretlabel.setLayoutX(190);
                startgro.getChildren().add(alretlabel);

                //Reset button design
                reset = new Button("Reset");
                reset.setOnAction(Event -> MachineView.reset());
                reset.setText(" Reset  ");
                reset.setCursor(Cursor.HAND);
                reset.setLayoutX(530);
                reset.setLayoutY(355);
                reset.setStyle(btstyle);
                startgro.getChildren().add(reset);

                //Bet one design button
                betone = new Button("Bet one");
                betone.setOnAction(Event -> MachineView.betOne());
                betone.setText("Bet One");
                betone.setCursor(Cursor.HAND);
                betone.setLayoutX(330);
                betone.setLayoutY(355);
                betone.setStyle(btstyle);
                startgro.getChildren().add(betone);

                //bet max design button
                betmax = new Button("Bet 3");
                betmax.setOnAction(Event -> MachineView.betMax());
                betmax.setText("Bet Max");
                betmax.setCursor(Cursor.HAND);
                betmax.setLayoutX(130);
                betmax.setLayoutY(355);
                betmax.setStyle(btstyle);
                startgro.getChildren().add(betmax);

                //spin design button
                spin = new Button("Spin");
                spin.setOnAction(Event->MachineView.spin());
                spin.setText("Spin");
                spin.setCursor(Cursor.HAND);
                spin.setLayoutX(330);
                spin.setLayoutY(420);
                spin.setStyle(spinstyle);
                startgro.getChildren().add(spin);

                //add coin design
                addcoin = new Button("Add coin");
                addcoin.setOnAction(Event-> MachineView.addCoin());
                addcoin.setText("Add Coin");
                addcoin.setCursor(Cursor.HAND);
                addcoin.setLayoutX(600);
                addcoin.setLayoutY(25);
                addcoin.setStyle(btstyle);
                startgro.getChildren().add(addcoin);

                //lebal text
                balancecredit = new Text("Balance Credit");
                balancecredit.setStyle("-fx-stroke: aliceblue; -fx-font-size: 1.2em;");
                balancecredit.setLayoutX(600);
                balancecredit.setLayoutY(85);
                startgro.getChildren().add(balancecredit);

                //lebal text
                betamount = new Text("  Bet Amount");
                betamount.setStyle("-fx-stroke: aliceblue; -fx-font-size: 1.2em;");
                betamount.setLayoutX(25);
                betamount.setLayoutY(85);
                startgro.getChildren().add(betamount);


                //Current Credit Area
                Creditarea = new TextField();
                Creditarea.setText("10");
                Creditarea.setAlignment(Pos.CENTER);
                Creditarea.setLayoutX(600);
                Creditarea.setLayoutY(100);
                Creditarea.setEditable(false);
                Creditarea.setStyle("-fx-text-alignment:center;-fx-font-size: 2em");
                Creditarea.setPrefSize(100,50);
                startgro.getChildren().add(Creditarea);


                //Current bet Area
                betarea = new TextField();
                betarea.setText("00");
                betarea.setAlignment(Pos.CENTER);
                betarea.setLayoutX(25);
                betarea.setLayoutY(100);
                betarea.setEditable(false);
                betarea.setStyle("-fx-text-alignment: center;-fx-font-size: 2em");
                betarea.setPrefSize(100,50);
                startgro.getChildren().add(betarea);


                //Reel image view 1

            try {
                roll1 = new ImageView("sample/images/bell.png");
                roll1.setOnMouseClicked(Event -> MachineView.stopreel1());
                roll1.setId("roll1");
                roll1.setLayoutX(140);
                roll1.setLayoutY(200);
                roll1.setPreserveRatio(true);
                roll1.setFitHeight(200);
                roll1.setFitWidth(100);
                roll1.setStyle("-fx-border-color: azure;-fx-border-width:5;-fx-border-style:solid;-fx-background-color: aqua ");
                startgro.getChildren().add(roll1);
            }catch (Exception c){
                alretlabel.setText("   ***Reel image is missing in the file***");

            }

            try{
                //Reel image view 2
                roll2 = new ImageView("sample/images/lemon.png");
                roll2.setOnMouseClicked(Event->MachineView.stopreel2());
                roll2.setLayoutX(320);
                roll2.setLayoutY(200);
                roll2.setPreserveRatio(true);
                roll2.setFitHeight(200);
                roll2.setFitWidth(100);
                roll2.setStyle("-fx-border-color: azure;-fx-border-width:5;-fx-border-style:solid ");
                startgro.getChildren().add(roll2);
            }catch (Exception c){
                alretlabel.setText("   ***Reel image is missing in the file***");

            }

                //Reel image view 3
             try{
                roll3 = new ImageView("sample/images/plum.png");
                roll3.setOnMouseClicked(Event->MachineView.stopreel3());
                roll3.setLayoutX(500);
                roll3.setLayoutY(200);
                roll3.setSmooth(true);
                roll3.setPreserveRatio(true);
                roll3.setFitHeight(200);
                roll3.setFitWidth(100);
                roll3.setStyle("-fx-background-color: white");
                startgro.getChildren().add(roll3);
             }catch (Exception c){
                    alretlabel.setText("   ***Reel image is missing in the file***");

             }

                startroot.getChildren().add(startgro);
                primaryStage.setScene(startscene);
                primaryStage.show();
            }
        });

        stats.setOnAction(new EventHandler<ActionEvent>() {


            @Override
            public void handle(ActionEvent event) {
                Group statsgro = new Group();
                StackPane statsroot = new StackPane();
                Scene statsscene = new Scene(statsroot,900,500);
                statsroot.setStyle("-fx-background-color:#252525; ");


                //PI Chart for display the statistic meaningfully
                ObservableList<PieChart.Data> pieChartData =
                        FXCollections.observableArrayList(
                                new PieChart.Data("Losses", MachineView.getLosses()),
                                new PieChart.Data("Wins", MachineView.getWins())

                        );

                //PI Chart Position
                final PieChart chart = new PieChart(pieChartData);
                chart.setLayoutX(460);
                chart.setLayoutY(150);

                chart.setTitle("Game Statistics");
                chart.setStyle("-fx-background-color: azure;-fx-border-color: dimgray; -fx-border-width: 5px");


                Label totalmatches = new Label("Total Spins :  " + MachineView.getMatches());
                totalmatches.setFont(new Font("Arial",18));
                totalmatches.setStyle("-fx-font-weight: bold");
                totalmatches.setTextFill(Color.web("#ffffff"));
                totalmatches.setLayoutX(160);
                totalmatches.setLayoutY(280);

                //Winning Label
                Label statwins   = new Label("Spin Wins :  " + MachineView.getWins());
                statwins.setFont(new Font("Arial",18));
                statwins.setStyle("-fx-font-weight: bold");
                statwins.setTextFill(Color.web("#ffffff"));
                statwins.setLayoutX(160);
                statwins.setLayoutY(310);

                //Loss label
                Label statlosses = new Label("Spin loses :  " + MachineView.getLosses());
                statlosses.setFont(new Font("Arial",18));
                statlosses.setStyle("-fx-font-weight: bold");
                statlosses.setTextFill(Color.web("#ffffff"));
                statlosses.setLayoutX(160);
                statlosses.setLayoutY(340);

                Label average = new Label("Average net credit per spin :  " + MachineView.averagecredits());
                average.setFont(new Font("Arial",18));
                average.setStyle("-fx-font-weight: bold");
                average.setTextFill(Color.web("#ffffff"));
                average.setLayoutX(160);
                average.setLayoutY(370);




                //save button action
                Button save = new Button("Save Statistics");
                save.setStyle(savebt);
                save.setLayoutX(200);
                save.setLayoutY(410);

                save.setAlignment(Pos.CENTER_RIGHT);
                save.setOnAction(Event -> {
                    Controller.saveStats();
                    save.setDisable(true);
                });

                fadealert = new Label("");
                fadealert.setFont(new Font("Arial",15));
                fadealert.setStyle("-fx-text-fill: cornsilk;-fx-font-weight: bold");
                fadealert.setLayoutX(180);
                fadealert.setLayoutY(480);






                //added to the group
                statsgro.getChildren().add(fadealert);
                statsgro.getChildren().add(statlosses);
                statsgro.getChildren().add(statwins);
                statsgro.getChildren().add(average);
                statsgro.getChildren().add(totalmatches);
                statsgro.getChildren().add(chart);
                statsgro.getChildren().add(save);




                //Back from the game
                nback = new Button("Back to menu");
                nback.setText("   Back  ");
                nback.setCursor(Cursor.HAND);
                nback.setLayoutY(115);
                nback.setLayoutX(100);
                statsgro.getChildren().add(nback);
                nback.setStyle(btstyle);
                nback.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                            save.setDisable(false);
                            primaryStage.setScene(scene);
                            primaryStage.show();
                    }
                });


                statsroot.getChildren().add(statsgro);
                primaryStage.setScene(statsscene);
                primaryStage.show();
            }


        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}
