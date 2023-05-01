package cena.atividade.cena;

import cena.atividade.cena.*;
import cena.atividade.cena.*;
import cena.atividade.input.*;
import cena.atividade.cena.Barra;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.awt.TextRenderer;
import java.awt.Color;
import java.awt.Font;
import java.util.Random;
/**
 *
 * @author Kakugawa
 */
public class Cena implements GLEventListener{    
    private float xMin, xMax, yMin, yMax, zMin, zMax;
    
    GLU glu;
    
    private TextRenderer textRenderer;
    
    private int mode;
   
    String m = mode == GL2.GL_LINE ? "LINE" : "FILL";
    
    
    
    private int faseJogo;
    private boolean jogoPausado = false;
    private float velocidadeJogo = 0.0f;
    private int scoreJogo = 0;
    private int vidasJogador = 5;
    private char direcaoX = 0;
  //  private float xAtual = 0;
 //   private float yAtual = 0;
    private char ladoEsfera;
    private float tBarra;
    private boolean jogoParado = false;
    private float tTela;
    private final float posicaoInicialEsfera = -0.7f;
    private float localizacaoQuadrado;
    
    public float tLimiteTela = 1f;
    public float translacao;
    public float rotacao;
    
    Barra barra = new Barra(this.tBarra);
    Esfera esfera = new Esfera();
    Quadrado quadrado = new Quadrado();

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
        esfera.setxAtual(0);
        esfera.setyAtual(0);
        esfera.setValorVariavel(0);
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
        esfera.setxAtual(0);
        esfera.setyAtual(0);
        esfera.setValorVariavel(0);
        this.direcaoX = 'X';
        this.translacao = 0;
        pararJogo();
        geradorLadoRandomico();
        
    }
    
    public void geradorLadoRandomico(){
        double xAletorio = -0.8f + Math.random() * 1.6f;
        if (xAletorio > 0) {
            this.ladoEsfera = 'e';
            esfera.sobeEsq();
        } else {
            this.ladoEsfera = 'd';
            esfera.sobeDir();
	}
    }
    
    public void perdeVida(){
        --this.vidasJogador;
        if(vidasJogador == 0);
        this.scoreJogo -= 10;
        novoJogo();
    }
    
    public void mudaDirecao(){
        if(this.ladoEsfera == 'd') this.ladoEsfera = 'e';
        else this.ladoEsfera = 'd';
        this.direcaoX = 'T';
    }
    
    public float valorVariavelAleatorio(){
        Random rd = new Random();
        float valorFinal = rd.nextFloat()/10;
        valorFinal = valorFinal <= 0.03 ? valorFinal : 0.01f;
        return valorFinal;
    }
    
    public void validarScore(){
        if(this.scoreJogo == 200) this.faseJogo = 2;
        if(this.scoreJogo == 2) this.velocidadeJogo = 0.07f;
    }
    
    public void colisaoBarra(){
        if(esfera.getyAtual() <= -0.1f && (esfera.getxAtual() >= this.translacao && esfera.getxAtual() <= this.translacao+this.tBarra)){
            if(this.direcaoX != 'd')this.ladoEsfera = 'd';
            this.direcaoX = 'X';
            if(this.scoreJogo < 200)this.scoreJogo += 10;
            else validarScore();
            //diminuirTamanhoBarra();
            esfera.setValorVariavel(this.valorVariavelAleatorio());
        }
        if(esfera.getyAtual() <= -0.1f && (esfera.getxAtual() >= this.translacao-this.tBarra && esfera.getxAtual() <= this.translacao)){
            if(this.direcaoX != 'e')this.ladoEsfera = 'e';
            this.direcaoX = 'X';
            if(this.scoreJogo < 200)this.scoreJogo += 10;
            else validarScore();
            //diminuirTamanhoBarra();
            esfera.setValorVariavel(this.valorVariavelAleatorio());
        }
    }
    
    public void colisaoQuadrado(){
        
        //if(esfera.getxAtual() > -quadrado.gettQuadrado() && esfera.getxAtual() < quadrado.gettQuadrado());
        //if(esfera.getyAtual() < quadrado.gettQuadrado());
        //if(esfera.getyAtual() >= 3*quadrado.gettQuadrado() && (esfera.getxAtual() <= -quadrado.gettQuadrado() && esfera.getxAtual() >= quadrado.gettQuadrado())) mudaDirecao();
        //else if(esfera.getyAtual() >= quadrado.gettQuadrado() && (esfera.getxAtual() <= -quadrado.gettQuadrado() && esfera.getxAtual() >= quadrado.gettQuadrado()))mudaDirecao();
        //else if(esfera.getxAtual() <= -quadrado.gettQuadrado() && (esfera.getyAtual() <= quadrado.gettQuadrado() && esfera.getyAtual() <= 3*quadrado.gettQuadrado())) mudaDirecao();
        //else if(esfera.getxAtual() >= quadrado.gettQuadrado() && (esfera.getyAtual() <= quadrado.gettQuadrado() && esfera.getyAtual() <= 3*quadrado.gettQuadrado())) mudaDirecao();
    }
    
    public void colisaoParedes(){
        //TETO
        if(esfera.getxAtual() >= -this.tTela && esfera.getyAtual() >= this.tTela) direcaoX = 'T';
        //CHAO
        else if(esfera.getxAtual() >= -this.tTela && esfera.getyAtual() <=-0.2) direcaoX = 'C';
        //PAREDE ESQUERDA
        else if(esfera.getxAtual() <= -this.tTela && esfera.getyAtual() <=this.tTela) direcaoX = 'E';
        //PAREDE DIREIRA
        else if(esfera.getxAtual() >= this.tTela && esfera.getyAtual() <=this.tTela) direcaoX = 'D';
        //ACIMA DE -1 e 1
        else if(esfera.getxAtual() <= -this.tTela && esfera.getyAtual() >= this.tTela){this.ladoEsfera = 'e'; this.direcaoX = 'T';}
        //ACIMA DE 1 e 1
        else if(esfera.getxAtual() >= this.tTela && esfera.getyAtual() >= this.tTela){this.ladoEsfera = 'd'; this.direcaoX = 'T';}
    }
    
    public void geradorDeColisao(){
        //COLISAO BARRA
        colisaoBarra();
        //COLISAO PAREDES
        colisaoParedes();
        //COLSIAO QUADRADO
        colisaoQuadrado();
        
        
        switch(ladoEsfera){
            case 'd' -> {
                switch(direcaoX){
                    case 'T' -> esfera.desceDir();
                    case 'C' -> perdeVida();
                    case 'E' -> esfera.desceEsq();
                    case 'D' -> esfera.sobeEsq();
                    default -> esfera.sobeDir();
                }
            }
            case 'e' -> {
                switch(direcaoX){
                    case 'T' -> esfera.desceEsq();
                    case 'C' -> perdeVida();
                    case 'E' -> esfera.sobeDir();
                    case 'D' -> esfera.desceDir();
                    default -> esfera.sobeEsq();
                }
            }
        }
    }
    
    public void desenhaTexto(GL2 gl, int xPosicao, int yPosicao, Color cor, String frase){         
        gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_FILL);
        //Retorna a largura e altura da janela
        textRenderer.beginRendering(Renderer.screenWidth, Renderer.screenHeight);       
        textRenderer.setColor(cor);
        textRenderer.draw(frase, xPosicao, yPosicao);
        textRenderer.endRendering();
        gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, mode);
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
        this.textRenderer = new TextRenderer(new Font("Comic Sans MS Negrito", Font.BOLD, 15));
        
    }
    
    
    @Override
    public void display(GLAutoDrawable drawable) {  
        //obtem o contexto Opengl
        GL2 gl = drawable.getGL().getGL2();   
        
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
        esfera.avancoEsfera(0, 0, this.posicaoInicialEsfera);
        
        quadrado.settQuadrado(tTela);
        quadrado.draw(gl);
        this.localizacaoQuadrado = quadrado.gettQuadrado();
        
        barra.setTamanhoBarra(this.tBarra);
        barra.setTranslacao(translacao);
        barra.draw(gl);
        
        esfera.draw(gl);
        esfera.setVelocidadeJogo(this.velocidadeJogo);
        this.desenhaTexto(gl, 2, 2, Color.red, m);
        this.desenhaTexto(gl, 1000, 1000, Color.red, "Teste");
        
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
