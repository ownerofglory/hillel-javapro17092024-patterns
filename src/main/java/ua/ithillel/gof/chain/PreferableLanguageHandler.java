package ua.ithillel.gof.chain;

import java.util.List;

public class PreferableLanguageHandler extends MessageHanlder {
    private List<String> languages;

    public PreferableLanguageHandler(List<String> languages) {
        this.languages = languages;
    }

    @Override
    public void handle(Message message) {
        if (!languages.contains("")) {
            System.out.println("Language is not set as preferable");
            return;
        }

        if (next != null)
            next.handle(message);
    }
}
