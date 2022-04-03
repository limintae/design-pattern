package adapter;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

class AdapterPatternTest {

    private Map<String, Object> beans;
    private static final String FISHING_BEAN = "fisher";
    private static final String ROWING_BEAN = "captain";

    @BeforeEach
    public void setup() {
        beans = new HashMap<>();

        var fishingBoatAdapter = spy(new FishingBoatAdapter());
        beans.put(FISHING_BEAN, fishingBoatAdapter);

        var captain = new Captain();
        captain.setRowingBoat((FishingBoatAdapter) beans.get(FISHING_BEAN));
        beans.put(ROWING_BEAN, captain);
    }

    @Test
    void testAdapter() {
        var captain = (Captain) beans.get(ROWING_BEAN);

        // when captain moves
        captain.row();

        // the captain internally calls the battleship object to move
        var adapter = (RowingBoat) beans.get(FISHING_BEAN);
        verify(adapter).row();
    }

}