package ua.ithillel.gof.adapter.usainfo;


import ua.ithillel.gof.adapter.info.Information;

public class USADefaultInfoSystem implements USAInfoSystem {
    @Override
    public Information getInfo(String firstName, String lastName) {
        return new Information("Info for " + firstName + " " + lastName);
    }
}
