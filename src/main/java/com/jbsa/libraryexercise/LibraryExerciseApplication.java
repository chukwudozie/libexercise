package com.jbsa.libraryexercise;

import com.jbsa.libraryexercise.repositories.BookRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LibraryExerciseApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryExerciseApplication.class, args);
    }

    @Bean
    public CommandLineRunner createBooks(BookRepo repo) {
        return args -> {
            repo.save(new Book("BOOK-GRUFF472", "The Gruffalo", "A mouse taking a walk in the woods"));
            repo.save(new Book("BOOK-POOH222", "Winnie The Pooh", "In this first volume we meet all the friends from the Hundred Acre Wood."));
            repo.save(new Book("BOOK-WILL987", "The Wind In The Willows", "With the arrival of spring and fine weather outside,"+
                    "the good-natured Mole loses patience with spring cleaning. He flees his underground home, emerging to take in the air and ends up at the river, which he has " +
                   "never seen before. Here he meets Rat (a water vole), who at this time of year spends all his days in, on and close by the river. Rat takes Mole for a ride"));
        };
    }

}
