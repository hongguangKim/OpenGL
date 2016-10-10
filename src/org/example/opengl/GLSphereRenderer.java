package org.example.opengl;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import android.annotation.SuppressLint;
import android.opengl.EGLConfig;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;

public class GLSphereRenderer implements Renderer {

	// ������
	private final float[] mat_ambient = { 0.2f, 0.3f, 0.4f, 1.0f };
	private FloatBuffer mat_ambient_buf;
	// ƽ�������
	private final float[] mat_diffuse = { 0.4f, 0.6f, 0.8f, 1.0f };
	private FloatBuffer mat_diffuse_buf;
	// ��������
	private final float[] mat_specular = { 0.2f * 0.4f, 0.2f * 0.6f,
			0.2f * 0.8f, 1.0f };
	private FloatBuffer mat_specular_buf;

	private Sphere mSphere = new Sphere();

	public volatile float mLightX = 10f;
	public volatile float mLightY = 10f;
	public volatile float mLightZ = 10f;

	public void onDrawFrame(GL10 gl) {
		// �����Ļ����Ȼ���
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		// ���õ�ǰ��ģ�͹۲����
		gl.glLoadIdentity();

		gl.glEnable(GL10.GL_LIGHTING);
		gl.glEnable(GL10.GL_LIGHT0);

		// ����
		gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_AMBIENT,
				mat_ambient_buf);
		gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_DIFFUSE,
				mat_diffuse_buf);
		gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_SPECULAR,
				mat_specular_buf);
		// ����ָ�� 0~128 ԽСԽ�ֲ�
		gl.glMaterialf(GL10.GL_FRONT_AND_BACK, GL10.GL_SHININESS, 46.0f);
		
		
		// ��Դλ��
		float[] light_position = { mLightX, mLightY, mLightZ, 0.0f };
		ByteBuffer mpbb = ByteBuffer.allocateDirect(light_position.length * 4);
		mpbb.order(ByteOrder.nativeOrder());
		FloatBuffer mat_posiBuf = mpbb.asFloatBuffer();
		mat_posiBuf.put(light_position);
		mat_posiBuf.position(0);
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_POSITION, mat_posiBuf);

		gl.glTranslatef(0.0f, 0.0f, -3.0f);
		mSphere.draw(gl);
	}

	public void onSurfaceChanged(GL10 gl, int width, int height) {

		// ���������Ļ��С
		gl.glViewport(0, 0, width, height);

		// ����ͶӰ����
		gl.glMatrixMode(GL10.GL_PROJECTION);
		// ����ͶӰ����
		gl.glLoadIdentity();

		 GLU.gluPerspective(gl, 90.0f, (float) width / height, 0.1f, 50.0f);

		// ѡ��ģ�͹۲����
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		
		// ����ģ�͹۲����
		gl.glLoadIdentity();

	}

	private void initBuffers() {
		ByteBuffer bufTemp = ByteBuffer.allocateDirect(mat_ambient.length * 4);
		bufTemp.order(ByteOrder.nativeOrder());
		mat_ambient_buf = bufTemp.asFloatBuffer();
		mat_ambient_buf.put(mat_ambient);
		mat_ambient_buf.position(0);

		bufTemp = ByteBuffer.allocateDirect(mat_diffuse.length * 4);
		bufTemp.order(ByteOrder.nativeOrder());
		mat_diffuse_buf = bufTemp.asFloatBuffer();
		mat_diffuse_buf.put(mat_diffuse);
		mat_diffuse_buf.position(0);

		bufTemp = ByteBuffer.allocateDirect(mat_specular.length * 4);
		bufTemp.order(ByteOrder.nativeOrder());
		mat_specular_buf = bufTemp.asFloatBuffer();
		mat_specular_buf.put(mat_specular);
		mat_specular_buf.position(0);
	}

	public void onSurfaceCreated(GL10 gl,
			javax.microedition.khronos.egl.EGLConfig arg1) {
		// TODO Auto-generated method stub
		// ��������ɫ
		gl.glClearColor(0, 0, 0, 0);
		// ������Ӱƽ��
		gl.glShadeModel(GL10.GL_SMOOTH);
		// ��λ��Ȼ���
		gl.glClearDepthf(1.0f);
		// ������Ȳ���
		gl.glEnable(GL10.GL_DEPTH_TEST);
		// ������Ȳ��Ե�����
		gl.glDepthFunc(GL10.GL_LEQUAL);
		// ��͸�ӽ�������
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);

		initBuffers();
	}
}
