package com.bryonnicoson.singletondatabase;

/**
 * Created by bryon on 7/10/16.
 */
public class Game {
    private int id;
    private String name;

    public Game(String name){
        this.name = name;
    }

    public Game(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
