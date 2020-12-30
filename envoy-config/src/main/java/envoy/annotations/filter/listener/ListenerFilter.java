package envoy.annotations.filter.listener;

import java.lang.annotation.*;

@Target({ElementType.TYPE,ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.SOURCE)
@Repeatable(ListenerFilters.class)
public @interface ListenerFilter {
    LFTypeNames value();
    String typed_config() default "";
}

@Target({ElementType.TYPE,ElementType.METHOD,ElementType.FIELD})
@interface ListenerFilters{
    ListenerFilter[] value();
}
