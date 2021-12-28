import com.mongodb.rx.client.*
import db.ReactiveMongoDriver
import db.entities.Good
import db.entities.User
import org.bson.Document
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.core.classloader.annotations.SuppressStaticInitializationFor
import org.powermock.modules.junit4.PowerMockRunner
import rx.*
import rx.internal.util.ScalarSynchronousObservable
import server.Methods

import java.io.IOException
import java.util.*

import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@RunWith(PowerMockRunner::class)
@PrepareForTest(ReactiveMongoDriver::class)
@SuppressStaticInitializationFor("db.ReactiveMongoDriver")
class RXTest {
    @Mock
    lateinit var db: ReactiveMongoDriver

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun addUserCorrectnessTest() {
        //val db = Mockito.mock(ReactiveMongoDriver::class.java)
        //`when`(db.createUser(Mockito.anyObject())).thenReturn(Success.SUCCESS)
        val m = Methods(db)
        val params = HashMap<String, List<String>>()
        params["id"] = listOf("1")
        var res: rx.Observable<String> = m.addUser(params)
        Assert.assertEquals("Please fill in field(s) name, currency", (res as ScalarSynchronousObservable<*>).get())
        params["name"] = listOf("Test")
        res = m.addUser(params)
        Assert.assertEquals("Please fill in field(s) currency", (res as ScalarSynchronousObservable<*>).get())
        params["currency"] = listOf("rub")
        res = m.addUser(params)
        Assert.assertTrue((res as ScalarSynchronousObservable<*>).get().toString().startsWith("New user added"))
    }

    @Test
    fun addGoodCorrectnessTest() {
       //val db = Mockito.mock(ReactiveMongoDriver::class.java)
       //`when`(db.addGoods(Mockito.anyObject())).thenReturn(Success.SUCCESS)
        val m = Methods(db)
        val params = HashMap<String, List<String>>()
        params["id"] = listOf("1")
        params["name"] = listOf("Test")
        var res: rx.Observable<String> = m.addGood(params)
        Assert.assertEquals("Please fill in field(s) eur, usd, rub", (res as ScalarSynchronousObservable<*>).get())
        params["eur"] = listOf("5")
        params["usd"] = listOf("8")
        params["rub"] = listOf("100")
        res = m.addGood(params)
        Assert.assertTrue((res as ScalarSynchronousObservable<*>).get().toString().startsWith("New good added"))
    }
}
