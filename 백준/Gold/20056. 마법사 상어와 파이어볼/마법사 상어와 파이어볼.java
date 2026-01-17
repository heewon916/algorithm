import java.io.*;
import java.util.*;
public class Main{
    static class Fireball {
        int r, c, m, s, d;

        @Override
        public String toString() {
            return "Fireball{" +
                    " m=" + m +
                    ", s=" + s +
                    ", d=" + d +
                    '}';
        }

        public Fireball(int r, int c, int m, int s, int d) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;

        }
    }
    static int N, M, K; // nxn m개의 파이어볼 k번 명령
    static int[][] d = {{-1, 0}, {-1,1}, {0,1}, {1,1}, {1,0}, {1,-1}, {0, -1}, {-1, -1}};
    static Map<Integer, ArrayList<Fireball>> map; // (r,c)에 있는 파이어볼 리스트
    static Map<Integer, ArrayList<Fireball>> temp;
    static Fireball tempFire;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new HashMap<>();
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int pos = r*100 + c;
            if(!map.containsKey(pos)){
                ArrayList<Fireball> fires = new ArrayList<>();
                fires.add(new Fireball(r,c,m,s,d));
                map.put(pos, fires);
            }else{
                ArrayList<Fireball> temp = map.get(pos);
                temp.add(new Fireball(r,c,m,s,d));
                map.put(pos, temp);
            }
        }
        
        for(int k=0; k<K; k++){
            temp = new HashMap<>();
            // r,c에 있는 파이어볼들을 움직인다.
            for(Map.Entry<Integer, ArrayList<Fireball>> entry: map.entrySet()){
                int r = entry.getKey() / 100; // 현재 위치
                int c = entry.getKey() % 100;

                for(Fireball fire: entry.getValue()){
                    // 순환구조: 현재좌표 + N + 이동방향*(속력%N) 을 N으로 나눈 거
                    int nr = (r + N + d[fire.d][0] * (fire.s%N)) % N;
                    int nc = (c + N + d[fire.d][1] * (fire.s%N)) % N;

                    int newPos = nr*100 + nc;
                    if(!temp.containsKey(newPos)){
                        ArrayList<Fireball> fires = new ArrayList<>();
                        fires.add(new Fireball(nr, nc, fire.m, fire.s, fire.d));
                        temp.put(newPos, fires);
                    }else{
                        ArrayList<Fireball> fires = temp.get(newPos);
                        fires.add(new Fireball(nr, nc, fire.m, fire.s, fire.d));
                        temp.put(newPos, fires);
                    }
                }
            }
            map = temp;
            // map에서 2개 이상 있는 칸에 대해서
            // 1. 하나로 합치고 4개로 나누기
            temp = new HashMap<>();
            for(Map.Entry<Integer, ArrayList<Fireball>> entry: map.entrySet()){
                int size = entry.getValue().size();
                int r = entry.getKey() / 100;
                int c = entry.getKey() % 100;
                if(size == 1) {
                    temp.put(entry.getKey(), entry.getValue()); // 그대로 대입
                    continue;
                }
                // 2개 이상 있는 경우
                int newM = 0, newS = 0, newD = 0;
                for(Fireball fire: entry.getValue()){
                    newM += fire.m;
                    newS += fire.s;
                    newD += (fire.d)%2; // 전부 짝수면 결과가 0. 전부 홀수면 결과가 count값일 것
                }
                newM = (int) Math.floor(newM / 5);
                newS = (int) Math.floor(newS / size);
                if(newD == 0 || newD == size) newD = 0; // 방향이 0,2,4,6으로 나뉠 것
                else newD = 1; // 1,3,5,7로 나뉠 것

                // newM == 0이면 소멸
                if(newM > 0){
                    ArrayList<Fireball> fires = new ArrayList<>();
                    for(int i=0; i<4; i++){
                        fires.add(new Fireball(r, c, newM, newS, newD + i*2));
                    }
                    temp.put(entry.getKey(), fires);
                }
            }
            map = temp;
        }
        int result = 0;
        for(Map.Entry<Integer, ArrayList<Fireball>> entry: map.entrySet()){
            for(Fireball f: entry.getValue()){
                result += f.m;
            }
        }
        System.out.println(result);


    }
}
