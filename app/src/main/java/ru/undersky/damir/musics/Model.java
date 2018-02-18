package ru.undersky.damir.musics;

public class Model{
    String name;
    boolean value;

    Model(String name, boolean value){
        this.name = name;
        this.value = value;
    }
    public String getName(){
        return this.name;
    }
    public boolean getValue(){
        return this.value;
    }

}