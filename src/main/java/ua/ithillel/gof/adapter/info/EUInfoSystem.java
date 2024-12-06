package ua.ithillel.gof.adapter.info;

public class EUInfoSystem implements InfoSystem {
    @Override
    public Information getInformation(String fullName) {
        Information information = new Information("Infor for " + fullName);
        return information;
    }
}
