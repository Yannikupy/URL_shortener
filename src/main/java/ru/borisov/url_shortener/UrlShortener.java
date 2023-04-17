package ru.borisov.url_shortener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UrlShortener {

    private final static String BASE_62 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private UrlRecordRepository urlRecordRepository;

    @Autowired
    public UrlShortener(UrlRecordRepository urlRecordRepository) {
        this.urlRecordRepository = urlRecordRepository;
    }

    String shortToFullUrl(String shortUrl) {
        long id = 0L;
        int length = shortUrl.length();
        for (int i = 0; i < length; ++i) {
            id += (long) Math.pow(62, i) * BASE_62.indexOf(shortUrl.charAt(length - i - 1));
        }
        return urlRecordRepository.findUrlRecordById(id).getUrl();
    }

    String idToShortUrl(Long id) {
        StringBuilder shortUrl = new StringBuilder();
        do {
            shortUrl.insert(0, BASE_62.charAt((int) (id % 62)));
            id /= 62;
        } while (id > 0);
        return shortUrl.toString();
    }

    public void shortenUrl(String url) {
        UrlRecord urlRecord = new UrlRecord();
        urlRecord.setUrl(url);
        urlRecordRepository.save(urlRecord);
        System.out.println(idToShortUrl(urlRecord.getId()));
    }

}
