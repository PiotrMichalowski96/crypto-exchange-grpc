package pl.piotr.michalowski.cryptoexchange.extension

import pl.piotr.michalowski.cryptoexchange.dto.CryptoResponse
import pl.piotr.michalowski.cryptoexchange.entity.Crypto
import pl.piotr.michalowski.cryptoexchange.model.CryptoProto
import java.math.BigInteger
import java.time.Instant
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter.ISO_OFFSET_DATE_TIME
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

fun Crypto.toProto(): CryptoProto.CryptoCurrency = CryptoProto.CryptoCurrency.newBuilder()
    .setId(this.id.toString())
    .setName(this.cryptoCurrency)
    .setAmount(this.amount.toDouble())
    .setTargetCurrency(this.targetCurrency)
    .setTimestamp(ISO_OFFSET_DATE_TIME.format(this.timestamp))
    .build()

