import com.artembotnev.core.WeatherStationRepositoryFactory
import com.artembotnev.core.domain.WeatherStationRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class WeatherStationRepositoryTest {

    private val factory = WeatherStationRepositoryFactory()

    private lateinit var repository: WeatherStationRepository

    @Before
    fun before() {
        repository = factory.get(BASE_URL)
    }

    @Test
    fun getDevicesTest() {
        runBlocking {
            val devices = repository.getDevices()
            println(devices)
        }
    }

    @Test
    fun getMeasurementTest() {
        runBlocking {
            val devices = repository.getMeasurement(0)
            println(devices)
        }
    }

    @Test
    fun getDeviceDailyErrorsTest() {
        runBlocking {
            val devices = repository.getDeviceDailyErrors(0)
            println(devices)
        }
    }

    @Test
    fun getDeviceListDailyErrorsTest() {
        runBlocking {
            val devices = repository.getDeviceListDailyErrors()
            println(devices)
        }
    }
}