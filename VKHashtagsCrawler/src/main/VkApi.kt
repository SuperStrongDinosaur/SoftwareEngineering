package main

import com.vk.api.sdk.client.VkApiClient
import com.vk.api.sdk.client.actors.UserActor
import com.vk.api.sdk.exceptions.ClientException
import com.vk.api.sdk.objects.newsfeed.responses.SearchResponse
import com.vk.api.sdk.objects.wall.WallPostFull
import java.util.*

class VkApi(private val vk: VkApiClient) {
    private val actor: UserActor = UserActor(	6730830, "e0940fe6e0940fe6e0940fe6dde0f2bba8ee094e0940fe6bb449699f91d7a36ecfaade2")

    fun search(hashtag: String, hours: Int, curTime: Int): List<WallPostFull> {
        val posts = ArrayList<WallPostFull>()
        try {
            val startTime = curTime - hours * 60 * 60
            var endTime = curTime
            val searchString = "#$hashtag"
            var response: SearchResponse

            while (true) {
                response = vk.newsfeed().search(actor)
                        .q(searchString)
                        .count(200)
                        .startTime(startTime)
                        .endTime(endTime)
                        .execute()
                posts.addAll(response.items)
                if (response.items.size < 200) {
                    break
                }
                endTime = response.items[response.items.size - 1].date - 1
                Thread.sleep(500)
            }
        } catch (e: ClientException) {
            println(e)
        }
        return posts
    }
}
