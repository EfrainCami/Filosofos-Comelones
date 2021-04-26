public class Tenedor {
    private int numero;
    private int quienLoTiene;

    public int getQuienLoTiene() {
        return quienLoTiene;
    }

    public void setQuienLoTiene(int quienLoTiene) {
        this.quienLoTiene = quienLoTiene;
    }

    private boolean tomado;

    public Tenedor(int numero) {
        this.numero = numero;
        this.tomado = false;
        this.quienLoTiene = 0;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public boolean isTomado() {
        return tomado;
    }

    public void setTomado(boolean tomado) {
        this.tomado = tomado;
    }
}
