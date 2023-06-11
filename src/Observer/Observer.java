package Observer;

@FunctionalInterface
public interface Observer {

    public void update(ObserveType t, Object o);

}
