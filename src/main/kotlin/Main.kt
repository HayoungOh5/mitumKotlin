import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.nio.charset.StandardCharsets

fun main() {
    // insert privatekey
    val privatekey = "27a4771cfd9c8fb25a8c261d912b83ee78315d4f9c980e68b1039cb51d496194fpr"
    // insert operation json to be signed, wrapping with """
    val jsonString = """{"_hint":"mitum-currency-transfer-operation-v0.0.1","fact":{"_hint":"mitum-currency-transfer-operation-fact-v0.0.1","hash":"5wvTE4wGLjhLGUUec9RdZPHWf2F2218mPUTzjbVz3xdH","token":"MjAyNC0wOC0xNiAwMDo1Nzo1OS41NjcgKzAwMDAgVVRD","sender":"0xa88fD796EE13ac305f376C54bdfe3270b883643cfca","items":[{"_hint":"mitum-currency-transfer-item-multi-amounts-v0.0.1","amounts":[{"_hint":"mitum-currency-amount-v0.0.1","currency":"FACT","amount":"10"}],"receiver":"0x0B957c51061aa484c62ef3083AaaeF3c8Cc2cDe9fca"},{"_hint":"mitum-currency-transfer-item-multi-amounts-v0.0.1","amounts":[{"_hint":"mitum-currency-amount-v0.0.1","currency":"FACT","amount":"10"}],"receiver":"0x8e590eB9F5Bdb44b23E175467430BA1Bc6Bc9D88fca"}]},"hash":"","signs":[]}"""
    val json = Json { ignoreUnknownKeys = true }
    val operation = json.decodeFromString<Operation>(jsonString)
    
    // executing sign function
    val signedOperation = accSign(privatekey, operation)
    val signedJsonString = json.encodeToString(signedOperation)

    // print the result
    println(signedJsonString)
}

