package com.zama.bookstore.bookrepository;

import com.zama.bookstore.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepository extends JpaRepository<Book,Long> {

}
