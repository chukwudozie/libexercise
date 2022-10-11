package com.jbsa.libraryexercise;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface BookRepo extends JpaRepository<Book, Long> {

    boolean existsByReference(String reference);

    @Query("SELECT b FROM Book b where b.reference = :reference")
    Book retrieveBook(@Param("reference") String reference);


}
