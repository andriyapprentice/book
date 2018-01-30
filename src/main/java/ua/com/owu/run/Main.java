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
        System.out.println("1-додати книгу,2-Перегляд списка книг, 3-Перегляд списка книг по автору, 4-Модифікація автора, 5-Видалення книги");
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
                break;
            }

            case 2: {

                List<Book> books = manager.createQuery("from Book r", Book.class).getResultList();
                for (Book b : books) {

                    System.out.println(b);
                    break;
                }

            }

            case 3: {
                Scanner scanner1 = new Scanner(System.in);
                String name11 = scanner.next();


                List<Author> authors = manager.createQuery("from Author r where r.name=:name11", Author.class)
                        .setParameter("name11", name11)
                        .getResultList();

//               Book book=manager.find(Book.class,authors);

                for (Author a : authors) {
                    System.out.println(a);
                }
                break;
            }
            case 4: {

                System.out.println("введіть імя для заміни автора");
                Scanner scanner1 = new Scanner(System.in);
                String name7 = scanner.next();

                List<Author> authors = manager.createQuery("from Author a where a.name=:name").setParameter("name", name7).getResultList();


                for (Author a : authors) {
                    System.out.println(a.getId());
                    Author authors1 = manager.find(Author.class, a.getId());
                    authors1.setName("zminaa");
                }


                break;
            }

            case 5: {


//                Scanner scanner1 = new Scanner(System.in);
//                String name = scanner.next();

                System.out.println("Видалення книги");


                Book book = manager.find(Book.class, 2);
                manager.remove(book);

                break;
            }


            default:
                return;
        }


        manager.getTransaction().commit();
        manager.close();
        factory.close();

    }
}
