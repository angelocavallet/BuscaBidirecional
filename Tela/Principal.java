package Tela;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.border.TitledBorder;

import jogo.Movimento;
import jogo.Destaque;
import jogo.Bidirecional;

public class Principal extends JFrame implements ActionListener {

	private boolean primeiraVez = true;
	private Destaque Destaque1 = new Destaque("1", 1, null, null, null, null);
	private Destaque Destaque2 = new Destaque("2", 2, Destaque1, null, null, null);
	private Destaque Destaque3 = new Destaque("3", 3, Destaque2, null, null, null);
	private Destaque Destaque4 = new Destaque("4", 6, null, null, Destaque3, null);
	private Destaque Destaque5 = new Destaque("5", 9, null, null, Destaque4, null);
	private Destaque Destaque6 = new Destaque("6", 8, null, Destaque5, null, null);
	private Destaque Destaque7 = new Destaque("7", 7, null, Destaque6, null, null);
	private Destaque Destaque8 = new Destaque("8", 4, null, null, Destaque1, Destaque7);
	private Destaque DestaqueVazia = new Destaque("vazia", 5, Destaque8, Destaque4, Destaque2, Destaque6);

	private Movimento MovimentoFinal = null;
	private ButtonDestaque button1 = new ButtonDestaque();
	private ButtonDestaque button2 = new ButtonDestaque();
	private ButtonDestaque button3 = new ButtonDestaque();
	private ButtonDestaque button4 = new ButtonDestaque();
	private ButtonDestaque button5 = new ButtonDestaque();
	private ButtonDestaque button6 = new ButtonDestaque();
	private ButtonDestaque button7 = new ButtonDestaque();
	private ButtonDestaque button8 = new ButtonDestaque();
	private ButtonDestaque button9 = new ButtonDestaque();

	private JButton buttonEmbaralhar = new JButton("EMBARALHAR");
	private JButton buttonBuscaLargura = new JButton("LARGURA");
	private JButton buttonBuscaAEstrela = new JButton("A*");
	private JButton buttonBuscaProfunda = new JButton("PROFUNDIDADE");
	private JButton buttonBuscaProfundaLim = new JButton("PROF LIMITADA");
	private JButton buttonBuscaProfundaIte = new JButton("PROF INTERATIVA");
	private JButton buttonBuscaBidirecional = new JButton("BIDIRECIONAL");

	private JLabel lbStatus = new JLabel();

	SwingWorker<Object, Object> embaralhador = null;

	private boolean embaralhando = false;

