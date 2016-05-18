
package jogo;

public class Destaque {
	private String numero;
	private Integer posicao;
	private Destaque DestaqueDireita = null;
	private Destaque DestaqueEsquerda = null;
	private Destaque DestaqueBaixo = null;
	private Destaque DestaqueCima = null;
	private static int COLUNAS = 3;
	public final String VAZIO = "vazia";
	
	
	
	public Destaque(String numero, Integer posicao, Destaque DestaqueEsquerda, Destaque DestaqueDireita, Destaque DestaqueCima, Destaque DestaqueBaixo ) {
		this.setNumero(numero);
		this.setPosicao(posicao);
		this.setDestaqueCima(DestaqueCima);
		this.setDestaqueDireita(DestaqueDireita);
		this.setDestaqueEsquerda(DestaqueEsquerda);
		this.setDestaqueBaixo(DestaqueBaixo);
		
		
		
	}
	
	public Destaque(String numero) {
		this.setNumero(numero);
	}
	
	public Destaque(Integer posicao) {
		this.setPosicao(posicao);
	}

	
	public Integer mover(){
		if(moverCima() == 1){
			return 1;
		}
		if(moverBaixo() == 1){
			return 3;
		}
		if(moverEsquerda() == 1){
			return 4;
		}
		
		if(moverDireita() == 1){
			return 2;
		}
		
		
		return 0;
	}
	public Integer moverCima(){
		if(DestaqueCima != null && DestaqueCima.getNumero() == VAZIO || (DestaqueCima != null && this.numero == VAZIO)){
			Destaque auxEsquerda = getDestaqueCima().getDestaqueEsquerda();
			Destaque auxDireita = getDestaqueCima().getDestaqueDireita();
			Destaque aux = getDestaqueCima();
			aux.setPosicao(this.getPosicao());
			this.setPosicao(getPosicao()-COLUNAS);
			aux.setDestaqueDireita(getDestaqueDireita());
			aux.setDestaqueEsquerda(getDestaqueEsquerda());
			this.setDestaqueEsquerda(auxEsquerda);
			this.setDestaqueDireita(auxDireita);
			this.setDestaqueCima(getDestaqueCima().getDestaqueCima());
			aux.setDestaqueCima(this);
			aux.setDestaqueBaixo(this.getDestaqueBaixo());
			this.setDestaqueBaixo(aux);			
			
			if(getDestaqueEsquerda()!= null){
				this.getDestaqueEsquerda().setDestaqueDireita(this);
			}			
			if(getDestaqueDireita()!= null){
				this.getDestaqueDireita().setDestaqueEsquerda(this);
			}
			if(getDestaqueCima()!= null){
				this.getDestaqueCima().setDestaqueBaixo(this);
			}
			if(this.getDestaqueBaixo().getDestaqueEsquerda() != null){
				this.getDestaqueBaixo().getDestaqueEsquerda().setDestaqueDireita(this.getDestaqueBaixo());
			}		
			if(this.getDestaqueBaixo().getDestaqueDireita() != null){
				this.getDestaqueBaixo().getDestaqueDireita().setDestaqueEsquerda(this.getDestaqueBaixo());
			}			
			if(this.getDestaqueBaixo().getDestaqueBaixo() != null){
				this.getDestaqueBaixo().getDestaqueBaixo().setDestaqueCima(this.getDestaqueBaixo());
			}
			aux = null;
			auxDireita = null;
			auxEsquerda = null;
			return 1;
		}
		return 0;
	}
	public Integer moverBaixo(){
		if((DestaqueBaixo != null && DestaqueBaixo.getNumero() == VAZIO) || (DestaqueBaixo != null && this.numero == VAZIO)){
			Destaque auxEsquerda = getDestaqueBaixo().getDestaqueEsquerda();
			Destaque auxDireita = getDestaqueBaixo().getDestaqueDireita();
			Destaque aux = getDestaqueBaixo();
			aux.setPosicao(this.getPosicao());
			this.setPosicao(getPosicao()+COLUNAS);
			aux.setDestaqueDireita(getDestaqueDireita());
			aux.setDestaqueEsquerda(getDestaqueEsquerda());
			this.setDestaqueEsquerda(auxEsquerda);
			this.setDestaqueDireita(auxDireita);
			this.setDestaqueBaixo(getDestaqueBaixo().getDestaqueBaixo());
			aux.setDestaqueBaixo(this);
			aux.setDestaqueCima(this.getDestaqueCima());
			this.setDestaqueCima(aux);	
			if(getDestaqueEsquerda()!= null){
				this.getDestaqueEsquerda().setDestaqueDireita(this);
			}			
			if(getDestaqueDireita()!= null){
				this.getDestaqueDireita().setDestaqueEsquerda(this);
			}
			if(getDestaqueBaixo()!= null){
				this.getDestaqueBaixo().setDestaqueCima(this);
			}
			if(this.getDestaqueCima().getDestaqueEsquerda() != null){
				this.getDestaqueCima().getDestaqueEsquerda().setDestaqueDireita(this.getDestaqueCima());
			}		
			if(this.getDestaqueCima().getDestaqueDireita() != null){
				this.getDestaqueCima().getDestaqueDireita().setDestaqueEsquerda(this.getDestaqueCima());
			}			
			if(this.getDestaqueCima().getDestaqueCima() != null){
				this.getDestaqueCima().getDestaqueCima().setDestaqueBaixo(this.getDestaqueCima());
			}			
			aux = null;
			auxDireita = null;
			auxEsquerda = null;
			return 1;
		}
		return 0;
	}
	
