package main

import com.vk.api.sdk.client.VkApiClient
import com.vk.api.sdk.httpclient.HttpTransportClient
import com.vk.api.sdk.objects.wall.WallPostFull
import java.util.*

class Counter {
    internal fun countPostsPerHour(posts: List<WallPostFull>, hours: Int, curTime: Int): List<Int> {
        val result = ArrayList(Collections.nCopies(hours, 0))

        for (obj in posts) {
            val index = (curTime - obj.date) / 3600
            result[index] = result[index] + 1
        }
        return result
    }

    internal fun getDiagram(hashtag: String, hours: Int): List<Int> {
        val transportClient = HttpTransportClient.getInstance()
        val vk = VkApiClient(transportClient)
        val vkapi = VkApi(vk)

        val curTime = (System.currentTimeMillis() / 1000L).toInt()
        val response = vkapi.search(hashtag, hours, curTime)
        return countPostsPerHour(response, hours, curTime)
    }
}