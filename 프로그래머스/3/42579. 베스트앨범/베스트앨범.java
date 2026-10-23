import java.util.*; 
class Solution {
    public int[] solution(String[] genres, int[] plays) {
        List<Integer> ans = new ArrayList<>(); 
        Map<String, Integer> gMap = new HashMap<>(); 
        Map<String, List<int[]>> pMap = new HashMap<>();
        for(int i=0; i<genres.length; i++){
            String s = genres[i]; // 장르
            Integer p = plays[i]; // 재생횟수
            gMap.put(s, gMap.getOrDefault(s, 0) + plays[i]);
            
            pMap.putIfAbsent(s, new ArrayList<>()); 
            pMap.get(s).add(new int[]{i, p});
        }
        
        List<String> genreAsc = new ArrayList<>(gMap.keySet()); 
        genreAsc.sort((a, b) -> gMap.get(b) - gMap.get(a)); 
        
        for(Map.Entry<String, List<int[]>> entry: pMap.entrySet()){
            entry.getValue().sort((a, b) -> {
                if(a[1] != b[1]) return b[1] - a[1]; 
                return a[0] - b[0]; 
            });
        }
        
        for(String s: genreAsc){
            List<int[]> tmp = pMap.get(s); 
            for(int i=0; i<(2<tmp.size()? 2: tmp.size()); i++){
                ans.add(tmp.get(i)[0]);
            }
        }
        return ans.stream().mapToInt(i -> i).toArray();
    }
}
/*
장르: 총 재생횟수
장르: [곡id, 재생횟수]
*/