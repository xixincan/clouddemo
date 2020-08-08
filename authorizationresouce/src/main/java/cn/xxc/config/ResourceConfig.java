package cn.xxc.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * 通过@EnableResourceServer开启资源服务器的相关自动配置
 *
 * @author xixincan
 * 2020-08-05
 * @version 1.0.0
 */
@Configuration
@EnableResourceServer
public class ResourceConfig extends ResourceServerConfigurerAdapter {

    @Resource
    private RestTemplate restTemplate;

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenStore(jwtTokenStore()).stateless(true);
        // 配置RemoteTokenServices， 用于想AuthorizationServer验证令牌
        RemoteTokenServices tokenServices  = new RemoteTokenServices();
        tokenServices.setAccessTokenConverter(accessTokenConverter());
        RestTemplate restTemplate = restTemplate();
        // 为restTemplate配置异常处理器，忽略400错误
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
                if (response.getRawStatusCode() != 400) {
                    super.handleError(response);
                }
            }
        });
        tokenServices.setRestTemplate(restTemplate);

        tokenServices.setCheckTokenEndpointUrl("http://AUTHORIZATIONSERVER1/oauth/check_token");
        tokenServices.setClientId("client");
        tokenServices.setClientSecret("secret");
        resources.tokenServices(tokenServices).stateless(true);
    }

    @Bean
    public JwtTokenStore jwtTokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
        accessTokenConverter.setSigningKey("secret");
        return accessTokenConverter;
    }
    /**
     * resourceServerTokenServices 类的实例，用来实现令牌服务。
     * @return
     */
    @Bean
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(jwtTokenStore());
        return defaultTokenServices;
    }



    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .requestMatchers().anyRequest()
                .and()
                .anonymous()
                .and()
                .authorizeRequests()
                .antMatchers("/user/**").authenticated()
                .and()
                .exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
    }
}
