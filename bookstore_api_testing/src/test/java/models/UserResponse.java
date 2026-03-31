package models;

import java.util.List;

public class UserResponse {

    private String userId;
    private String username;
    private List<Book> books;

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public List<Book> getBooks() {
        return books;
    }

    public static class Book {
        private String isbn;
        private String title;

        public String getIsbn() {
            return isbn;
        }

        public String getTitle() {
            return title;
        }
    }
}