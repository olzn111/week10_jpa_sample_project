package com.webservice.sample.service;

import com.webservice.sample.dto.BookRequest;
import com.webservice.sample.dto.BookResponse;
import com.webservice.sample.entity.Book;
import com.webservice.sample.repository.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService {
    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    /* Q7. save()를 호출하면 새로운 데이터 등록과 기존 데이터 수정이 어떻게 처리되는가?
     * 전달받은 엔티티의 @Id 값이 null이면 새로운 엔티티로 인식하여 INSERT 쿼리를 실행하고,
     * @Id 값이 이미 존재하면 DB 조회를 거쳐 기존 데이터로 인식하여 UPDATE 쿼리를 실행한다. */

    public BookResponse create(BookRequest request) {
        Book saved = repository.save(new Book(request)); // INSERT
        return BookResponse.from(saved);
    }

    public List<BookResponse> findAll() {
        return repository.findAll().stream().map(BookResponse::from).toList();
    }

    public BookResponse findById(Long id) {
        return BookResponse.from(findEntity(id));
    }
    /* Q8. @Transactional과 Dirty Checking은 어떤 관계가 있는가?
     @Transactional이 적용된 메서드 내에서 조회된 엔티티는 영속성을 띠고
     메서드가 종료될 떄 최초 상태와 비교하여 변경 사항을 감지하고,
     repository.save()를 호출하지 않아도 자동으로 데이터베이스에 업데이트를 반영한다.*/

    @Transactional
    public BookResponse update(Long id, BookRequest request) {
        Book book = findEntity(id);
        book.update(request);
        book.update(request); // Week 09: explicitly save (Week 10 changes this to Dirty Checking).
        return BookResponse.from(book);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found: " + id);
        }
        repository.deleteById(id);
    }

    private Book findEntity(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Book not found: " + id));
    }

    public List<BookResponse> findByCategory(String category) {
        return repository.findByCategory(category).stream()
                .map(BookResponse::from)
                .toList();

    }

    public List<BookResponse> findByPriceBetween(Integer minPrice, Integer maxPrice) {
        return repository.findByPriceBetween(minPrice, maxPrice).stream()
                .map(BookResponse::from)
                .toList();
    }

    public List<BookResponse> findByAuthorOrderByPriceDesc(String author) {
        return repository.findByAuthorOrderByPriceDesc(author).stream()
                .map(BookResponse::from)
                .toList();
    }
}
