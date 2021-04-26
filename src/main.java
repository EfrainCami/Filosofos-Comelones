public class main {
    public static void main(String[] args) {

        Tenedor tenedor1 = new Tenedor(1);
        Tenedor tenedor2 = new Tenedor(2);
        Tenedor tenedor3 = new Tenedor(3);
        Tenedor tenedor4 = new Tenedor(4);
        Tenedor tenedor5 = new Tenedor(5);

        Thread filosofo1 = new Filosofo("Filosofo 1",tenedor1,tenedor2,1);
        Thread filosofo2 = new Filosofo("Filosofo 2",tenedor2,tenedor3,2);
        Thread filosofo3 = new Filosofo("Filosofo 3",tenedor3,tenedor4,3);
        Thread filosofo4 = new Filosofo("Filosofo 4",tenedor4,tenedor5,4);
        Thread filosofo5 = new Filosofo("Filosofo 5",tenedor5,tenedor1,5);

        filosofo1.start();
        filosofo2.start();
        filosofo3.start();
        filosofo4.start();
        filosofo5.start();
    }
}
