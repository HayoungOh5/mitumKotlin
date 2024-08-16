import org.bouncycastle.crypto.digests.SHA3Digest

// SHA3-256 해시 함수
fun sha3(msg: ByteArray): ByteArray {
    val digest = SHA3Digest(256)
    digest.update(msg, 0, msg.size)
    val hash = ByteArray(digest.digestSize)
    digest.doFinal(hash, 0)
    return hash
}