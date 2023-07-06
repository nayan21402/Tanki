package com.mygdx.game;
import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.resources.Player;
import com.mygdx.game.states.GameStateManager;
import com.mygdx.game.states.StartingState1;
import com.mygdx.game.states.fight;


public class TankStars extends Game {
		public static final int WIDTH = 1920;
		public static final int HEIGHT = 1080;
		public static final String TITLE = "TANK STARS" ;

		public static SpriteBatch batch; // sprite batches are heavy therefore we make it public so that all the classes can share
		// same sprite batch
		public static ShapeRenderer sr;
		public static GameStateManager gsm ;
		public static Texture red_tank,blue_tank,yellow_tank,title;
		public static TextureRegion[] AnimationFrames;
		public static ArrayList< Animation<TextureRegion>  > tank_Animations=new ArrayList<Animation<TextureRegion>>();
		public static Animation<TextureRegion> title_animation;
		public static int p1_choice;
		public static int p2_choice;
		public static Player player1;
		public static Player player2;
		private static TankStars tankstar=null;
		public static TankStars getInstance(){
			if(tankstar==null){
				tankstar=new TankStars();
			}
			return tankstar;
		}
		private TankStars(){

		}
		/* (non-Javadoc)
		 * @see com.badlogic.gdx.ApplicationListener#create()
		 */
		//@Override

		public void create () {
			player1 = new Player(100.0f, new com.mygdx.game.resources.Tank(100.0f, new Vector3(0, 0, 0), null));
			player2 = new Player(100.0f, new com.mygdx.game.resources.Tank(100.0f, new Vector3(0, 0, 0), null));
			batch = new SpriteBatch();
			sr=new ShapeRenderer();
			gsm = new GameStateManager();
			ScreenUtils.clear(1, 0, 0, 1);
			red_tank = new Texture("screens/red_tank.png");
			yellow_tank = new Texture("screens/yellow_tank.png");
			blue_tank = new Texture("screens/blue_tank.png");
			title=new Texture("screens/title.png");
			tank_Animations.add(animate(red_tank,red_tank.getWidth(),red_tank.getHeight(),1,18,18,18));
			tank_Animations.add(animate(blue_tank,blue_tank.getWidth(),blue_tank.getHeight(),1,18,18,18));
			tank_Animations.add(animate(yellow_tank,yellow_tank.getWidth(),yellow_tank.getHeight(),1,18,18,18));
			//title_animation = animate(title,title.getWidth(), title.getHeight(),1,72,24,72);
			gsm.push(new StartingState1(gsm));

		}
		public Animation<TextureRegion> animate(Texture tank,int width, int height,int row,int colm,float fps,int frame_count){
			TextureRegion[][] tmpFrames = TextureRegion.split(tank, width / colm,height / row);
			AnimationFrames = new TextureRegion[frame_count];
			int index = 0;
			for (int i = 0; i < row; i++)
				for (int j = 0; j < colm; j++) {
					if(index<frame_count)
					AnimationFrames[index++] = tmpFrames[i][j];
				}
			return new Animation<TextureRegion>(1f/fps , AnimationFrames);
		}

		@Override
		public void render () { 
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // renders everything from scratch
			batch.begin();
			batch.end();
			gsm.update(Gdx.graphics.getDeltaTime());

		}

		@Override
		public void dispose () {
			batch.dispose();
			red_tank.dispose();
			blue_tank.dispose();
			yellow_tank.dispose();
	
		}
	}
