package com.morgan_lab4;

/**
 * Created by tmorgan2 on 11/20/2017.
 */

public class Player {

    public int _id;
    public String Name;
    public int Wins;
    public int Losses;
    public int Ties;

    public Player(int id, String name, int wins, int losses, int ties) {
        this._id = id;
        this.Name = name;
        this.Wins = wins;
        this.Losses = losses;
        this.Ties = ties;
    }
}
