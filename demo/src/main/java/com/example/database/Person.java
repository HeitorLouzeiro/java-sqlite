package com.example.database;

public class Person {
    private int id;
    private String name;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person() {

    }

    public String toString() {
        return "Person [id=" + id + ", name=" + name + "]";
    }
    
}
