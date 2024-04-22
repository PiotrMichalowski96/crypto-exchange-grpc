package pl.piotr.michalowski.cryptoexchange

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.scheduling.annotation.EnableScheduling
import pl.piotr.michalowski.cryptoexchange.client.CryptoApiClient

@EnableFeignClients(basePackageClasses = [CryptoApiClient::class])
@EnableScheduling
@SpringBootApplication
class CryptoExchangeGrpcApplication

fun main(args: Array<String>) {
    runApplication<CryptoExchangeGrpcApplication>(*args)
}
