package test

import com.vk.api.sdk.objects.wall.WallPostFull
import main.Counter
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

import java.util.ArrayList
import java.util.Collections
import java.util.Random

import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class DiagramProducerTest {
    @Test
    internal fun countPostsPerHourTest() {
        val rand = Random()
        val curTime = (System.currentTimeMillis() / 1000L).toInt()

        val hours = rand.nextInt(24) + 1
        val postsAmount = rand.nextInt(2000)

        val mockList = ArrayList<WallPostFull>()

        for (i in 0 until postsAmount) {
            val mockPost = mock(WallPostFull::class.java)
            val postTime = curTime - (rand.nextInt(hours) + 1) * 3600
            `when`(mockPost.date).thenReturn(postTime)
        }

        val producer = Counter()
        val result = producer.countPostsPerHour(mockList, hours, curTime).toMutableList()

        for (el in mockList) {
            val index = (curTime - el.date!!) / 3600
            result[index] = result[index] - 1
        }

        val expected = ArrayList(Collections.nCopies(hours, 0))

        Assertions.assertEquals(expected, result)
    }
}
