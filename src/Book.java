public class Book {
    private int id;
    private static int nextId = 1;
    private String title;
    private String author;
    private int year;


    public Book(String title, String author, int year) {
        this.id = nextId++;
        this.title = title;
        this.author = author;
        this.year = year;
    }


    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
