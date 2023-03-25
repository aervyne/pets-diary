package studia.paulinanowak.petsdiary.repositories;

import org.springframework.data.repository.CrudRepository;
import studia.paulinanowak.petsdiary.model.Announcement;

import java.util.List;

public interface AnnouncementRepository extends CrudRepository<Announcement, Long> {
    List<Announcement> findByUsername(String username);
}
