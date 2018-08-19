package com.qianli.ilink.cloud_platform.messagecenterudp.spring.customized;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "udp.port")
@Component
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UdpPortConfig {
    private int apUserInfoServerPort;
    private int userInternetLogServerPort;
}
