package solutions.egen.kiran.hazelcastexample.config;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.ClientNetworkConfig;
import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MaxSizeConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HazelcastConfiguration {
    @Bean
    public HazelcastInstance hazelcastInstance() {

        Config config = new Config();
        config.setInstanceName("hz-instance");
        config.getNetworkConfig().getJoin().getTcpIpConfig().setEnabled(true);
        config.getNetworkConfig().getJoin().getMulticastConfig().setEnabled(false);
        config.getNetworkConfig().getJoin().getTcpIpConfig().addMember("172.17.0.2:5701");
        return Hazelcast.getOrCreateHazelcastInstance(config);

        /*ClientConfig clientConfig = new ClientConfig();
        ClientNetworkConfig clientNetworkConfig = new ClientNetworkConfig();
        clientNetworkConfig.addAddress("172.17.0.2:5701");
        clientConfig.setNetworkConfig(clientNetworkConfig);
        HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);
        return client;*/
    }
}