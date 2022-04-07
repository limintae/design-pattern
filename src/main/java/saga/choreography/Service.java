package saga.choreography;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Supplier;

public abstract class Service implements ChoreographyChapter {
    protected static final Logger log = LoggerFactory.getLogger(Service.class);
    private final ServiceDiscoveryService service;

    public Service(ServiceDiscoveryService service) {
        this.service = service;
    }

    @Override
    public Saga execute(Saga saga) {
        var nextSaga = saga;
        Object nextVal;
        var chapterName = saga.getCurrent().getName();
        if (chapterName.equals(getName())) {
            if (saga.isForward()) {
                nextSaga = process(saga);
                nextVal = nextSaga.getCurrentValue();
                if (nextSaga.isCurrentSuccess()) {
                    nextSaga.forward();
                } else {
                    nextSaga.back();
                }
            } else {
                nextSaga = rollback(saga);
                nextVal = nextSaga.getCurrentValue();
                nextSaga.back();
            }

            if (isSagaFinished(nextSaga)) {
                return nextSaga;
            }

            nextSaga.setCurrentValue(nextVal);
        }
        var finalNextSaga = nextSaga;

        return service.find(chapterName).map(ch -> ch.execute(finalNextSaga))
                .orElseThrow(serviceNotFoundException(chapterName));
    }

    private Supplier<RuntimeException> serviceNotFoundException(String chServiceName) {
        return () -> new RuntimeException(
                String.format("the service %s has not been found", chServiceName));
    }

    @Override
    public Saga process(Saga saga) {
        var inValue = saga.getCurrentValue();
        log.info("The chapter '{}' has been started. "
                        + "The data {} has been stored or calculated successfully",
                getName(), inValue);
        saga.setCurrentStatus(ChapterResult.SUCCESS);
        saga.setCurrentValue(inValue);
        return saga;
    }

    @Override
    public Saga rollback(Saga saga) {
        var inValue = saga.getCurrentValue();
        log.info("The Rollback for a chapter '{}' has been started. "
                        + "The data {} has been rollbacked successfully",
                getName(), inValue);

        saga.setCurrentStatus(ChapterResult.ROLLBACK);
        saga.setCurrentValue(inValue);
        return saga;
    }

    private boolean isSagaFinished(Saga saga) {
        if (!saga.isPresent()) {
            saga.setFinished(true);
            log.info(" the saga has been finished with {} status", saga.getResult());
            return true;
        }
        return false;
    }

}
