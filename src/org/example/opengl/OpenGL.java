/***
 * Excerpted from "Hello, Android!",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/eband for more book information.
 ***/

package org.example.opengl;

import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.egl.EGLConfig;
import android.app.Activity;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.Window;

public class OpenGL extends Activity implements OnGestureListener {
	// ������ת�Ƕ�
	private float anglex = 0f;
	private float angley = 0f;
	static final float ROTATE_FACTOR = 90f;
	// �������Ƽ����ʵ��
	GestureDetector detector;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		// ȥ������
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
//		// ����һ��GLSurfaceView��������ʾOpenGL���Ƶ�ͼ��
//		GLSurfaceView glView = new GLSurfaceView(this);
//		// ����GLSurfaceView�����ݻ�����
//		GLRenderer myRender = new GLRenderer(this);
//		// ΪGLSurfaceView���û�����
//		glView.setRenderer(myRender);
//		setContentView(glView);
//		// �������Ƽ����
//		detector = new GestureDetector(this);

		 OpenGLView mOpenGLView = new OpenGLView(this);
		 setContentView(mOpenGLView);
	}

	@Override   
	public boolean onTouchEvent(MotionEvent event) {
		// ����Activity�ϵĴ����¼�����GestureDetector����
		return detector.onTouchEvent(event);
	}

	// �û��ᴥ����������1��MotionEvent ACTION_DOWN����
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("onDown");
		return false;
	}

	/*
	 * �û����´������������ƶ����ɿ�����1��MotionEvent ACTION_DOWN, ���ACTION_MOVE,
	 * 1��ACTION_UP����(non-Javadoc)
	 * Fling�¼��Ĵ�����룺���˵�һ������Fling��ACTION_DOWN�����һ��ACTION_MOVE�а������������Ϣ��
	 * �����ǻ����Ը����û���X�����Y���ϵ��ƶ��ٶ���Ϊ����
	 * ��������Ĵ��������Ǿ����û��ƶ�����100�����أ���X����ÿ����ƶ��ٶȴ���200����ʱ�Ž��д���
	 * 
	 * @see android.view.GestureDetector.OnGestureListener#onFling(android.view.
	 * MotionEvent, android.view.MotionEvent, float, float)
	 * ��������У�tv.setLongClickable( true )�Ǳ���ģ���Ϊ
	 * ֻ��������view���ܹ�����ͬ��Tap���ᴥ����hold����ACTION_MOVE�����߶��ACTION_DOWN��
	 * ������ͬ������ͨ��layout�����е�android:longClickable��������һ��
	 */
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		System.out.println("onFling");
		// �������ͣ�
		// e1����1��ACTION_DOWN MotionEvent
		// e2�����һ��ACTION_MOVE MotionEvent
		// velocityX��X���ϵ��ƶ��ٶȣ�����/��
		// velocityY��Y���ϵ��ƶ��ٶȣ�����/��
		velocityX = e1.getX() - e2.getX();
		velocityY = e1.getY() - e2.getY();
		velocityX = velocityX > 4000 ? 4000 : velocityX;
		velocityX = velocityX < -4000 ? -4000 : velocityX;
		velocityY = velocityY > 4000 ? 4000 : velocityY;
		velocityY = velocityY < -4000 ? -4000 : velocityY;
		// ���ݺ����ϵ��ٶȼ�����Y����ת�ĽǶ�
		angley += -velocityX * ROTATE_FACTOR / 4000;
		// ���������ϵ��ٶȼ�����X����ת�ĽǶ�
		anglex += -velocityY * ROTATE_FACTOR / 4000;
		return true;
	}

	// Touch�˲��ƶ�һֱTouch downʱ����
	public void onLongPress(MotionEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("onLongPress");
	}

	// �û����´����������϶�����1��MotionEvent ACTION_DOWN, ���ACTION_MOVE����
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		// TODO Auto-generated method stub
		System.out.println("onScroll");
		return false;
	}

	/*
	 * Touch�˻�û�л���ʱ���� (1)onDownֻҪTouch Downһ�����̴��� (2)Touch
	 * Down���һ��û�л����ȴ���onShowPress�ٴ���onLongPress So: Touch Down��һֱ��������onDown ->
	 * onShowPress -> onLongPress���˳�򴥷���
	 */
	public void onShowPress(MotionEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("onShowPress");
	}

	/*
	 * ��������������Touch Down����û�л���(onScroll)����û�г���(onLongPress)��Ȼ��Touch Upʱ����
	 * ���һ�·ǳ����(������)Touch Up: onDown->onSingleTapUp->onSingleTapConfirmed
	 * ���һ����΢�����(������)Touch Up://ȷ���ǵ����¼�����
	 * onDown->onShowPress->onSingleTapUp->onSingleTapConfirmed
	 */
	public boolean onSingleTapUp(MotionEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("onSingleTopUp");
		return false;
	}

	class GLRenderer implements GLSurfaceView.Renderer {
		private final Context context;
		private final GLCube cube = new GLCube();

		GLRenderer(Context context) {
			this.context = context;
		}

		public void onSurfaceCreated(GL10 gl, EGLConfig config) {
			// Define the lighting
			float lightAmbient[] = new float[] { 0.2f, 0.2f, 0.2f, 1 };
			float lightDiffuse[] = new float[] { 1, 1, 1, 1 };
			float[] lightPos = new float[] { 1, 1, 1, 1 };
			gl.glEnable(GL10.GL_LIGHTING);
			gl.glEnable(GL10.GL_LIGHT0);
			gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_AMBIENT, lightAmbient, 0);
			gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_DIFFUSE, lightDiffuse, 0);
			gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_POSITION, lightPos, 0);

			// What is the cube made of?
			float matAmbient[] = new float[] { 1, 1, 1, 1 };
			float matDiffuse[] = new float[] { 1, 1, 1, 1 };
			gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_AMBIENT,
					matAmbient, 0);
			gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_DIFFUSE,
					matDiffuse, 0);

			// Set up any OpenGL options we need
			gl.glEnable(GL10.GL_DEPTH_TEST);
			gl.glDepthFunc(GL10.GL_LEQUAL);
			gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

			gl.glDisable(GL10.GL_DEPTH_TEST);
			gl.glEnable(GL10.GL_BLEND);
			gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE);

			// Enable textures
			gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
			gl.glEnable(GL10.GL_TEXTURE_2D);

			// Load the cube's texture from a bitmap
			GLCube.loadTexture(gl, context, R.drawable.android);

		}

		public void onSurfaceChanged(GL10 gl, int width, int height) {
			// Define the view frustum
			gl.glViewport(0, 0, width, height);
			gl.glMatrixMode(GL10.GL_PROJECTION);
			gl.glLoadIdentity();
			float ratio = (float) width / height;
			GLU.gluPerspective(gl, 45.0f, ratio, 1, 100f);

		}

		public void onDrawFrame(GL10 gl) {
			// Clear the screen to black
			gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

			// Position model so we can see it
			gl.glMatrixMode(GL10.GL_MODELVIEW);
			gl.glLoadIdentity();
			gl.glTranslatef(0, 0, -3.0f);

			gl.glRotatef(angley, 0, 1, 0);
			gl.glRotatef(anglex, 1, 0, 0);

			// Draw the model
			cube.draw(gl);
		}
	}
}
