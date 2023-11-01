package com.example.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDB {
    public static void main(String[] args) throws IOException{
        
        Connection connection = null;
        try {
            connection = Database.getInstance().getConnection();
            Statement statement = connection.createStatement(); // set timeout to 30 sec.

            // create a database connection
            String sql = FileUltils.loadTextFile("demo/src/main/java/com/example/resource/description.sql");
            /* System.out.println(sql); */

            // Run the query
            statement.executeUpdate(sql);

            // Insert data
             Person person = new Person();
            person.setId(3);
            person.setName("Gean");

            // data access object
            PersonDao personDao = new PersonDao();
            personDao.insert(person);

            ResultSet rs = statement.executeQuery("select * from person");
            while (rs.next()) {
                // read the result set
                System.out.println("name = " + rs.getString("name"));
                System.out.println("id = " + rs.getInt("id"));
            }
        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
    }
}
