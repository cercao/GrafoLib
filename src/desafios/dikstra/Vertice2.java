package desafios.dikstra;

import desafios.kruskal.Node;

public class Vertice2 {
	private double lat;
	private double lon;
	private int estado;
	private Node n;

	public Vertice2(double _lat, double _lon, int _estado) {
		this.setLat(_lat);
		this.setLon(_lon);
		this.setEstado(_estado);
	}

	public void setNode(Node n) {
		this.n = n;
	}

	public Node getNode() {
		return this.n;
	}

	public static double distancia(Vertice2 a, Vertice2 b) {
		double R = 6371.0; // raio da terrakm
		double dLat = (b.getLat() - a.getLat()) * Math.PI / 180.0;
		double dLon = (b.getLon() - a.getLon()) * Math.PI / 180;

		double x = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(a.getLat() * Math.PI / 180)
				* Math.cos(b.getLat() * Math.PI / 180) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
		double y = 2 * Math.atan2(Math.sqrt(x), Math.sqrt(1 - x));
		double z = R * y;
		// distancia alterada para o dikistra para evitar retas longas (elevar ao quadrado)
		return Math.pow(z,2);
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int _estado) {
		this.estado = _estado;
	}
}
