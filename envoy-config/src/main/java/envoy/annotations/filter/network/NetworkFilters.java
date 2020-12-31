package envoy.annotations.filter.network;

import envoy.annotations.ServerTls;

public @interface NetworkFilters {
    HttpManager httpmanager() default @HttpManager();
    TCPProxy[] tcp_filters() default {};
    ServerTls tls() default @ServerTls;
}
