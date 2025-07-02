import com.artembotnev.weather_station_core.WeatherStationClient
import kotlinx.coroutines.runBlocking
import org.junit.Test

private const val BASE_URL = "your_url"

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
}