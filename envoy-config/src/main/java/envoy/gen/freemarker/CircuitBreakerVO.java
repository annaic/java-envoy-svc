package envoy.gen.freemarker;

public class CircuitBreakerVO {
    private int maxConnections;
    private int maxPendingRequests;

    public int getMaxConnections() {
        return maxConnections;
    }

    public void setMaxConnections(int maxConnections) {
        this.maxConnections = maxConnections;
    }

    public int getMaxPendingRequests() {
        return maxPendingRequests;
    }

    public void setMaxPendingRequests(int maxPendingRequests) {
        this.maxPendingRequests = maxPendingRequests;
    }
}
