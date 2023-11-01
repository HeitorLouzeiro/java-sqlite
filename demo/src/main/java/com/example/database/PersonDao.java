package com.example.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

// DAO: Data Access Object
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
}
