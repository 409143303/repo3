package readinglist;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReadingListRepostitory extends JpaRepository<Book, Long> {
	
	List<Book> findByReader(String reader);
	
}
