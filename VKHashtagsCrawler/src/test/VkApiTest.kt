package test

import com.vk.api.sdk.actions.Newsfeed
import com.vk.api.sdk.client.VkApiClient
import com.vk.api.sdk.client.actors.UserActor
import com.vk.api.sdk.exceptions.ApiException
import com.vk.api.sdk.exceptions.ClientException
import com.vk.api.sdk.objects.newsfeed.responses.SearchResponse
import com.vk.api.sdk.objects.wall.WallPostFull
import com.vk.api.sdk.queries.newsfeed.NewsfeedSearchQuery
import main.VkApi
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers

import java.util.ArrayList
import java.util.Random

import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class VKAPITest {
    @Test
    @Throws(ApiException::class, ClientException::class)
    internal fun searchTest() {
        Random()
        val curTime = (System.currentTimeMillis() / 1000L).toInt()
        val postsAmount = 199

        val vk = mock(VkApiClient::class.java)
        val newsfeed = mock(Newsfeed::class.java)
        val sQuery = mock(NewsfeedSearchQuery::class.java)
        val sResponse = mock(SearchResponse::class.java)

        val mockResult = ArrayList<WallPostFull>()
        for (i in 0 until postsAmount) {
            val wPost = mock(WallPostFull::class.java)
            `when`(wPost.date).thenReturn(curTime - 1 - i)
            mockResult.add(wPost)
        }

        `when`(vk.newsfeed()).thenReturn(newsfeed)
        `when`<NewsfeedSearchQuery>(newsfeed.search(ArgumentMatchers.any(UserActor::class.java))).thenReturn(sQuery)
        `when`(sQuery.q(ArgumentMatchers.any(String::class.java))).thenReturn(sQuery)
        `when`(sQuery.count(ArgumentMatchers.any(Int::class.java))).thenReturn(sQuery)
        `when`(sQuery.startTime(ArgumentMatchers.any(Int::class.java))).thenReturn(sQuery)
        `when`(sQuery.endTime(ArgumentMatchers.any(Int::class.java))).thenReturn(sQuery)
        `when`(sQuery.execute()).thenReturn(sResponse)
        `when`(sResponse.items).thenReturn(mockResult)

        val vkapi = VkApi(vk)

        val res = vkapi.search("a", 24, curTime)
        Assertions.assertEquals(mockResult, res)
    }
}
