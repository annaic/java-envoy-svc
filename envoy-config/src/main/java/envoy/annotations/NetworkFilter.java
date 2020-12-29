package envoy.annotations;

public @interface NetworkFilter {
    HttpManager httpmanager();
    ServerTls tls() default @ServerTls;
}
