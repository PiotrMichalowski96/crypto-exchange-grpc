package pl.piotr.michalowski.cryptoexchange.service

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import pl.piotr.michalowski.cryptoexchange.client.CryptoApiClient
import pl.piotr.michalowski.cryptoexchange.dto.CryptoResponse
import pl.piotr.michalowski.cryptoexchange.extension.toEntity
import pl.piotr.michalowski.cryptoexchange.repository.CryptoRepository
import java.util.concurrent.TimeUnit

private val logger = KotlinLogging.logger {}

@Service
class FetchCryptoService(
    private val apiClient: CryptoApiClient,
    private val repository: CryptoRepository
) {

    @Transactional
    @Scheduled(
        fixedDelayString = "\${crypto-api.scheduler.delay-minutes}",
        initialDelayString = "\${crypto-api.scheduler.init-delay-minutes}",
        timeUnit = TimeUnit.MINUTES)
    fun fetchCrypto() {
        fetchAndLogCryptos()
            .map { it.toEntity() }
            .forEach { repository.save(it) }
    }

    private fun fetchAndLogCryptos(): List<CryptoResponse> {
        logger.info { "Starting to fetch crypto..." }
        val cryptos = apiClient.getCryptos()
        logger.info { "Fetched cryptos: ${cryptos.count()}" }
        return cryptos
    }
}