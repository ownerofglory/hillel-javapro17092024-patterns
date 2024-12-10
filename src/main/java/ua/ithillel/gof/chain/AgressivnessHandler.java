package ua.ithillel.gof.chain;

public class AgressivnessHandler extends MessageHanlder {
    @Override
    public void handle(Message message) {
        if (message.getContent().contains("bad")) {
            System.out.println("Aggressive content");

            return;
        }

        if (next != null)
            next.handle(message);
    }
}
