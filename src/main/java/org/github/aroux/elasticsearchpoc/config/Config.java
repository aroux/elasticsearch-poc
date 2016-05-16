package org.github.aroux.elasticsearchpoc.config;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
@EnableElasticsearchRepositories(basePackages = "org.github.aroux.elasticsearchpoc.repository",
        repositoryImplementationPostfix = "Impl")
class Config {

    @Bean
    public ElasticsearchTemplate elasticsearchTemplate() throws UnknownHostException {
        Client client = TransportClient.builder().build()
                .addTransportAddress(new InetSocketTransportAddress(
                        InetAddress.getByName("127.0.0.1"), 9300));

        return new ElasticsearchTemplate(client);
    }
}
