package envoy.annotations.filter.listener;

public enum LFTypeNames {
    ORIG_DST("envoy.filters.listener.original_dst"),
    TLS_INSPECT("envoy.filters.listener.tls_inspector");

    private String value;

    LFTypeNames(String s) {
        this.value = s;
    }

    public String getValue() {
        return value;
    }
}
