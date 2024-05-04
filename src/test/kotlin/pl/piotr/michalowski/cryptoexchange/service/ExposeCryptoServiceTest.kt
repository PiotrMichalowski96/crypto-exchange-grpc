package pl.piotr.michalowski.cryptoexchange.service

import com.google.protobuf.Empty
import pl.piotr.michalowski.cryptoexchange.model.CryptoServiceGrpc
import net.devh.boot.grpc.client.inject.GrpcClient
import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ActiveProfiles
import pl.piotr.michalowski.cryptoexchange.entity.Crypto
import pl.piotr.michalowski.cryptoexchange.model.CryptoProto
import java.math.BigDecimal
import java.time.OffsetDateTime
import java.util.*

@ActiveProfiles("TEST")
@AutoConfigureDataMongo
@SpringBootTest(properties = ["grpc.server.port=9092"])
@EnableAutoConfiguration
@DirtiesContext
class ExposeCryptoServiceTest(
    @Autowired private val mongoTemplate: MongoTemplate
) {

    @GrpcClient("crypto-service-grpc")
    private lateinit var stub: CryptoServiceGrpc.CryptoServiceBlockingStub

    @Test
    fun `given crypto data in db when call gRPC find all then returns cryptos list`() {
        //given
        val cryptosToSave = listOf(
            Crypto(
                id = UUID.randomUUID(),
                cryptoCurrency = "BTB",
                amount = BigDecimal.valueOf(0.00027953550101098),
                targetCurrency = "USD",
                timestamp = OffsetDateTime.now()
            ),
            Crypto(
                id = UUID.randomUUID(),
                cryptoCurrency = "BTC",
                amount = BigDecimal.valueOf(65077.849872518),
                targetCurrency = "USD",
                timestamp = OffsetDateTime.now()
            )
        )

        val expectedCryptos = CryptoProto.CryptoCurrencies.newBuilder()
            .addCryptos(
                CryptoProto.CryptoCurrency.newBuilder()
                    .setName("BTC")
                    .setAmount(65077.849872518)
                    .setTargetCurrency("USD")
                    .build()
            )
            .addCryptos(
                CryptoProto.CryptoCurrency.newBuilder()
                    .setName("BTB")
                    .setAmount(0.00027953550101098)
                    .setTargetCurrency("USD")
                    .build()
            )
            .build()

        cryptosToSave.forEach { mongoTemplate.save(it) }

        //when
        val cryptoCurrencies = stub.findAll(Empty.getDefaultInstance())

        //then
        assertThat(cryptoCurrencies.cryptosList)
            .usingRecursiveComparison()
            .ignoringFields("id_", "timestamp_", "memoizedHashCode", "memoizedIsInitialized")
            .isEqualTo(expectedCryptos.cryptosList)
    }
}