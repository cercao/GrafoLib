package desafios.dikstra;

import java.util.LinkedList;
import java.util.Queue;

public class Dijkstra {

	static final int NUM = 9;

	public static void main(String[] args) {
		Vertex[] vexs = new Vertex[NUM];
		for (int i = 0; i < vexs.length; i++) {
			vexs[i] = new Vertex("v" + i);
		}

		int[][] e = { { 4, 7, 7 }, { 2, 8, 8 }, { 0, 1, 10 }, { 0, 5, 11 }, { 1, 8, 12 }, { 3, 7, 16 }, { 1, 6, 16 },
				{ 5, 6, 17 }, { 1, 2, 18 }, { 6, 7, 19 }, { 3, 4, 20 }, { 3, 8, 21 }, { 2, 3, 22 }, { 3, 6, 24 },
				{ 4, 5, 26 } };
		Edge1[] edges = new Edge1[e.length];
		for (int i = 0; i < edges.length; i++) {
			edges[i] = new Edge1(e[i][0], e[i][1], e[i][2]);
		}

		dijkstra(vexs, edges, 1, 4);
	}

	public static void dijkstra(Vertex[] vexs, Edge1[] edges, int start) {
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(start);

		boolean[] visited = new boolean[vexs.length];
		// 最短路径的上一个顶点下标
		int[] parent = new int[vexs.length];
		// 起点到当前顶点的最短路径
		int[] sp = new int[vexs.length];
		parent[start] = -1;
		for (int i = 0; i < sp.length; i++) {
			sp[i] = Integer.MAX_VALUE;
		}
		sp[start] = 0;

		while (!queue.isEmpty()) {
			System.out.println(queue);
			int current = queue.poll();
			visited[current] = true;
			for (Edge1 e : edges) {
				int w, s;
				if (e.begin == current && !visited[e.end]) {
					w = e.begin;
					s = e.end;
				} else if (e.end == current && !visited[e.begin]) {
					w = e.end;
					s = e.begin;
				} else {
					continue;
				}
				// 找到可用边
				int a = sp[w] + e.weight;
				int b = sp[s];
				if (a < b) {
					sp[s] = a;
					parent[s] = w;
				}
				if (!queue.contains(s)) {
					queue.offer(s);
				}
			}
		}
		for (int i = 0; i < sp.length; i++) {
			System.out.println(i + " " + sp[i]);
		}
	}

	public static void dijkstra(Vertex[] vexs, Edge1[] edges, int start, int end) {
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(start);

		boolean[] visited = new boolean[vexs.length];
		// 最短路径的上一个顶点下标
		int[] parent = new int[vexs.length];
		// 起点到当前顶点的最短路径
		int[] sp = new int[vexs.length];
		parent[start] = -1;
		for (int i = 0; i < sp.length; i++) {
			sp[i] = Integer.MAX_VALUE;
		}
		sp[start] = 0;

		while (!queue.isEmpty()) {
			int current = queue.poll();
			visited[current] = true;
			if (current == end) {
				// done
				StringBuilder sb = new StringBuilder();
				while (current != -1) {
					sb.insert(0, current + " ");
					current = parent[current];
				}
				sb.append("---- " + sp[end]);
				System.out.println(sb.toString());
				return;
			}
			for (Edge1 e : edges) {
				int w, s;
				if (e.begin == current && !visited[e.end]) {
					w = e.begin;
					s = e.end;
				} else if (e.end == current && !visited[e.begin]) {
					w = e.end;
					s = e.begin;
				} else {
					continue;
				}
				// 找到可用边
				int a = sp[w] + e.weight;
				int b = sp[s];
				if (a < b) {
					sp[s] = a;
					parent[s] = w;
				}
				if (!queue.contains(s)) {
					queue.offer(s);
				}
			}
		}
	}
}

class Vertex {
	String data;

	public Vertex(String data) {
		this.data = data;
	}
}

class Edge1 {
	int begin;
	int end;
	int weight;

	public Edge1(int begin, int end, int weight) {
		this.begin = begin;
		this.end = end;
		this.weight = weight;
	}
}