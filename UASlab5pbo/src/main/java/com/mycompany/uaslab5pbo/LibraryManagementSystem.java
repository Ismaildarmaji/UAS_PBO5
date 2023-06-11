/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.uaslab5pbo;

/**
 *
 * @author Asus
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Kelas utama
public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library(); // Membuat objek Library

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            // Menampilkan menu
            System.out.println("=== Sistem Manajemen Perpustakaan ===");
            System.out.println("|         1. Tambah Buku            |");
            System.out.println("|         2. Daftar Buku            |");
            System.out.println("|         3. Cari Buku              |");
            System.out.println("|         4. Peminjaman Buku        |");
            System.out.println("|         5. Pengembalian Buku      |");
            System.out.println("|         0. Keluar                 |");
            System.out.println("=====================================");
            System.out.print("Masukkan Pilihan : ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    library.addBook();
                    break;
                case 2:
                    library.displayBooks();
                    break;
                case 3:
                    library.searchBook();
                    break;
                case 4:
                    library.borrowBook();
                    break;
                case 5:
                    library.returnBook();
                    break;
                case 0:
                    System.out.println("====== Terima kasih! ======");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                    break;
            }

            System.out.println();
        } while (choice != 0);

        scanner.close();
    }
}

// Kelas Buku
class Book {
    private String title;
    private String author;
    private boolean available;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.available = true;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

// Kelas Perpustakaan
class Library {
    private List<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public void addBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan judul buku: ");
        String title = scanner.nextLine();
        System.out.print("Masukkan penulis buku: ");
        String author = scanner.nextLine();

        Book book = new Book(title, author);
        books.add(book);

        System.out.println("Buku berhasil ditambahkan.");
    }

    public void displayBooks() {
        System.out.println("=== Daftar Buku ===");
        if (books.isEmpty()) {
            System.out.println("Tidak ada buku yang tersedia.");
        } else {
            for (Book book : books) {
                System.out.println("Judul: " + book.getTitle());
                System.out.println("Penulis: " + book.getAuthor());
                System.out.println("Status: " + (book.isAvailable() ? "Tersedia" : "Dipinjam"));
                System.out.println();
            }
        }
    }

    public void searchBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan judul atau penulis buku: ");
        String keyword = scanner.nextLine();

        boolean found = false;
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(keyword) || book.getAuthor().equalsIgnoreCase(keyword)) {
                System.out.println("Buku ditemukan:");
                System.out.println("Judul: " + book.getTitle());
                System.out.println("Penulis: " + book.getAuthor());
                System.out.println("Status: " + (book.isAvailable() ? "Tersedia" : "Dipinjam"));
                System.out.println();
                found = true;
            }
        }

        if (!found) {
            System.out.println("Buku tidak ditemukan.");
        }
    }

    public void borrowBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan judul buku yang ingin dipinjam: ");
        String title = scanner.nextLine();

        boolean found = false;
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title) && book.isAvailable()) {
                book.setAvailable(false);
                System.out.println("Peminjaman berhasil.");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Buku tidak tersedia atau tidak ditemukan.");
        }
    }

    public void returnBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan judul buku yang ingin dikembalikan: ");
        String title = scanner.nextLine();

        boolean found = false;
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title) && !book.isAvailable()) {
                book.setAvailable(true);
                System.out.println("Pengembalian berhasil.");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Buku tidak dapat dikembalikan atau tidak ditemukan.");
        }
    }
}
