package ke.co.safaricom.books;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class BookController {

    private BookRepository repo;

    @GetMapping("/books")
    public List<Book> getAllBooks(){
        return repo.findAll();
    }

    @GetMapping("/books/{id}")
    public Book getBook(@PathVariable("id") Long id){
        return repo.getReferenceById(id);
    }

    @PostMapping("/books")
    public Book createBook(@RequestBody Book postBook){
        return repo.save(postBook);
    }

    @PutMapping("/books/{id}")
    public Book updateBook(@PathVariable("id") Long id){
        Book book = repo.getReferenceById(id);
        book.setTitle(book.getTitle());
        book.setAuthor(book.getTitle());
        book.setDescription(book.getDescription());

        final Book updatedBook = repo.save(book);
        return updatedBook;
    }

}
