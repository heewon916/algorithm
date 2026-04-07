#include <vector>
#include <iostream>
#include <unordered_map> 

using namespace std;

int solution(vector<int> nums)
{
    int n = nums.size(); 
    unordered_map<int, int> mp; 
    for(int n: nums){
        mp[n]++; 
    }
    
    // 만약 mp의 길이 > n/2 -> n/2개만큼의 종류가 나올 수 있음 
    // 아니라면 -> mp의 길이만큼의 종류밖에 못 나옴 
    
    if(mp.size() > n/2){
        return n/2; 
    }else{
        return mp.size();
    }
}