import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone

// 최소한의 Timestamp 클래스
class TimeStamp(private val t: Date = Date()) {
    fun UTC(): String {
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS '+0000 UTC'")
        formatter.timeZone = TimeZone.getTimeZone("UTC") // UTC 시간대 설정
        return formatter.format(t)
    }

    fun ISO(): String {
        val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        formatter.timeZone = TimeZone.getTimeZone("UTC") // UTC 시간대 설정
        return formatter.format(t)
    }
}
