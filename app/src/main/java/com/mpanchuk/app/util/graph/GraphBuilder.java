package com.mpanchuk.app.util.graph;

import com.mpanchuk.app.model.Distance;
import com.mpanchuk.app.repository.DistanceRepository;
import com.mpanchuk.app.util.model.Adjacency;
import com.mpanchuk.app.util.model.Pair;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Component
public class GraphBuilder {

    private List<Adjacency> refs;
    private HashMap<Long, ArrayList<Pair<Long, Integer>>> nodes;
    private final DistanceRepository distanceRepository ;

    @PostConstruct
    public void init() {
        convert(distanceRepository.findAll());
        buildGraph();
    }

    private void convert(List<Distance> list) {
        refs = new ArrayList<>() ;
        list.forEach(d -> refs.add(Adjacency
                .builder()
                .root(d.getCt1().getId())
                .child(d.getCt2().getId())
                .distance(d.getDistance())
                .build()));
    }

    private void buildGraph() {
        nodes = new HashMap<>() ;
        long root;
        long child;
        int distance ;

        for (Adjacency ref : refs) {
            root = ref.getRoot();
            child = ref.getChild();
            distance = ref.getDistance();
            makeRef(root, child, distance);
            makeRef(child, root, distance);
        }
    }

    private void makeRef(long root, long child, int distance) {
        ArrayList<Pair<Long, Integer>> values = nodes.getOrDefault(root, new ArrayList<>()) ;
        values.add(new Pair<>(child, distance)) ;
        nodes.put(root, values);
    }
}
