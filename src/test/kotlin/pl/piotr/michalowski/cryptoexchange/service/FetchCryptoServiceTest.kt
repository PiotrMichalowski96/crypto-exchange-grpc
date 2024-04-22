package pl.piotr.michalowski.cryptoexchange.service

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assumptions.assumeThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.test.annotation.DirtiesContext
import pl.piotr.michalowski.cryptoexchange.entity.Crypto

@AutoConfigureDataMongo
@SpringBootTest(properties = ["de.flapdoodle.mongodb.embedded.version=7.0.8"])
@EnableAutoConfiguration
@DirtiesContext
class FetchCryptoServiceTest(
    @Autowired private val fetchCryptoService: FetchCryptoService,
    @Autowired private val mongoTemplate: MongoTemplate
) {

    @Test
    fun `given empty mongodb database when fetch cryptos then crypto data is persisted in mongodb`() {
        //given
        assumeThat(mongoTemplate.findAll(Crypto::class.java)).isEmpty()

        //when
        fetchCryptoService.fetchCrypto()

        //then
        val savedCryptos = mongoTemplate.findAll(Crypto::class.java)
        assertThat(savedCryptos).isNotEmpty
    }
}