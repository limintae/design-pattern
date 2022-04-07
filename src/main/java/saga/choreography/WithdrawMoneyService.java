package saga.choreography;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WithdrawMoneyService extends Service {
    protected static final Logger log = LoggerFactory.getLogger(WithdrawMoneyService.class);
    public WithdrawMoneyService(ServiceDiscoveryService service) {
        super(service);
    }

    @Override
    public String getName() {
        return "withdrawing Money";
    }

    @Override
    public Saga process(Saga saga) {
        var inValue = saga.getCurrentValue();

        if (inValue.equals("bad_order")) {
            log.info("The chapter '{}' has been started. But the exception has been raised." + "The rollback is about to start {}", getName(), inValue);
            saga.setCurrentStatus(ChapterResult.ROLLBACK);
            return saga;
        }
        return super.process(saga);
    }

}
