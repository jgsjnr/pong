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
public class Barra {
    
    private float tamanhoBarra;
    private float translacao;
    
    public Barra(float tamanhoBarraInicial){
        this.tamanhoBarra = tamanhoBarraInicial;
    }

    public float getTranslacao() {
        return translacao;
    }

    public void setTranslacao(float translacao) {
        this.translacao = translacao;
    }
    

    public float getTamanhoBarra() {
        return tamanhoBarra;
    }

    public void setTamanhoBarra(float tamanhoBarra) {
        this.tamanhoBarra = tamanhoBarra;
    }
    
    public void draw(GL2 gl){
        gl.glPushMatrix();
        gl.glTranslatef(translacao, 0, 0);
        gl.glBegin(GL2.GL_QUADS);
            gl.glVertex2f(-this.tamanhoBarra, -0.8f);
            gl.glVertex2f(0.0f, -0.8f);
            gl.glVertex2f(0.0f, -0.9f);
            gl.glVertex2f(-this.tamanhoBarra, -0.9f);
        gl.glEnd();
        gl.glBegin(GL2.GL_QUADS);
            gl.glVertex2f(this.tamanhoBarra, -0.8f);
            gl.glVertex2f(0.0f, -0.8f);
            gl.glVertex2f(0.0f, -0.9f);
            gl.glVertex2f(this.tamanhoBarra, -0.9f);
        gl.glEnd();  
        gl.glPopMatrix();
    }
    

    
    
    
}
