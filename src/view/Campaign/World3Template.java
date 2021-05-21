package view.Campaign;

import control.MainProgram;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import view.Menu.RightPanel;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class World3Template extends GridPane {


    /**
     * Author André Eklund
     */
    private MainProgram mainProgram;
    private RightPanel rightPanel;
    private int[][] level;
    private ArrayList<Label> collectibles = new ArrayList<>();
    private ArrayList<Label> pickaxes = new ArrayList<>();
    private MouseListener mouseListener = new MouseListener();
    private Image wall;
    private Image path;
    private Image border;
    private Image goal;
    private Image diamond;
    private Image start;
    private Image ghost;
    private Image heart;
    private boolean startButtonPressed;
    private boolean allCollectiblesObtained;
    private boolean wallDestroyed;
    private int collectiblesObtained = 0;
    private int squareSize;
    private int currentLevel;
    private int heartCrystals;
    private Image pickAxeImage;
    private boolean pickaxeObtained;
    private ImageView imageView = new ImageView();
    private boolean gameStarted = false;
    private boolean startNotClickedOnce = true;

    private PathTransition animation;
    private PathTransition animation2;
    private PathTransition animation3;
    private PathTransition animation4;
    private PathTransition animation5;
    private PathTransition animation6;


    private File diamondSound = new File("files/sounds/Diamond1.mp3");
    private Media diamondMedia = new Media(diamondSound.toURI().toString());
    private MediaPlayer diamondPlayer = new MediaPlayer(diamondMedia);

    private File deathSound = new File("files/sounds/MazegenDeath.mp3");
    private Media deathMedia = new Media(deathSound.toURI().toString());
    private MediaPlayer deathPlayer = new MediaPlayer(deathMedia);


    private File startSound = new File("files/sounds/MazegenStart.mp3");
    private Media startMedia = new Media(startSound.toURI().toString());
    private MediaPlayer startPlayer = new MediaPlayer(startMedia);

    private File goalSound = new File("files/sounds/MazegenGoal.mp3");
    private Media goalMedia = new Media(goalSound.toURI().toString());
    private MediaPlayer goalPlayer = new MediaPlayer(goalMedia);

    //Konstruktorn ska kunna ta emot int-arrayer och representera dem i GUIt
    public World3Template(int[][] level, int currentLevel, int heartCrystals, MainProgram mainProgram, RightPanel rightPanel) throws FileNotFoundException {
        this.mainProgram = mainProgram;
        this.currentLevel = currentLevel;
        this.rightPanel = rightPanel;
        this.level = level;
        this.heartCrystals = heartCrystals;
        squareSize = 600/(level.length+2);
        setBackground();
        setupImages();
        setupBorders();
        setupLevel();
        setupGhost();
        rightPanel.setSTARTTIME(15);
    }
    public void setupGhost() throws FileNotFoundException {
        ghost = new Image("file:files/ghost.png", squareSize, squareSize, false, false);

        imageView.setImage(ghost);

        imageView.setX(1);
        imageView.setY(1);
        imageView.setFitHeight(squareSize);
        imageView.setFitWidth(squareSize);

        imageView.setOnMouseEntered(e -> enteredWall(e));



        initialize();
    }
    public void initialize() {
        if (currentLevel==1){
            ImageView ghost3V = new ImageView();
            ghost3V.setImage(ghost);

            add(ghost3V,5,5);

            Rectangle rectangle = new Rectangle(145,400);
            rectangle.setY(50);
            rectangle.setX(-150);
            rectangle.setArcWidth(50);
            rectangle.setArcHeight(50);

            animation = new PathTransition();
            animation.setNode(ghost3V);
            animation.setDuration(Duration.seconds(4));
            animation.setCycleCount(Animation.INDEFINITE);
            animation.setPath(rectangle);
            animation.play();

        }

        else if (currentLevel ==2){
            ImageView ghost1V = new ImageView();
            ImageView ghost2V = new ImageView();

            ghost1V.setImage(ghost);
            ghost2V.setImage(ghost);

            add(ghost1V,10,0);
            add(ghost2V,9,0);

            Rectangle rectangle = new Rectangle(145,400);
            rectangle.setY(50);
            rectangle.setX(-150);
            rectangle.setArcWidth(50);
            rectangle.setArcHeight(50);

            Rectangle rectangle1 = new Rectangle(400,145);
            rectangle1.setY(200);
            rectangle1.setX(-250);
            rectangle1.setArcWidth(50);
            rectangle1.setArcHeight(50);

            animation = new PathTransition();
            animation.setNode(ghost1V);
            animation.setDuration(Duration.seconds(4));
            animation.setCycleCount(Animation.INDEFINITE);
            animation.setPath(rectangle);
            animation.play();

            animation2 = new PathTransition();
            animation2.setNode(ghost2V);
            animation2.setDuration(Duration.seconds(5));
            animation2.setCycleCount(Animation.INDEFINITE);
            animation2.setPath(rectangle1);
            animation2.play();
        }
        else if (currentLevel ==3){
          ImageView ghost4V = new ImageView();
          ImageView ghost2V = new ImageView();

          ghost2V.setImage(ghost);
          ghost4V.setImage(ghost);

          add(ghost2V,5,5);
          add(ghost4V,4,3);
            Rectangle rectangle = new Rectangle(100,350);
            rectangle.setY(50);
            rectangle.setX(-150);
            rectangle.setArcWidth(50);
            rectangle.setArcHeight(50);

            Rectangle rectangle1 = new Rectangle(100,200);
            rectangle1.setY(200);
            rectangle1.setX(-250);
            rectangle1.setArcWidth(50);
            rectangle1.setArcHeight(50);

            animation = new PathTransition();
            animation.setNode(ghost4V);
            animation.setDuration(Duration.seconds(4));
            animation.setCycleCount(Animation.INDEFINITE);
            animation.setPath(rectangle);
            animation.play();

            animation2 = new PathTransition();
            animation2.setNode(ghost2V);
            animation2.setDuration(Duration.seconds(5));
            animation2.setCycleCount(Animation.INDEFINITE);
            animation2.setPath(rectangle1);
            animation2.play();

        }
        else if(currentLevel==4){
           ImageView ghost5V = new ImageView();
           ImageView ghost6V = new ImageView();
           ImageView ghost7V = new ImageView();

           ghost5V.setImage(ghost);
           ghost6V.setImage(ghost);
           ghost7V.setImage(ghost);

           add(ghost5V,4,5);
           add(ghost6V,5,3);
           add(ghost7V,4,3);
            Rectangle rectangle = new Rectangle(100,350);
            rectangle.setY(50);
            rectangle.setX(-150);
            rectangle.setArcWidth(50);
            rectangle.setArcHeight(50);

            Rectangle rectangle1 = new Rectangle(100,200);
            rectangle1.setY(200);
            rectangle1.setX(-250);
            rectangle1.setArcWidth(50);
            rectangle1.setArcHeight(50);

            Rectangle rectangle3 = new Rectangle(120,275);
            rectangle1.setY(250);
            rectangle1.setX(-300);
            rectangle1.setArcWidth(80);
            rectangle1.setArcHeight(50);

            animation = new PathTransition();
            animation.setNode(ghost5V);
            animation.setDuration(Duration.seconds(4));
            animation.setCycleCount(Animation.INDEFINITE);
            animation.setPath(rectangle);
            animation.play();

            animation2 = new PathTransition();
            animation2.setNode(ghost6V);
            animation2.setDuration(Duration.seconds(5));
            animation2.setCycleCount(Animation.INDEFINITE);
            animation2.setPath(rectangle1);
            animation2.play();

            animation3 = new PathTransition();
            animation3.setNode(ghost7V);
            animation3.setDuration(Duration.seconds(5));
            animation3.setCycleCount(Animation.INDEFINITE);
            animation3.setPath(rectangle3);
            animation3.play();


        }
/*





        ghost3V.setImage(ghost);
        ghost4V.setImage(ghost);
        ghost5V.setImage(ghost);
        ghost6V.setImage(ghost);


        add(ghost3V,8,0);
        add(ghost4V, 1, 0);
        add(ghost5V, 2, 0);
        add(ghost6V, 3, 0);

*/








    }
    public void setBackground(){
        BackgroundImage menuBackground = new BackgroundImage(new Image("file:files/MenuBackground.jpg",800,600,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        this.setBackground(new Background(menuBackground));
    }
    public void setupBorders() {
        for (int i = 0; i < level.length + 1; i++) {
            add(getBorders(), i, 0);
        }
        for (int i = 0; i < level.length + 1; i++) {
            add(getBorders(), 0, i);
        }
        for (int i = 0; i < level.length + 2; i++) {
            add(getBorders(), i, level.length + 1);
        }
        for (int i = 0; i < level.length + 2; i++) {
            add(getBorders(), level.length + 1, i);
        }
    }
    public void setupLevel() {
        for (int i = 0; i < level.length; i++) {
            for (int j = 0; j < level.length; j++) {

                if (level[i][j] == 1) {
                    add(getPath(),j + 1,i + 1);
                }
                else if (level[i][j] == 0){
                    add(getWall(),j + 1,i + 1);
                }
                else if (level[i][j] == 2){
                    add(getStart(),j + 1,i + 1);
                }
                else if (level[i][j] == 3){
                    add(getGoal(),j + 1,i + 1);
                }
                else if (level[i][j] == 4){
                    add(getPath(),j + 1,i + 1);
                    add(addCollectible(),j + 1,i + 1);
                }
                else if (level[i][j] == 5){
                    add(getPath(),j + 1,i + 1);
                    add(addPickAxe(),j + 1,i + 1);
                }
                else if (level[i][j] == 6){
                    add(getBreakableWall(),j + 1,i + 1);
                }
                else if (level[i][j] == 7){
                    add(getPath(),j + 1,i + 1);
                    add(addHeartCrystal(),j + 1,i + 1);
                }
            }
        }
    }
    public void setupImages(){
        wall = new Image("file:files/lava/wall.png", squareSize, squareSize, false, false);
        path = new Image("file:files/lava/path.png", squareSize, squareSize, false, false);
        border = new Image("file:files/lava/border.png", squareSize, squareSize, false, false);
        goal = new Image("file:files/lava/goal.png", squareSize, squareSize, false, false);
        diamond = new Image("file:files/lava/collectible.png", squareSize, squareSize, false, false);
        start = new Image("file:files/lava/start.png", squareSize, squareSize, false, false);
        pickAxeImage = new Image("file:files/items/pickaxe.png", squareSize, squareSize, false, false);
        heart = new Image("file:files/items/heart.png", squareSize, squareSize, false, false);
    }

    public Label getWall() {
        Label label = new Label();
        ImageView wallView = new ImageView(wall);
        wallView.setFitHeight(squareSize);
        wallView.setFitWidth(squareSize);
        label.setGraphic(wallView);
        //label.setStyle("-fx-border-color: grey; ");
        label.setOnMouseEntered(e -> enteredWall(e));
        label.setOnMouseExited(e -> exitedLabel(e));
        return label;
    }
    private Label getPath() {
        Label label = new Label();
        ImageView pathView = new ImageView(path);
        pathView.setFitHeight(squareSize);
        pathView.setFitWidth(squareSize);
        label.setGraphic(pathView);
        //label.setStyle("-fx-border-color: grey;");
        return label;
    }
    private Label getBorders() {
        Label label = new Label();
        ImageView borderView = new ImageView(border);
        borderView.setFitHeight(squareSize);
        borderView.setFitWidth(squareSize);
        label.setGraphic(borderView);
        //label.setStyle("-fx-border-color: grey;");
        label.setOnMouseEntered(e -> enteredWall(e));
        label.setOnMouseExited(e -> exitedLabel(e));
        return label;
    }
    private Label getBreakableWall() {
        Label label = new Label();
        ImageView borderView = new ImageView(border);
        borderView.setFitHeight(squareSize);
        borderView.setFitWidth(squareSize);
        label.setGraphic(borderView);
        label.setOnMouseEntered(e -> enteredBreakableWall(e));
        return label;
    }
    private Label getGoal() {
        Label label = new Label();
        ImageView borderView = new ImageView(goal);
        borderView.setFitHeight(squareSize);
        borderView.setFitWidth(squareSize);
        label.setGraphic(borderView);
        //label.setStyle("-fx-border-color: grey;");
        label.setOnMouseEntered(e -> {
            try {
                enteredGoal();
            } catch (FileNotFoundException | InterruptedException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });
        return label;
    }
    private Label getStart() {
        Label label = new Label();
        ImageView borderView = new ImageView(start);
        borderView.setFitHeight(squareSize);
        borderView.setFitWidth(squareSize);
        label.setGraphic(borderView);
        //label.setStyle("-fx-border-color: grey;");
        label.setOnMouseClicked(e -> startLevel());
        return label;
    }
    public Label addCollectible() {
        Label collectible = new Label();
        ImageView borderView = new ImageView(diamond);
        borderView.setFitHeight(squareSize);
        borderView.setFitWidth(squareSize);
        Glow glow = new Glow();
        glow.setLevel(0.7);
        borderView.setEffect(glow);
        collectible.setStyle("fx-background-color: transparent;");
        collectible.setGraphic(borderView);
        collectible.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseListener);
        collectibles.add(collectible);
        return collectible;
    }
    public Label addHeartCrystal() {
        Label heartCrystal = new Label();
        ImageView borderView = new ImageView(heart);
        borderView.setFitHeight(squareSize);
        borderView.setFitWidth(squareSize);
        Glow glow = new Glow();
        glow.setLevel(0.8);
        borderView.setEffect(glow);
        heartCrystal.setStyle("fx-background-color: transparent;");
        heartCrystal.setGraphic(borderView);
        heartCrystal.setOpacity(0.8);
        heartCrystal.setOnMouseEntered(e -> heartCrystalObtained(e));
        return heartCrystal;
    }

    private void heartCrystalObtained(MouseEvent e) {

        Label label = (Label)e.getSource();
        ImageView pathView = new ImageView(path);

        if (startButtonPressed) {
            label.setGraphic(pathView);
            heartCrystals++;
        }
    }

    public Label addPickAxe() {
        Label pickAxe = new Label();
        ImageView borderView = new ImageView(pickAxeImage);
        borderView.setFitHeight(squareSize);
        borderView.setFitWidth(squareSize);
        Glow glow = new Glow();
        glow.setLevel(0.7);
        borderView.setEffect(glow);
        pickAxe.setStyle("fx-background-color: transparent;");
        pickAxe.setGraphic(borderView);
        pickAxe.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseListener);
        pickaxes.add(pickAxe);
        return pickAxe;
    }
    public void enteredWall(MouseEvent e) {
        Label label = (Label)e.getSource();
        FadeTransition fade = new FadeTransition();
        fade.setNode(label);
        fade.setDuration(Duration.seconds(0.3));
        fade.setFromValue(10);
        fade.setToValue(0.6);
        fade.play();

        if (startButtonPressed) {

            heartCrystals--;
            System.out.println("Hearts left: " + heartCrystals);

            if (heartCrystals == 0) {
                gameOver();
            }
            deathPlayer.play();
            deathPlayer.seek(Duration.ZERO);
            startButtonPressed = false;
        }
    }

    private void gameOver() {
        System.out.println("Game over!!!!");
    }

    public void enteredGoal() throws FileNotFoundException, InterruptedException {
        if (startButtonPressed && allCollectiblesObtained) {
            goalPlayer.play();
            goalPlayer.seek(Duration.ZERO);
            mainProgram.nextWorld1Level(currentLevel, heartCrystals);

            rightPanel.pauseClock();
            gameStarted = true;
        }
    }
    public void startLevel() {

        if (!gameStarted){
            rightPanel.resumeClock();
            gameStarted = true;
        }else if (startNotClickedOnce){
            rightPanel.runClock();
        }

        startPlayer.play();
        startPlayer.seek(Duration.ZERO);
        startButtonPressed = true;
    }
    public void exitedLabel(MouseEvent e) {
        Label label = (Label)e.getSource();
        FadeTransition fade = new FadeTransition();
        fade.setNode(label);
        fade.setDuration(Duration.seconds(0.3));
        fade.setFromValue(0.6);
        fade.setToValue(10);
        fade.play();
    }
    public void enteredBreakableWall(MouseEvent e) {

        Label label = (Label)e.getSource();
        ImageView pathView = new ImageView(path);

        if (startButtonPressed) {

            if (pickaxeObtained) {
                label.setGraphic(pathView);
                pickaxeObtained = false;
                wallDestroyed = true;
            }
            else if (!wallDestroyed) {
                enteredWall(e);
            }
        }
    }

    private class MouseListener implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent e) {
            if (startButtonPressed) {

                diamondPlayer.play();
                diamondPlayer.seek(Duration.ZERO);

                for (Label label : pickaxes){
                    if (e.getSource()== label){
                        label.setVisible(false);
                        pickaxeObtained = true;
                    }
                }

                for (Label label: collectibles) {
                    if (e.getSource() == label) {
                        label.setVisible(false);
                        collectiblesObtained++;
                        if (collectiblesObtained == collectibles.size()) {
                            allCollectiblesObtained = true;
                        }
                    }
                }
            }
        }
    }
}//Class
