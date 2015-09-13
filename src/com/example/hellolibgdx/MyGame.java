package com.example.hellolibgdx;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane.ScrollPaneStyle;

public class MyGame implements ApplicationListener {
	Stage stage;
	ScrollPane pane;//定义一个ScrollPane类对象
	ScrollPaneStyle style;//ScrolPaneStyle指的是ScrollPane的风格
	Group group;
	Image images[];
	//以下变量用于实现使用按钮来操控滚动条
	TextureAtlas atlas;//按钮的素材.
	Image zuojiantouImage;//左箭头
	Image youjiantouImage;//右箭头
	float deltaX = 250;//每次点击箭头滚动条移动的举例
	@Override
	public void create() {
		stage = new Stage(480, 800, false);
		group = new Group();
		group.setSize(256*30, 256);//横向滚动条
//		group.setSize(256, 512*30);//纵向滚动条
		images = new Image[30];
		int i;
		for(i = 0 ; i < 30 ; ++i){
			images[i] = new Image(new Texture(Gdx.files.internal("data/lengjiao1.png")));
//			images[i] = new Image(new Texture(Gdx.files.internal("data/test1.jpg")));
			images[i].setPosition(i*256, 0);//横向滚动条
//			images[i].setPosition(0, i*512);//纵向滚动条
			group.addActor(images[i]);
 		}
		style = new ScrollPaneStyle();//初始化一个ScrollPaneStyle
		style.background = null;//把背景设成null,即这个滚动条不需要背景
		pane = new ScrollPane(group, style);
		pane.setSize(480, 256);//横向滚动条
//		pane.setScrollingDisabled(false, true);//设置是否可上下、左右移动..这里设置了横向可移动、纵向不可移动..  
//		pane.setSize(256,800);
		//以上代码是滚动条的代码。以下是为了操控滚动条新加的代码
		atlas = new TextureAtlas(Gdx.files.internal("data/event.atlas"));
		zuojiantouImage = new Image(atlas.findRegion("zuo"));//初始化左箭头
		youjiantouImage = new Image(atlas.findRegion("you"));//初始化右箭头
		zuojiantouImage.setPosition(100, 500);//设置左箭头的位置
		youjiantouImage.setPosition(220, 500);//设置有箭头的位置
		zuojiantouImage.setColor(Color.YELLOW);
		youjiantouImage.setColor(Color.YELLOW);
		zuojiantouImage.addListener(new InputListener(){//给左箭头添加点击事件
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				float positionX = pane.getScrollX();//滚动条向左滚动
				pane.setScrollX(positionX - deltaX);
				return true;
			}
		});
		youjiantouImage.addListener(new InputListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				float positionX = pane.getScrollX();
				pane.setScrollX(positionX + deltaX);
				return true;
			}
		});
		stage.addActor(pane);
		stage.addActor(zuojiantouImage);//将左箭头添加到舞台上
		stage.addActor(youjiantouImage);//将右箭头添加到舞台上
		Gdx.input.setInputProcessor(stage);
	}
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
	}
	@Override
	public void pause() {
		// TODO Auto-generated method stub
	}
	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);// 设置背景为白色
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);// 清屏
		stage.act();
		stage.draw();
	}
	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub
	}
	@Override
	public void resume() {
		// TODO Auto-generated method stub
	}
}
