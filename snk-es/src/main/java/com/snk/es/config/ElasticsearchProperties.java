package com.snk.es.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

/**
 * @author :Cai.ChangJun
 * @version 1.0.0
 * @Description : es配置类
 * @Date : 2021/5/26
 */
@Configuration
@ConfigurationProperties(prefix = "es")
public class ElasticsearchProperties {
    @Value("${es.clusterNodes.list}")
    private String hosts;
    @Value("${es.clusterNodes.port}")
    private int port;
    private static String schema = "http";
    private static ArrayList<HttpHost> hostList = new ArrayList<>();
    /**连接超时时间*/
    private static int connectTimeOut = 1000;
    /**连接超时时间*/
    private static int socketTimeOut = 30000;
    /**获取连接超时时间*/
    private static int connectRequestTimeOut = 500;
    /**最大链接数*/
    private static int maxConnectNum = 100;
    /**最大路由连接数*/
    private static int maxConnectPerRoute = 100;

    @PostConstruct
    private void init() {
        String[] hostStrs = hosts.split(",");
        for (String hostStr : hostStrs) {
            hostList.add(new HttpHost(hostStr,port,schema));
        }
    }

    @Bean
    public RestHighLevelClient client() {
        RestClientBuilder builder = RestClient.builder(hostList.toArray(new HttpHost[0]));
        //异步httpclient连接延时配置
        builder.setRequestConfigCallback(requestConfigBuilder->{
           requestConfigBuilder.setConnectTimeout(connectTimeOut);
           requestConfigBuilder.setSocketTimeout(socketTimeOut);
           requestConfigBuilder.setConnectionRequestTimeout(connectRequestTimeOut);
           return requestConfigBuilder;
        });
        //异步httpclient连接数配置
        builder.setHttpClientConfigCallback(httpClientBuilder->{
            httpClientBuilder.setMaxConnPerRoute(maxConnectPerRoute);
            httpClientBuilder.setMaxConnTotal(maxConnectNum);
            return httpClientBuilder;
        });
        return new RestHighLevelClient(builder);
    }

}
