package envoy.annotations;

public enum LBPolicy {
    ROUND_ROBIN, LEAST_REQUEST,RING_HASH, RANDOM, MAGLEV, CLUSTER_PROVIDED
}
