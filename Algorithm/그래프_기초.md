# 그래프

- 용어들
  - 정점: 그래프의 구성 요소로 하나의 연결점
  - 간선: 두 정점을 연결하는 선
  - 차수: 정점에 연결된 간선의 수
  - 그래프: 정점들의 집합과 이들을 연결하는 간선들의 집합으로 구성된 자료구조
  - 경로: 같은 정점을 거치지 않는 간선들의 순서
  - 사이클: 경로의 시작 정점과 끝 정점이 같은 경로

## 그래프 유형과 표현

- 무방향/ 유방향/ 가중치 그래프/ 사이클 없는 방향 그래프
- 완전 그래프: 정점들에 대해 가능한 모든 간선들을 가진 그래프
- 부분 그래프: 원래 그래프에서 일부의 정점이나 간선을 제외한 그래프

표현

- 간선의 정보를 저장하는 방식

1. 인접 행렬
   1. V x V 크기의 2차원 배열을 이용해서 간선 정보를 저장한다.
2. 인접 리스트
   1. 각 정점마다 다른 정점으로 나가는 간선의 정보를 저장한다.
3. 간선 리스트
   1. 간선 (s, e) 정보를 객체로 표현해 리스트에 저장

## 인접 행렬

```java
public class GraphMatrixMain{
	static int N;
	static int[][] g;		// 인접행렬
	static boolean[] v;	// 방문 여부 확인

	public static void main(String[] args) throws Exception{
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); 		// 7 입력 들어옴
		int E = sc.nextInt(); // 8

		g = new int[N][N];  	// 인접행렬
		v = new boolean[N]; 	// 방문 처리

		for(int i=0; i<E; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			g[from][to] = 1;
			g[to][from] = 1;
		}
//		dfs(0);
		bfs(0);
		sc.close();
	}
	static void dfs(int i) {
		v[i] = true;
		System.out.print((char)(i+'A') + " ");
		for(int j=0; j<N; j++) { // 앞에서부터 dfs: A B D F E C G
			if(g[i][j] != 0 && !v[j]) {
				dfs(j);
			}
		}
	}
	static void bfs(int i) {
		ArrayDeque<Integer> q = new ArrayDeque<>();
		v[i] = true;
		q.offer(i);
		while(!q.isEmpty()) {
			i = q.poll();
			System.out.print((char)(i+'A') + " ");
			for(int j=0; j<N; j++) { //  A B C D E F G
				if(g[i][j] != 0 && !v[j]) {
					v[j] = true;
					q.offer(j);
				}
			}
		}
	}
}
/* 무방향 그래프
....A0
.../.\
..B1.C2
./.\./
D3..E4
..\.|
...F5-G6

7
8
0 1
0 2
1 3
1 4
2 4
3 5
4 5
5 6

x 0 1 2 3 4 5 6
0 0 1 1 . . . .
1 1 0 . 1 1 . .
2 1 . 0 . 1 . .
3 . 1 . 0 . 1 .
4 . 1 1 . 0 1 .
5 . . . 1 1 0 1
6 . . . . . 1 0

=dfs=========
A B D F E C G : 0->N
A C E F G D B : N->0
=bfs=========
A B C D E F G : 0->N
A C B E D F G : N->0
*/
```

## 인접 리스트

- 각 정점에 대한 인접 정점들을 순차적으로 표현한다.
- 하나의 정점에 대한 인접 정점 → 각각 연결리스트 안에서 저장한다.

> [!note]
> ❓ `List<int[]>[] g` 와 `List<int[]> g`의 차이점
>
> 1. `List<int[]>[] g`: 배열 + 리스트
>
> - 인접리스트로 사용한다.
> - 정점 u별로 g[u] 안에 (v,w)이웃들이 들어감 `int[]{v,w}`
> - BFS/DFS/다익스트라/프림 등 **정점에서 확장**하는 알고리즘에 최적이다.
> - 메모리: O(N+E)
>   ![Graph기초_인접리스트1](img/image.png)
>
> 2. `List<int[]> g`
>
> - 간선 리스트로 사용한다. `int[]{u,v,w}`
> - 모든 간선을 한 바구니에 전부 담아둠
> - 크루스칼 (간선 한번에 정렬 -> 작은 것부터 union)할 때 최적
> - 메모리: O(E)
>
>   ![Graph기초_인접리스트2](img/image-1.png)

```java
public class GraphListMain{
	static int N;
	static List<Integer>[] g; // 인접 리스트
	static boolean[] v;

	public static void main(String[] args) throws Exception{
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); // 7 입력 들어옴
		int E = sc.nextInt(); // 8

		g = new List[N]; for(int i=0; i<N; i++) g[i] = new ArrayList<>();
		v = new boolean[N]; // 방문 처리

		for(int i=0; i<E; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			g[from].add(to);
			g[to].add(from);
		}

		for(List<Integer> a: g) System.out.println(a); System.out.println();
//		dfs(0);
//		bfs(0);
		sc.close();
	}
	static void dfs(int i) { // i축에서 j축으로 인접해서 나가는 방향
		v[i] = true;
		System.out.print((char)(i+'A') + " ");
		for(int j:g[i]) {
			if(!v[j])
			dfs(j);
		}
	}
	static void bfs(int i) { // i축에서 j축으로 인접해서 나가는 방향
		ArrayDeque<Integer> q = new ArrayDeque<>();
		v[i] = true;
		q.offer(i);
		while(!q.isEmpty()) {
			i = q.poll();
			System.out.print((char)(i+'A')+" ");
			for(int j: g[i]) {
				if(!v[j]) {
					v[j]=true;
					q.offer(j);
				}
			}
		}
	}
}
/*
 * 모두 인접해 있으면, 인접행렬이 낫고,
 * 그렇지 않으면, 인접리스트가 낫다
....A0
.../.\
..B1.C2
./.\./
D3..E4
..\.|
...F5-G6

// 노드는 7개, 간선 정보는 8개



=dfs=========
A B D F E C G : 0->N
A C E F G D B : N->0
=bfs=========
A B C D E F G : 0->N
A C B E D F G : N->0

0: [1, 2]
1: [0, 3, 4]
2: [0, 4]
3: [1, 5]
4: [1, 2, 5]
5: [3, 4, 6]
6: [5]
*/
```
