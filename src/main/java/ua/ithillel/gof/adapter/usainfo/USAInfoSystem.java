package ua.ithillel.gof.adapter.usainfo;


import ua.ithillel.gof.adapter.info.Information;

public interface USAInfoSystem {
    Information getInfo(String firstName, String lastName);
}
