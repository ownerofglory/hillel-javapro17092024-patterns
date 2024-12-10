package ua.ithillel.solid.di;

class User {
    String email;
}

public class NotificationSystem {
    private EmailSender emailSender;

    public NotificationSystem(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void notifyUser(User user) {
        System.out.println("checking user");

        // ...


        //
        // notify via email
        emailSender.send(user.email, "regisrer", "Hello welcome");
    }
}
