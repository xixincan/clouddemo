package cn.xxc.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.annotation.Resource;

/**
 * 继承自 AuthorizationServerConfigurerAdapter 对授权服务进行覆盖配置
 * 通过@EnableAuthorizationServer 开启授权服务自动配置
 *
 * 首先在内存中配置一个客户端（可以配置在数据库中），他可以通过授权码类型和密码类型获取到访问令牌；
 * 然后对tokenServices进行了配置，使用了JWT作为令牌的转换器，这里将JWT的密钥设置为"secret"，同时使用InMemoryTokenStore将令牌保存到内存中（JdbcTokenStore/RedisTokenStore）；
 * 最后对授权端点的访问控制进行配置
 *
 * @author xixincan
 * 2020-08-05
 * @version 1.0.0
 */
@Configuration
// @EnableAuthorizationServer 开启授权服务
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private static final String RESOURCE_ID = "user";

    @Resource
    private AuthenticationManager authenticationManager;

    /**
     * 配置appId、appKey（secret）、回调地址、token有效期
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 配置一个客户端， 既可以通过授权码类型获取令牌，也可以通过密码类型获取令牌
        clients.inMemory()
                .withClient("client") // 客户端ID/appId
                .authorizedGrantTypes("authorization_code", "password", "refresh_token")    // 客户端可以使用的授权类型
                .scopes("all")  // 允许请求的范围
                .secret("secret")   // 客户端安全码appKey/secret
                .autoApprove(true)  // 自动授权
                .redirectUris("http://localhost:8888/login"); // 回调地址
    }

    // 配置AuthorizationServer tokenServices
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(new InMemoryTokenStore())
                .accessTokenConverter(accessTokenConverter())
                .authenticationManager(this.authenticationManager)
                .reuseRefreshTokens(false);
    }

    // 配置JWT转换器
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("secret");
        return converter;
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // 允许所有人请求令牌
        // 已验证的客户端才能请求check_token
        security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients();
    }
}
