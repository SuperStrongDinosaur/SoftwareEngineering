#include "LRUCache.h"
#include <cassert>

const int LRUCache::get(const Key& key) {
    assert(data.size() == pos.size());
    
    auto it = data.find(key);
    if (it == data.end())
        throw std::out_of_range("Out of range");;
    auto ans = it->second.first;
    
    auto temp = data.find(key);
    unsigned int cnt = temp->second.second;
    auto value = temp->second.first;
    pos.erase(cnt);
    data.erase(key);
    insert_to(key, value);
    
    return ans;
}

void LRUCache::put(const Key& key, const Value& value) {
    if (data.find(key) != data.end()) {
        pos.erase(data.find(key)->second.second);
        data.erase(key);
        insert_to(key, value);
    }
    
    if (cur_size == max_size) {
        data.erase(pos.begin()->second);
        pos.erase(pos.begin());
        insert_to(key, value);
        
        assert(data.size() == max_size);
        assert(pos.size() == max_size);
    } else {
        insert_to(key, value);
        ++cur_size;
    }
    
    assert(pos.rbegin()->second == key);
}

void LRUCache::insert_to(const int& key, const int& value) {
    assert(data.size() == pos.size());
    
    pos.insert(std::make_pair(time, key));
    data.insert(std::make_pair(key, std::make_pair(value, time++)));
}
