import com.artembotnev.core.WeatherStationClient
import kotlinx.coroutines.runBlocking
import org.junit.Test

class WeatherStationClientTest {

    private val client = WeatherStationClient(BASE_URL)

    @Test
    fun getDevicesTest() {
        runBlocking {
            val response = client.getDeviseList()
            println(response.toString())
        }
    }

    @Test
    fun getMeasurementTest() {
        runBlocking {
            val response = client.getMeasurement(0)
            println(response.toString())
        }
    }

    @Test
    fun getDeviceDailyErrorsTest() {
        runBlocking {
            val response = client.getDeviceDailyErrors(0)
            println(response.toString())
        }
    }

    @Test
    fun getDeviceListDailyErrorsTest() {
        runBlocking {
            val response = client.getDeviceListDailyErrors()
            println(response.toString())
        }
    }
}
