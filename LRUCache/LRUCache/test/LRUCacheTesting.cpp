#include "../LRUCache.h"
#include "gtest/gtest.h"
#include <array>
#include <random>

TEST(LRUCache, constructor)
{
    LRUCache cache(10);
    EXPECT_EQ(10, cache.get_max_size());
}

#define CHECK_MAP_LIST(cache, key, value)    \
    {                                        \
        EXPECT_EQ((value), cache.get(key));  \
    }

TEST(LRUCache, simple_put)
{
    LRUCache cache(10);
    cache.put(1, 0);
    CHECK_MAP_LIST(cache, 1, 0);
    EXPECT_EQ(cache.size(), 1);
}

TEST(LRUCache, many_puts)
{
    LRUCache cache(100);
    for (int i = 0; i != 95; ++i)
        cache.put(i, i + 1);
    for (int i = 0; i != 95; ++i)
        CHECK_MAP_LIST(cache, i, i + 1)
    cache.put(100, 101);
    for (int i = 0; i != 95; ++i)
        CHECK_MAP_LIST(cache, i, i + 1)
    CHECK_MAP_LIST(cache, 100, 101);
}

TEST(LRUCache, random_puts)
{
    LRUCache cache(100);
    srand(time(0));
    for (int i = 0; i < 200; ++i)
    {
        cache.put(i, i + 1);
    }
    int max_int = std::numeric_limits<int>::max();
    for (int i = 0; i < 1000; ++i)
    {
        int key = random() % max_int;
        int value = key + 1;
        cache.put(key, value);
        CHECK_MAP_LIST(cache, key, value)
    }
}

TEST(LRUCache, simple_get)
{
    LRUCache cache(100);
    cache.put(0, 1);
    auto value = cache.get(0);
    CHECK_MAP_LIST(cache, 0, value);
}

TEST(LRUCache, edge_get)
{
    LRUCache cache(100);
    for (int i = 0; i != 100; ++i)
        cache.put(i, i + 1000);
    int value = cache.get(0);
    EXPECT_EQ(value, 1000);
    cache.put(100, 1100);
    CHECK_MAP_LIST(cache, 100, 1100);
    try {
        value = cache.get(1);
        EXPECT_FALSE(true);
    } catch (std::exception& e) {
        EXPECT_FALSE(false);
    }
}

TEST(LRUCache, random_gets)
{
    const int N = 1000;
    LRUCache cache(N);
    for (int i = 0; i != N; ++i)
        cache.put(i, N + i);
    for (int i = 0; i < 10 * N; ++i)
    {
        int key = random() % 2 * N;
        try {
            int value = cache.get(key);
            EXPECT_EQ(value, key + N);
        } catch (std::exception& e) {
             EXPECT_EQ(e.what(), std::string("Out of range"));
        } catch(...) {
            FAIL() << "Expected std::out_of_range";
        }
    }
}

