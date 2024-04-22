package pl.piotr.michalowski.cryptoexchange.extension

import pl.piotr.michalowski.cryptoexchange.dto.CryptoResponse
import pl.piotr.michalowski.cryptoexchange.entity.Crypto
import java.math.BigInteger
import java.time.Instant
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.util.*

fun CryptoResponse.toEntity() = Crypto(
    id = UUID.randomUUID(),
    cryptoCurrency = this.coin,
    amount = this.price,
    targetCurrency = "USD",
    timestamp = this.updated.toOffsetDateTime()
)

fun BigInteger.toOffsetDateTime(): OffsetDateTime =
    OffsetDateTime.ofInstant(Instant.ofEpochSecond(this.toLong()), ZoneOffset.UTC)