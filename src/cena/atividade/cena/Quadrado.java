/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cena.atividade.cena;

import com.jogamp.opengl.GL2;
/**
 *
 * @author José Silva
 */
public class Quadrado {
    
    
    private float tQuadrado;
    public Quadrado(float tQuadrado){
        this.tQuadrado = tQuadrado;
    }
    
    public void draw(GL2 gl){
//        gl.glPushMatrix();
//        gl.glBegin(GL2.GL_QUADS);
//            gl.glVertex2f(-1.6f+this.tQuadrado, 1.6f-this.tQuadrado);
//            gl.glVertex2f(this.tQuadrado, 1.6f-this.tQuadrado);
//            gl.glVertex2f(this.tQuadrado, this.tQuadrado);
//            gl.glVertex2f(-1.6f+this.tQuadrado, this.tQuadrado);
//        gl.glEnd();
//        gl.glPopMatrix();
//        
        gl.glPushMatrix();
        gl.glBegin(GL2.GL_QUADS);
            gl.glVertex2f(-1.6f*0.1f, 1.6f*0.1f);
            gl.glVertex2f(1.6f*0.1f, 1.6f*0.1f);
            gl.glVertex2f(1.6f*0.1f, -1.6f*0.1f);
            gl.glVertex2f(-1.6f*0.1f, -1.6f*0.1f);
        gl.glEnd();
        gl.glPopMatrix();
    }
    
    
    
}
