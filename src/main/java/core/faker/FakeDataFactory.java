package core.faker;

import com.github.javafaker.Faker;
import core.enums.WordTypes;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.text.CaseUtils;

import java.util.Random;

public class FakeDataFactory {
    public static Faker fakeData = new Faker();

    public String randomString(int size, WordTypes wordTypes) {
        switch (wordTypes) {
            case CAPITAL_CASE:
                return RandomStringUtils.randomAlphabetic(size).toUpperCase();
            case SMALL_CASE:
                return RandomStringUtils.randomAlphabetic(size).toLowerCase();
            case CAMEL_CASE:
                return CaseUtils.toCamelCase(RandomStringUtils.randomAlphabetic(size), true);
            case MIX_CASE:
            default:
                return RandomStringUtils.randomAlphabetic(size);
        }
    }

    public String randomInteger(int size) {
        return RandomStringUtils.randomNumeric(size);
    }

    public String randomStringWithSpace(int wordCount, int wordLength, WordTypes wordTypes) {
        Random randomLength = new Random();
        StringBuilder wordWithSpace = new StringBuilder();
        for (int i = 0; i < wordCount; i++) {
            wordWithSpace.append(RandomStringUtils.randomAlphabetic(randomLength.nextInt(wordLength - 1) + 1)).append(" ");
        }
        switch (wordTypes) {
            case CAPITAL_CASE:
                return wordWithSpace.toString().toUpperCase();
            case SMALL_CASE:
                return wordWithSpace.toString().toLowerCase();
            case MIX_CASE:
            default:
                return wordWithSpace.toString();
        }
    }

    public String randomAlphaNumericString(int size, WordTypes wordTypes) {
        switch (wordTypes) {
            case CAPITAL_CASE:
                return RandomStringUtils.randomAlphanumeric(size).toUpperCase();
            case SMALL_CASE:
                return RandomStringUtils.randomAlphanumeric(size).toLowerCase();
            case CAMEL_CASE:
                return CaseUtils.toCamelCase(RandomStringUtils.randomAlphanumeric(size), true);
            case MIX_CASE:
            default:
                return RandomStringUtils.randomAlphanumeric(size);
        }
    }

    public String randomAlphaNumericStringWithSpace(int wordCount, int wordLength, WordTypes wordTypes) {
        Random randomLength = new Random();
        StringBuilder wordWithSpace = new StringBuilder();
        for (int i = 0; i < wordCount; i++) {
            wordWithSpace.append(RandomStringUtils.randomAlphanumeric(randomLength.nextInt(wordLength - 1) + 1)).append(" ");
        }
        switch (wordTypes) {
            case CAPITAL_CASE:
                return wordWithSpace.toString().toUpperCase();
            case SMALL_CASE:
                return wordWithSpace.toString().toLowerCase();
            case MIX_CASE:
            default:
                return wordWithSpace.toString();
        }
    }

    public String randomStringWithSpecialCharacter(int size, WordTypes wordTypes) {
        switch (wordTypes) {
            case CAPITAL_CASE:
                return RandomStringUtils.randomPrint(size).toUpperCase();
            case SMALL_CASE:
                return RandomStringUtils.randomPrint(size).toLowerCase();
            case CAMEL_CASE:
                return CaseUtils.toCamelCase(RandomStringUtils.randomPrint(size), true);
            case MIX_CASE:
            default:
                return RandomStringUtils.randomPrint(size);
        }
    }

    public String randomStringWithSpecialCharacterAndSpace(int wordCount, int wordLength, WordTypes wordTypes) {
        Random randomLength = new Random();
        StringBuilder wordWithSpace = new StringBuilder();
        for (int i = 0; i < wordCount; i++) {
            wordWithSpace.append(RandomStringUtils.randomPrint(randomLength.nextInt(wordLength - 1) + 1)).append(" ");
        }
        switch (wordTypes) {
            case CAPITAL_CASE:
                return wordWithSpace.toString().toUpperCase();
            case SMALL_CASE:
                return wordWithSpace.toString().toLowerCase();
            case MIX_CASE:
            default:
                return wordWithSpace.toString();
        }
    }
}