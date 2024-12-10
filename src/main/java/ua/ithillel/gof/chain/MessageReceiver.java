package ua.ithillel.gof.chain;

public class MessageReceiver {
    private MessageHanlder chain;

    public MessageReceiver(MessageHanlder chain) {
        this.chain = chain;
    }

    public void receiveMessage(Message message) {
        chain.handle(message);

        System.out.println("Message received: " + message);


        System.out.println("Notifuy email");
        System.out.println("Notifuy WahtsApp");
        System.out.println("Notifuy SMS");
    }
}
