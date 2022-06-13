package Structural.Proxy;

interface BookService{
    Book getBook();
}

class BookServiceImpl implements BookService {

    @Override
    public Book getBook() {
        return new Book("Robin Hood", 20);
    }
}

class BookServiceLoggingProxy implements BookService {

    private BookServiceImpl bookService;

    @Override
    public Book getBook() {

        System.out.println("Book is fetching..."); //It is like @Before annotation at AOP.
                                                    //It logs before each invocation of getBook method.
        bookService = new BookServiceImpl();
        return bookService.getBook();
    }
}
class Book {
    String name;
    int pageCount;

    public Book(String name, int pageCount) {
        this.name = name;
        this.pageCount = pageCount;
    }
}
public class AopExample {

    public static void main(String[] args) {

        BookServiceLoggingProxy bookServiceLoggingProxy = new BookServiceLoggingProxy();
        final Book book = bookServiceLoggingProxy.getBook();
        System.out.println(book);

    }
}
