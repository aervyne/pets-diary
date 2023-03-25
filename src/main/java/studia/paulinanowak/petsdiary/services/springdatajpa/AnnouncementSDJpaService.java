package studia.paulinanowak.petsdiary.services.springdatajpa;

import org.springframework.stereotype.Service;
import studia.paulinanowak.petsdiary.model.Announcement;
import studia.paulinanowak.petsdiary.repositories.AnnouncementRepository;
import studia.paulinanowak.petsdiary.services.AnnouncementService;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnnouncementSDJpaService implements AnnouncementService {
    private final AnnouncementRepository announcementRepository;

    public AnnouncementSDJpaService(AnnouncementRepository announcementRepository) {
        this.announcementRepository = announcementRepository;
    }

    @Override
    public List<Announcement> findByUser(String username) {
        List<Announcement> announcements = new ArrayList<>();
        announcementRepository.findByUsername(username).forEach(announcements::add);
        return announcements;
    }
}
