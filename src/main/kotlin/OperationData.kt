import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class Operation(
    @SerialName("_hint") val hint: String,
    val fact: JsonObject,
    val hash: String,
    val signs: List<Sign>
)

@Serializable
data class Fact(
    val hash: String
)

@Serializable
data class Sign(
    val signer: String,
    val signature: String,
    val signed_at: String
)