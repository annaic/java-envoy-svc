package envoy.gen.freemarker.mapper;

import envoy.annotations.CircuitBreaker;
import envoy.annotations.Cluster;
import envoy.gen.freemarker.AddressVO;
import envoy.gen.freemarker.CircuitBreakerVO;
import envoy.gen.freemarker.ClusterVO;

public class AnnotationToVO {

    public static ClusterVO mapCluster(Cluster cluster, String name){
        ClusterVO cvo = new ClusterVO();
        cvo.setName(name);
        cvo.setTimeout_s(cluster.timeout_s());
        cvo.setLbPolicy(cluster.lbtype().name());
        cvo.setServiceDiscovery(cluster.discovery().name());
        AddressVO addressVO = new AddressVO();
        addressVO.setHost(cluster.address().host());
        addressVO.setPort(cluster.address().port());
        cvo.setAddress(addressVO);
        CircuitBreaker circuitbreaker = cluster.circuitbreaker();
        cvo.setiscb(circuitbreaker.active());
        if(circuitbreaker.active()){
            CircuitBreakerVO circuitBreakerVO = new CircuitBreakerVO();
            circuitBreakerVO.setMaxConnections(circuitbreaker.maxconnections());
            circuitBreakerVO.setMaxPendingRequests(circuitbreaker.queuedepth());
            cvo.setCircuitBreaker(circuitBreakerVO);
        }


        return cvo;
    }
}
