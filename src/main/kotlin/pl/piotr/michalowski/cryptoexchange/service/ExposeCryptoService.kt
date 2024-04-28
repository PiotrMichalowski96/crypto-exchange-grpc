package pl.piotr.michalowski.cryptoexchange.service

import com.google.protobuf.Empty
import com.google.protobuf.StringValue
import io.grpc.stub.StreamObserver
import net.devh.boot.grpc.server.service.GrpcService
import pl.piotr.michalowski.cryptoexchange.extension.toProto
import pl.piotr.michalowski.cryptoexchange.model.CryptoProto
import pl.piotr.michalowski.cryptoexchange.model.CryptoServiceGrpc
import pl.piotr.michalowski.cryptoexchange.repository.CryptoRepository

@GrpcService
class ExposeCryptoService(private val repository: CryptoRepository) : CryptoServiceGrpc.CryptoServiceImplBase() {

    override fun findAll(request: Empty?, responseObserver: StreamObserver<CryptoProto.CryptoCurrencies>?) {
        val cryptos: List<CryptoProto.CryptoCurrency> = repository.findAll()
            .map { it.toProto() }
        val cryptosProto: CryptoProto.CryptoCurrencies = CryptoProto.CryptoCurrencies.newBuilder()
            .addAllCryptos(cryptos)
            .build()
        responseObserver?.onNext(cryptosProto)
        responseObserver?.onCompleted()
    }

    override fun findByName(request: StringValue?, responseObserver: StreamObserver<CryptoProto.CryptoCurrency>?) {
        request?.value
            ?.let { cryptoName ->
                repository.findFirstByCryptoCurrencyOrderByTimestampDesc(cryptoName)
            }
            ?.let { crypto ->
                val cryptoProto = crypto.toProto()
                responseObserver?.onNext(cryptoProto)
                responseObserver?.onCompleted()
            }
    }
}