package pl.piotr.michalowski.cryptoexchange.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal
import java.math.BigInteger

data class CryptoResponse(
    val id: String,
    val coin: String,
    val name: String,
    val type: String,
    val algorithm: String,
    @JsonProperty("network_hashrate")
    val networkHashrate: BigInteger,
    val difficulty: String,
    val reward: String,
    @JsonProperty("reward_unit")
    val rewardUnit: String,
    @JsonProperty("reward_block")
    val rewardBlock: BigDecimal,
    val price: BigDecimal,
    val volume: BigDecimal,
    val updated: BigInteger
)
