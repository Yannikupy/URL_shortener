package ru.borisov.url_shortener;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRecordRepository extends CrudRepository<UrlRecord, Long> {
    UrlRecord findUrlRecordById(Long id);
    UrlRecord findUrlRecordByUrl(String url);
}
