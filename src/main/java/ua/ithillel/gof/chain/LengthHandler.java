package ua.ithillel.gof.chain;


public class LengthHandler extends MessageHanlder {
    @Override
    public void handle(Message message) {
        if (message.getContent().length() > 12) {
            System.out.println("Content length exceeded max length");
            return;
        }

        if (next != null)
            next.handle(message);
    }
}
