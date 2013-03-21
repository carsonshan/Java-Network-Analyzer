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
package com.graphhopper.sna.data;

/**
 * {@link NodeBetweennessInfo} for weighted graphs.
 *
 * All distances are {@code double}s; we initialize them to
 * {@link Double#POSITIVE_INFINITY}.
 *
 * @author Adam Gouge
 */
public class WeightedNodeBetweennessInfo extends NodeBetweennessInfo
        implements DistanceInfoDouble {

    /**
     * Length of a shortest path starting from a certain source leading to this
     * node (Dijkstra).
     */
    private double distance;

    public WeightedNodeBetweennessInfo() {
        distance = Double.POSITIVE_INFINITY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reset() {
        super.reset();
        distance = Double.POSITIVE_INFINITY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSource() {
        super.setSource();
        distance = 0.0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getDistance() {
        return distance;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDistance(double newDistance) {
        distance = newDistance;
    }
}
