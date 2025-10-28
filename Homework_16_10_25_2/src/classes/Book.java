package classes;

public class Book {
    private String title;
    private String author;
    private int year;
    private String publisher;
    private String genre;
    private int pages;

    public Book() {
        this.title = "";
        this.author = "";
        this.year = 0;
        this.publisher = "";
        this.genre = "";
        this.pages = 0;
    }

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.year = 0;
        this.publisher = "";
        this.genre = "";
        this.pages = 0;
    }

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.publisher = "";
        this.genre = "";
        this.pages = 0;
    }

    public Book(String title, String author, int year, String publisher, String genre, int pages) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.publisher = publisher;
        this.genre = genre;
        this.pages = pages;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "Book{title='" + title + "', author='" + author +
                "', year=" + year + ", publisher='" + publisher +
                "', genre='" + genre + "', pages=" + pages + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Book book = (Book) obj;
        return year == book.year &&
                pages == book.pages &&
                title.equals(book.title) &&
                author.equals(book.author) &&
                publisher.equals(book.publisher) &&
                genre.equals(book.genre);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(title, author, year, publisher, genre, pages);
    }

    public void inputData(String title, String author, int year, String publisher, String genre, int pages) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.publisher = publisher;
        this.genre = genre;
        this.pages = pages;
    }

    public void inputData(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public void displayData() {
        System.out.println(this.toString());
    }

    public void displayData(boolean brief) {
        if (brief) {
            System.out.println("'" + title + "' by " + author + " (" + year + ")");
        } else {
            displayData();
        }
    }

    public void displayData(String format) {
        if ("detailed".equalsIgnoreCase(format)) {
            System.out.println("Book Details:");
            System.out.println("  Title: " + title);
            System.out.println("  Author: " + author);
            System.out.println("  Year: " + year);
            System.out.println("  Publisher: " + publisher);
            System.out.println("  Genre: " + genre);
            System.out.println("  Pages: " + pages);
        } else {
            displayData();
        }
    }
}
