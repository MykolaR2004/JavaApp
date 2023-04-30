package com.example.javaapp.model;

import com.example.javaapp.view.View;
import javafx.scene.control.Alert;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    public boolean running = true;

    public final static int SIZE_XY = 20;

    private final Random random = new Random();
    private final Tank tank;
    private Target target;
    public int time = 10;
    public int score = 0;

    private List<View> views = new ArrayList<>();

    public Game() {
        tank = new Tank();
        newTarget();
    }

    private void newTarget() {
        do {
            target = new Target(random.nextInt(SIZE_XY), random.nextInt(SIZE_XY));
        } while (tankCovers(target));
    }

    private boolean tankCovers(Target target) {
        if (tank.hit(target)) return true;
        time+=2;
        return tank.getBody().stream().anyMatch(e -> e.getX() == target.getX() && e.getY() == target.getY());
    }

    public void registerView(View view) {
        views.add(view);
    }

    public void tick() {
        if (running) {
            time -=1;
            System.out.println(time);
            if (time == 0){
                running = false;
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Game over!\n You run out of time!" );
                alert.setTitle("Game");
                alert.setHeaderText(null);
                alert.show();
            }
            if (tank.isOut()) {
                running = false;
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Game over!\n You get out of game field!");
                alert.setTitle("Game");
                alert.setHeaderText(null);
                alert.show();
            }
            if (time >= 60){
                running = false;
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You win!" );
                alert.setTitle("Game");
                alert.setHeaderText(null);
                alert.show();
            }
        }
            views.forEach(View::update);
//        }
    }

    public void turnTank(Direction direction) {
        if (running) {
        tank.setDirection(direction);
        Element e = tank.move();
        views.forEach(View::update);}
    }

    public Tank getTank() {

        return tank;
    }

    public Target getTarget() {
        return target;
    }

    public void shoot() {
        if (running) {
        if(tankCovers(target)){
            System.out.println("HIT!");
            score+=1;
            newTarget();
        }
        else {
            System.out.println("MISS!");
        }
        views.forEach(View::update);}
    }

//    public void move(Direction direction) {
//        Element e = tank.move(direction);
//        views.forEach(View::update);
//    }


}
