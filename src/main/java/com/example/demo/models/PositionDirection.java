package com.example.demo.models;

public enum PositionDirection {
    UPLEFT("upleft"),
    UP("up"),
    UPRIGHT("upright"),
    LEFT("left"),
    RIGHT("right"),
    DOWNLEFT("downleft"),
    DOWN("down"),
    DOWNRIGHT("downright");

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
