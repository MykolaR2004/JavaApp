package com.example.javaapp;

import com.example.javaapp.model.Direction;
import com.example.javaapp.model.Element;
import com.example.javaapp.model.Game;
import com.example.javaapp.model.Tank;
import com.example.javaapp.view.GameView;
import com.example.javaapp.view.View;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class HelloController {
    @FXML
    public Label timer;
    @FXML
    private Label score;
    @FXML
    private Pane pane;
    @FXML
    private Canvas canvas;

    private Game game;
    private Tank tank;



    public void initialize() {
        canvas.widthProperty().bind(pane.widthProperty());
        canvas.heightProperty().bind(pane.heightProperty());

        game = new Game();
        View view = new GameView(game, canvas);
        game.registerView(view);
    //    game.registerView(scoreView);

        canvas.widthProperty().addListener(evt -> view.update());
        canvas.heightProperty().addListener(evt -> view.update());

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), e -> processTick()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        pane.requestFocus();
    }

    public void processTick() {
        timer.setText("Seconds left " + game.time);
        score.setText("Score = " + (game.score));
        game.tick();
    }

    public Tank getTank() {
        return tank;
    }


    public void processKey(KeyEvent keyEvent) {
        KeyCode keyCode = keyEvent.getCode();
        if (game.running) {
        System.out.println(keyCode);
            switch (keyCode) {
//            case UP -> game.turnTank(Direction.UP);
//            case DOWN -> game.turnTank(Direction.DOWN);
//            case LEFT -> game.turnTank(Direction.LEFT);
//            case RIGHT -> game.turnTank(Direction.RIGHT);
                case UP -> game.turnTank(Direction.UP);
                case DOWN -> game.turnTank(Direction.DOWN);
                case LEFT -> game.turnTank(Direction.LEFT);
                case RIGHT -> game.turnTank(Direction.RIGHT);
                case SPACE -> game.shoot();
            }
        }
    }
}