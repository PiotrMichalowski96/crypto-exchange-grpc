package pl.piotr.michalowski.cryptoexchange.extension

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import pl.piotr.michalowski.cryptoexchange.dto.CryptoResponse
import pl.piotr.michalowski.cryptoexchange.entity.Crypto
import pl.piotr.michalowski.cryptoexchange.model.CryptoProto
import java.math.BigDecimal
import java.math.BigInteger
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.util.*

class CryptoExtensionTest {

    @Test
    fun `given big integer number when use extension function then return offset date time`() {
        //given
        val number = BigInteger.valueOf(1713712692L)
        val expectedOffsetDateTime = OffsetDateTime.of(2024, 4, 21, 15, 18, 12, 0, ZoneOffset.UTC)

        //when
        val actualOffsetDateTime = number.toOffsetDateTime()

        //then
        assertThat(actualOffsetDateTime).isEqualTo(expectedOffsetDateTime)
    }

    @Test
    fun `given crypto response from api when use extension function then return crypto entity`() {
        //given
        val response = CryptoResponse(
            id = "a195fd59ce0ebc3f9b2d99b3c396ff198bcb4a5e",
            coin = "BTC",
            name = "Bitcoin",
            type = "coin",
            algorithm = "SHA-256",
            networkHashrate = "6.0980459510613e+20",
            difficulty = "86388558925171",
            reward = "3.032050509576e-20",
            rewardUnit = "BTC",
            rewardBlock = BigDecimal.valueOf(3.125),
            price = BigDecimal.valueOf(65077.849872518),
            volume = BigDecimal.valueOf(19006813790.054),
            updated = BigInteger.valueOf(1713712692L)
        )

        val expectedEntity = Crypto(
            id = UUID.randomUUID(),
            cryptoCurrency = "BTC",
            amount = BigDecimal.valueOf(65077.849872518),
            targetCurrency = "USD",
            timestamp = OffsetDateTime.of(2024, 4, 21, 15, 18, 12, 0, ZoneOffset.UTC)
        )

        //when
        val actualEntity = response.toEntity()

        //then
        assertThat(actualEntity)
            .usingRecursiveComparison()
            .ignoringFields("id")
            .isEqualTo(expectedEntity)
    }

    @Test
    fun `given crypto entity when use extension function then return crypto proto`() {
        //given
        val id = UUID.randomUUID()
        val cryptoEntity = Crypto(
            id = id,
            cryptoCurrency = "XYZ",
            amount = BigDecimal.valueOf(1.01),
            targetCurrency = "PLN",
            timestamp = OffsetDateTime.of(2024, 4, 27, 17, 56, 59, 0, ZoneOffset.UTC)
        )

        val expectedProto = CryptoProto.CryptoCurrency.newBuilder()
            .setId(id.toString())
            .setName("XYZ")
            .setAmount(1.01)
            .setTargetCurrency("PLN")
            .setTimestamp("2024-04-27T17:56:59Z")
            .build()

        //when
        val actualProto = cryptoEntity.toProto()

        //then
        assertThat(actualProto).isEqualTo(expectedProto)
    }
}