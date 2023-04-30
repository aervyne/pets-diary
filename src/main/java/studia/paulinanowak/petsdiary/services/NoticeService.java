package studia.paulinanowak.petsdiary.services;

import studia.paulinanowak.petsdiary.model.Notice;

import java.util.List;

public interface NoticeService {
    List<Notice> findByUser(String username);
}
