import java.util.*; 
class Solution {
    public int solution(int[] mats, String[][] park) {
        int answer = 0;
        List<int[]> points = new ArrayList<>();
        for(int i=0; i<park.length; i++){
            for(int j=0; j<park[0].length; j++){
                if(park[i][j].equals("-1")){
                    points.add(new int[]{i, j}); 
                }
            }
        }
        
        // Arrays.sort(mats, (a, b) -> b-a);
        Arrays.sort(mats);
        
        // point -> mat 
        // for(int[] point: points){
        //     for(int i=mats.length-1; i>=0; i--){
        //         int w = mats[i]; 
        //         if(answer < w && check(park, point, w)){ // point에서 w길이의 돗자리를 둘 수 있는가?
        //             answer = Math.max(w, answer);
        //         }
        //     }
        // }
        // mat -> point 
        for(int i=mats.length-1; i>=0; i--){
            int w = mats[i]; 
            for(int[] point: points){
                if(check(park, point, w)){ // point에서 w길이의 돗자리를 둘 수 있는가?
                    return w; 
                }
            }
        }
        
        return -1;
    }
    public boolean check(String[][] parks, int[] point, int w){
        int r = point[0]; 
        int c = point[1]; 
        
        // 3 /3 -> 3,4,5 
        if(r+w > parks.length || c+w > parks[0].length) return false; 
        
        for(int i=r; i<r+w; i++){
            for(int j=c; j<c+w; j++){
                if(!parks[i][j].equals("-1")) return false; 
            }
        }
        return true; 
    }
}
/*
-1 인 칸을 기준으로 했을 때 

지민이가 갖고 있는 가장 큰 돗자리의 변부터 해서
nxn이 전부 -1인지 검사한다? 
가능하다고 판단했으면..? 그 칸에 최대 크기를 적어놓는다고 봐야 할까 

근데 50x50 이면 장애물들이 
살펴보게 될 칸이 최대 2500개. 
돗자리 10개 
돗자리 최대 길이 20 
20 x 20 = 400 
400x10x2500 = 100 * 10^5 = 10^7 

가장 큰 돗자리부터 놓는다 && 이미 구한 가능한 돗자리 크기보다 작으면 패스 


*/