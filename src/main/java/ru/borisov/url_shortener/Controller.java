package ru.borisov.url_shortener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/api")
public class Controller {
    UrlShortener urlShortener;


    @Autowired
    public Controller(UrlShortener urlShortener) {
        this.urlShortener = urlShortener;
    }

    @GetMapping("/shorten")
    public String shortenRequest(@RequestParam("url") String url) {
        urlShortener.shortenUrl(url);
        return "";
    }

    @GetMapping("/go/{shortUrl}")
    public RedirectView redirect(@PathVariable String shortUrl) {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(urlShortener.shortToFullUrl(shortUrl));
        return redirectView;
    }
}
