package com.xlip.threedtemp.Menu.Object;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.xlip.threedtemp.Input.Abs.Clickable;

import lombok.Getter;

/**
 * Created by Arif on 14.07.2017.
 */

public class MenuObject extends Clickable {
    private TextureRegion enabledTexture;
    private TextureRegion clickedTexture;
    private TextureRegion disabledTexture;
    private Vector2 position;
    private boolean finisher;

    @Getter
    private GlyphLayout glyphLayout;

    private MenuObjectCallBacks menuObjectCallBacks;
    private OnClickListener onClickListener;

    private BitmapFont font;
    private String textToBeRendered;


    public MenuObject(TextureRegion texture, Vector2 position, Vector2 wh) {
        super(wh, position);
        this.enabledTexture = texture;
        this.position = position;
        this.finisher = false;
    }

    public MenuObject(BitmapFont font, String textToBeRendered, Vector2 position, Vector2 wh) {
        super(wh, position);
        this.font = font;
        this.textToBeRendered = textToBeRendered;
        this.position = position;
        this.finisher = false;
        this.glyphLayout = new GlyphLayout(font,textToBeRendered);
    }



    @Override
    public void clicked() {
        if(finisher)
            menuObjectCallBacks.finish();

        if(onClickListener != null)
        onClickListener.onClick();

    }

    public void update(float delta){

    }


    public void draw(SpriteBatch batch, float delta) {
        TextureRegion t = getEnabledTexture();

        if(isDisabled()) {
            t = getDisabledTexture();
        }else {
            if(isOnIt()) {
                t = getClickedTexture();
            }
        }
        if(t != null){
            batch.draw(t,getPosition().x,getPosition().y,getWh().getWidth(),getWh().getHeight());
        }
        if(font != null && textToBeRendered != null) {
            glyphLayout = font.draw(batch, textToBeRendered, getPosition().x, getPosition().y);
        }


        update(delta);

    }





    //GETTER SETTER


    public TextureRegion getEnabledTexture() {
        return enabledTexture;
    }

    public void setEnabledTexture(TextureRegion enabledTexture) {
        this.enabledTexture = enabledTexture;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        getWh().setPosition(position);
        this.position = position;
    }

    public TextureRegion getClickedTexture() {
        return clickedTexture;
    }

    public void setClickedTexture(TextureRegion clickedTexture) {
        this.clickedTexture = clickedTexture;
    }

    public TextureRegion getDisabledTexture() {
        return disabledTexture;
    }

    public void setDisabledTexture(TextureRegion disabledTexture) {
        this.disabledTexture = disabledTexture;
    }

    public MenuObjectCallBacks getMenuObjectCallBacks() {
        return menuObjectCallBacks;
    }

    public void setMenuObjectCallBacks(MenuObjectCallBacks menuObjectCallBacks) {
        this.menuObjectCallBacks = menuObjectCallBacks;
    }

    public boolean isFinisher() {
        return finisher;
    }

    public MenuObject setFinisher() {
        this.finisher = true;
        return this;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface MenuObjectCallBacks {
        boolean finish();

    }
}
