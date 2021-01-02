package envoy.gen.freemarker;


public class AdminVO {
    private String service;
    private AddressVO address;
    private String access_log_path;

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public AddressVO getAddress() {
        return address;
    }

    public void setAddress(AddressVO address) {
        this.address = address;
    }

    public String getAccess_log_path() {
        return access_log_path;
    }

    public void setAccess_log_path(String access_log_path) {
        this.access_log_path = access_log_path;
    }
}
