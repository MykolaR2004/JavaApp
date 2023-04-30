package com.example.javaapp.view;

import com.example.javaapp.model.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GameView implements View{
    public final static int CELL_SIZE = 10;

    private final Game game;
    private final Canvas canvas;

    public GameView(Game game, Canvas canvas) {
        this.game = game;
        this.canvas = canvas;
    }



    private void drawTank() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Tank tank = game.getTank();
        double scale = getScale();

        drawElement(tank.getHead(), gc, scale);

//        for (TankElement element : game.getTank().getBody()) {
//            drawElement(element, gc, scale, Color.BLUE);
//        }
    }
    private double getScale() {
        double size = Math.min(canvas.getWidth(), canvas.getHeight());
        return CELL_SIZE * Game.SIZE_XY / size;
    }

    private void drawElement(Element element, GraphicsContext gc, double scale) {
        gc.setFill(Color.DARKGREEN);
        gc.fillRect(element.getX() * CELL_SIZE / scale, element.getY() * CELL_SIZE / scale, CELL_SIZE / scale, CELL_SIZE / scale);
    }

    private void drawTargetElement(Element element, GraphicsContext gc, double scale, Color color) {
        gc.setFill(color);
        gc.fillOval(element.getX() * CELL_SIZE / scale, element.getY() * CELL_SIZE / scale, CELL_SIZE / scale, CELL_SIZE / scale);
    }

    public void draw() {
        clear();
        drawField();
        drawTank();
        drawTarget();
    }

    private void drawTarget() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Target target = game.getTarget();
        drawTargetElement(target, gc, getScale(), Color.DARKRED);
    }

    private void clear() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.WHITESMOKE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }
    private void drawField() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setStroke(Color.GRAY);
        double size = Math.min(canvas.getWidth(), canvas.getHeight());
        double scale = CELL_SIZE * Game.SIZE_XY / size;
        for (int i = 0; i < Game.SIZE_XY; i++) {
            gc.strokeLine(0, i * CELL_SIZE / scale, size, i * CELL_SIZE / scale);
            gc.strokeLine(i * CELL_SIZE / scale, 0, i * CELL_SIZE / scale, size);
        }
        gc.strokeRect(0, 0, size, size);
    }

    @Override
    public void update() {
        draw();
    }
}
