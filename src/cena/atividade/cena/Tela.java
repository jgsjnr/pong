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
public class Tela {
    
    
    private float tTela;
    
    public Tela(float tTela){
        this.tTela = tTela;
    }
    
    public void draw(GL2 gl){
        gl.glPushMatrix();
        gl.glBegin(GL2.GL_LINE_LOOP);
            gl.glVertex2d(-this.tTela, this.tTela);
            gl.glVertex2d(this.tTela, this.tTela);
            gl.glVertex2d(this.tTela, -this.tTela);
            gl.glVertex2d(-this.tTela, -this.tTela);
//            gl.glVertex2d(-this.tTela, this.tTela);
        gl.glEnd();
        gl.glFlush();
        gl.glPopMatrix();
    } 
}
