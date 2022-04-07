package saga.choreography;

import java.util.ArrayList;
import java.util.List;

public class Saga {

    private final List<Chapter> chapters;
    private int pos;
    private boolean forward;
    private boolean finished;

    public static Saga create() {
        return new Saga();
    }

    private Saga() {
        this.chapters = new ArrayList<>();
        this.pos = 0;
        this.forward = true;
        this.finished = false;
    }

    public SagaResult getResult() {
        if (finished) {
            return forward
                    ? SagaResult.FINISHED
                    : SagaResult.ROLLBACKED;
        }

        return SagaResult.PROGRESS;
    }

    public Saga chapter(String name) {
        this.chapters.add(new Chapter(name));
        return this;
    }

    public Saga setInValue(Object value) {
        if (chapters.isEmpty()) {
            return this;
        }
        chapters.get(chapters.size() - 1).setInValue(value);
        return this;
    }

    public Object getCurrentValue() {
        return chapters.get(pos).getInValue();
    }

    public void setCurrentValue(Object value) {
        chapters.get(pos).setInValue(value);
    }

    public void setCurrentStatus(ChapterResult result) {
        chapters.get(pos).setResult(result);
    }

    void setFinished(boolean finished) {
        this.finished = finished;
    }

    boolean isForward() {
        return forward;
    }

    int forward() {
        return ++pos;
    }

    int back() {
        this.forward = false;
        return --pos;
    }

    Chapter getCurrent() {
        return chapters.get(pos);
    }

    boolean isPresent() {
        return pos >= 0 && pos < chapters.size();
    }

    boolean isCurrentSuccess() {
        return chapters.get(pos).isSuccess();
    }

    public static class Chapter {
        private final String name;
        private ChapterResult result;
        private Object inValue;

        public Chapter(String name) {
            this.name = name;
            this.result = ChapterResult.INIT;
        }

        public Object getInValue() {
            return inValue;
        }

        public void setInValue(Object object) {
            this.inValue = object;
        }

        public String getName() {
            return name;
        }
        public void setResult(ChapterResult result) {
            this.result = result;
        }
        public boolean isSuccess() {
            return result == ChapterResult.SUCCESS;
        }

    }

}
