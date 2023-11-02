package com.example.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

// DAO: Data Access Object
public class PersonDao {
    private Connection connetion;
    public PersonDao() throws IOException {
        connetion = Database.getInstance().getConnection();

    }

    public void insert(Person person) throws SQLException{
        // Usando PreparedStatement
        PreparedStatement statement = connetion.prepareStatement("insert into person values(?, ?)");
        
        statement.setInt(1, person.getId());
        statement.setString(2, person.getName());
        
        statement.executeUpdate();
        

        //statement.executeUpdate("insert into person values(" + person.getId() + ", '" + person.getName() + "')");

        statement.close();
    }

    public void delete(int id) throws SQLException{
        Statement statement = connetion.createStatement();

        statement.executeUpdate("delete from person where id = " + id);

        statement.close();
    }

    public List<Person> getAll() throws SQLException{
        List <Person> persons = new ArrayList<Person>();
        Statement statement = connetion.createStatement();

        ResultSet result = statement.executeQuery("select * from person");

        while (result.next()) {
            // read the result set
            Person person = new Person();
            person.setId(result.getInt("id"));
            person.setName(result.getString("name"));
            persons.add(person);
        }

        statement.close();
        return persons;

    }

    public Person getById(int id) throws SQLException{
        Statement statement = connetion.createStatement();

        ResultSet result = statement.executeQuery("select * from person where id = " + id);
        
        if (result.next()) {
            // read the result set
            Person person = new Person();
            person.setId(result.getInt("id"));
            person.setName(result.getString("name"));
            return person;
        }

        statement.close();
        
        return null;
    }

    public void update(Person person) throws SQLException{
        Statement statement = connetion.createStatement();

        /* UPDATE table SET column = value [, ...] [WHERE condition] */

        statement.executeUpdate("update person set name = '" + person.getName() + "' where id = " + person.getId());

        statement.close();
    }
}
