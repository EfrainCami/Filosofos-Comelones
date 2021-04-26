public class Filosofo2 extends Thread {
    private String nombre;
    private Tenedor manoDerecha;
    private Tenedor manoIzquierda;
    private int noFilosofo;
    private int manoParaIniciar;

    public Filosofo2(String nombre, Tenedor tenedor1, Tenedor tenedor2, int noFilosofo, int manoParaIniciar) {
        this.nombre = nombre;
        this.manoDerecha = tenedor1;
        this.manoIzquierda = tenedor2;
        this.noFilosofo = noFilosofo;
        this.manoParaIniciar = manoParaIniciar;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Tenedor getManoDerecha() {
        return manoDerecha;
    }

    public void setManoDerecha(Tenedor manoDerecha) {
        this.manoDerecha = manoDerecha;
    }

    public Tenedor getManoIzquierda() {
        return manoIzquierda;
    }

    public void setManoIzquierda(Tenedor manoIzquierda) {
        this.manoIzquierda = manoIzquierda;
    }

    @Override
    public void run() {
        vivir(manoParaIniciar);
    }

    public void vivir(int mano) {
        System.out.println(nombre + ": Toma asiento de manera filosofica");
        while (true) {
            if (tomarTenedores(mano)) {
                comer();
            } else {
                pensar();
            }
        }
    }

    public void pensar() {
        System.out.println(nombre + ": * Pensanding *\n");
        dormir();
    }

    public void dormir() {
        try {
            Thread.sleep((long) Math.floor((Math.random() * 20) * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean tomarDerecho() {
        boolean tomado = false;
        synchronized (manoDerecha) {
            if (manoDerecha.getQuienLoTiene() == 0) {
                manoDerecha.setQuienLoTiene(noFilosofo);
                System.out.println(nombre + ": he tomado mi tenedor derecho " + manoDerecha.getNumero() + "\n");
                tomado = true;
            } else {
                System.out.println(nombre + ": no pude tomar mi tenedor derecho " + manoDerecha.getNumero() + "\n");
                try {
                    manoDerecha.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return tomado;
    }

    public boolean tomarIzquierdo() {
        boolean tomado = false;
        synchronized (manoIzquierda){
            if (manoIzquierda.getQuienLoTiene() == 0) {
                manoIzquierda.setQuienLoTiene(noFilosofo);
                System.out.println(nombre + ": he tomado mi tenedor izquierdo " + manoIzquierda.getNumero() + "\n");
                tomado = true;
            } else {
                System.out.println(nombre + ": no pude tomar mi tenedor izquierdo " + manoIzquierda.getNumero() + "\n");
                try {
                    manoIzquierda.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return tomado;
        }
    }


    public boolean tomarTenedores(int mano) {
        boolean tomado = false;
        if (mano == 1) {
            if (tomarDerecho()) {
                if(tomarIzquierdo()){
                    tomado = true;
                }else {
                    liberarTenedores();
                }
            }else {
                liberarTenedores();
            }
        }else if(mano == 2){
            if (tomarIzquierdo()) {
                if(tomarDerecho()){
                    tomado = true;
                }else {
                    liberarTenedores();
                }
            }else {
                liberarTenedores();
            }
        }
        return tomado;
    }


    public void comer() {
        System.out.println(nombre + ": Mmmm que rico spaghetti\n");
        dormir();
        liberarTenedores();
        System.out.println(nombre + ": Ya me llene jaja\n");
        pensar();
    }

    public void liberarTenedores() {
        synchronized (manoDerecha) {
            synchronized (manoIzquierda) {
                manoDerecha.setQuienLoTiene(0);
                manoDerecha.notifyAll();
                manoIzquierda.setQuienLoTiene(0);
                manoIzquierda.notifyAll();
            }
        }
    }
}
