package ke.co.safaricom.books;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class BookController {

    private final BookRepository bookRepository;
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/books")
    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    @GetMapping("/books/{id}")
    public Optional<Book> getBook(@PathVariable("id") Long id){
        return bookRepository.findById(id);
    }

    @PostMapping("/books")
    public Book createBook(@RequestBody Book postBook){
        return bookRepository.save(postBook);
    }

    @PutMapping("/books/{id}")
    public Book updateBook(@PathVariable("id") Long id,@RequestBody Book uBook){
        var bookToUpdate = bookRepository.getReferenceById(id);
        bookToUpdate.setTitle(uBook.getTitle());
        bookToUpdate.setAuthor(uBook.getAuthor());
        bookToUpdate.setDescription(uBook.getDescription());

        final Book updatedBook = bookRepository.save(bookToUpdate);
        return updatedBook;
    }

    @DeleteMapping("/books/{id}")
    public void deleteBook(@PathVariable("id") Long id){
        bookRepository.deleteById(id);
    }

}
