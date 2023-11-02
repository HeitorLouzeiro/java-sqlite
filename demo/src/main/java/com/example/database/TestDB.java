package com.example.database;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class TestDB {
    public static void main(String[] args) throws SQLException, IOException{
        boolean isRunning = true;

        Scanner scanner = new Scanner(System.in);

        PersonDao personDao = new PersonDao();

        while(true){
            System.out.println("\n\nEnter your choice: ");
            System.out.println("1. Insert a new person");
            System.out.println("2. List persons");
            System.out.println("3. Search person by id");
            System.out.println("4. Delete person by id");
            System.out.println("5. Exit");

            System.out.print("\tYour choice: ");
            int choice = scanner.nextInt();

            switch(choice){
                case 1:
                    System.out.println("Enter ID:");
                    int id = scanner.nextInt();

                    System.out.println("Enter person name: ");
                    String name = scanner.next();

                    Person person = new Person();
                    person.setId(id);
                    person.setName(name);
                    
                    personDao.insert(person);

                    break;
                case 2:
                    System.out.println("\nList of persons: ");
                    List<Person> persons = personDao.getAll();
                    for(Person personList : persons){
                        System.out.println("\t" + personList);
                    }

                    break;
                case 3:
                    System.out.println("Enter ID:");
                    int idSearch = scanner.nextInt();

                    Person personSearch = personDao.getById(idSearch);
                    
                    // caso der erro, usar o scanner.nextLine() para limpar o buffer
                    //in.nextLine();

                    if (personSearch ==null){
                        System.out.println("Person not found");
                    }else{
                        System.out.println("Data found: " + personSearch);
                        System.out.println("Do you want to update this person?");
                        System.out.println("1. Yes");
                        System.out.println("2. No");
                        System.out.print("\tYour choice: ");

                        int choiceUpdate = scanner.nextInt();

                        if (choiceUpdate == 1) {
                            System.out.println("Enter new name: ");
                            String newName = scanner.next();
                            personSearch.setName(newName);
                            personDao.update(personSearch);
                        }else{
                            System.out.println("Person not updated");
                        }
                    }
                    break;
                case 4:
                    System.out.println("Enter ID:");
                    int idDelete = scanner.nextInt();
                    personDao.delete(idDelete);
                    break;
                case 5:
                    isRunning = false;
                    System.out.println("Sistema Encerrado...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");
            }


        }
    }
}

