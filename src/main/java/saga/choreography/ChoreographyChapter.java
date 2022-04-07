package saga.choreography;

public interface ChoreographyChapter {

    Saga execute(Saga saga);

    String getName();

    Saga process(Saga saga);

    Saga rollback(Saga saga);

}
