package envoy.gen.freemarker;

import envoy.annotations.TLS;

public class ServerTlsVO {
    private boolean active;
    private String name;
    private String typed_config;
    private String cert_path;
    private String server_validation_path;
    private String min_tls;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTyped_config() {
        return typed_config;
    }

    public void setTyped_config(String typed_config) {
        this.typed_config = typed_config;
    }

    public String getCert_path() {
        return cert_path;
    }

    public void setCert_path(String cert_path) {
        this.cert_path = cert_path;
    }

    public String getServer_validation_path() {
        return server_validation_path;
    }

    public void setServer_validation_path(String server_validation_path) {
        this.server_validation_path = server_validation_path;
    }

    public String getMin_tls() {
        return min_tls;
    }

    public void setMin_tls(String min_tls) {
        this.min_tls = min_tls;
    }
}
