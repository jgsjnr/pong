/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cena.atividade.cena;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;

/**
 *
 * @author José Silva
 */
public class Esfera {
    private double rX;
    private double rY;
    private float avancoDireita = 0;
    private float xAtual;
    private float yAtual;
    private float posicaoInicialEsfera;
    
    
    public void avancoEsfera(float xAtual, float yAtual, float posicaoInicialEsfera){
        this.xAtual += xAtual;
        this.yAtual += yAtual;
        this.posicaoInicialEsfera = posicaoInicialEsfera;
    }
    
    
    public void draw(GL2 gl){
        double limite = 2 * Math.PI;
        double i, cX = 0, cY = 0;
        
        cX = 0f;
        cY = this.posicaoInicialEsfera;
        this.rX = 0.05f;
        this.rY = 0.05f;
        gl.glPushMatrix();
        gl.glTranslatef(this.xAtual, this.yAtual, 0);
        gl.glBegin(GL2.GL_POLYGON);
            for (i = 0; i < limite; i += 0.01) {
                gl.glVertex2d(cX + this.rX * Math.cos(i),
                        cY + this.rY * Math.sin(i));
            }
        gl.glEnd();
        gl.glPopMatrix();
    }
    
}
