package pl.piotr.michalowski.cryptoexchange

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CryptoExchangeGrpcApplication

fun main(args: Array<String>) {
    runApplication<CryptoExchangeGrpcApplication>(*args)
}
