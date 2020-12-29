package envoy.annotations;

public @interface Listener {
    Address address();
    NetworkFilter netfilter();
}
