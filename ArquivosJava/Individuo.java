import java.io.Serializable;

// Classe para representar um indivíduo no AG (Algoritmo Genético)
public abstract class Individuo<T,E> implements Serializable{
    protected T cromossomo;
    
    public Individuo(T cromossomo) {
        this.cromossomo = cromossomo;
    }
    
    // Para retornar o fenótipo (característica) do indivíduo.
    public abstract E getFenotipo();
    public abstract float getAptidao();
    public abstract void mostrarIndividuo();
}
