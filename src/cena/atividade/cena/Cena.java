package cena.atividade.cena;

import cena.atividade.cena.*;
import cena.atividade.cena.*;
import cena.atividade.input.*;
import cena.atividade.cena.Barra;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;
import java.util.Locale;
import java.util.Random;
/**
 *
 * @author Kakugawa
 */
public class Cena implements GLEventListener{    
    private float xMin, xMax, yMin, yMax, zMin, zMax;
    
    GLU glu;
    
    private int faseJogo;
    private boolean jogoPausado = false;
    private float velocidadeJogo = 0.0f;
    private int scoreJogo = 0;
    private int vidasJogador = 5;
    private char direcaoX = 0;
    private float xAtual = 0;
    private float yAtual = 0;
    private char ladoEsfera;
    private float tBarra;
    private boolean jogoParado = false;
    private float valorVariavel;
    private float tTela;
    
    public float tLimiteTela = 1f;
    public float translacao;
    public float rotacao;

    
    public float getVelocidadeJogo() {
        return velocidadeJogo;
    }
    
    public void setVelocidadeJogo(float velocidadeJogo) {
        this.velocidadeJogo = velocidadeJogo;
    }
    
    
    public void ressetarJogo(){
        this.velocidadeJogo = 0;
        this.vidasJogador = 0;
        this.scoreJogo = 0;
        this.xAtual = this.yAtual = 0;
        this.faseJogo = 0;
        this.direcaoX = 'X';
        this.tBarra = 0.2f;
    }
    
    public void diminuirTamanhoBarra(){
        if(faseJogo == 0 && this.tBarra >= 0.12f) this.tBarra -= 0.01f;
        if(faseJogo == 1 && this.tBarra >= 0.10f) this.tBarra -= 0.02f;
    }
    
    
    public void pausarJogo(){
        this.jogoPausado = true;
    }
    public void retornarJogoPausado(){
        this.jogoPausado = false;
    }
    
    public void retornarJogoParado(){
        this.jogoParado = false;
    }

    public boolean isJogoPausado() {
        return jogoPausado;
    }

    public void setJogoPausado(boolean jogoPausado) {
        this.jogoPausado = jogoPausado;
    }
    
    public void pararJogo(){
        this.jogoParado = true;
    }

    public boolean isJogoParado() {
        return jogoParado;
    }

    public void setJogoParado(boolean jogoParado) {
        this.jogoParado = jogoParado;
    }
    
    public void novoJogo(){
        this.xAtual = this.yAtual = this.valorVariavel = 0;
        this.direcaoX = 'X';
        this.translacao = 0;
        pararJogo();
        geradorLadoRandomico();
        
    }
   

    
    
    public void geradorLadoRandomico(){
        double xAletorio = -0.8f + Math.random() * 1.6f;
        if (xAletorio > 0) {
            this.ladoEsfera = 'e';
            sobeEsq();
        } else {
            this.ladoEsfera = 'd';
            sobeDir();
	}
    }
    
    public void sobeDir(){
        this.xAtual += 0.02f+this.velocidadeJogo;
        this.yAtual += 0.01f+valorVariavel+this.velocidadeJogo;
    }
    public void desceDir(){
        this.xAtual -= 0.02+this.velocidadeJogo;
        this.yAtual -= 0.01f+valorVariavel+this.velocidadeJogo;
    }
    public void sobeEsq(){
        this.xAtual -= 0.02+this.velocidadeJogo;
        this.yAtual += 0.01f+valorVariavel+this.velocidadeJogo;
    }
    public void desceEsq(){
        this.xAtual += 0.02+this.velocidadeJogo;
        this.yAtual -= 0.01f+valorVariavel+this.velocidadeJogo;
    }
    
    public void perdeVida(){
        --this.vidasJogador;
        if(vidasJogador == 0);
        this.scoreJogo -= 10;
        novoJogo();
    }
    
//    public void mudaDirecao(){
//        if(this.ladoEsfera == 'd') this.ladoEsfera = 'e';
//        else this.ladoEsfera = 'd';
//        this.direcaoX = 'X';
//    }
    
    public float valorVariavelAleatorio(){
        Random rd = new Random();
        float valorFinal = rd.nextFloat()/10;
        valorFinal = valorFinal <= 0.03 ? valorFinal : 0.01f;
        System.out.println(valorFinal);
        return valorFinal;
    }
    
    public void validarScore(){
        if(this.scoreJogo == 200) this.faseJogo = 2;
        if(this.scoreJogo == 2) this.velocidadeJogo = 0.07f;
    }
    
