package jogo;

import java.util.*;

public class Bidirecional {

	protected Movimento inicial;
	protected Movimento objetivo;
	protected Movimento atual;
	protected Queue<Movimento> movimentos = new LinkedList<Movimento>();//Lista que armazena todos os movimentos possiveis ainda nao visitados
	protected Queue<Movimento> movimentosTotais = new LinkedList<Movimento>();//Lista que armazena todos os movimentos possiveis e visitados
	protected Queue<Movimento> movimentosVisitados = new LinkedList<Movimento>();//Lista que armazena os movimentos já visitados
	protected Integer passos;
	
	
	//Método Construtor da classe 
	public Bidirecional(Movimento inicio, Movimento fim){
		
		this.inicial = inicio;//identifica a posição de inicio do processo
		this.objetivo = fim;//identifica o objetivo que deverá ser alcancado
		this.movimentos.add(inicio);//adiciona nos movimentos a posicao inicial
		this.movimentosTotais.add(inicio);//adiciona nos movimentos totais a posicao inicial
		this.passos = 0;// registra o numero de passos realizados 
		
	}
	
	//Verifica se a arvore de movimentos está vazia
	public boolean vazia(){
		return this.movimentos.isEmpty();
	}
	
	//retorna o estado atual
	public Movimento getEstadoAtual(){
		
		return this.atual;
	}
	
	//retorna o numero de passos
	public Integer getNumPassos(){
		
		return this.passos;
	}	
	
	//metodo que busca um novo estado na lista de estados 
	//possiveis e verifica se o mesmo eh o estado objetivo
	public Movimento verificaObjetivo(){
		this.passos++; //incrementa o numero de passos
		
		//a cada 1000 passos exibe mensagem de debug
        if (this.passos % 1000 == 0) {
            System.out.println(this.passos+" passos executados ate agora");
            System.out.println("Nos nao abertos na lista = "+this.movimentos.size());
        }
        
        //armazena o no atual removendo da lista de movimentos disponiveis
        this.atual = this.movimentos.remove();
        this.movimentosVisitados.add(this.atual);//armazena o movimento atual aos movimentos visitados
        
        //verifica se o moviemento atual eh o movimento objetivo
        if (this.atual.equals(this.objetivo)) {
        	System.out.println("Solucao encontrada no passo "+this.passos);
        	System.out.println("Nos nao abertos na lista = "+this.movimentos.size());
        	return this.atual;//caso seja o movimento objetivo retorna 
        }
        
        //Busca os possiveis movimentos a partir do movimento atual
		for (Movimento e : this.atual.getMovimentosFilhos()) {
			//Se este movimento nao for um movimento repitido sera adicionado aos movimentos possiveis e aos totais
			if (!this.movimentosTotais.contains(e)) {
				this.movimentos.add(e);
				this.movimentosTotais.add(e);
			}
		}
		
		return null;
	}
	 
	//Metodo que verifica se um estado passado por parametro ja foi visitado pela busca
    public Movimento verificaMovimentosVisitados(Movimento estadoAtual) {   
    	Queue<Movimento> mVisitados = new LinkedList<Movimento>();
    			
        while (!this.movimentosVisitados.isEmpty()) {

            Movimento mv = this.movimentosVisitados.remove();
            mVisitados.add(mv);
            //se este estado passado por parametro ja foi visitado 
            //pela busca é retornado o estado da busca ao qual foi comparado e identificado como igual
            if (mv.equals(estadoAtual)) {
                return mv;
            }
        }
        //caso nao seja encontrado nenhum estado igual ao estado passado 
        //por parametro a lista de estado visitados é armazenada novamente na lista de estados visitados
        this.movimentosVisitados = mVisitados;        
        return null;
    }
      
	
}
