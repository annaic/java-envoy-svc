package envoy.gen.freemarker;


public class ClientTlsVO {

    private String sni;
    private String min_tls;
    private String client_cert_path;
    private String client_validation_path;
    private boolean allow_renegotiation;

    public String getSni() {
        return sni;
    }

    public void setSni(String sni) {
        this.sni = sni;
    }

    public String getMin_tls() {
        return min_tls;
    }

    public void setMin_tls(String min_tls) {
        this.min_tls = min_tls;
    }

    public String getClient_cert_path() {
        return client_cert_path;
    }

    public void setClient_cert_path(String client_cert_path) {
        this.client_cert_path = client_cert_path;
    }

    public String getClient_validation_path() {
        return client_validation_path;
    }

    public void setClient_validation_path(String client_validation_path) {
        this.client_validation_path = client_validation_path;
    }

    public boolean isAllow_renegotiation() {
        return allow_renegotiation;
    }

    public void setAllow_renegotiation(boolean allow_renegotiation) {
        this.allow_renegotiation = allow_renegotiation;
    }
}
