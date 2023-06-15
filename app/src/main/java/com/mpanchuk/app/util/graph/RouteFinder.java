package com.mpanchuk.app.util.graph;

import com.mpanchuk.app.model.City;
import com.mpanchuk.app.util.model.Pair;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@Component
public class RouteFinder {

    private GraphBuilder graphBuilder ;

    @Autowired
    public RouteFinder(GraphBuilder graphBuilder) {
        this.graphBuilder = graphBuilder ;
    }
    public int[] findRoute(Long destinationId, Set<City> from) {
        int[][] matrix = makeMatrix(graphBuilder.getNodes());
        int[] routes = algorithm(Math.toIntExact(destinationId) - 1, matrix) ;

        int min = from.stream()
                .mapToInt(city -> (int) (city.getId() - 1))
                .map(idx -> routes[idx])
                .min()
                .orElse(Integer.MAX_VALUE);

        int minFromId = from.stream()
                .mapToInt(city -> (int) (city.getId() - 1))
                .filter(idx -> routes[idx] == min)
                .findFirst()
                .orElse(-1);

        return new int[]{minFromId + 1, min} ;
    }
    private int[] algorithm(int from, int[][] matrix) {
        int n = matrix.length;
        int[] dist = new int[n];
        boolean[] visited = new boolean[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[from] = 0;
        for (int i = 0; i < n - 1; i++) {
            int u = findMinDist(dist, visited);
            visited[u] = true;

            for (int v = 0; v < n; v++) {
                if (!visited[v] && matrix[u][v] != 0 && dist[u] != Integer.MAX_VALUE) {
                    int newDist = dist[u] + matrix[u][v];
                    if (newDist < dist[v]) {
                        dist[v] = newDist;
                    }
                }
            }
        }

        return dist;
    }
    private int findMinDist(int[] dist, boolean[] visited) {
        int minDist = Integer.MAX_VALUE;
        int minNode = -1;
        for (int i = 0; i < dist.length; i++) {
            if (!visited[i] && dist[i] < minDist) {
                minDist = dist[i];
                minNode = i;
            }
        }
        return minNode;
    }

    private int[][] makeMatrix(HashMap<Long, ArrayList<Pair<Long, Integer>>> nodes) {
        int size = nodes.size() ;
        int[][] matrix = new int[size][size] ;

        for (Map.Entry<Long, ArrayList<Pair<Long, Integer>>> entry : nodes.entrySet()) {
            int row = Math.toIntExact(entry.getKey());
            entry.getValue().forEach(pair -> matrix[row - 1][Math.toIntExact(pair.getFirst()) - 1] = pair.getSecond());
        }
        return matrix ;
    }
}
