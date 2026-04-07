#include <iostream>
#include <vector> 
using namespace std;

int n; 
int toFind; 

// 상 우 하 좌 
vector<int> di = {-1, 0, 1, 0}; 
vector<int> dj = {0, 1, 0, -1}; 

int main() {
    cin >> n; 
    cin >> toFind; 

    vector<vector<int>> graph(n, vector<int>(n,0)); 

    int r = n/2, c = n/2; 
    graph[r][c] = 1; 

    // cout << r << ", " << c << " : " << graph[r][c] << "\n";

    int len = 1; 
    int num = 2; 
    int d = 0; 
    while(num <= n*n){
        // 하나의 len당 2개를 진행해야 함
        for(int turn=0; turn<2; turn++){
            for(int i=0; i<len; i++){
                r = r + di[d]; 
                c = c + dj[d]; 
                if(r<0 || r>=n || c<0 || c>=n) continue; 
                // cout << r << ", " << c << " len: " << len << "\n";
                graph[r][c] = num++; 
            }
            d = (d+1)%4; 
        }
        len++; 
    }

    int fi = -1, fj = -1; 
    for(int i=0; i<n; i++){ 
        for(int j=0; j<n; j++){
            if(graph[i][j] == toFind) {
                fi = i; fj = j; 
            }
            cout << graph[i][j] << " "; 
        }
        cout << "\n"; 
    }

    cout << fi+1 << " " << fj+1 ; 

    // system("pause");
    return 0;
}