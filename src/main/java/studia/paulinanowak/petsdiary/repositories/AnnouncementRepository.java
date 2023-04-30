package studia.paulinanowak.petsdiary.repositories;

import org.springframework.data.repository.CrudRepository;
import studia.paulinanowak.petsdiary.model.Notice;

import java.util.List;

public interface AnnouncementRepository extends CrudRepository<Notice, Long> {
    List<Notice> findByUsername(String username);
}
