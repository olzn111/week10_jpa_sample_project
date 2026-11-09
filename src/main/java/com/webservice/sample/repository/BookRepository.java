package com.webservice.sample.repository;

import com.webservice.sample.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/* Q3. JpaRepository<Entity, Long>에서 두 타입은 각각 무엇을 의미하는가?
 * Entity는 클래스, Long은 해당 entity의 @id 필드 타입이다.*/

/*Q6. JpaRepository에서 기본 제공하는 메서드와 개발자가 직접 작성하는 Query Method의 차이는 무엇인가?
 * 기본 제공 메서드는 상속을 받아 쓰는 공통 CRUD메서드이고,
 * 개발자가 직접 작성하는 Query Method는 개발자가 메서드 이름의 규칙에 맞게 직접 정의할 떄 JPA가 해당하는 쿼리를 자동으로 생성한다. */
public interface BookRepository extends JpaRepository<Book, Long> {
    // Week 09: All basic CRUD methods are inherited from JpaRepository.

    /* Q5. findById()의 반환 타입이 Optional인 이유는 무엇인가?
     * DB에 해당 ID의 데이터가 존재하지 않을 경우에 null이 반환되어 발생할 수 문제를 방지하고
     * 서비스계층에서 안전하고 깔끔하게 예외처리를 유도하기 위함이다. */


    /* Q4. JpaRepository에서 기본 제공하는 CRUD 메서드를 5개 이상 찾아 메서드명과 역할을 주석으로 작성하세요.
     * 1. save(S entity) : 엔터티를 DB에 저장하거나 수정한다.
     * 2. findById(ID id) : 주어진 프라이머리키값으로 엔티티 단건을 조회한다.
     * 3. findAll() : 테이블의 모든 엔티티를 조회한다.
     * 4. deleteById(ID id) : 주어진 프라이머리키값에 해당하는 엔티티를 삭제한다.
     * 5. existById(ID id) : 해당 프라이머리카값을 가진 데이터의 존재 여부를 boolean으로 변환한다.*/

    List<Book> findByCategory(String category);

    List<Book> findByPriceBetween(Integer minPrice, Integer maxPrice);

    List<Book> findByAuthorOrderByPriceDesc(String author);
}
