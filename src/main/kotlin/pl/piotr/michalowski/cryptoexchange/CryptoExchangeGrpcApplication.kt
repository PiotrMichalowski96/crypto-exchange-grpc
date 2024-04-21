package pl.piotr.michalowski.cryptoexchange

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import pl.piotr.michalowski.cryptoexchange.client.CryptoApiClient

@EnableFeignClients(basePackageClasses = [CryptoApiClient::class])
@SpringBootApplication
class CryptoExchangeGrpcApplication

fun main(args: Array<String>) {
    runApplication<CryptoExchangeGrpcApplication>(*args)
}
