import com.artembotnev.weather_station_core.WeatherStationRepositoryFactory
import kotlinx.coroutines.runBlocking
import org.junit.Test

class WeatherStationRepositoryTest {

    private val factory = WeatherStationRepositoryFactory(BASE_URL)

    @Test
    fun getDevicesTest() {
        val repository = factory.get()
        runBlocking {
            val devices = repository.getDevices()
            println(devices)
        }
    }
}