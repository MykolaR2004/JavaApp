package com.example.javaapp.view;

import com.example.javaapp.model.Game;
import javafx.scene.control.Label;

public class ParamsView implements View{
    private Game game;
    private Label score;
    private Label timer;

    public ParamsView(Game game, Label score, Label timer) {
        this.game = game;
        this.score = score;
        this.timer = timer;
    }
    @Override
    public void update() {
        timer.setText("Seconds left " + game.time);
        score.setText("Score = " + (game.score));
    }
}
