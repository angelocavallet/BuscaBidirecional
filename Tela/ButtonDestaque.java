package Tela;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JButton;

import jogo.Destaque;

public class ButtonDestaque extends JButton {
	
	private String numero;
	private Destaque Destaque;
	private int vazio = 0;
	private int clickado = 0;	
	
	
	protected void paintComponent(Graphics g) {
        
        super.paintComponent(g);
        int w = getWidth();
        int h = getHeight();
        int leftW = w -1 ;
        int topH = h -1;
        if(vazio == 0){
        	g.setColor(Color.white);
        	g.fillRect(0, 0, leftW, topH);
            g.fillRect(leftW, topH, w - leftW, h - topH);
            
        }else{
        	g.setColor(Color.WHITE);
        	g.fillRect(0, 0, leftW, topH);
            g.fillRect(leftW, topH, w - leftW, h - topH);

        }
        g.setColor(Color.BLACK);
        g.setFont(new Font(Font.SANS_SERIF,Font.TRUETYPE_FONT,30));
        g.drawString(getNumero(), 15, 35);   
        
    }
	
	

	public Destaque getDestaque() {
		return Destaque;
	}

	public void setDestaque(Destaque Destaque) {
		this.Destaque = Destaque;
		if(Destaque.getNumero().equals(Destaque.VAZIO)){
			this.numero = "";
			this.vazio = 1;
		}else{
			this.numero = Destaque.getNumero();
			this.vazio = 0;
		}	
		repaint();
	}

	public int getVazio() {
		return vazio;
	}

	public String getNumero() {
		if(getDestaque().isVazia()){
			return "";
		}else{
			return getDestaque().getNumero();
		}
	}

}

