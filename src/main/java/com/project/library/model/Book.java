    package com.project.library.model;


    import jakarta.persistence.*;
    import jdk.jfr.Enabled;
    import lombok.Data;

    @Entity
    @Data
    public class Book {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Integer id;
        String bookName;
        String bookAuthor;
        boolean bookAvailable;
        Integer bookQuantity;
        Integer bookPrice;
        String bookCategory;
        @Lob
        @Column(columnDefinition = "LONGBLOB")
        byte[] bookImage;
        String bookImageType;
        String bookImageName;

        public Book() {
        }

        @Override
        public String toString() {
            return "Book{" +
                    "id=" + id +
                    ", bookName='" + bookName + '\'' +
                    ", bookAuthor='" + bookAuthor + '\'' +
                    ", bookAvailable=" + bookAvailable +
                    ", bookQuantity=" + bookQuantity +
                    ", bookPrice=" + bookPrice +
                    ", bookCategory='" + bookCategory + '\'' +
                    '}';
        }
    }
