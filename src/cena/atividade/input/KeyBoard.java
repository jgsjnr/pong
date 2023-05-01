package cena.atividade.input;

import cena.atividade.input.*;
import cena.atividade.cena.Cena;
import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.KeyListener;
/**
 *
 * @author Kakugawa
 */
public class KeyBoard implements KeyListener{
    private Cena cena;
    
    public KeyBoard(Cena cena){
        this.cena = cena;
    }
    
    @Override
    public void keyPressed(KeyEvent e) {        
        System.out.println("Key pressed: " + e.getKeyCode());
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
            System.exit(0);
        
//        if(e.getKeyChar() == 'a'){
//            System.out.println("Pressionou tecla a");
//            cena.setVelocidadeJogo(cena.getVelocidadeJogo()+0.2f); ;
//        }
            
        
        
        if(e.getKeyChar() == 'r'){
            System.out.println("positivo");
            cena.rotacao += 10;
        }
        if(e.getKeyChar() == 'R'){
            System.out.println("negativo");
            cena.ressetarJogo();
        }
        if(e.getKeyChar() == 'a'){
            if(cena.isJogoParado()) cena.retornarJogoParado();
            if(cena.translacao <= -1*cena.tLimiteTela);
            else cena.translacao -= 0.07;
        }
        if(e.getKeyChar() == 'd'){
            if(cena.isJogoParado()) cena.retornarJogoParado();
            if(cena.translacao >= cena.tLimiteTela);
            else cena.translacao += 0.04;
        }
        if(e.getKeyChar() == 'K'){
            if(cena.isJogoPausado()) cena.retornarJogoPausado();
            else cena.pausarJogo();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { }

}
