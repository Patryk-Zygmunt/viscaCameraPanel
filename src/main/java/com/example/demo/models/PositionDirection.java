package com.example.demo.models;


public enum PositionDirection {
    UP("up"),
    LEFT("left"),
    RIGHT("right"),
    DOWN("down");

    private String direction;

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    PositionDirection(String direction) {
        this.direction = direction;
    }
}
