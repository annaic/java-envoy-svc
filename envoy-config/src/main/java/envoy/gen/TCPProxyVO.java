package envoy.gen;

public class TCPProxyVO {
    private String name;
    private String typed_config;
    private String cluster_name;
    private String stat_prefix;
    private int destination_port;

    public int getDestination_port() {
        return destination_port;
    }

    public void setDestination_port(int destination_port) {
        this.destination_port = destination_port;
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

    public String getCluster_name() {
        return cluster_name;
    }

    public void setCluster_name(String cluster_name) {
        this.cluster_name = cluster_name;
    }

    public String getStat_prefix() {
        return stat_prefix;
    }

    public void setStat_prefix(String stat_prefix) {
        this.stat_prefix = stat_prefix;
    }
}
