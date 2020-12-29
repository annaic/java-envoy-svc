package envoy.gen.freemarker.mapper;

import envoy.annotations.CircuitBreaker;
import envoy.annotations.ClientTls;
import envoy.annotations.Cluster;
import envoy.gen.freemarker.AddressVO;
import envoy.gen.freemarker.CircuitBreakerVO;
import envoy.gen.freemarker.ClientTlsVO;
import envoy.gen.freemarker.ClusterVO;
import org.checkerframework.checker.units.qual.C;

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
        ClientTls tls = cluster.tls();
        if(tls.apply()){
            cvo.setIsClientTlsEnabled(true);
            ClientTlsVO tlsVO = new ClientTlsVO();
            tlsVO.setMin_tls(tls.min_tls().name());
            tlsVO.setAllow_renegotiation(tls.allow_renegotiation());
            if("".equals(tls.sni())){
                tlsVO.setSni(cluster.address().host());
            }else{
                tlsVO.setSni(tls.sni());
            }
            tlsVO.setClient_validation_path(tls.client_validation_path());
            tlsVO.setClient_cert_path(tls.client_cert_path());
            cvo.setClientTls(tlsVO);
        }


        return cvo;
    }
}
