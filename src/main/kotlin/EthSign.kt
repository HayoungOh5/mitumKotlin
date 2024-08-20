import org.bouncycastle.jce.provider.BouncyCastleProvider
import org.bouncycastle.crypto.digests.SHA256Digest
import org.bouncycastle.crypto.params.ECPrivateKeyParameters
import org.bouncycastle.crypto.signers.ECDSASigner
import org.bouncycastle.math.ec.ECCurve
import org.bouncycastle.math.ec.ECPoint
import org.bouncycastle.math.ec.custom.sec.SecP256K1Curve
import org.bouncycastle.crypto.params.ECDomainParameters
import org.bouncycastle.util.encoders.Hex
import org.bitcoinj.core.Utils
import java.math.BigInteger
import java.security.MessageDigest
import java.security.Security

fun ethSign(privateKeyHex: String, msg: ByteArray): Pair<String, ByteArray> {
    // BouncyCastleProvider를 추가
    Security.addProvider(BouncyCastleProvider())

    // 개인 키를 Hex로부터 BigInteger로 변환
    val privateKeyBigInt = BigInteger(privateKeyHex, 16)

    // SECP256K1 커브와 관련된 파라미터를 생성
    val curve = SecP256K1Curve()
    // SECP256K1 커브의 파라미터를 하드코딩
    val g = curve.createPoint(
        BigInteger("79be667ef9dcbbac55a06295ce870b07029bfcdb2dce28d959f2815b16f81798", 16),
        BigInteger("483ada7726a3c4655da4fbfc0e1108a8fd17b448a68554199c47d08ffb10d4b8", 16)
    )

    val n = BigInteger("fffffffffffffffffffffffffffffffebaaedce6af48a03bbfd25e8cd0364141", 16)

    // ECDomainParameters를 생성
    val params = ECDomainParameters(curve, g, n)

    // ECPrivateKeyParameters를 생성
    val privateKeyParams = ECPrivateKeyParameters(privateKeyBigInt, params)

    // 서명 생성기 인스턴스를 생성
    val signer = ECDSASigner()
    signer.init(true, privateKeyParams)

    val digest = MessageDigest.getInstance("SHA-256")
    
    // 입력 메시지를 해싱
    val hashedMsg = digest.digest(msg)

    val signature = signer.generateSignature(hashedMsg)
    val r = signature[0]
    val s = signature[1]

    // BigInteger를 바이트 배열로 변환
    val rBytes = r.toByteArray()
    val sBytes = s.toByteArray()

    // 서명 버퍼를 생성
    val sigBuffer = ByteArray(4 + rBytes.size + sBytes.size)

    Utils.uint32ToByteArrayLE(rBytes.size.toLong(), sigBuffer, 0)
    System.arraycopy(rBytes, 0, sigBuffer, 4, rBytes.size)
    System.arraycopy(sBytes, 0, sigBuffer, 4 + rBytes.size, sBytes.size)

    // comporessed publickey 생성
    val publicKeyPoint: ECPoint = g.multiply(privateKeyBigInt)
    val publicKeyCompressed = publicKeyPoint.getEncoded(true)
    val publicKeyHex = Hex.toHexString(publicKeyCompressed)
    val publickey = publicKeyHex + "fpu"

    return Pair(publickey, sigBuffer)
}