package desafios.kruskal;

public enum Estado {
	RO(0),
	AC(1),
	AM(2),
	RR(3),
	PA(4),
	AP(5),
	TO(6),
	MA(7),
	PI(8),
	CE(9),
	RN(10),
	PB(11),
	PE(12),
	AL(13),
	SE(14),
	BA(15),
	MG(16),
	ES(17),
	RJ(18),
	SP(19),
	PR(20),
	SC(21),
	RS(22),
	MS(23),
	MT(24),
	GO(25),
	DF(26);
	
	public int valor;
	Estado(int _valor) {
		valor = _valor;
	}
}
