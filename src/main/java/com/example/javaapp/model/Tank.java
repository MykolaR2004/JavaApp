package com.example.javaapp.model;

import java.util.LinkedList;
import java.util.List;

public class Tank {
    private LinkedList<TankElement> body;

    private TankElement head;

    private Direction direction;

    public Tank() {
        head = new TankElement(Game.SIZE_XY / 2, Game.SIZE_XY / 2);
        body = new LinkedList<>();
    }

    public Element move() {
        body.addFirst(head);
        head = this.direction.next(head);
        return body.removeLast();
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public TankElement getHead() {
        return head;
    }

    public List<TankElement> getBody() {
        return body;
    }

    public boolean isOut() {
        return head.getX() < 0 || head.getY() < 0 || head.getX() >= Game.SIZE_XY || head.getY() >= Game.SIZE_XY;
    }


//    public boolean eat(Apple apple) {
//        return head.getX() == apple.getX() && head.getY() == apple.getY();
//    }



    public boolean hit(Target target) {
        return head.getX() == target.getX() || head.getY() == target.getY();
    }
}
