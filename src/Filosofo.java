public class Filosofo extends Thread {
    private String nombre;
    private Tenedor manoDerecha;
    private Tenedor manoIzquierda;
    private int noFilosofo;

    public Filosofo(String nombre, Tenedor tenedor1, Tenedor tenedor2, int noFilosofo) {
        this.nombre = nombre;
        this.manoDerecha = tenedor1;
        this.manoIzquierda = tenedor2;
        this.noFilosofo = noFilosofo;
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
        vivir();
    }

    public void vivir() {
        System.out.println(nombre + ": Toma asiento de manera filosofica");
        while (true) {
            int mano = (int) (Math.random() * 2 + 1);
            if (tomarTenedor(mano)) {
                comer();
            } else {
                pensar();
            }
        }
    }

    public void pensar() {
        System.out.println(nombre + ": * Pensanding *\n");
        esperar();
    }

    public void esperar() {
        try {
            Thread.sleep((long) Math.floor((Math.random() * 10) * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public boolean tomarTenedor(int mano) {
        boolean tomado = false;
        try {
            if (mano == 1) {
                if (manoDerecha.getQuienLoTiene() == 0) {
                    manoDerecha.setQuienLoTiene(noFilosofo);
                    System.out.println(nombre + ": he tomado mi tenedor derecho " + manoDerecha.getNumero()+ "\n");
                } else {
                    System.out.println(nombre + ": no pude tomar mi tenedor derecho "+ manoDerecha.getNumero()+ "\n");
                }
            }else if (mano == 2){
                if (manoIzquierda.getQuienLoTiene() == 0) {
                    manoIzquierda.setQuienLoTiene(noFilosofo);
                    System.out.println(nombre + ": he tomado mi tenedor izquierdo "+ manoIzquierda.getNumero()+ "\n");
                } else {
                    System.out.println(nombre + ": no pude tomar mi tenedor izquierdo "+ manoIzquierda.getNumero()+ "\n");
                }
            }
/*
            switch (mano) {
                case 1:
                    if (manoDerecha.getQuienLoTiene() == 0) {
                        manoDerecha.setQuienLoTiene(noFilosofo);
                        System.out.println(nombre + ": he tomado mi tenedor derecho\n");
                    } else {
                        System.out.println(nombre + ": no pude tomar mi tenedor derecho\n");
                    }
                case 2:
                    if (manoIzquierda.getQuienLoTiene() == 0) {
                        manoIzquierda.setQuienLoTiene(noFilosofo);
                        System.out.println(nombre + ": he tomado mi tenedor izquierdo\n");
                    } else {
                        System.out.println(nombre + ": no pude tomar mi tenedor izquierdo\n");
                    }
                default:
                    System.out.println("Ora que pdo " + mano);
            }

 */


            if (manoDerecha.getQuienLoTiene() == noFilosofo && manoIzquierda.getQuienLoTiene() == noFilosofo) {
                tomado = true;
            }
        } catch (Exception exception) {
            System.out.println(exception);
        }

        return tomado;
    }


    public void comer() {
        //System.out.println(nombre + ": derecha adc: " + manoDerecha.isTomado() + "\n");
        //System.out.println(nombre + ": izquierda adc: " + manoIzquierda.isTomado() + "\n");
        System.out.println(nombre + ": Mmmm que rico spaghetti\n");
        esperar();
        liberarTenedores();
        System.out.println(nombre + ": Ya me llene jaja\n");
        //System.out.println(nombre + ": derecha ddc: " + manoDerecha.isTomado() + "\n");
        //System.out.println(nombre + ": izquierda ddc: " + manoIzquierda.isTomado() + "\n");
    }

    public void liberarTenedores() {
        manoDerecha.setQuienLoTiene(0);
        manoIzquierda.setQuienLoTiene(0);

    }

}
