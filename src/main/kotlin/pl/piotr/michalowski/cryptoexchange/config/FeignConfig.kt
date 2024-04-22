package pl.piotr.michalowski.cryptoexchange.config

import feign.Logger
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FeignConfig {

    @Bean
    fun logger() = Logger.Level.FULL
}