	public Integer moverEsquerda(){	
		if(DestaqueEsquerda != null && DestaqueEsquerda.getNumero() == VAZIO || (DestaqueEsquerda != null && this.numero == VAZIO)){
			Destaque auxCima = getDestaqueEsquerda().getDestaqueCima();
			Destaque auxBaixo = getDestaqueEsquerda().getDestaqueBaixo();
			Destaque aux = getDestaqueEsquerda();
			aux.setPosicao(this.getPosicao());
			this.setPosicao(getPosicao()-1);
			aux.setDestaqueBaixo(getDestaqueBaixo());
			aux.setDestaqueCima(getDestaqueCima());
			this.setDestaqueCima(auxCima);
			this.setDestaqueBaixo(auxBaixo);
			this.setDestaqueEsquerda(getDestaqueEsquerda().getDestaqueEsquerda());
			aux.setDestaqueEsquerda(this);
			aux.setDestaqueDireita(this.getDestaqueDireita());
			this.setDestaqueDireita(aux);	
			if(getDestaqueCima()!= null){
				this.getDestaqueCima().setDestaqueBaixo(this);
			}			
			if(getDestaqueBaixo()!= null){
				this.getDestaqueBaixo().setDestaqueCima(this);
			}
			if(getDestaqueEsquerda()!= null){
				this.getDestaqueEsquerda().setDestaqueDireita(this);
			}
			if(this.getDestaqueDireita().getDestaqueCima() != null){
				this.getDestaqueDireita().getDestaqueCima().setDestaqueBaixo(this.getDestaqueDireita());
			}		
			if(this.getDestaqueDireita().getDestaqueBaixo() != null){
				this.getDestaqueDireita().getDestaqueBaixo().setDestaqueCima(this.getDestaqueDireita());
			}			
			if(this.getDestaqueDireita().getDestaqueDireita() != null){
				this.getDestaqueDireita().getDestaqueDireita().setDestaqueEsquerda(this.getDestaqueDireita());
			}
			aux = null;
			auxBaixo = null;
			auxCima = null;
			return 1;
		}
		return 0;
	}
	
