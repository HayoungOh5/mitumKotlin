import org.bitcoinj.core.Base58
import org.bitcoinj.core.Utils
import java.nio.charset.StandardCharsets
import java.security.Security
import org.bouncycastle.jce.provider.BouncyCastleProvider
import kotlinx.serialization.json.jsonPrimitive

// accSign 함수
fun accSign(privateKey: String, operation: Operation): Operation {
    Security.addProvider(BouncyCastleProvider())

    val networkID = "mitum"
    val now = TimeStamp()

    val factHash = operation.fact["hash"]?.jsonPrimitive?.content
    val decodedFactHash = Base58.decode(factHash)

    // generate publicKey and sigature
    val (publicKey, sigBuffer) = ethSign(
        privateKey.substring(0, privateKey.length - 3),
        networkID.toByteArray(StandardCharsets.UTF_8) +
        decodedFactHash +
        now.UTC().toByteArray(StandardCharsets.UTF_8)
    )

    val encodedSignature = Base58.encode(sigBuffer)
    val updatedSigns = operation.signs.toMutableList()

    val newSign = Sign(
        signer = publicKey,
        signature = encodedSignature,
        signed_at = now.ISO()
    )
    updatedSigns.add(newSign)

    val factSigns =
        publicKey.toByteArray(StandardCharsets.UTF_8) +
        Base58.decode(encodedSignature) +
        now.UTC().toByteArray(StandardCharsets.UTF_8)
    
    val msg = decodedFactHash + factSigns
    val newHash = Base58.encode(sha3(msg))

    return operation.copy(signs = updatedSigns, hash = newHash)
}
