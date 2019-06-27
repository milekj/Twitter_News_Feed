package agh.tai.twitter_news_feed.service;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class HashtagParser {
    private String hashtag;
    private StringBuilder builder;
    private Scanner scanner;
    private boolean lastUppercase;
    private boolean currentUppercase;

    public HashtagParser(String hashtag) {
        this.hashtag = hashtag;
        builder = new StringBuilder();
        scanner = new Scanner(hashtag);
        scanner.useDelimiter("");
        lastUppercase = true;
    }

    public String parse() {
        if(alreadyParsed())
            return hashtag;
        return splitToWords();
    }

    private boolean alreadyParsed() {
        List<Character> chars = hashtag.chars()
                .mapToObj(c -> (char)c)
                .collect(Collectors.toList());
        return chars.stream()
                .noneMatch(Character::isUpperCase);
    }

    private String splitToWords() {
        while (scanner.hasNext()) {
            char current = scanner.next().charAt(0);
            currentUppercase = Character.isUpperCase(current);
            if (isStartOfNewWord())
                builder.append(" ");
            builder.append(current);
            lastUppercase = currentUppercase;
        }
        return builder.toString();
    }

    private boolean isStartOfNewWord() {
        return !lastUppercase && currentUppercase;
    }
}
