package studia.paulinanowak.petsdiary.services;

import studia.paulinanowak.petsdiary.model.Announcement;

import java.util.List;

public interface AnnouncementService {
    List<Announcement> findByUser(String username);
}
