package envoy.annotations;

public @interface AdminListener {
    Address address();
    String access_log_path() default "/tmp/admin_access.log";
}
