package studia.paulinanowak.petsdiary.services.springdatajpa;

import org.springframework.stereotype.Service;
import studia.paulinanowak.petsdiary.model.Notice;
import studia.paulinanowak.petsdiary.repositories.AnnouncementRepository;
import studia.paulinanowak.petsdiary.services.NoticeService;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoticeSDJpaService implements NoticeService {
    private final AnnouncementRepository announcementRepository;

    public NoticeSDJpaService(AnnouncementRepository announcementRepository) {
        this.announcementRepository = announcementRepository;
    }

    @Override
    public List<Notice> findByUser(String username) {
        List<Notice> notices = new ArrayList<>();
        announcementRepository.findByUsername(username).forEach(notices::add);
        return notices;
    }
}
