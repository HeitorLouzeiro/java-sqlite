package com.example.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class PersonDao {
    private Connection connetion;
    public PersonDao() {
        connetion = Database.getInstance().getConnection();

    }

    public void insert(Person person) throws SQLException{
        Statement statement = connetion.createStatement();

        statement.executeUpdate("insert into person values(" + person.getId() + ", '" + person.getName() + "')");

        statement.close();
    }
}
