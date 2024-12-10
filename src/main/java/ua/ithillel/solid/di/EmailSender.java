package ua.ithillel.solid.di;

public interface EmailSender {
    public void send(String to, String subject, String body);
}
