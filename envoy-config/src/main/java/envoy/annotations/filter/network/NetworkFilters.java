package envoy.annotations.filter.network;

public @interface NetworkFilters {
    HttpManager httpmanager() default @HttpManager();
    TCPProxy[] tcp_filters() default {};
}
