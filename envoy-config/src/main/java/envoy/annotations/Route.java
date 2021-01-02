package envoy.annotations;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.SOURCE)
@Repeatable(Routes.class)
public @interface Route {
    String prefix_match() default "/";
    String prefix_rewrite() default "";
    String header_match_name() default "";
    String header_match_value() default "";
    String cluster_name();
}

@Target({ElementType.FIELD})
@interface Routes{
    Route[] value();
}
