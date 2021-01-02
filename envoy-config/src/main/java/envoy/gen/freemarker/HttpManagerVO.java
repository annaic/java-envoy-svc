package envoy.gen.freemarker;

public class HttpManagerVO {
    private String name;
    private String typed_config;
    private String stat_prefix;
    private boolean active;
    private VHostVO[] vHosts = {};
    private ServerTlsVO serverTls;

    public ServerTlsVO getServerTls() {
        return serverTls;
    }

    public void setServerTls(ServerTlsVO serverTls) {
        this.serverTls = serverTls;
    }

    public String getStat_prefix() {
        return stat_prefix;
    }

    public void setStat_prefix(String stat_prefix) {
        this.stat_prefix = stat_prefix;
    }

    public VHostVO[] getvHosts() {
        return vHosts;
    }

    public void setvHosts(VHostVO[] vHosts) {
        this.vHosts = vHosts;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }


}
