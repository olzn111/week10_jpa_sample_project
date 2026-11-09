package com.webservice.sample.entity;

import com.webservice.sample.dto.BookRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
/* Q1. @Entity, @Id, @GeneratedValue는 각각 어떤 역할을 하는가?
* @Entity는 이 클래스가 JPA를 관리하는 Entity임을 나타낸다. */

/* Q2. Entity 클래스와 데이터베이스 테이블은 어떻게 연결되는가?
* @Entity가 붙은 클래스는 기본적으로 클래스명과 매핑되는 테이블과 연결되고
* @Table(name=books)같이 직접 테이블명을 적어서 연결할 수 있다*/

@Entity
@Table(name = "books")
public class Book {
    /* Q1. @Entity, @Id, @GeneratedValue는 각각 어떤 역할을 하는가?
     * @Id는 이 필드가 DB 테이블의 primary Key임을 선언한다.
     *  @GeneratedValue는 프라이머리 키의 자동생성 설정을 하고 DENTITY는 DB를 이용해 pk를 자동으로 다음 번호를 생성해 부여해준다.  */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(nullable = false, length = 100)
    private String author;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false, length = 50)
    private String category;

    protected Book() {
    } // JPA uses the no-args constructor.

    public Book(BookRequest request) {
        this.title = request.title();
        this.author = request.author();
        this.price = request.price();
        this.category = request.category();
    }

    public void update(BookRequest request) {
        this.title = request.title();
        this.author = request.author();
        this.price = request.price();
        this.category = request.category();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Integer getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }
}
