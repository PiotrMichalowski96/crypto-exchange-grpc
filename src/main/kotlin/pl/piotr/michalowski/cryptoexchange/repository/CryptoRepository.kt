package pl.piotr.michalowski.cryptoexchange.repository

import org.springframework.data.mongodb.repository.MongoRepository
import pl.piotr.michalowski.cryptoexchange.entity.Crypto
import java.util.UUID

interface CryptoRepository: MongoRepository<Crypto, UUID>
