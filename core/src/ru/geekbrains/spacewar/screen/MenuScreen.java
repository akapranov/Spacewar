package ru.geekbrains.spacewar.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.spacewar.base.Base2DScreen;

/*
*  Экран Меню
*/
public class MenuScreen extends Base2DScreen{
    public static final float SPEED = 1.f;
    Texture backgraund;
    Texture hero;
    Vector2 position;
    Vector2 velocity;
    Vector2 touch;
    Vector2 temp;

    public MenuScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        hero = new Texture("ship.png");
        backgraund = new Texture("background.jpg");
        velocity = new Vector2(0,0);
        position = new Vector2(0,0);
        touch = new Vector2();
        temp = new Vector2();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        temp.set(touch);
        if (temp.sub(position).len() > SPEED){
            position.add(velocity);
        }else{
            position.set(touch);
            velocity.setZero();
        }
        batch.begin();
        batch.draw(backgraund,0,0, 1024,780);
        batch.draw(hero, position.x, position.y, 52.75f, 53f);
        batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();
        backgraund.dispose();
        hero.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        super.touchDown(screenX, screenY, pointer, button);
        touch.set(screenX, Gdx.graphics.getHeight() - screenY);
        velocity.set(touch.cpy().sub(position).setLength(SPEED));
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        super.touchUp(screenX, screenY, pointer, button);
        return false;
    }
}
