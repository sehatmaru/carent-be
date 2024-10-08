package xcode.biz.utils

import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.math.pow
import kotlin.random.Random

object CommonUtil {

    fun generateSecureId(): String {
        return UUID.randomUUID().toString()
    }

    fun getDifferenceDays(date1: Date, date2: Date): Long {
        val diff = date2.time - date1.time
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)
    }

    fun setDateTime(date: Date?, hour: Int): Date {
        val cal = Calendar.getInstance()
        cal.time = date
        cal[Calendar.HOUR_OF_DAY] = hour

        if (hour == 7) {
            cal[Calendar.MINUTE] = 0
            cal[Calendar.SECOND] = 0
            cal[Calendar.MILLISECOND] = 0
        } else {
            cal[Calendar.MINUTE] = 58
            cal[Calendar.SECOND] = 59
            cal[Calendar.MILLISECOND] = 999
        }

        return cal.time
    }

    fun stringToArray(requests: String): Array<String> {
        return requests.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
    }

    fun getTomorrowDate(): Date {
        return Date(System.currentTimeMillis() + (24 * 60 * 60 * 1000).toLong())
    }

    fun generateOTP(): String {
        val length = 5

        val min = (10.0.pow(length - 1)).toInt()
        val max = (10.0.pow(length) - 1).toInt()

        val otp = Random.nextInt(min, max + 1)

        return otp.toString()
    }
}