	public Principal() {
		this.setSize(630, 300);
		this.setTitle("Jogo do 8");
		this.setResizable(false);
		this.setLayout(null);
		this.setLocation(100, 200);
		this.posicionarComponentes();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public void posicionarComponentes() {
		Destaque1.setDestaqueDireita(Destaque2);
		Destaque2.setDestaqueDireita(Destaque3);
		Destaque3.setDestaqueBaixo(Destaque4);
		Destaque4.setDestaqueBaixo(Destaque5);
		Destaque5.setDestaqueEsquerda(Destaque6);
		Destaque6.setDestaqueEsquerda(Destaque7);
		Destaque1.setDestaqueBaixo(Destaque8);
		Destaque7.setDestaqueCima(Destaque8);
		Destaque8.setDestaqueDireita(DestaqueVazia);
		Destaque4.setDestaqueEsquerda(DestaqueVazia);
		Destaque2.setDestaqueBaixo(DestaqueVazia);
		Destaque6.setDestaqueCima(DestaqueVazia);
		Destaque DestaquesFinais[] = { Destaque1, Destaque2, Destaque3, Destaque4, Destaque5, Destaque6, Destaque7,
				Destaque8, DestaqueVazia };

		this.MovimentoFinal = new Movimento(Arrays.asList(DestaquesFinais), null);
		montarTabuleiro();
		lbStatus.setBounds(176, 200, 860, 20);
		lbStatus.setText("Escolha uma op��o de Busca");
		lbStatus.setForeground(Color.BLACK);

		button1.setBounds(30, 30, 50, 50);
		button2.setBounds(80, 30, 50, 50);
		button3.setBounds(130, 30, 50, 50);
		button4.setBounds(30, 80, 50, 50);
		button5.setBounds(80, 80, 50, 50);
		button6.setBounds(130, 80, 50, 50);
		button7.setBounds(30, 130, 50, 50);
		button8.setBounds(80, 130, 50, 50);
		button9.setBounds(130, 130, 50, 50);
		buttonEmbaralhar.setBounds(45, 200, 120, 40);

		JPanel panelBtn = new JPanel();
		panelBtn.setBorder(new TitledBorder(null, "M�todos de Busca",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		panelBtn.setBounds(215, 10, 420, 185);
		this.add(panelBtn);
		panelBtn.setLayout(null);

		buttonBuscaLargura.setBounds(20, 30, 170, 40);
		buttonBuscaAEstrela.setBounds(20, 80, 170, 40);
		buttonBuscaProfunda.setBounds(200, 30, 200, 40);
		buttonBuscaProfundaLim.setBounds(200, 80, 200, 40);
		buttonBuscaProfundaIte.setBounds(200, 130, 200, 40);
		buttonBuscaBidirecional.setBounds(20, 130, 170, 40);

		buttonEmbaralhar.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT,
			15));
		buttonBuscaLargura.setFont(new Font(Font.SANS_SERIF,
				Font.TRUETYPE_FONT, 15));
		buttonBuscaAEstrela.setFont(new Font(Font.SANS_SERIF,
				Font.TRUETYPE_FONT, 15));
		buttonBuscaProfunda.setFont(new Font(Font.SANS_SERIF,
				Font.TRUETYPE_FONT, 15));
		buttonBuscaProfundaLim.setFont(new Font(Font.SANS_SERIF,
				Font.TRUETYPE_FONT, 15));
		buttonBuscaProfundaIte.setFont(new Font(Font.SANS_SERIF,
				Font.TRUETYPE_FONT, 15));
		buttonBuscaBidirecional.setFont(new Font(Font.SANS_SERIF,
				Font.TRUETYPE_FONT, 15));

		this.add(button1);
		this.add(button2);
		this.add(button3);
		this.add(button4);
		this.add(button5);
		this.add(button6);
		this.add(button7);
		this.add(button8);
		this.add(button9);
		this.add(buttonEmbaralhar);

		panelBtn.add(buttonBuscaLargura);
		panelBtn.add(buttonBuscaAEstrela);
		this.add(lbStatus);
		panelBtn.add(buttonBuscaProfunda);
		panelBtn.add(buttonBuscaProfundaLim);
		panelBtn.add(buttonBuscaProfundaIte);
		panelBtn.add(buttonBuscaBidirecional);

		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
		button4.addActionListener(this);
		button5.addActionListener(this);
		button6.addActionListener(this);
		button7.addActionListener(this);
		button8.addActionListener(this);
		button9.addActionListener(this);
		buttonEmbaralhar.addActionListener(this);
		buttonBuscaLargura.addActionListener(this);
		buttonBuscaAEstrela.addActionListener(this);
		buttonBuscaProfunda.addActionListener(this);
		buttonBuscaProfundaLim.addActionListener(this);
		buttonBuscaProfundaIte.addActionListener(this);
		buttonBuscaBidirecional.addActionListener(this);
	}

	public void montarTabuleiro() {
		Destaque[] arrayDestaques = { Destaque1, Destaque2, Destaque3, Destaque4, Destaque5, Destaque6, Destaque7,
				Destaque8, DestaqueVazia };
		for (int i = 0; i < arrayDestaques.length; i++) {
			if (arrayDestaques[i].getPosicao() == 1) {
				button1.setDestaque(arrayDestaques[i]);
			} else {
				if (arrayDestaques[i].getPosicao() == 2) {
					button2.setDestaque(arrayDestaques[i]);
				} else {
					if (arrayDestaques[i].getPosicao() == 3) {
						button3.setDestaque(arrayDestaques[i]);
					} else {
						if (arrayDestaques[i].getPosicao() == 4) {
							button4.setDestaque(arrayDestaques[i]);
						} else {
							if (arrayDestaques[i].getPosicao() == 5) {
								button5.setDestaque(arrayDestaques[i]);
							} else {
								if (arrayDestaques[i].getPosicao() == 6) {
									button6.setDestaque(arrayDestaques[i]);
								} else {
									if (arrayDestaques[i].getPosicao() == 7) {
										button7.setDestaque(arrayDestaques[i]);
									} else {
										if (arrayDestaques[i].getPosicao() == 8) {
											button8.setDestaque(arrayDestaques[i]);
										} else {
											if (arrayDestaques[i].getPosicao() == 9) {
												button9.setDestaque(arrayDestaques[i]);
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == buttonEmbaralhar) {
			if (buttonEmbaralhar.getText().equals("Parar")) {
				buttonEmbaralhar.setText("Embaralhar");
				embaralhador.cancel(true);
				repaint();
				embaralhando = false;
				lbStatus.setText("Escolha uma op��o de Busca");
				setEnabledAllButtons(true);
				return;
			}
			buttonEmbaralhar.setText("Parar");
			embaralhando = true;
			embaralhador = new SwingWorker<Object, Object>() {
				protected Object doInBackground() throws Exception {
					lbStatus.setForeground(Color.BLACK);
					lbStatus.setText("Embaralhando...");
					setEnabledAllButtons(false);
					buttonEmbaralhar.setEnabled(true);
					buttonBuscaLargura.setEnabled(false);
					buttonBuscaAEstrela.setEnabled(false);
					buttonBuscaProfunda.setEnabled(false);
					buttonBuscaProfundaLim.setEnabled(false);
					buttonBuscaProfundaIte.setEnabled(false);

					repaint();
					int ultimoLado = 0;
					while (true) {
						int movido = 0;
						Random random = new Random();
						int lado = random.nextInt(4);
						if (lado == 0 && ultimoLado != 2) {
							movido = DestaqueVazia.moverEsquerda();
						} else {
							if (lado == 1 && ultimoLado != 3) {
								movido = DestaqueVazia.moverCima();
							} else {
								if (lado == 2 && ultimoLado != 0) {
									movido = DestaqueVazia.moverDireita();
								} else {
									if (lado == 3 && ultimoLado != 1) {
										movido = DestaqueVazia.moverBaixo();
									}
								}
							}
						}
						if (movido == 1) {
							montarTabuleiro();
							ultimoLado = lado;
							Thread.sleep(300);
						}

					}
				}
			};
			embaralhador.execute();
		}
		// valida os Movimentos se for bot�o 1 ele pode ir para so 2 posicioes
		if (e.getSource() == button1) {
			if (!button1.getDestaque().isVazia() && !embaralhando) {
				int movimento = button1.getDestaque().mover();
				if (movimento == 2) {
					button2.setDestaque(button1.getDestaque());
					button1.setDestaque(DestaqueVazia);
				}
				if (movimento == 3) {
					button4.setDestaque(button1.getDestaque());
					button1.setDestaque(DestaqueVazia);
				}
				if (movimento != 0) {
					repaint();// atualiza a interfase
				}
			}
		}
		if (e.getSource() == button2) {
			if (!button2.getDestaque().isVazia() && !embaralhando) {
				int movimento = button2.getDestaque().mover();
				if (movimento == 2) {
					button3.setDestaque(button2.getDestaque());
					button2.setDestaque(DestaqueVazia);
				}
				if (movimento == 3) {
					button5.setDestaque(button2.getDestaque());
					button2.setDestaque(DestaqueVazia);
				}
				if (movimento == 4) {
					button1.setDestaque(button2.getDestaque());
					button2.setDestaque(DestaqueVazia);
				}
				if (movimento != 0) {
					repaint();
				}
			}
		}
		if (e.getSource() == button3) {
			if (!button3.getDestaque().isVazia() && !embaralhando) {
				int movimento = button3.getDestaque().mover();
				if (movimento == 3) {
					button6.setDestaque(button3.getDestaque());
					button3.setDestaque(DestaqueVazia);
				}
				if (movimento == 4) {
					button2.setDestaque(button3.getDestaque());
					button3.setDestaque(DestaqueVazia);
				}
				if (movimento != 0) {
					repaint();
				}
			}
		}
		if (e.getSource() == button4) {
			if (!button4.getDestaque().isVazia() && !embaralhando) {
				int movimento = button4.getDestaque().mover();
				if (movimento == 1) {
					button1.setDestaque(button4.getDestaque());
					button4.setDestaque(DestaqueVazia);
				}
				if (movimento == 2) {
					button5.setDestaque(button4.getDestaque());
					button4.setDestaque(DestaqueVazia);
				}
				if (movimento == 3) {
					button7.setDestaque(button4.getDestaque());
					button4.setDestaque(DestaqueVazia);
				}
				if (movimento != 0) {
					repaint();
				}
			}
		}
		if (e.getSource() == button5) {
			if (!button5.getDestaque().isVazia() && !embaralhando) {
				int movimento = button5.getDestaque().mover();
				if (movimento == 1) {
					button2.setDestaque(button5.getDestaque());
					button5.setDestaque(DestaqueVazia);
					;
				}
				if (movimento == 2) {
					button6.setDestaque(button5.getDestaque());
					button5.setDestaque(DestaqueVazia);
				}
				if (movimento == 3) {
					button8.setDestaque(button5.getDestaque());
					button5.setDestaque(DestaqueVazia);
				}
				if (movimento == 4) {
					button4.setDestaque(button5.getDestaque());
					button5.setDestaque(DestaqueVazia);
				}
				if (movimento != 0) {
					repaint();
				}
			}
		}
		if (e.getSource() == button6) {
			if (!button6.getDestaque().isVazia() && !embaralhando) {
				int movimento = button6.getDestaque().mover();
				if (movimento == 1) {
					button3.setDestaque(button6.getDestaque());
					button6.setDestaque(DestaqueVazia);
				}
				if (movimento == 3) {
					button9.setDestaque(button6.getDestaque());
					button6.setDestaque(DestaqueVazia);
				}
				if (movimento == 4) {
					button5.setDestaque(button6.getDestaque());
					button6.setDestaque(DestaqueVazia);
				}
				if (movimento != 0) {
					repaint();
				}
			}

		}
		if (e.getSource() == button7) {
			if (!button7.getDestaque().isVazia() && !embaralhando) {
				int movimento = button7.getDestaque().mover();
				if (movimento == 1) {
					button4.setDestaque(button7.getDestaque());
					button7.setDestaque(DestaqueVazia);
				}
				if (movimento == 2) {
					button8.setDestaque(button7.getDestaque());
					button7.setDestaque(DestaqueVazia);
				}
				if (movimento != 0) {
					repaint();
				}
			}
		}
		if (e.getSource() == button8) {
			if (!button8.getDestaque().isVazia() && !embaralhando) {
				int movimento = button8.getDestaque().mover();
				if (movimento == 1) {
					button5.setDestaque(button8.getDestaque());
					button8.setDestaque(DestaqueVazia);
				}
				if (movimento == 2) {
					button9.setDestaque(button8.getDestaque());
					button8.setDestaque(DestaqueVazia);
				}
				if (movimento == 4) {
					button7.setDestaque(button8.getDestaque());
					button8.setDestaque(DestaqueVazia);
				}
				if (movimento != 0) {
					repaint();
				}
			}

		}
		if (e.getSource() == button9) {
			if (!button9.getDestaque().isVazia() && !embaralhando) {
				int movimento = button9.getDestaque().mover();
				if (movimento == 1) {
					button6.setDestaque(button9.getDestaque());
					button9.setDestaque(DestaqueVazia);
				}
				if (movimento == 4) {
					button8.setDestaque(button9.getDestaque());
					button9.setDestaque(DestaqueVazia);
				}
				if (movimento != 0) {
					repaint();
				}
			}
		}

		// A��o do bot�o Busca largura
		if (e.getSource() == buttonBuscaLargura) {
			if (!embaralhando) {
				SwingWorker<Object, Object> buscaEmLargura = new SwingWorker<Object, Object>() {
					@Override
					protected Object doInBackground() throws Exception {
						lbStatus.setForeground(Color.BLACK);
						lbStatus.setText("Realizando Busca em largura...");
						embaralhando = true;
						Principal.this.setEnabledAllButtons(false);

						Destaque arrayDestaques[] = { Destaque1, Destaque2, Destaque3, Destaque4,
								Destaque5, Destaque6, Destaque7, Destaque8, DestaqueVazia };
						Movimento MovimentoInicio = new Movimento(
								Arrays.asList(arrayDestaques), null);
						Queue<Movimento> Movimento = buscaEmLargura(MovimentoInicio,
								MovimentoFinal);

						Principal.this.processarBusca(
								"Busca em largura concluida com custo", Movimento);

						Principal.this.setEnabledAllButtons(true);
						embaralhando = false;
						return null;
					}
				};
				buscaEmLargura.execute();
			}
		}
		if (e.getSource() == buttonBuscaAEstrela) {
			if (!embaralhando) {
				SwingWorker<Object, Object> buscaEstrela = new SwingWorker<Object, Object>() {
					@Override
					protected Object doInBackground() throws Exception {
						lbStatus.setForeground(Color.BLACK);
						lbStatus.setText("Realizando a Busca A*...");

						embaralhando = true;
						Principal.this.setEnabledAllButtons(false);

						Destaque arrayDestaques[] = { Destaque1, Destaque2, Destaque3, Destaque4,
								Destaque5, Destaque6, Destaque7, Destaque8, DestaqueVazia };
						Movimento MovimentoInicio = new Movimento(
								Arrays.asList(arrayDestaques), null);
						Movimento Movimento = buscaAestrela(MovimentoInicio, MovimentoFinal);

						Principal.this.processarResultado(
								"Busca em A* concluida com custo", Movimento);

						Principal.this.setEnabledAllButtons(true);
						embaralhando = false;
						return null;
					}
				};
				buscaEstrela.execute();
			}
		} else if (e.getSource() == buttonBuscaProfunda) {
			if (!embaralhando) {
				SwingWorker<Object, Object> threadProf = new SwingWorker<Object, Object>() {
					@Override
					protected Object doInBackground() throws Exception {
						lbStatus.setForeground(Color.BLACK);
						lbStatus.setText("Realizando Busca em Profundidade...");
						embaralhando = true;
						Principal.this.setEnabledAllButtons(false);

						Destaque arrayDestaques[] = { Destaque1, Destaque2, Destaque3, Destaque4,
								Destaque5, Destaque6, Destaque7, Destaque8, DestaqueVazia };
						Movimento MovimentoInicio = new Movimento(
								Arrays.asList(arrayDestaques), null);

						Stack<Movimento> MovimentoObjetivo = buscaProfundidade(MovimentoInicio,
								MovimentoFinal);

						//Principal.this.processarBusca(
								//"Busca em Profundidade concluida com custo",
								//MovimentoObjetivo);

						Principal.this.setEnabledAllButtons(true);
						Principal.this.embaralhando = false;
						return null;
					}
				};
				threadProf.execute();
			}
		} else if (e.getSource() == buttonBuscaProfundaLim) {
			if (!embaralhando) {

				SwingWorker<Object, Object> threadProf = new SwingWorker<Object, Object>() {
					@Override
					protected Object doInBackground() throws Exception {
						String op;
						op = JOptionPane.showInputDialog("Informe um limite");

						if (op == null)
							return null;

						int limite = 0;
						try {
							limite = Integer.parseInt(op);
						} catch (NumberFormatException e) {
						}

						lbStatus.setForeground(Color.BLACK);
						lbStatus.setText("Realizando Busca em Profundidade Limitada...");
						embaralhando = true;
						Principal.this.setEnabledAllButtons(false);

						Destaque arrayDestaques[] = { Destaque1, Destaque2, Destaque3, Destaque4,
								Destaque5, Destaque6, Destaque7, Destaque8, DestaqueVazia };
						Movimento MovimentoInicio = new Movimento(
								Arrays.asList(arrayDestaques), null);

						Movimento MovimentoObjetivo = buscaProfundidadeLimitada(MovimentoInicio,
								MovimentoFinal, limite);

						//Principal.this
							//	.processarResultado(
								//		"Busca em Profundidade Limitada concluida com custo",
									//	MovimentoObjetivo);

						Principal.this.setEnabledAllButtons(true);
						Principal.this.embaralhando = false;
						return null;
					}
				};
				threadProf.execute();
			}
		} else if (e.getSource() == buttonBuscaProfundaIte) {
			if (!embaralhando) {
				SwingWorker<Object, Object> threadProf = new SwingWorker<Object, Object>() {
					@Override
					protected Object doInBackground() throws Exception {
						lbStatus.setForeground(Color.BLACK);
						lbStatus.setText("Realizando Busca em Profundidade Iterativo...");
						embaralhando = true;
						Principal.this.setEnabledAllButtons(false);

						Destaque arrayDestaques[] = { Destaque1, Destaque2, Destaque3, Destaque4,
								Destaque5, Destaque6, Destaque7, Destaque8, DestaqueVazia };
						Movimento MovimentoInicio = new Movimento(
								Arrays.asList(arrayDestaques), null);
						buscaProfundidadeInterativa(MovimentoInicio, MovimentoFinal);		    
					    

						Principal.this.setEnabledAllButtons(true);
						Principal.this.embaralhando = false;
						return null;
					}
				};
				threadProf.execute();
			}
		} else if (e.getSource() == buttonBuscaBidirecional) {
			if (!embaralhando) {
				SwingWorker<Object, Object> threadProf = new SwingWorker<Object, Object>() {
					@Override
					protected Object doInBackground() throws Exception {
						lbStatus.setForeground(Color.BLACK);
						lbStatus.setText("Realizando Busca Bidirecional...");
						embaralhando = true;
						Principal.this.setEnabledAllButtons(false);
						
						//Cria as posicoes de acordo com a posicao atual do embaralhamento
						Destaque arrayDestaques[] = { Destaque1, Destaque2, Destaque3, Destaque4,
								Destaque5, Destaque6, Destaque7, Destaque8, DestaqueVazia };
						
						//Cria o movimento inicial partindo da posicao atual
						Movimento MovimentoInicio = new Movimento(Arrays.asList(arrayDestaques), null);
						
						//Inicia as buscas direcionais, a primeira do inicio ao fim e a segunda do fim ao inicio
						Bidirecional esquerda = new Bidirecional(MovimentoInicio, MovimentoFinal);
						Bidirecional direita = new Bidirecional(MovimentoFinal, MovimentoInicio);
						
						//Pilha resultado para mostrar os resultados da busca de esquerda
						Stack<Movimento> movResult = new Stack<Movimento>();
						
						//enquanto ainda existir movimentos possiveis na busca da esquerda e busca da direita
						while(!esquerda.vazia() || !direita.vazia()){
							
							//verifica se o estado atual da busca da esquerda eh o estado final (objetivo)
							Movimento objEsq = esquerda.verificaObjetivo();
							
							//verifica se o estado atual da busca da direita eh o estado inicial (objetivo)
							Movimento objDir = direita.verificaObjetivo();
							
							//caso a busca da esquerda retorne o estado objetivo seu caminho sera mostrado na tela
							if(objEsq != null){
								lbStatus.setText("Busca Sequencial da esquerda encontrou a solucao! passos: "+esquerda.getNumPassos());
								//armazena na pilha os movimentos ate o estado objetivo
								while(objEsq != null){
									movResult.push(objEsq);
									objEsq = objEsq.getMovimentoPai();
								}
								
								//mostra na interface passo a passo o caminho da solucao
								while(!movResult.empty()){
									montaMovimento(movResult.pop());
									Thread.sleep(200);
								}
								
								break;
								
								//caso a busca da direita retorne o estado objetivo seu caminho sera mostrado na tela
							}else if(objDir != null){
								lbStatus.setText("Busca Sequencial da direita encontrou a solucao! passos: "+direita.getNumPassos());
								//criada uma fila para armazenar os movimentos ateh o estado objetivo
								Queue<Movimento> filaResult = new LinkedList<Movimento>();
								
								//armazena na fila os movimentos
								while(objDir != null){
									filaResult.add(objDir);
									objDir = objDir.getMovimentoPai();
								}
								
								//mostra na interface passo a passo o caminho da solucao
								while(!filaResult.isEmpty()){
									montaMovimento(filaResult.remove());
									Thread.sleep(200);
								}
								
								break;
								
							}else{
								//armazena os estados atuais das duas buscas
								objEsq = esquerda.getEstadoAtual();
								objDir = direita.getEstadoAtual();
								
								//busca da esquerda escolhe o proximo movimento 
								//possivel e verifica se este novo estado eh o estado final (objetivo)
								Movimento solucao = esquerda.verificaMovimentosVisitados(objDir);
								if(solucao != null){
									lbStatus.setText("Busca Sequencial encontrou uma solucao conjunta! passos: "+
														(direita.getNumPassos()+esquerda.getNumPassos())
										    		);
									
									//Se o estado atual da direita pertence a algum estado ja visitado da esquerda
									//monta a pilha da resultados da esquerda a partir do estado equivalente encontrado
									while(solucao != null){
										movResult.push(solucao);
										solucao = solucao.getMovimentoPai();
									}
									
									//mostra na interface passo a passo o caminho da busca da esquerda
									while(!movResult.empty()){
										montaMovimento(movResult.pop());
										Thread.sleep(200);
									}
									
									//monta a fila para mostrar os resultados da busca da direita
									Queue<Movimento> filaResult = new LinkedList<Movimento>();

									//armazena na fila os movimentos da busca da direita a partir do estado atual
						            Movimento mv = direita.getEstadoAtual().getMovimentoPai();
									while(mv != null){
										filaResult.add(mv);
										mv = mv.getMovimentoPai();
									}
									
									//mostra na interface passo a passo o caminho da busca da direita
									while(!filaResult.isEmpty()){
										montaMovimento(filaResult.remove());
										Thread.sleep(200);
									}
							        
									break;
								}
								
								//busca da direita escolhe o proximo movimento 
								//possivel e verifica se este novo estado eh o estado inicial (objetivo)
								solucao = direita.verificaMovimentosVisitados(objEsq);
								if(solucao != null){
									lbStatus.setText("Busca Sequencial encontrou uma solucao contraria conjunta! passos: "+
														(direita.getNumPassos()+esquerda.getNumPassos())
												    );									
									
									//Se o estado atual da esquerda pertence a algum estado ja visitado da direita
									//monta a pilha da resultados da esquerda a partir do estado atual				
						            Movimento mv = esquerda.getEstadoAtual().getMovimentoPai();
							        while (mv != null) {
							        	movResult.push(mv);
							            mv = mv.getMovimentoPai();
							        }
							        
							        //mostra na interface passo a passo o caminho da busca da esquerda
									while(!movResult.empty()){
										montaMovimento(movResult.pop());
										Thread.sleep(200);
									}
									//monta a fila para mostrar os resultados da busca da direita
									Queue<Movimento> filaResult = new LinkedList<Movimento>();
									
									//armazena na fila os movimentos da busca da direita a partir do estado equivalente
									while(solucao != null){
										filaResult.add(solucao);
										solucao = solucao.getMovimentoPai();
									}
									
									//mostra na interface passo a passo o caminho da busca da direita
									while(!filaResult.isEmpty()){
										montaMovimento(filaResult.remove());
										Thread.sleep(200);
									}
							       
									break;
								}
								
							}

						}

						Principal.this.setEnabledAllButtons(true);
						Principal.this.embaralhando = false;

						return null;
					}
				};
				threadProf.execute();
			}
		}
	}

	private void setEnabledAllButtons(boolean value) {
		this.buttonBuscaLargura.setEnabled(value);
		this.buttonBuscaAEstrela.setEnabled(value);
		this.buttonEmbaralhar.setEnabled(value);
		this.buttonBuscaProfunda.setEnabled(value);
		this.buttonBuscaProfundaLim.setEnabled(value);
		this.buttonBuscaProfundaIte.setEnabled(value);
	}

	private void processarResultado(String msg, Movimento Movimento) {
		if (Movimento == null) {
			lbStatus.setText("Movimento Objetivo não foi encontrado.");
			lbStatus.setForeground(Color.RED);
			return;
		}

		int custo = 0;
		Stack<Movimento> Movimentos = new Stack<Movimento>();

		Movimentos.add(Movimento);
		while (Movimento.getMovimentoPai() != null) {
			custo++;
			Movimento = Movimento.getMovimentoPai();
			Movimentos.add(Movimento);
		}
		lbStatus.setText(msg + " " + custo);
		while (!Movimentos.isEmpty()) {
			Movimento est = Movimentos.pop();
			Destaque1 = est.getDestaque1();
			Destaque2 = est.getDestaque2();
			Destaque3 = est.getDestaque3();
			Destaque4 = est.getDestaque4();
			Destaque5 = est.getDestaque5();
			Destaque6 = est.getDestaque6();
			Destaque7 = est.getDestaque7();
			Destaque8 = est.getDestaque8();
			DestaqueVazia = est.getDestaqueVazia();
			try {
				Thread.sleep(500);
				montarTabuleiro();
				repaint();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
	}
	private void processarBuscaLista(String msg, List<Movimento> Movimento) {
		if (Movimento == null) {
			lbStatus.setText("Movimento Objetivo n�o foi encontrado.");
			lbStatus.setForeground(Color.RED);
			return;
		}
		Iterator<Movimento> estad = Movimento.iterator();
		lbStatus.setText(msg + " " + Movimento.size());
		while (estad.hasNext()) {
			Movimento est = estad.next();
			Destaque1 = est.getDestaque1();
			Destaque2 = est.getDestaque2();
			Destaque3 = est.getDestaque3();
			Destaque4 = est.getDestaque4();
			Destaque5 = est.getDestaque5();
			Destaque6 = est.getDestaque6();
			Destaque7 = est.getDestaque7();
			Destaque8 = est.getDestaque8();
			DestaqueVazia = est.getDestaqueVazia();
			try {
				Thread.sleep(500);
				montarTabuleiro();
				repaint();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
	}
	// nova fun��o mostrar busca em largura
	private void processarBusca(String msg, Queue<Movimento> Movimento) {
		if (Movimento == null) {
			lbStatus.setText("Movimento Objetivo n�o foi encontrado.");
			lbStatus.setForeground(Color.RED);
			return;
		}

		lbStatus.setText(msg + " " + Movimento.size());
		while (!Movimento.isEmpty()) {
			Movimento est = Movimento.remove();
			Destaque1 = est.getDestaque1();
			Destaque2 = est.getDestaque2();
			Destaque3 = est.getDestaque3();
			Destaque4 = est.getDestaque4();
			Destaque5 = est.getDestaque5();
			Destaque6 = est.getDestaque6();
			Destaque7 = est.getDestaque7();
			Destaque8 = est.getDestaque8();
			DestaqueVazia = est.getDestaqueVazia();
			try {
				Thread.sleep(500);
				montarTabuleiro();
				repaint();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	//monta um Movimento
	public void montaMovimento(Movimento est){
		Destaque1 = est.getDestaque1();
		Destaque2 = est.getDestaque2();
		Destaque3 = est.getDestaque3();
		Destaque4 = est.getDestaque4();
		Destaque5 = est.getDestaque5();
		Destaque6 = est.getDestaque6();
		Destaque7 = est.getDestaque7();
		Destaque8 = est.getDestaque8();
		DestaqueVazia = est.getDestaqueVazia();
			montarTabuleiro();
			repaint();
		
	}
	// busca em largura
	private Queue<Movimento> buscaEmLargura(Movimento inicio, Movimento fim) {
		Queue<Movimento> Movimentos = new LinkedList<Movimento>();
		Queue<Movimento> MovimentosTotais = new LinkedList<Movimento>();
		Queue<Movimento> MovimentosVisitados = new LinkedList<Movimento>();
		
		Movimentos.add(inicio);
		MovimentosTotais.add(inicio);
		//int cont = 0;
		while (!Movimentos.isEmpty()) {
			Movimento Movimento = Movimentos.remove();
			//System.out.println("Movimentos: "+cont);
			//cont++;
			if (Movimento.equals(fim)) {
				MovimentosVisitados.add(Movimento);
				return MovimentosVisitados;
			}else{
				MovimentosVisitados.add(Movimento);
			}
			
			for (Movimento e : Movimento.getMovimentosFilhos()) {
				if (!MovimentosTotais.contains(e)) {
						Movimentos.add(e);
						MovimentosTotais.add(e);
				}
			}
			//System.out.println("Tamanho da Fila: "+MovimentosTotais.size());
			//System.out.println("Movimentos Leitura: "+Movimentos.size());
			
			if(MovimentosTotais.size() >= 181400){
				System.out.println("Erro na rotina!!!");
				break;
			}
							
		}
		return null;
	}

	// medodo busca A estrela
	private Movimento buscaAestrela(Movimento inicio, Movimento fim) {
		List<Movimento> MovimentosNaoCalculados = new ArrayList<Movimento>();
		List<Movimento> MovimentosCalculados = new ArrayList<Movimento>();
		MovimentosNaoCalculados.add(inicio);

		while (!MovimentosNaoCalculados.isEmpty()) {
			Movimento menorMovimento = Collections.min(MovimentosNaoCalculados);
			MovimentosNaoCalculados.remove(menorMovimento);
			MovimentosCalculados.add(menorMovimento);
			if (menorMovimento.equals(fim)) {
				return menorMovimento;
			}
			for (Movimento filho : menorMovimento.getMovimentosFilhos()) {

				if (!MovimentosNaoCalculados.contains(filho)
						&& !MovimentosCalculados.contains(filho)) {
					MovimentosNaoCalculados.add(filho);
				}
			}
		}
		return null;

	}

	

	// busca profundidade limitada
	private Movimento buscaProfundidadeLimitada(Movimento inicio, Movimento fim, int limite) throws InterruptedException {
		Stack<Movimento> caminho = new Stack<Movimento>();
		List<Movimento> visitados = new ArrayList<Movimento>();
		Movimento dado = inicio;
		int cont = 0;
		caminho.add(dado);
		visitados.add(dado);
		if(limite == 0 && !inicio.equals(fim)){
			lbStatus.setText("BUSCA EM PROFUNDIDADE LIMITADA EXECUTADA SEM SUCESSO");
			lbStatus.setForeground(Color.RED);
			return null;
		}		
		while(!caminho.isEmpty() && !dado.equals(fim) && limite != 0){		
			List<Movimento> Movimentos = dado.getMovimentosFilhosOrdenados();
			for(Movimento tab : Movimentos){
				cont = 0;
				if(tab != null){
					if(visitados.contains(tab)){
						continue;
					}
					cont = 1;
					dado = tab;
					caminho.add(tab);
					visitados.add(tab);
					Thread.sleep(300);
					montaMovimento(tab);
					break;
				}
			}
			if(cont == 0){
				System.out.println("Vontando "+caminho.size());
				dado = caminho.pop();
				Thread.sleep(300);
				montaMovimento(dado);
			}
			if(!dado.equals(fim) && caminho.size() >= limite+1){
				dado = caminho.pop();
				dado = caminho.pop();
				caminho.add(dado);
				Thread.sleep(300);
				montaMovimento(dado);
			}
			if(dado.equals(inicio)){
				Movimentos = dado.getMovimentosFilhosOrdenados();
				for(Movimento est: Movimentos){
					if(!visitados.contains(est)){
						caminho.add(dado);
						break;
					}
				}
			}
		}
		if(caminho.isEmpty()){
			lbStatus.setText("Busca em profundidade Limitada executada sem sucesso para o limite "+limite);
			lbStatus.setForeground(Color.RED);
			return null;
		}
		lbStatus.setText("Busca em profundidade Limitada executada com custo "+(caminho.size() -1));
		return fim;
	}

	// busca profundidade interativa
	private void buscaProfundidadeInterativa(Movimento inicio, Movimento fim) throws InterruptedException {
		int limite =0;
		Movimento dado = inicio;
		for(;;){
			lbStatus.setText("Busca em profundidade iterativa. N�vel : "+limite);
			dado = buscaProfundidadeLimitada(inicio, fim, limite);
			System.out.println("Nivel");
			if(dado != null && dado.equals(fim)){
				lbStatus.setText("Busca em profundidade iterativa executada com sucesso!!! Limite: "+limite);
				lbStatus.setForeground(Color.black);
				break;
			}
			limite++;
		}
		
	}
	
	public boolean jogoGanho() {
		if (button1.getDestaque().getNumero().equals("1")) {
			if (button2.getDestaque().getNumero().equals("2")) {
				if (button3.getDestaque().getNumero().equals("3")) {
					if (button4.getDestaque().getNumero().equals("8")) {
						if (button5.getDestaque().isVazia()) {
							if (button6.getDestaque().getNumero().equals("4")) {
								if (button7.getDestaque().getNumero().equals("7")) {
									if (button8.getDestaque().getNumero()
											.equals("6")) {
										if (button9.getDestaque().getNumero()
												.equals("5")) {
											return true;
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return false;
	}

	public void paint(Graphics g) {
		super.paint(g);
		if (jogoGanho()) {
			g.setColor(Color.green);
			g.fillRect(23, 43, 170, 10);// up
			g.fillRect(33, 205, 160, 10);// down
			g.fillRect(23, 50, 10, 165);// left
			g.fillRect(185, 43, 10, 172);// rigth
		} else {
			g.dispose();
		}
		if (primeiraVez) {
			repaint();
			primeiraVez = false;
		}

	}

	public static void main(String[] args) {
		Principal principal = new Principal();
		Principal.setDefaultLookAndFeelDecorated(true);
		principal.setLocationRelativeTo(null);
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {

				} catch (Exception dado) {
					dado.printStackTrace();
				}

			}
		});

	}


//buscar profundidade
private Stack<Movimento> buscaProfundidade(Movimento inicio, Movimento fim) throws InterruptedException {
	List<Movimento> visitados = new ArrayList<Movimento>();
	Stack<Movimento> caminho = new Stack<Movimento>();
	
	Movimento dado = inicio;
	int cont = 0;
	caminho.add(dado);
	visitados.add(dado);
	
	while(!caminho.isEmpty() && !dado.equals(fim)){		
		List<Movimento> Movimentos = dado.getMovimentosFilhosOrdenados();
		for(Movimento tab : Movimentos){
			cont = 0;
			if(tab != null){
				if(visitados.contains(tab)){
					continue;
				}
				cont = 1;
				dado = tab;
				caminho.add(tab);
				visitados.add(tab);
				Thread.sleep(00);
				montaMovimento(tab);
				break;
			}
		}
		if(cont == 0){
			System.out.println("Voltando "+caminho.size());
			dado = caminho.pop();
		}
	}   
	lbStatus.setText("BUSCA EM PROFUNDIDADE EXECUTADA COM SUCESSO "+(caminho.size() - 1));
	return caminho;
}
}
