package pl.piotr.michalowski.cryptoexchange.client

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import pl.piotr.michalowski.cryptoexchange.config.FeignConfig
import pl.piotr.michalowski.cryptoexchange.dto.CryptoResponse

@FeignClient(
    name = "crypto-api-client",
    url = "\${crypto-api.url}",
    configuration = [FeignConfig::class]
)
interface CryptoApiClient {

    @GetMapping("/coins")
    fun getCryptos(): List<CryptoResponse>
}