package ua.ithillel.gof.chain;

public abstract class MessageHanlder {
    protected MessageHanlder next;

    public void setNext(MessageHanlder next) {
        this.next = next;
    }

    public abstract void handle(Message message);
}
