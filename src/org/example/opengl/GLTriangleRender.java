package org.example.opengl;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLSurfaceView;

class GLTriangleRender implements GLSurfaceView.Renderer {
	float rotateTri, rotateQuad;
	int one = 0x10000;

	int[] trigger = new int[] { 0, one, 0, // �϶���
			-one, -one, 0, // �󶥵�
			one, -one, 0 // ���µ�
	};
	// �����ε�һ������
	private IntBuffer triggerBuffer;

	int[] quates = new int[] { one, one, 0, -one, -one, 0, one, -one, 0, -one,
			-one, 0 };

	// �����ε��ĸ�����
	private IntBuffer quateBuffer;

	int[] colors = new int[] { one, 0, 0, one, 0, one, 0, one, 0, 0, one, one };

	private IntBuffer colorBuffer;

	public void onDrawFrame(GL10 gl) {
		// TODO Auto-generated method stub

		// �����Ļ����Ȼ���
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		// ���õ�ǰ��ģ�͹۲����
		gl.glLoadIdentity();

		// ���� 1.5 ��λ����������Ļ 6.0
		gl.glTranslatef(-1.5f, 0.0f, -6.0f);
		// ������ת
		gl.glRotatef(rotateTri, 0.0f, 1.0f, 0.0f);

		// ���ö�������
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		// ������ɫ����
		gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
		ByteBuffer vbb = ByteBuffer.allocateDirect(colors.length * 4);
		vbb.order(ByteOrder.nativeOrder());
		colorBuffer = vbb.asIntBuffer();
		colorBuffer.put(colors);
		colorBuffer.position(0);
		gl.glColorPointer(4, GL10.GL_FIXED, 0, colorBuffer);
		vbb.clear();
		vbb = ByteBuffer.allocateDirect(trigger.length * 4);
		vbb.order(ByteOrder.nativeOrder());
		triggerBuffer = vbb.asIntBuffer();
		triggerBuffer.put(trigger);
		triggerBuffer.position(0);
		// ���������ζ���
		gl.glVertexPointer(3, GL10.GL_FIXED, 0, triggerBuffer);
		// ����������
		gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 3);

		// ���������ν���
		gl.glFinish();
		gl.glDisableClientState(GL10.GL_COLOR_ARRAY);

		/***********************/
		/* ��Ⱦ������ */
		// ���õ�ǰ��ģ�͹۲����
		gl.glLoadIdentity();

		// ���� 1.5 ��λ����������Ļ 6.0
		gl.glTranslatef(1.5f, 0.0f, -6.0f);

		// ���õ�ǰɫΪ��ɫ
		gl.glColor4f(0.5f, 0.5f, 1.0f, 1.0f);
		// ������ת
		gl.glRotatef(rotateQuad, 1.0f, 0.0f, 0.0f);
		vbb.clear();
		vbb = ByteBuffer.allocateDirect(quates.length * 4);
		vbb.order(ByteOrder.nativeOrder());
		quateBuffer = vbb.asIntBuffer();
		quateBuffer.put(quates);
		quateBuffer.position(0);
		// ���úͻ���������
		gl.glVertexPointer(3, GL10.GL_FIXED, 0, quateBuffer);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);

		// ���������ν���
		gl.glFinish();

		// ȡ����������
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);

		// �ı���ת�ĽǶ�
		rotateTri += 0.5f;
		rotateQuad -= 0.5f;
	}

	public void onSurfaceChanged(GL10 gl, int width, int height) {
		// TODO Auto-generated method stub

		float ratio = (float) width / height;
		// ����OpenGL�����Ĵ�С
		gl.glViewport(0, 0, width, height);
		// ����ͶӰ����
		gl.glMatrixMode(GL10.GL_PROJECTION);
		// ����ͶӰ����
		gl.glLoadIdentity();
		// �����ӿڵĴ�С
		gl.glFrustumf(-ratio, ratio, -1, 1, 1, 10);
		// ѡ��ģ�͹۲����
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		// ����ģ�͹۲����
		gl.glLoadIdentity();

	}

	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		// TODO Auto-generated method stub
		// ������Ӱƽ��
		gl.glShadeModel(GL10.GL_SMOOTH);
		// ��ɫ����
		gl.glClearColor(0, 0, 0, 0);
		// ������Ȼ���
		gl.glClearDepthf(122.0f);
		// ������Ȳ���
		gl.glEnable(GL10.GL_DEPTH_TEST);
		// ������Ȳ��Ե�����
		gl.glDepthFunc(GL10.GL_LEQUAL);
		// ����ϵͳ��͸�ӽ�������
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);
	}

}
