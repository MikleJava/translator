package translator;

import org.junit.Assert;

import java.io.IOException;

public class YandexTranslatorTest {
    @org.junit.Test
    public void main() throws IOException {
        String[] text = {"hi"};
        YandexTranslator.main(text);
    }

    @org.junit.Test
    public void detectRuLanguage() {
        String ruText = "привет";
        Assert.assertEquals("ru", YandexTranslator.detectLanguage(ruText));
    }

    @org.junit.Test
    public void detectEnLanguage() {
        String enText = "hello";
        Assert.assertEquals("en", YandexTranslator.detectLanguage(enText));
    }

    @org.junit.Test
    public void detectUnknownLanguage() {
        String ruText = " hello /][[;. 213313 привет hello";
        Assert.assertEquals("ru", YandexTranslator.detectLanguage(ruText));
    }

    @org.junit.Test
    public void translateFromRusToEng() throws IOException {
        String inputText = "привет";
        String outputText = "hi";
        String lang = YandexTranslator.detectLanguage(outputText);
        Assert.assertEquals(YandexTranslator.translate(lang, inputText), outputText);
    }

    @org.junit.Test
    public void translateFromEngToRus() throws IOException {
        String inputText = "hi";
        String outputText = "привет";
        String lang = YandexTranslator.detectLanguage(outputText);
        Assert.assertEquals(YandexTranslator.translate(lang, inputText), outputText);
    }

    @org.junit.Test
    public void translateFromUnknownToRus() throws IOException {
        String inputText = "..p[;l, как дела? hello']";
        String outputText = "p ..[; l, how you doing? hello']";
        String lang = YandexTranslator.detectLanguage(outputText);
        Assert.assertEquals(YandexTranslator.translate(lang, inputText), outputText);
    }

    @org.junit.Test(expected = IOException.class)
    public void translateIncorrectSymbols() throws IOException {
        String inputText = "123456";
        String outputText = "Incorrect symbols";
        String lang = YandexTranslator.detectLanguage(inputText);
        Assert.assertEquals(YandexTranslator.translate(lang, inputText), outputText);
    }
}