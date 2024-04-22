package pl.piotr.michalowski.cryptoexchange.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal
import java.time.OffsetDateTime
import java.util.UUID

@Document
data class Crypto(
    @Id
    val id: UUID,
    val cryptoCurrency: String,
    val amount: BigDecimal,
    val targetCurrency: String,
    val timestamp: OffsetDateTime
)
