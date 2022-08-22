package com.example.cinemates.model.data;

/**
 * @author Antonio Di Nuzzo
 * Created 29/05/2022 at 10:14
 */
public class Review {
    private String author, content, created_at, id, updated_at, url;
    private Author author_details;

    public Review(String author, String content, String created_at, String id, String updated_at, String url, Author author_details) {
        this.author = author;
        this.content = content;
        this.created_at = created_at;
        this.id = id;
        this.updated_at = updated_at;
        this.url = url;
        this.author_details = author_details;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Author getAuthor_details() {
        return author_details;
    }

    public void setAuthor_details(Author author_details) {
        this.author_details = author_details;
    }

    @Override
    public String toString() {
        return "Review{" +
                "author='" + author + '\'' +
                ", content='" + content + '\'' +
                ", created_at='" + created_at + '\'' +
                ", id='" + id + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", url='" + url + '\'' +
                ", author_details=" + author_details +
                '}';
    }

    public class Author {
        private String name, username, avatar_path;
        private int rating;

        public Author(String name, String username, String avatar_path, int rating) {
            this.name = name;
            this.username = username;
            this.avatar_path = avatar_path;
            this.rating = rating;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getAvatar_path() {
            return avatar_path;
        }

        public void setAvatar_path(String avatar_path) {
            this.avatar_path = avatar_path;
        }

        public int getRating() {
            return rating;
        }

        public void setRating(int rating) {
            this.rating = rating;
        }

        @Override
        public String toString() {
            return "Author{" +
                    "name='" + name + '\'' +
                    ", username='" + username + '\'' +
                    ", avatar_path='" + avatar_path + '\'' +
                    ", rating=" + rating +
                    '}';
        }
    }
}
