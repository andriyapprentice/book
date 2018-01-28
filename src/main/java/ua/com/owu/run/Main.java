package ua.com.owu.run;

import ua.com.owu.entity.Author;
import ua.com.owu.entity.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.text.html.parser.Entity;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("owu");
        EntityManager manager = factory.createEntityManager();

        manager.getTransaction().begin();
//        Book book =new Book("asasas", "aaa", 1);
//        manager.persist(book);
//     manager.persist(new Book("romeo and juleta","drama",1595));
//     manager.persist(new Book("white fang","adventure",1906));
//Author author =new Author("arthur", "conan");
//                    manager.persist(author);
//        List<Author> authorList = manager.createQuery("from Author a", Author.class)
//                .getResultList();
//        for (Author car : authorList) {
//        author.setBook(book);
//        }


        Scanner scanner = new Scanner(System.in);
        System.out.println("1-додати книгу,2-");
        int key = scanner.nextInt();

        switch (key) {
            case 1: {
        System.out.println("Введіть назву книги, опис, рік");
        Scanner scanner1 = new Scanner(System.in);
        String name = scanner.next();
        String descr = scanner.next();
        int yearB = scanner.nextInt();

        Book book = new Book(name, descr, yearB);
        manager.persist(book);

        System.out.println("Введіть атора книги: імя, прізвище");
        String par = "";
        while (!par.equals("no")) {

            String nameA = scanner.next();
            String surnameA = scanner.next();
            Author author = new Author(nameA, surnameA);
            author.setBook(book);
            manager.persist(author);

//                    List<Author> authors = new ArrayList<>();
//                    authors.add(author);

            System.out.println("додати ще автора? no-for exit");
            String p = scanner.next();
            if (p.equals("no")) {
                par = "no";
            }
        }




//


                break;
            }
            default:
                return;
//
        }

//        List<Book> books=  manager.createQuery("from Book r", Book.class).getResultList();
        List<Book> books = manager.createQuery("from Book r", Book.class).getResultList();
        for (Book b:books){

            System.out.println(b);
        }

        List<Author> authors=  manager.createQuery("from Author r", Author.class).getResultList();

        for (Author a :authors){

            System.out.println(a);
        }

        manager.getTransaction().commit();
        manager.close();
        factory.close();

}
}
