package envoy.gen.freemarker;

public class ClusterVO {
    private String name;
    private double timeout_s;
    private String serviceDiscovery;
    private String lbPolicy;
    private AddressVO address;
    private CircuitBreakerVO circuitBreaker;

    public CircuitBreakerVO getCircuitBreaker() {
        return circuitBreaker;
    }

    public void setCircuitBreaker(CircuitBreakerVO circuitBreaker) {
        this.circuitBreaker = circuitBreaker;
    }

    public boolean iscb() {
        return iscb;
    }

    public void setiscb(boolean iscb) {
        this.iscb = iscb;
    }

    private boolean iscb;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTimeout_s() {
        return timeout_s;
    }

    public void setTimeout_s(double timeout_s) {
        this.timeout_s = timeout_s;
    }

    public String getServiceDiscovery() {
        return serviceDiscovery;
    }

    public void setServiceDiscovery(String serviceDiscovery) {
        this.serviceDiscovery = serviceDiscovery;
    }

    public String getLbPolicy() {
        return lbPolicy;
    }

    public void setLbPolicy(String lbPolicy) {
        this.lbPolicy = lbPolicy;
    }

    public AddressVO getAddress() {
        return address;
    }

    public void setAddress(AddressVO addressVO) {
        this.address = addressVO;
    }





}
