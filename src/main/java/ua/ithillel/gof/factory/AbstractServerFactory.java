package ua.ithillel.gof.factory;

public class AbstractServerFactory {
    public ServerFactory createSingleThreadedServerFactory() {
        return new SingleThrededServerFactory();
    }

    public ServerFactory createMultiThreadedServerFactory() {
        return new MultiThreadedServerFactory();
    }
}
