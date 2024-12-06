package ua.ithillel.gof.decorator.hnotifier;


import ua.ithillel.gof.decorator.notifier.Notifier;

public class SMSNotifier extends NotifierDecorator {
    private String phoneNumber;

    public SMSNotifier(Notifier notifier, String phoneNumber) {
        super(notifier);
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void doNotify(String message) {
        System.out.println("Sending SMS: " + message + " to " + phoneNumber);

        notifier.doNotify(message);
    }
}
