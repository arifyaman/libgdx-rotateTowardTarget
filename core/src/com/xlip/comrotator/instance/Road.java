package com.xlip.comrotator.instance;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.g3d.model.MeshPart;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.badlogic.gdx.utils.Array;
import com.xlip.comrotator.instance.sub.Collision;
import com.xlip.comrotator.instance.sub.Triangle;
import com.xlip.comrotator.model.GameModels;
import com.xlip.threedtemp.Objects.GameObject;

import java.util.ArrayList;

import lombok.Getter;

public class Road extends GameObject<Road> {

    @Getter
    float[] vertices;
    @Getter
    int vertexSize;

    @Getter
    short[] meshPartIndices = {};

    @Getter
    ArrayList<Vector3> terrainVertices = new ArrayList<>();


    public Road() {
        super(GameModels.road);
        setColor(Color.valueOf("F26CA7"));

        MeshPart mp = model.meshParts.get(0);
        Mesh terrainMesh = mp.mesh.copy(false);
        meshPartIndices = new short[mp.size];
        terrainMesh.getIndices(mp.offset, mp.size, meshPartIndices, 0);
        vertexSize = terrainMesh.getVertexSize() / 4;
        vertices = new float[terrainMesh.getNumVertices() * vertexSize];
        terrainMesh.getVertices(vertices);

        System.out.println(vertices.length);


        terrainVertices.clear();

        for (int i = 0; i < meshPartIndices.length; i++) {
            int i1 = meshPartIndices[i] * vertexSize;
            Vector3 v = new Vector3(vertices[i1], vertices[i1 + 1], vertices[i1 + 2]);

            // v.set(v.prj(tempM.set(transform).scl(1).rotate(Vector3.X,-90)));
            v.set(v.prj(transform));
            terrainVertices.add(v);
        }
    }


    public Collision checkIntersection(Ray ray) {
        Array<Collision> bests = new Array<>();


        for (int i = 0; i < terrainVertices.size(); i += 3) {
            Vector3 p1 = terrainVertices.get(i);
            Vector3 p2 = terrainVertices.get(i + 1);
            Vector3 p3 = terrainVertices.get(i + 2);
            Vector3 nearsest = new Vector3();

            if (Intersector.intersectRayTriangle(ray, p1, p2, p3, nearsest)) {
                bests.add(new Collision(new Triangle(p1, p2, p3), nearsest));
            }
        }

        if (bests.size == 0) return null;
        if (bests.size == 1) return bests.get(0);
        Collision bestCollision = bests.get(0);

        for (Collision collision : bests) {
            if (collision.getBest().dst(ray.origin) < bestCollision.getBest().dst(ray.origin)) {
                bestCollision = collision;
            }
        }

        return bestCollision;
    }

}
