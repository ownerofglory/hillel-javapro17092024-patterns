package ua.ithillel.gof.decorator.hnotifier;


import ua.ithillel.gof.decorator.notifier.Notifier;

public abstract class NotifierDecorator implements Notifier {
    protected Notifier notifier;

    public NotifierDecorator(Notifier notifier) {
        this.notifier = notifier;
    }
}
