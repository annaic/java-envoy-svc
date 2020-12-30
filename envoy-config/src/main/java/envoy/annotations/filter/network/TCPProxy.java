package envoy.annotations.filter.network;

public @interface TCPProxy {
    String name() default "envoy.filters.network.tcp_proxy";
    String typed_config() default
            "type.googleapis.com/envoy.config.filter.network.tcp_proxy.v2.TcpProxy";
    String cluster_name();
    String stat_prefix() default "tcp";
}
