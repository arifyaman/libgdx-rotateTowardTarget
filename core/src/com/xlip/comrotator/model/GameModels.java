package com.xlip.comrotator.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.ModelLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;
import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.graphics.g3d.utils.shapebuilders.BoxShapeBuilder;

public class GameModels {
    public static Model road;

    public static void init(ModelBuilder modelBuilder) {

        ModelLoader loader = new ObjLoader();


        Material material = new Material();
        material.set(new ColorAttribute(ColorAttribute.createDiffuse(Color.valueOf("#456990"))));
        material.set(new ColorAttribute(ColorAttribute.createSpecular(Color.valueOf("#F45B69"))));
        material.set(new BlendingAttribute(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA, 1));

        road = loader.loadModel(Gdx.files.internal("road.obj"));



        modelBuilder.node().id = "target";
        MeshPartBuilder meshPartBuilder = modelBuilder.part("target", GL20.GL_TRIANGLES, VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates, material);
        BoxShapeBuilder.build(meshPartBuilder, 2, 2, 2);


        modelBuilder.node().id = "pointer";
        BoxShapeBuilder.build(modelBuilder.part("pointer", GL20.GL_TRIANGLES, VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates, material),
                0.2f, 0.2f, 2);

    }
}
