package envoy.gen;
import envoy.gen.freemarker.AddressVO;
import envoy.gen.freemarker.ClusterVO;
import freemarker.template.*;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException, TemplateException {
        /* ------------------------------------------------------------------------ */
        /* You should do this ONLY ONCE in the whole application life-cycle:        */

        /* Create and adjust the configuration singleton */
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);
        cfg.setNumberFormat("computer");
        cfg.setClassForTemplateLoading(Main.class, "templates");
        //cfg.setDirectoryForTemplateLoading(new File("/where/you/store/templates"));

        // Recommended settings for new projects:
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);
        cfg.setFallbackOnNullLoopVariable(false);
        Template temp = cfg.getTemplate("cluster.yaml.ftl");
        Map root = new HashMap();
        ClusterVO clusterVO = new ClusterVO();
        clusterVO.setName("httpbin_service");
        clusterVO.setTimeout_s(0.5);
        clusterVO.setLbPolicy("ROUND_ROBIN");
        clusterVO.setServiceDiscovery("LOGICAL_DNS");
        AddressVO addressVO = new AddressVO();
        addressVO.setHost("127.0.0.1");
        addressVO.setPort(8000);
        clusterVO.setAddress(addressVO);
        root.put("cluster", clusterVO);
        /* Merge data-model with template */
        Writer out = new OutputStreamWriter(System.out);
        temp.process(root, out);
    }
}
