#include <iostream> 
#include <vector> 
#include <cmath>
#include <algorithm> 
#include <map> 
using namespace std; 

int r, c, k;

/**
 * 1초마다:
행 개수 >= 열 개수
- R연산: 모든 행에 대한 정렬
- 수의 등장 횟수 오름차순 - 수 오름차순 
- {수, 등장횟수}로 다시 저장
- 가장 큰 행 기준으로 모든 행의 크기 변화 (0으로 fill)

 */
void calcR(vector<vector<int>>& arr){
    int rSize = arr.size(), cSize = arr[0].size();  
    vector<vector<int>> temp(rSize);
    int maxColsize = 0; 
    for(int i=0; i<rSize; i++){ // R * (C + MlogM + C )  = 100 * (100 + 100 * 2 + 100) = 4* 10^4 

        // 수, 등장횟수
        map<int, int> hmap;
        for(int j=0; j<cSize; j++){ // O(C)
            if(arr[i][j] != 0) hmap[arr[i][j]]++; 
        }
        
        // 수, 등장횟수 기준으로 정렬이 필요하다 
        vector<pair<int, int>> list; 
        for(auto& entry: hmap){
            list.push_back({entry.first, entry.second}); 
        }
        sort(list.begin(), list.end(), [](auto a, auto b){ // O(M logM)
            if(a.second != b.second){
                return a.second < b.second; // 등장횟수 오름차순
            }else {
                return a.first < b.first; // 수 오름차순 
            }
        });

        // 정렬하고 다시 저장해야됨 
        for(pair<int, int>& entry: list){ // O(C)
            temp[i].push_back(entry.first); 
            if(temp[i].size() >= 100) break; // 100개까지만 저장하는 거니까 100에서부터 멈춰야 한다 
            temp[i].push_back(entry.second); 
            if(temp[i].size() >= 100) break; 
        }


        // 가장 긴 행의 크기를 저장해둬야 한다. 
        maxColsize = max(maxColsize, (int)temp[i].size());  // int형 변환이 필요하다 
    }  
    
    // 그래도 100보다는 작아야 해 
    maxColsize = min(maxColsize, 100); 
    // 배열을 재저장해줘야 한다 
    for(int i=0; i<rSize; i++){
        temp[i].resize(maxColsize, 0); // [!!] 현재 크기 < maxColsize -> 뒤에 0을 붙임/ 반대면 뒤를 잘라낸다 
        // for(int j=temp[i].size(); j<maxColsize; j++){
        //     temp[i].push_back(0); 
        // }
    }

    arr = temp;

}
/*
행 개수 < 열 개수 
- C연산: 모든 열에 대한 정렬 
- 수의 등장 횟수 오름차순 - 수 오름차순
- 가장 큰 열 기준으로 모든 열의 크기 변화 (0으로 fill) 
*/
void calcC(vector<vector<int>>& map){
    // calcR 호출 후 

    // 전치 필요
}

/**
 * r*c -> c*r 배열로 전치하는 거 
 */
vector<vector<int>> transpose(vector<vector<int>>& arr){
    int rSize = arr.size(); 
    int cSize = arr[0].size(); 
    
    vector<vector<int>> res(cSize, vector<int>(rSize)); 
    for(int i=0; i<rSize; i++){
        for(int j=0; j<cSize; j++){
            res[j][i] = arr[i][j]; 
        }
    }
    return res; 
}


int main(){
    cin >> r >> c >> k; 
    r--; c--; // 1-index => 0-index로 변경 

    vector<vector<int>> arr(3, vector<int>(3,0)); 
    for(int i=0; i<3; i++){
        int a; 
        for(int j=0; j<3; j++){
            cin >> a; 
            arr[i][j] = a; 
        }
    }

    int time = 0; 
    int r_size, c_size; 
    while(time <= 100){
        if(r < arr.size() && c < arr[0].size() && arr[r][c] == k){ // 배열의 범위를 벗어날 수 있기 때문에 반드시 범위.. 
            cout << time ; 
            system("pause"); 
            return 0; 
        }
        time++; 
        r_size = arr.size(); 
        c_size = arr[0].size(); 

        if(r_size >= c_size) calcR(arr); 
        else {
            // 전치 
            arr = transpose(arr); 
            // calcR연산 
            calcR(arr); 
            // 다시 전치 연산 
            arr = transpose(arr); 
        }

        // cout << "--- after " << time << "\n"; 
        // for(int i=0; i<arr.size(); i++){
        //     for(int j=0; j<arr[0].size(); j++){
        //         cout << arr[i][j] << " "; 
        //     }
        //     cout << "\n"; 
        // }
    }

    cout << -1 ; 
    system("pause"); 
    return 0; 
}
/*
초기 배열: 3x3 

인덱스 1부터 시작 

행/열 크기가 100까지가 최대. 

목표: arr[r][c] = k가 되는 최소 시간 
100초가 지나도 A[r][c] = k가 되지 않으면 -1 출력하기

1초마다: 

행 개수 >= 열 개수
- R연산: 모든 행에 대한 정렬
- 수의 등장 횟수 오름차순 - 수 오름차순 
- {수, 등장횟수}로 다시 저장
- 가장 큰 행 기준으로 모든 행의 크기 변화 (0으로 fill)

행 개수 < 열 개수 
- C연산: 모든 열에 대한 정렬 
- 수의 등장 횟수 오름차순 - 수 오름차순
- 가장 큰 열 기준으로 모든 열의 크기 변화 (0으로 fill) 

3 1 1 -> 3: 1, 1: 2 -> 3 1 1 2 
3 1 1 2 -> 3:1 / 1:2/ 2:1 -> 2 1 3 1 1 2 


*/