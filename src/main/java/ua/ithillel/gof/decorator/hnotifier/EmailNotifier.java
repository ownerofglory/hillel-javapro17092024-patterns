package ua.ithillel.gof.decorator.hnotifier;


import ua.ithillel.gof.decorator.notifier.Notifier;

public class EmailNotifier extends NotifierDecorator {
    private String email;

    public EmailNotifier(Notifier notifier, String email) {
        super(notifier);
        this.email = email;
    }

    @Override
    public void doNotify(String message) {
        System.out.println("Sending email to " + email + " message: " + message);

        notifier.doNotify(message);
    }
}
