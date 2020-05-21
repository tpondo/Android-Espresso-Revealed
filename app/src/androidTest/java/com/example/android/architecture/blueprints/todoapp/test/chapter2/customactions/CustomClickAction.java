package com.example.android.architecture.blueprints.todoapp.test.chapter2.customactions;

import androidx.test.espresso.PerformException;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.CoordinatesProvider;
import androidx.test.espresso.action.GeneralLocation;
import androidx.test.espresso.action.PrecisionDescriber;
import androidx.test.espresso.action.Press;
import androidx.test.espresso.action.Tap;
import androidx.test.espresso.action.Tapper;
import androidx.test.espresso.core.internal.deps.guava.base.Optional;
import androidx.test.espresso.util.HumanReadables;
import android.view.View;
import android.view.ViewConfiguration;
import android.webkit.WebView;

import org.hamcrest.Matcher;

import static androidx.test.espresso.core.internal.deps.guava.base.Preconditions.checkNotNull;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayingAtLeast;
import static org.hamcrest.CoreMatchers.allOf;

/**
 * Class that holds a copy of Espresso ViewActions.click() and allows to perform
 * clicks on a view with different visibility.
 */
public final class CustomClickAction implements ViewAction {

    private final CoordinatesProvider coordinatesProvider;
    private final Tapper tapper;
    private final PrecisionDescriber precisionDescriber;
    private final Optional<ViewAction> rollbackAction;
    private static int visibility = 90;

    public CustomClickAction(Tapper tapper, CoordinatesProvider coordinatesProvider,
                             PrecisionDescriber precisionDescriber) {
        this(tapper, coordinatesProvider, precisionDescriber, null);
    }

    public CustomClickAction(Tapper tapper, CoordinatesProvider coordinatesProvider,
                             PrecisionDescriber precisionDescriber, ViewAction rollbackAction) {
        this.coordinatesProvider = coordinatesProvider;
        this.tapper = tapper;
        this.precisionDescriber = precisionDescriber;
        this.rollbackAction = Optional.fromNullable(rollbackAction);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Matcher<View> getConstraints() {
        Matcher<View> standardConstraint = isDisplayingAtLeast(visibility);
        if (rollbackAction.isPresent()) {
            return allOf(standardConstraint, rollbackAction.get().getConstraints());
        } else {
            return standardConstraint;
        }
    }

    @Override
    public void perform(UiController uiController, View view) {
        float[] coordinates = coordinatesProvider.calculateCoordinates(view);
        float[] precision = precisionDescriber.describePrecision();

        Tapper.Status status = Tapper.Status.FAILURE;
        int loopCount = 0;
        // Native event injection is quite a tricky process. A tap is actually 2
        // seperate motion events which need to get injected into the system. Injection
        // makes an RPC call from our app under test to the Android system server, the
        // system server decides which window layer to deliver the event to, the system
        // server makes an RPC to that window layer, that window layer delivers the event
        // to the correct UI element, activity, or window object. Now we need to repeat
        // that 2x. for a simple down and up. Oh and the down event triggers timers to
        // detect whether or not the event is a long vs. short press. The timers are
        // removed the moment the up event is received (NOTE: the possibility of eventTime
        // being in the future is totally ignored by most motion event processors).
        //
        // Phew.
        //
        // The net result of this is sometimes we'll want to do a regular tap, and for
        // whatever reason the up event (last half) of the tap is delivered after long
        // press timeout (depending on system load) and the long press behaviour is
        // displayed (EG: show a context menu). There is no way to avoid or handle this more
        // gracefully. Also the longpress behavour is app/widget specific. So if you have
        // a seperate long press behaviour from your short press, you can pass in a
        // 'RollBack' ViewAction which when executed will undo the effects of long press.

        while (status != Tapper.Status.SUCCESS && loopCount < 3) {
            try {
                status = tapper.sendTap(uiController, coordinates, precision);
            } catch (RuntimeException re) {
                throw new PerformException.Builder()
                        .withActionDescription(this.getDescription())
                        .withViewDescription(HumanReadables.describe(view))
                        .withCause(re)
                        .build();
            }

            // ensures that all work enqueued to process the tap has been run.
            uiController.loopMainThreadForAtLeast(ViewConfiguration.getPressedStateDuration());
            if (status == Tapper.Status.WARNING) {
                if (rollbackAction.isPresent()) {
                    rollbackAction.get().perform(uiController, view);
                } else {
                    break;
                }
            }
            loopCount++;
        }
        if (status == Tapper.Status.FAILURE) {
            throw new PerformException.Builder()
                    .withActionDescription(this.getDescription())
                    .withViewDescription(HumanReadables.describe(view))
                    .withCause(new RuntimeException(String.format("Couldn't "
                                    + "click at: %s,%s precision: %s, %s . Tapper: %s coordinate provider: %s precision " +
                                    "describer: %s. Tried %s times. With Rollback? %s", coordinates[0], coordinates[1],
                            precision[0], precision[1], tapper, coordinatesProvider, precisionDescriber, loopCount,
                            rollbackAction.isPresent())))
                    .build();
        }

        if (tapper == Tap.SINGLE && view instanceof WebView) {
            // WebViews will not process click events until double tap
            // timeout. Not the best place for this - but good for now.
            uiController.loopMainThreadForAtLeast(ViewConfiguration.getDoubleTapTimeout());
        }
    }

    @Override
    public String getDescription() {
        return tapper.toString().toLowerCase() + " click";
    }

    public static ViewAction clickElementWithVisibility(final int viewVisibility) {
        checkNotNull(viewVisibility);
        if ((viewVisibility > 0) && (viewVisibility <= 100)) {
            visibility = viewVisibility;
        }
        return new CustomClickAction(Tap.SINGLE, GeneralLocation.TOP_CENTER, Press.FINGER);
    }
}
