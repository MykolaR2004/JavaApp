package com.example.javaapp.model;

public interface Direction {

    Direction UP = e -> new TankElement(e.getX(), e.getY() - 1);
    Direction DOWN = e -> new TankElement(e.getX(), e.getY() + 1);
    Direction LEFT = e -> new TankElement(e.getX() - 1, e.getY());
    Direction RIGHT = e -> new TankElement(e.getX() + 1, e.getY());
    Direction NONE = e -> new TankElement(e.getX(), e.getY());

   TankElement next(TankElement element);
}
