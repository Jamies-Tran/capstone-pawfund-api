package com.paw.fund.configuration.websocket;

import com.paw.fund.configuration.security.user.detail.PawFundUserDetailService;
import com.paw.fund.utils.token.TokenUtil;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.util.Objects;

@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableWebSocketMessageBroker
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {

    @NonNull
    TokenUtil tokenUtil;

    @NonNull
    PawFundUserDetailService userDetailsService;

    @Override
    public void registerStompEndpoints(@NonNull StompEndpointRegistry registry) {
        registry.addEndpoint("/pawfund-sockjs")
                .withSockJS();

        registry.addEndpoint("/pawfund-ws");
    }

    @Override
    public void configureMessageBroker(@NonNull MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic", "/queue");
        registry.setApplicationDestinationPrefixes("/app");
        registry.setUserDestinationPrefix("/user");
    }

    @Override
    public void configureClientInboundChannel(@NonNull ChannelRegistration registration) {
        registration.interceptors(new ChannelInterceptor() {
            @Override
            public Message<?> preSend(@NonNull Message<?> message, @NonNull MessageChannel channel) {
                StompHeaderAccessor accessor = StompHeaderAccessor.getAccessor(message,  StompHeaderAccessor.class);
                if(accessor != null
                        && Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {
                    String token = accessor.getFirstNativeHeader("Authorization");
                    if(StringUtils.hasText(token) && token.startsWith("Bearer ")) {
                        token = token.substring(7);
                        String identification = tokenUtil.getUserIdentifyFromToken(token);
                        UserDetails userDetails = userDetailsService.loadUserByUsername(identification);
                        UsernamePasswordAuthenticationToken authToken = UsernamePasswordAuthenticationToken.authenticated(
                                userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
                        SecurityContext securityContext = SecurityContextHolder.getContext();
                        securityContext.setAuthentication(authToken);
                        SecurityContextHolder.setContext(securityContext);
                    }
                }

                return message;
            }
        });
    }
}
