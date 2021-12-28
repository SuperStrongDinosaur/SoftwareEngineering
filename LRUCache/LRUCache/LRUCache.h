#ifndef LRUCache_h
#define LRUCache_h

#include <map>
#include <string>

class LRUCache {
    using Key = int;
    using Value = int;
    
public:
    LRUCache(unsigned int size) : max_size(size), cur_size(0) {}
    const int get(const Key& key);
    void put(const Key& key, const Value& value);
    size_t get_max_size() { return max_size; }
    size_t size() { return cur_size; }
    
private:
    std::map<Key, std::pair<Value, unsigned int> > data;
    std::map<int, Key> pos;
    
    unsigned int max_size;
    unsigned int cur_size;
    unsigned int time;
   
    void insert_to(const Key& key, const Value& value);
};

#endif
