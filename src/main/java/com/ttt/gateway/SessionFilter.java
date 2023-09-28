package com.ttt.gateway;

import cn.com.mjsoft.cms.member.bean.MemberBean;
import cn.com.mjsoft.framework.security.session.SecuritySession;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.http.HttpCookie;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Mono;

@Component
public class SessionFilter implements GatewayFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        MultiValueMap<String, HttpCookie> cookies= exchange.getRequest().getCookies();
        HttpCookie sessionCookie=cookies.getFirst("SESSION");
        if(null==sessionCookie){
            return chain.filter(exchange);
        }
        String val=sessionCookie.getValue();
        if(null==val || "".equals(val) || "undefined".equals(val)){
            return chain.filter(exchange);
        }
        // 在这里获取会话信息
        Mono<WebSession> sessionMono = exchange.getSession();
        return sessionMono.flatMap(session -> {
            // 在这里可以使用session来进行会话管理或其他操作
            // 例如，获取会话属性：
            //String userId = session.getAttribute("userId");
            // 设置会话属性：
            //session.getAttributes().put("key", "value");
            Object obj=session.getAttribute("___JTOP__SYSTEM__FRAMEWORK__SECURITY__SESSION___");

            if(null!=obj ){
                SecuritySession securitySession= (SecuritySession) obj;
                if(null!=securitySession.getMember()){
                    MemberBean member= (MemberBean) securitySession.getMember();
                    ServerWebExchange ex=this.addUserInfo2Header(exchange,member.getMemberId());
                    // 继续请求链
                    return chain.filter(ex);
                }

            }
            return chain.filter(exchange);

        }).switchIfEmpty(Mono.defer(() -> {
            return chain.filter(exchange);
        }));
    }

    @Override
    public int getOrder() {
        // 设置过滤器执行的顺序，返回值越小越先执行
        return 0;
    }

    private ServerWebExchange addUserInfo2Header(ServerWebExchange exchange,Long uid){
        ServerHttpRequest req = exchange.getRequest();
        ServerHttpRequest.Builder requestBuilder = req.mutate();
        // 先删除，后新增
        requestBuilder.headers(k -> k.remove("session_uid"));
        requestBuilder.header("session_uid", uid.toString());
        ServerHttpRequest request = requestBuilder.build();
        return exchange.mutate().request(request).build();

    }
}
