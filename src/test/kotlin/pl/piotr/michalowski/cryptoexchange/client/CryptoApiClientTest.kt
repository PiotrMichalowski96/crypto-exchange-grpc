package pl.piotr.michalowski.cryptoexchange.client

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import pl.piotr.michalowski.cryptoexchange.config.FeignConfig
import pl.piotr.michalowski.cryptoexchange.dto.CryptoResponse

@ActiveProfiles("TEST")
@SpringBootTest
@Import(FeignConfig::class)
class CryptoApiClientTest(@Autowired private val apiClient: CryptoApiClient) {

    @Test
    fun `when call crypto api then returns crypto responses list`() {
        val cryptoResponses: List<CryptoResponse> = apiClient.getCryptos()
        assertThat(cryptoResponses).isNotEmpty
        assertNoNullFields(cryptoResponses)
    }

    private fun assertNoNullFields(cryptoResponses: List<CryptoResponse>) {
        cryptoResponses.forEach {
            assertThat(it).hasNoNullFieldsOrProperties()
        }
    }
}