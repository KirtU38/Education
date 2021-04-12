package ru.beloshitsky.telegrambot.parsers;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component
public class AvitoTagsParser {

    public Elements selectPages(Document html) {
        return html.select("span[data-marker~=page[(]\\d+[)]]");
    }

    public Elements selectPrices(Document html) {
        Elements elementsInYourCity = html.select("div[data-marker=catalog-serp]");
        return elementsInYourCity.select("span[class~=price-text-.+]");
    }
}