	public Integer moverDireita(){
		if(DestaqueDireita != null && DestaqueDireita.getNumero() ==VAZIO || (DestaqueDireita != null && this.numero == VAZIO)){
			Destaque auxCima = getDestaqueDireita().getDestaqueCima();
			Destaque auxBaixo = getDestaqueDireita().getDestaqueBaixo();
			Destaque aux = getDestaqueDireita();
			aux.setPosicao(this.getPosicao());
			this.setPosicao(getPosicao()+1);
			aux.setDestaqueBaixo(getDestaqueBaixo());
			aux.setDestaqueCima(getDestaqueCima());
			this.setDestaqueCima(auxCima);
			this.setDestaqueBaixo(auxBaixo);
			this.setDestaqueDireita(getDestaqueDireita().getDestaqueDireita());
			aux.setDestaqueDireita(this);
			aux.setDestaqueEsquerda(this.getDestaqueEsquerda());
			this.setDestaqueEsquerda(aux);		
			if(getDestaqueCima()!= null){
				this.getDestaqueCima().setDestaqueBaixo(this);
			}			
			if(getDestaqueBaixo()!= null){
				this.getDestaqueBaixo().setDestaqueCima(this);
			}
			if(getDestaqueDireita()!= null){
				this.getDestaqueDireita().setDestaqueEsquerda(this);
			}
			if(this.getDestaqueEsquerda().getDestaqueCima() != null){
				this.getDestaqueEsquerda().getDestaqueCima().setDestaqueBaixo(this.getDestaqueEsquerda());
			}		
			if(this.getDestaqueEsquerda().getDestaqueBaixo() != null){
				this.getDestaqueEsquerda().getDestaqueBaixo().setDestaqueCima(this.getDestaqueEsquerda());
			}			
			if(this.getDestaqueEsquerda().getDestaqueEsquerda() != null){
				this.getDestaqueEsquerda().getDestaqueEsquerda().setDestaqueDireita(this.getDestaqueEsquerda());
			}
			aux = null;
			auxBaixo = null;
			auxCima = null;
			return 1;			
		}
		return 0;
	}
	

	
	
	
	


	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Integer getPosicao() {
		return posicao;
	}


	public void setPosicao(Integer posicao) {
		this.posicao = posicao;
	}


	public Destaque getDestaqueDireita() {
		return DestaqueDireita;
	}


	public void setDestaqueDireita(Destaque DestaqueDireita) {
		this.DestaqueDireita = DestaqueDireita;
	}


	public Destaque getDestaqueEsquerda() {
		return DestaqueEsquerda;
	}


	public void setDestaqueEsquerda(Destaque DestaqueEsquerda) {
		this.DestaqueEsquerda = DestaqueEsquerda;
	}


	public Destaque getDestaqueBaixo() {
		return DestaqueBaixo;
	}


	public void setDestaqueBaixo(Destaque DestaqueBaixo) {
		this.DestaqueBaixo = DestaqueBaixo;
	}


	public Destaque getDestaqueCima() {
		return DestaqueCima;
	}

	public void setDestaqueCima(Destaque DestaqueCima) {
		this.DestaqueCima = DestaqueCima;
	}
	
	public String toString() {
		return getNumero();
		
	}
	
	public boolean isVazia(){
		return getNumero().equals(VAZIO);
	}

	
	public Integer getColuna(){
		if(this.posicao == 1 || this.posicao == 4 || this.posicao == 7){
			return 1;
		}else{
			if(this.posicao == 2 || this.posicao == 5 || this.posicao == 8){
				return 2;
			}else{
				return 3;
			}
		}
	}
	
	public Integer getLinha(){
		if(this.getPosicao() <=3){
			return 1;
		}else{
			if(this.getPosicao() >=4 && this.getPosicao() <=6 ){
				return 2;
			}else{
				return 3;
			}
		}
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime * result + ((posicao == null) ? 0 : posicao.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Destaque other = (Destaque) obj;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		if (posicao == null) {
			if (other.posicao != null)
				return false;
		} else if (!posicao.equals(other.posicao))
			return false;
		return true;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
		

}


