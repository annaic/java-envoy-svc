package envoy.annotations;

import envoy.annotations.filter.listener.ListenerFilter;
import envoy.annotations.filter.network.NetworkFilters;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.SOURCE)
public @interface Listener {
    Address address();
    NetworkFilters netfilter();
    ListenerFilter[] listener_filters() default {};
}
