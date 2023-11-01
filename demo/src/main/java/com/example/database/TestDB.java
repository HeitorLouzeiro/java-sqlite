package com.example.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

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

            // Delete data
            /* personDao.delete(2); */

            Person person3 = new Person();

            person3.setId(3);
            person3.setName("Carlos");

            personDao.update(person3);



            // Get all data
            List<Person> persons = personDao.getAll();
            
            for (Person p : persons) {
                System.out.println(p);
            }

            // mostrando dados pelo metodo da classe PersonDao com um metodo especifico
            /* PersonDao personDao = new PersonDao(); */
/*             Person person2 = personDao.getById(2);
            System.out.println(person2); */

            

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
