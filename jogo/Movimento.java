
package jogo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Movimento implements Comparable<Movimento> {

	private Destaque Destaque1 = new Destaque("1", 0, null, null, null, null);
	private Destaque Destaque2 = new Destaque("2", 0, null, null, null, null);;
	private Destaque Destaque3 = new Destaque("3", 0, null, null, null, null);;
	private Destaque Destaque4 = new Destaque("4", 0, null, null, null, null);;
	private Destaque Destaque5 = new Destaque("5", 0, null, null, null, null);;
	private Destaque Destaque6 = new Destaque("6", 0, null, null, null, null);;
	private Destaque Destaque7 = new Destaque("7", 0, null, null, null, null);;
	private Destaque Destaque8 = new Destaque("8", 0, null, null, null, null);;
	private Destaque DestaqueVazia = new Destaque("vazia", 0, null, null, null, null);

	private Integer hScore = null;
	private Integer gScore = null;
	private Integer fScore = null;

	private int nivelArvore;

	public String toMovimentos() {
		return "Posicao1: " + Destaque1.getNumero() + " Posicao2: "
				+ Destaque2.getNumero() + " Posicao3: " + Destaque3.getNumero()
				+ " Posicao4: " + Destaque4.getNumero() + " Posicao5: "
				+ Destaque5.getNumero() + " Posicao6: " + Destaque6.getNumero()
				+ " Posicao7: " + Destaque7.getNumero() + " Posicao8: "
				+ Destaque8.getNumero() + "Posicao9: " + DestaqueVazia.getNumero();

	}

	public Integer getgScore() {
		if (gScore == null) {
			gScore = 0;
			if (Destaque1.getPosicao() != 1) {
				gScore++;
			}
			if (Destaque2.getPosicao() != 2) {
				gScore++;
			}
			if (Destaque3.getPosicao() != 3) {
				gScore++;
			}
			if (Destaque4.getPosicao() != 4) {
				gScore++;
			}
			if (Destaque5.getPosicao() != 5) {
				gScore++;
			}
			if (Destaque6.getPosicao() != 6) {
				gScore++;
			}
			if (Destaque7.getPosicao() != 7) {
				gScore++;
			}
			if (Destaque8.getPosicao() != 8) {
				gScore++;
			}
			if (DestaqueVazia.getPosicao() != 9) {
				gScore++;
			}
		}
		return gScore;
	}

	public Integer gethScore() {
		if (hScore == null) {
			hScore = 0;
			Destaque Destaques[] = { Destaque1, Destaque2, Destaque3, Destaque8, DestaqueVazia, Destaque4,
					Destaque7, Destaque6, Destaque5 };
			Integer colunasFinais[] = { 1, 2, 3, 1, 2, 3, 1, 2, 3 };
			Integer linhasFinais[] = { 1, 1, 1, 2, 2, 2, 3, 3, 3 };
			for (int x = 0; x < Destaques.length; x++) {
				Integer valor = 0;
				if (Destaques[x].getPosicao() != (x + 1)) {
					Integer coluna = Destaques[x].getColuna();
					Integer coluna2 = colunasFinais[x];
					Integer linha = Destaques[x].getLinha();
					Integer linha2 = linhasFinais[x];
					if (coluna == coluna2) {
						valor = linha - linha2;
						if (valor < 0) {
							valor = valor * -1;
						}
					} else {
						if (coluna + 1 == coluna2 || coluna - 1 == coluna2) {
							valor = (linha + 1) - linha2;
							if (valor < 0) {
								valor = valor * -1;
							}
						} else {
							valor = (linha + 2) - linha2;
							if (valor < 0) {
								valor = valor * -1;
							}
						}
					}
				}
				hScore += valor;
			}
		}
		return hScore;
	}

	public Integer getfScore() {
		if (fScore == null) {
			fScore = getgScore() + gethScore();
		}
		return fScore;
	}

	private List<Movimento> MovimentosFilhos = new ArrayList<Movimento>();
	private Movimento MovimentoPai = null;
	private boolean visitado = false;

	public Movimento(List<Destaque> DestaquesOriginais, Movimento pai) {
		List<Destaque> Destaques = new ArrayList<Destaque>();
		Destaque1.setPosicao(DestaquesOriginais.get(0).getPosicao());
		Destaque2.setPosicao(DestaquesOriginais.get(1).getPosicao());
		Destaque3.setPosicao(DestaquesOriginais.get(2).getPosicao());
		Destaque4.setPosicao(DestaquesOriginais.get(3).getPosicao());
		Destaque5.setPosicao(DestaquesOriginais.get(4).getPosicao());
		Destaque6.setPosicao(DestaquesOriginais.get(5).getPosicao());
		Destaque7.setPosicao(DestaquesOriginais.get(6).getPosicao());
		Destaque8.setPosicao(DestaquesOriginais.get(7).getPosicao());
		DestaqueVazia.setPosicao(DestaquesOriginais.get(8).getPosicao());
		Destaques.add(Destaque1);
		Destaques.add(Destaque2);
		Destaques.add(Destaque3);
		Destaques.add(Destaque4);
		Destaques.add(Destaque5);
		Destaques.add(Destaque6);
		Destaques.add(Destaque7);
		Destaques.add(Destaque8);
		Destaques.add(DestaqueVazia);
		String strEsquerda = "";
		String strDireita = "";
		String strCima = "";
		String strBaixo = "";
		for (Destaque Destaque : DestaquesOriginais) {
			if (Destaque.getDestaqueEsquerda() != null) {
				strEsquerda = Destaque.getDestaqueEsquerda().getNumero();
				if (strEsquerda.equals("vazia")) {
					strEsquerda = "9";
				}
				Destaques.get(DestaquesOriginais.indexOf(Destaque)).setDestaqueEsquerda(
						Destaques.get(Integer.parseInt(strEsquerda) - 1));
			} else {
				Destaques.get(DestaquesOriginais.indexOf(Destaque)).setDestaqueEsquerda(null);
			}
			if (Destaque.getDestaqueDireita() != null) {
				strDireita = Destaque.getDestaqueDireita().getNumero();
				if (strDireita.equals("vazia")) {
					strDireita = "9";
				}
				Destaques.get(DestaquesOriginais.indexOf(Destaque)).setDestaqueDireita(
						Destaques.get(Integer.parseInt(strDireita) - 1));
			} else {
				Destaques.get(DestaquesOriginais.indexOf(Destaque)).setDestaqueDireita(null);
			}
			if (Destaque.getDestaqueCima() != null) {
				strCima = Destaque.getDestaqueCima().getNumero();
				if (strCima.equals("vazia")) {
					strCima = "9";
				}
				Destaques.get(DestaquesOriginais.indexOf(Destaque)).setDestaqueCima(
						Destaques.get(Integer.parseInt(strCima) - 1));
			} else {
				Destaques.get(DestaquesOriginais.indexOf(Destaque)).setDestaqueCima(null);
			}
			if (Destaque.getDestaqueBaixo() != null) {
				strBaixo = Destaque.getDestaqueBaixo().getNumero();
				if (strBaixo.equals("vazia")) {
					strBaixo = "9";
				}
				Destaques.get(DestaquesOriginais.indexOf(Destaque)).setDestaqueBaixo(
						Destaques.get(Integer.parseInt(strBaixo) - 1));
			} else {
				Destaques.get(DestaquesOriginais.indexOf(Destaque)).setDestaqueBaixo(null);
			}
		}
		this.Destaque1 = Destaques.get(0);
		this.Destaque2 = Destaques.get(1);
		this.Destaque3 = Destaques.get(2);
		this.Destaque4 = Destaques.get(3);
		this.Destaque5 = Destaques.get(4);
		this.Destaque6 = Destaques.get(5);
		this.Destaque7 = Destaques.get(6);
		this.Destaque8 = Destaques.get(7);
		this.DestaqueVazia = Destaques.get(8);
		this.setMovimentoPai(pai);
		Destaques = null;
		DestaquesOriginais = null;
	}

	public Movimento getMovimentoPai() {
		return MovimentoPai;
	}

	public void setMovimentoPai(Movimento MovimentoPai) {
		this.MovimentoPai = MovimentoPai;
	}

	public boolean isVisitado() {
		return visitado;
	}

	public void setVisitado(boolean visitado) {
		this.visitado = visitado;
	}
	
	//---------------------------------------------------------------------------
	public List<Movimento> getMovimentosFilhos() {
		MovimentosFilhos = new ArrayList<Movimento>();
		for (int x = 0; x < 4; x++) {
			Destaque arrayDestaques[] = { Destaque1, Destaque2, Destaque3, Destaque4, Destaque5, Destaque6,
					Destaque7, Destaque8, DestaqueVazia };
			List<Destaque> Destaques = Arrays.asList(arrayDestaques);
			if (x == 0) {
				if (DestaqueVazia.moverCima() != 0) {
					Movimento Movimento = new Movimento(Destaques, this);
					if (this.getMovimentoPai() == null
							|| !Movimento.equals(this.getMovimentoPai())) {
						MovimentosFilhos.add(Movimento);
					}
					DestaqueVazia.moverBaixo();
				}
			}
			if (x == 1) {
				if (DestaqueVazia.moverBaixo() != 0) {
					Movimento Movimento = new Movimento(Destaques, this);
					if (this.getMovimentoPai() == null
							|| !Movimento.equals(this.getMovimentoPai())) {
						MovimentosFilhos.add(Movimento);
					}
					DestaqueVazia.moverCima();
				}
			}
			if (x == 2) {
				if (DestaqueVazia.moverEsquerda() != 0) {
					Movimento Movimento = new Movimento(Destaques, this);
					if (this.getMovimentoPai() == null
							|| !Movimento.equals(this.getMovimentoPai())) {
						MovimentosFilhos.add(Movimento);
					}
					DestaqueVazia.moverDireita();
				}
			}
			if (x == 3) {
				if (DestaqueVazia.moverDireita() != 0) {
					Movimento Movimento = new Movimento(Destaques, this);
					if (this.getMovimentoPai() == null
							|| !Movimento.equals(this.getMovimentoPai())) {
						MovimentosFilhos.add(Movimento);
					}
					DestaqueVazia.moverEsquerda();
				}
			}
		}
		return MovimentosFilhos;
	}
	//---------------------------------------------------------------------
	public List<Movimento> getMovimentosFilhosOrdenados() {
		MovimentosFilhos = new ArrayList<Movimento>();
		for (int x = 0; x < 4; x++) {
			Destaque arrayDestaques[] = { Destaque1, Destaque2, Destaque3, Destaque4, Destaque5, Destaque6,
					Destaque7, Destaque8, DestaqueVazia };
			List<Destaque> Destaques = Arrays.asList(arrayDestaques);
			if (x == 0) {
				if (DestaqueVazia.moverCima() != 0) {
					Movimento Movimento = new Movimento(Destaques, this);
					if (this.getMovimentoPai() == null
							|| !Movimento.equals(this.getMovimentoPai())) {
						MovimentosFilhos.add(Movimento);
					}
					else{
						MovimentosFilhos.add(null);
					}
					DestaqueVazia.moverBaixo();
				}
			}
			if (x == 1) {
				if (DestaqueVazia.moverEsquerda() != 0) {
					Movimento Movimento = new Movimento(Destaques, this);
					if (this.getMovimentoPai() == null
							|| !Movimento.equals(this.getMovimentoPai())) {
						MovimentosFilhos.add(Movimento);
					}
					else{
						MovimentosFilhos.add(null);
					}
					DestaqueVazia.moverDireita();
				}
			}
			if (x == 2) {
				if (DestaqueVazia.moverDireita() != 0) {
					Movimento Movimento = new Movimento(Destaques, this);
					if (this.getMovimentoPai() == null
							|| !Movimento.equals(this.getMovimentoPai())) {
						MovimentosFilhos.add(Movimento);
					}
					else{
						MovimentosFilhos.add(null);
					}
					DestaqueVazia.moverEsquerda();
				}
			}
			if (x == 3) {
				if (DestaqueVazia.moverBaixo() != 0) {
					Movimento Movimento = new Movimento(Destaques, this);
					if (this.getMovimentoPai() == null
							|| !Movimento.equals(this.getMovimentoPai())) {
						MovimentosFilhos.add(Movimento);
					}
					else{
						MovimentosFilhos.add(null);
					}
					DestaqueVazia.moverCima();
				}
			}
		}
		return MovimentosFilhos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Destaque1 == null) ? 0 : Destaque1.hashCode());
		result = prime * result + ((Destaque2 == null) ? 0 : Destaque2.hashCode());
		result = prime * result + ((Destaque3 == null) ? 0 : Destaque3.hashCode());
		result = prime * result + ((Destaque4 == null) ? 0 : Destaque4.hashCode());
		result = prime * result + ((Destaque5 == null) ? 0 : Destaque5.hashCode());
		result = prime * result + ((Destaque6 == null) ? 0 : Destaque6.hashCode());
		result = prime * result + ((Destaque7 == null) ? 0 : Destaque7.hashCode());
		result = prime * result + ((Destaque8 == null) ? 0 : Destaque8.hashCode());
		result = prime * result
				+ ((DestaqueVazia == null) ? 0 : DestaqueVazia.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;

		if (!(obj instanceof Movimento))
			return false;

		Movimento other = (Movimento) obj;
		if (Destaque1 == null) {
			if (other.Destaque1 != null)
				return false;
		} else if (!Destaque1.equals(other.Destaque1))
			return false;
		if (Destaque2 == null) {
			if (other.Destaque2 != null)
				return false;
		} else if (!Destaque2.equals(other.Destaque2))
			return false;
		if (Destaque3 == null) {
			if (other.Destaque3 != null)
				return false;
		} else if (!Destaque3.equals(other.Destaque3))
			return false;
		if (Destaque4 == null) {
			if (other.Destaque4 != null)
				return false;
		} else if (!Destaque4.equals(other.Destaque4))
			return false;
		if (Destaque5 == null) {
			if (other.Destaque5 != null)
				return false;
		} else if (!Destaque5.equals(other.Destaque5))
			return false;
		if (Destaque6 == null) {
			if (other.Destaque6 != null)
				return false;
		} else if (!Destaque6.equals(other.Destaque6))
			return false;
		if (Destaque7 == null) {
			if (other.Destaque7 != null)
				return false;
		} else if (!Destaque7.equals(other.Destaque7))
			return false;
		if (Destaque8 == null) {
			if (other.Destaque8 != null)
				return false;
		} else if (!Destaque8.equals(other.Destaque8))
			return false;
		if (DestaqueVazia == null) {
			if (other.DestaqueVazia != null)
				return false;
		} else if (!DestaqueVazia.equals(other.DestaqueVazia))
			return false;
		return true;
	}

	public Destaque getDestaque1() {
		return Destaque1;
	}

	public void setDestaque1(Destaque Destaque1) {
		this.Destaque1 = Destaque1;
	}

	public Destaque getDestaque2() {
		return Destaque2;
	}

	public void setDestaque2(Destaque Destaque2) {
		this.Destaque2 = Destaque2;
	}

	public Destaque getDestaque3() {
		return Destaque3;
	}

	public void setDestaque3(Destaque Destaque3) {
		this.Destaque3 = Destaque3;
	}

	public Destaque getDestaque4() {
		return Destaque4;
	}

	public void setDestaque4(Destaque Destaque4) {
		this.Destaque4 = Destaque4;
	}

	public Destaque getDestaque5() {
		return Destaque5;
	}

	public void setDestaque5(Destaque Destaque5) {
		this.Destaque5 = Destaque5;
	}

	public Destaque getDestaque6() {
		return Destaque6;
	}

	public void setDestaque6(Destaque Destaque6) {
		this.Destaque6 = Destaque6;
	}

	public Destaque getDestaque7() {
		return Destaque7;
	}

	public void setDestaque7(Destaque Destaque7) {
		this.Destaque7 = Destaque7;
	}

	public Destaque getDestaque8() {
		return Destaque8;
	}

	public void setDestaque8(Destaque Destaque8) {
		this.Destaque8 = Destaque8;
	}

	public Destaque getDestaqueVazia() {
		return DestaqueVazia;
	}

	public void setDestaqueVazia(Destaque DestaqueVazia) {
		this.DestaqueVazia = DestaqueVazia;
	}

	public void setNivelArvore(int nivel) {
		this.nivelArvore = nivel;
	}

	public int getNivelArvore() {
		return this.nivelArvore;
	}

	public int compareTo(Movimento e) {
		if (this.getfScore() < e.getfScore()) {
			return -1;
		} else {
			if (this.getfScore() > e.getfScore()) {
				return 1;
			}
		}
		return 0;
	}

}


