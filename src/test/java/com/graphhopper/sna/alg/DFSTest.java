/**
 * GraphHopper-SNA implements a collection of social network analysis
 * algorithms. It is based on the <a
 * href="http://graphhopper.com/">GraphHopper</a> library.
 *
 * GraphHopper-SNA is distributed under the GPL 3 license. It is produced by the
 * "Atelier SIG" team of the <a href="http://www.irstv.fr">IRSTV Institute</a>,
 * CNRS FR 2488.
 *
 * Copyright 2012 IRSTV (CNRS FR 2488)
 *
 * GraphHopper-SNA is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 *
 * GraphHopper-SNA is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * GraphHopper-SNA. If not, see <http://www.gnu.org/licenses/>.
 */
package com.graphhopper.sna.alg;

import com.graphhopper.sna.data.DFSInfo;
import com.graphhopper.sna.model.DirectedPseudoG;
import com.graphhopper.sna.model.Edge;
import org.jgrapht.graph.AsUndirectedGraph;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

/**
 * Tests the DFS algorithm on (un)directed graphs. Note: Both the choice of
 * start vertex and the order in which neighbors are visited depend on the
 * underlying graph structure. (In the below examples, the start node seems to
 * be the first node added to the graph when the graph is created.) To specify
 * the start vertex, use {@link DFSRootNode}.
 *
 * @author Adam Gouge
 */
public class DFSTest {

    @Test
    public void testDFSDirected() {
        DirectedPseudoG<DFSInfo, Edge> graph =
                prepareGraph();

        new DFS<DFSInfo, Edge>(graph).calculate();

        DFSInfo one = graph.getVertex(1);
        DFSInfo two = graph.getVertex(2);
        DFSInfo three = graph.getVertex(3);
        DFSInfo four = graph.getVertex(4);
        DFSInfo five = graph.getVertex(5);
        DFSInfo six = graph.getVertex(6);

        assertTrue(one.getDiscoveryTime() == 1);
        assertTrue(one.getFinishingTime() == 8);
        assertTrue(two.getDiscoveryTime() == 2);
        assertTrue(two.getFinishingTime() == 7);
        assertTrue(three.getDiscoveryTime() == 3);
        assertTrue(three.getFinishingTime() == 6);
        assertTrue(four.getDiscoveryTime() == 4);
        assertTrue(four.getFinishingTime() == 5);
        assertTrue(five.getDiscoveryTime() == 9);
        assertTrue(five.getFinishingTime() == 12);
        assertTrue(six.getDiscoveryTime() == 10);
        assertTrue(six.getFinishingTime() == 11);
    }

    @Test
    public void testDFSUndirected() {

        // Note: The traveral order is a little different than if we had
        // directly constructed a PseudoG, but traversal order is in general
        // not unique in DFS.
        AsUndirectedGraph<DFSInfo, Edge> graph =
                new AsUndirectedGraph<DFSInfo, Edge>(prepareGraph());

        new DFS<DFSInfo, Edge>(graph).calculate();

        // Note: Cannot use graph.getVertex(int) because AsUndirectedGraph
        // is not a keyed graph!
        for (DFSInfo node : graph.vertexSet()) {
            if (node.getID() == 1) {
                assertTrue(node.getDiscoveryTime() == 1);
                assertTrue(node.getFinishingTime() == 12);
            } else if (node.getID() == 2) {
                assertTrue(node.getDiscoveryTime() == 2);
                assertTrue(node.getFinishingTime() == 11);
            } else if (node.getID() == 3) {
                assertTrue(node.getDiscoveryTime() == 4);
                assertTrue(node.getFinishingTime() == 5);
            } else if (node.getID() == 4) {
                assertTrue(node.getDiscoveryTime() == 3);
                assertTrue(node.getFinishingTime() == 10);
            } else if (node.getID() == 5) {
                assertTrue(node.getDiscoveryTime() == 6);
                assertTrue(node.getFinishingTime() == 9);
            } else if (node.getID() == 6) {
                assertTrue(node.getDiscoveryTime() == 7);
                assertTrue(node.getFinishingTime() == 8);
            }
        }
    }

    /**
     * Prepares the graph to be used in BFS tests.
     *
     * @return The graph.
     */
    protected DirectedPseudoG<DFSInfo, Edge> prepareGraph() {
        DirectedPseudoG<DFSInfo, Edge> graph = null;
        try {
            graph = new DirectedPseudoG<DFSInfo, Edge>(
                    DFSInfo.class, Edge.class);
            graph.addEdge(1, 2);
            graph.addEdge(1, 3);
            graph.addEdge(2, 3);
            graph.addEdge(3, 4);
            graph.addEdge(4, 2);
            graph.addEdge(5, 4);
            graph.addEdge(5, 6);
            graph.addEdge(6, 6);
        } catch (NoSuchMethodException ex) {
        }
        return graph;
    }
}
