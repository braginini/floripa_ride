package com.floriparide.gtfs.writer;

import com.floriparide.gtfs.dao.NodeDao;
import com.floriparide.gtfs.dao.RelationDao;
import com.floriparide.gtfs.dao.WayDao;
import com.floriparide.gtfs.model.osm.Node;
import com.floriparide.gtfs.model.osm.Relation;
import com.floriparide.gtfs.model.Shape;
import com.floriparide.gtfs.model.osm.Way;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Mikhail Bragin
 */
public class ShapesWriter extends AbstractGTFSFileWriter<Shape> {

    RelationDao relationDao;

    WayDao wayDao;

    NodeDao nodeDao;

    int corruptedRelations = 0;

    public ShapesWriter(RelationDao relationDao, WayDao wayDao, NodeDao nodeDao) {
        this.relationDao = relationDao;
        this.wayDao = wayDao;
        this.nodeDao = nodeDao;
    }

    @Override
    protected void writeContents() {

        List<Relation> relations = getAllRouteRelations();

        relation:
        for (Relation relation : relations) {

            List<Way> ways = relation.getWays();
            List<Node> shape = new ArrayList<>();

            for (Way way : ways) {

                Way.Intersection intersection = getWayIntersection(shape, way.getNodes());
                List<Node> nodes = way.getNodes();

                switch (intersection) {

                    case LAST_FIRST:
                        if (!shape.isEmpty() && !nodes.isEmpty())
                            nodes.remove(0); //remove zero element to exclude duplicates
                        shape.addAll(nodes);
                        break;

                    case LAST_LAST:
                        Collections.reverse(nodes);
                        if (!shape.isEmpty() && !nodes.isEmpty())
                            nodes.remove(0);
                        shape.addAll(nodes);
                        break;

                    case FIRST_FIRST: //can be only after we added the first way to the shape
                        Collections.reverse(shape);
                        if (!shape.isEmpty() && !nodes.isEmpty())
                            nodes.remove(0);
                        shape.addAll(nodes);
                        break;
                    case FIRST_LAST: //other direction, can be only after we added the first way to the shape
                        Collections.reverse(shape);
                        Collections.reverse(nodes);
                        if (!shape.isEmpty() && !nodes.isEmpty())
                            nodes.remove(0);
                        shape.addAll(nodes);
                        break;
                    case NONE:
                        /*throw new RuntimeException("No way intersection in relation relationId="
                                + relation.getId() + " wayId=" + way.getId());*/
                        System.out.println("No way intersection in relation relationId="
                                + relation.getId() + " wayId=" + way.getId());
                        corruptedRelations++;
                        continue relation;
                }
            }


            PrintWriter writer = null;
            try {
                writer = new PrintWriter(new BufferedWriter(new FileWriter("shapes.txt", true)));

                int sequenceId = 0;
                for (Node node : shape) {
                    writer.println(getLine(new Shape(node.getLat(), node.getLon(), sequenceId, relation.getId())));
                    sequenceId++;
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (writer != null) {
                    writer.flush();
                    writer.close();
                }
            }
        }

        System.out.println("Corrupted relations " + corruptedRelations);
    }

    private Way.Intersection getWayIntersection(List<Node> wayNodes1, List<Node> wayNodes2) {

        if (wayNodes1.isEmpty() || wayNodes2.isEmpty())
            return Way.Intersection.LAST_FIRST;

        if (wayNodes1.get(wayNodes1.size() - 1).equals(wayNodes2.get(0)))
            return Way.Intersection.LAST_FIRST;

        if (wayNodes1.get(wayNodes1.size() - 1).equals(wayNodes2.get(wayNodes2.size() - 1)))
            return Way.Intersection.LAST_LAST;

        if (wayNodes1.get(0).equals(wayNodes2.get(0)))
            return Way.Intersection.FIRST_FIRST;

        if (wayNodes1.get(0).equals(wayNodes2.get(wayNodes2.size() - 1)))
            return Way.Intersection.FIRST_LAST;

        return Way.Intersection.NONE;
    }

    @Override
    protected void writeHeading() throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter("shapes.txt", "UTF-8");

        StringBuilder sb = new StringBuilder()
                .append("shape_id")
                .append(DELIMETER)
                .append("shape_pt_lat")
                .append(DELIMETER)
                .append("shape_pt_lon")
                .append(DELIMETER)
                .append("shape_pt_sequence")
                .append(DELIMETER)
                .append("shape_dist_traveled");

        writer.println(sb.toString());
        writer.close();
    }

    @Override
    protected String getLine(Shape shape) {
        StringBuilder sb = new StringBuilder()
                .append(shape.getRouteId())
                .append(DELIMETER)
                .append(shape.getLat())
                .append(DELIMETER)
                .append(shape.getLon())
                .append(DELIMETER)
                .append(shape.getSequenceId())
                .append(DELIMETER);

        return sb.toString();
    }

    List<Relation> getAllRouteRelations() {

        List<Relation> relations = relationDao.getRelationsWithTagSimple("route", "bus");

        for (Relation relation : relations) {
            List<Way> ways = wayDao.getWaysInRelationOrdered(relation.getId());

            for (Way way : ways) {
                way.setNodes(nodeDao.getNodesInWayOrdered(way.getId()));
            }

            relation.setWays(ways);
        }

        return relations;
    }
}
