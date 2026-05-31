import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;

public class City {
    static class Location {
        String name;
        int x, y;

        public Location(String name, int x, int y){
            this.name = name;
            this.x = x;
            this.y = y;
        }
    }

    public Location[] locs = {
        new Location("KK12", 1, 9),
        new Location("KK11", 3, 6),
        new Location("KK7", 1, 5),
        new Location("KPS", 0, 0),
        new Location("KK13", 8, 2),
        new Location("KK1", 4, 1),
        new Location("KK4", 6, 7),
        };

    public int N = locs.length;
    public double[][] roads = new double[N][N];

    public City(){
        initCity();
    }

    public void initCity() {
        for (double[] row : roads){
            Arrays.fill(row, -1);
        }

        for (int i = 0; i < N;i++){
            roads[i][i] = 0;
        }

            connectRoad(0, 1);
            connectRoad(0, 2);
            connectRoad(1, 4);
            connectRoad(1, 5);
            connectRoad(2, 3);
            connectRoad(3, 5);
            connectRoad(6, 4);
            connectRoad(5, 4);
        }

    public double calculateDistance(int a, int b) {
        double dx = locs[a].x - locs[b].x;
        double dy = locs[a].y - locs[b].y;

        return (Math.sqrt(dx*dx + dy*dy));
    }

    public void connectRoad(int a, int b){
        double d = calculateDistance(a, b);
        roads[a][b] = d;
        roads[b][a] = d;
    }

    public void displayMap(){
        System.out.println("\n City Grid Map: ");
        System.out.println("  Y");
        for(int y = 9; y>=0 ; y--){
            System.out.printf("%d |", y);

            for(int x = 0 ; x < 9; x++){
                boolean found = false;
                for (int i = 0; i < N; i++){
                    if (locs[i].x==x && locs[i].y==y){
                        System.out.print("[" + i + "]");
                        found = true;
                        break;
                    }
                }
                if (found != true){
                    System.out.print(" . ");
                }
            }
            System.out.println();
        }
        System.out.println("   __________________________ X");
        System.out.println("    0  1  2  3  4  5  6  7  8\n");

        for (int i = 0; i < N; i++){
            System.out.println("[" + i + "] " + locs[i].name + " (" + locs[i].x + ", " + locs[i].y + ")");
        }
    }

    public void dijkstra(int start, int end){
        double[] dist = new double[N];
        int[] prev = new int[N];
        boolean[] visited = new boolean[N];

        Arrays.fill(dist, Double.MAX_VALUE);
        Arrays.fill(prev, -1);

        dist[start] = 0;

        for (int step = 0; step < N ; step++){
            int u = -1;
            for (int v = 0; v < N; v++){
                if (!visited[v] && (u == -1 || dist[v] < dist[u])){
                    u = v;
                }
            }

        if (u == -1 || dist[u] == Double.MAX_VALUE){
            break;
        }

        visited[u] = true;

        for (int v = 0; v < N ; v++){
            if (!visited[v] && roads[u][v] > 0){
                double newDist = dist[u] + roads[u][v];

                if (newDist < dist[v]){
                    dist[v] = newDist;
                    prev[v] = u;
                }
            }
        }
    }

    if (dist[end] == Double.MAX_VALUE){
        System.out.println("No route found");
        return;
    }

    List<String> path = new ArrayList<>();

    for (int at = end; at != -1; at = prev[at]){
        path.add(locs[at].name);
    }

    Collections.reverse(path);

    System.out.println("Route: " + String.join(" -> ", path));
    System.out.printf("Distance : %.1f km%n", dist[end]);
    }
}
