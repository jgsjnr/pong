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

    public float gettQuadrado() {
        return tQuadrado;
    }

    public void settQuadrado(float tQuadrado) {
        this.tQuadrado = tQuadrado;
    }
    
    
    public Quadrado(float tQuadrado){
        this.tQuadrado = tQuadrado*0.1f;
    }
    
    public void draw(GL2 gl){
        gl.glPushMatrix();
        gl.glBegin(GL2.GL_QUADS);
            gl.glVertex2f(-this.tQuadrado, this.tQuadrado);
            gl.glVertex2f(this.tQuadrado, this.tQuadrado);
            gl.glVertex2f(this.tQuadrado, -this.tQuadrado);
            gl.glVertex2f(-this.tQuadrado, -this.tQuadrado);
        gl.glEnd();
        gl.glPopMatrix();
        System.out.println(tQuadrado);
    }
    
    
    
}
