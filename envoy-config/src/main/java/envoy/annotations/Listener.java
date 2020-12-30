package envoy.annotations;

import envoy.annotations.filter.listener.ListenerFilter;
import envoy.annotations.filter.network.NetworkFilters;

public @interface Listener {
    Address address();
    NetworkFilters netfilter();
    ListenerFilter[] listener_filters() default {};
}