    public void colisaoBarra(){
        if(this.yAtual <= -0.1f && (this.xAtual >= this.translacao && this.xAtual <= this.translacao+this.tBarra)){
            if(this.direcaoX != 'd')this.ladoEsfera = 'd';
            this.direcaoX = 'X';
            if(this.scoreJogo < 200)this.scoreJogo += 10;
            else validarScore();
            //diminuirTamanhoBarra();
            this.valorVariavel = this.valorVariavelAleatorio();
            System.out.print("Score atual: ");
            System.out.println(this.scoreJogo);
        }
        if(this.yAtual <= -0.1f && (this.xAtual >= this.translacao-this.tBarra && this.xAtual <= this.translacao)){
            if(this.direcaoX != 'e')this.ladoEsfera = 'e';
            this.direcaoX = 'X';
            if(this.scoreJogo < 200)this.scoreJogo += 10;
            else validarScore();
            //diminuirTamanhoBarra();
            this.valorVariavel = this.valorVariavelAleatorio();
            System.out.print("Score atual: ");
            System.out.println(this.scoreJogo);
        }
    }
    
    public void colisaoQuadrado(){
       // if(this.xAtual == )
    }
    
    public void geradorDeColisao(){
        //COLISAO BARRA
        colisaoBarra();
        //TETO
        if(this.xAtual >= -this.tTela && this.yAtual >= this.tTela) direcaoX = 'T';
        //CHAO
        else if(this.xAtual >= -this.tTela && this.yAtual <=-0.2) direcaoX = 'C';
        //PAREDE ESQUERDA
        else if(this.xAtual <= -this.tTela && this.yAtual <=this.tTela) direcaoX = 'E';
        //PAREDE DIREIRA
        else if(this.xAtual >= this.tTela && this.yAtual <=this.tTela) direcaoX = 'D';
        
        else if(this.xAtual <= -this.tTela && this.yAtual >= this.tTela){this.ladoEsfera = 'e'; this.direcaoX = 'T'; System.out.println("Correção manual acionada");}
        else if(this.xAtual >= this.tTela && this.yAtual >= this.tTela){this.ladoEsfera = 'd'; this.direcaoX = 'T'; System.out.println("Correção manual acionada");}
        
        switch(ladoEsfera){
            case 'd' -> {
                switch(direcaoX){
                    case 'T' -> desceDir();
                    case 'C' -> perdeVida();
                    case 'E' -> desceEsq();
                    case 'D' -> sobeEsq();
                    default -> sobeDir();
                }
            }
            case 'e' -> {
                switch(direcaoX){
                    case 'T' -> desceEsq();
                    case 'C' -> perdeVida();
                    case 'E' -> sobeDir();
                    case 'D' -> desceDir();
                    default -> sobeEsq();
                }
            }
        }
    }
    
    
    @Override
    public void init(GLAutoDrawable drawable) {
        //dados iniciais da cena
        glu = new GLU();
        //Estabelece as coordenadas do SRU (Sistema de Referencia do Universo)
        xMin = yMin = zMin = -1f;
        xMax = yMax = zMax  = 1f;    
        this.tTela = 1.6f;
        geradorLadoRandomico();
        this.tBarra = 0.2f;
        this.faseJogo = 1;
        this.valorVariavel = valorVariavelAleatorio();
        
    }

    @Override
    public void display(GLAutoDrawable drawable) {  
        //obtem o contexto Opengl
        GL2 gl = drawable.getGL().getGL2();   
        Barra barra = new Barra(this.tBarra);
        Esfera esfera = new Esfera();
        Quadrado quadrado = new Quadrado(0.2f);
        //Tela tela = new Tela(tTela);
        
        //define a cor da janela (R, G, G, alpha)
        gl.glClearColor(0, 0, 0, 1);        
        //limpa a janela com a cor especificada
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);       
        gl.glLoadIdentity(); //lê a matriz identidade
        
        /*
            desenho da cena        
        *
        */
        
        gl.glColor3f(1,1,1); //cor branca        
        //tela.draw(gl);
        if(!jogoPausado && !jogoParado) geradorDeColisao();
        esfera.avancoEsfera(this.xAtual, this.yAtual);
        
        
        
        quadrado.draw(gl);
            
        
        
        barra.setTamanhoBarra(this.tBarra);
        barra.setTranslacao(translacao);
        barra.draw(gl);
        
        esfera.draw(gl);
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {    
        //obtem o contexto grafico Opengl
        GL2 gl = drawable.getGL().getGL2();  
        
        //evita a divisão por zero
        if(height == 0) height = 1;
        //calcula a proporção da janela (aspect ratio) da nova janela
        float aspect = (float) width / height;
        
        //seta o viewport para abranger a janela inteira
        gl.glViewport(0, 0, width, height);
                
        //ativa a matriz de projeção
        gl.glMatrixMode(GL2.GL_PROJECTION);      
        gl.glLoadIdentity(); //lê a matriz identidade
        
        //Projeção ortogonal
        //true:   aspect >= 1 configura a altura de -1 para 1 : com largura maior
        //false:  aspect < 1 configura a largura de -1 para 1 : com altura maior
        if(width >= height)            
            gl.glOrtho(xMin * aspect, xMax * aspect, yMin, yMax, zMin, zMax);
        else        
            gl.glOrtho(xMin, xMax, yMin / aspect, yMax / aspect, zMin, zMax);
                
        //ativa a matriz de modelagem
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity(); //lê a matriz identidade
        System.out.println("Reshape: " + width + ", " + height);
    }    
       
    @Override
    public void dispose(GLAutoDrawable drawable) {}         
}
