package ua.ithillel.gof.factory;

public interface Server extends AutoCloseable {
    public void start() throws Exception;
}
