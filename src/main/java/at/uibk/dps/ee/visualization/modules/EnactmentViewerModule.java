package at.uibk.dps.ee.visualization.modules;

import org.opt4j.core.config.annotations.Info;
import org.opt4j.core.config.annotations.Order;
import org.opt4j.core.start.Constant;
import org.opt4j.viewer.Viewport;

import at.uibk.dps.ee.guice.modules.VisualizationModule;
import at.uibk.dps.ee.visualization.control.ControlToolBarService;
import at.uibk.dps.ee.visualization.process.EnactmentProcessViewer;

/**
 * The {@link EnactmentViewerModule} configures the viewer visualizing the
 * enactment process.
 * 
 * @author Fedor Smirnov
 */
@Info("A graphical viewer to observe the enactment process.")
public class EnactmentViewerModule extends VisualizationModule {

  @Order(1)
  @Info("If checked, the viewer automatically closes when the Enactment terminates.")
  @Constant(namespace = EnactmentProcessViewer.class, value = "closeOnTerminate")
  public boolean closeOnTerminate = true;

  @Order(2)
  @Info("Controls the rate at which the visualization is refreshed.")
  @Constant(namespace = EnactmentProcessViewer.class, value = "updatePeriod")
  public int updatePeriodMs = 100;

  @Override
  protected void config() {
    bind(Viewport.class).in(SINGLETON);
    addEnactmentStateListener(EnactmentProcessViewer.class);
    addModelModificationListener(EnactmentProcessViewer.class);
    addToolBarService(ControlToolBarService.class);
  }

  public boolean isCloseOnTerminate() {
    return closeOnTerminate;
  }

  public void setCloseOnTerminate(boolean closeOnTerminate) {
    this.closeOnTerminate = closeOnTerminate;
  }

  public int getUpdatePeriodMs() {
    return updatePeriodMs;
  }

  public void setUpdatePeriodMs(int updatePeriodMs) {
    this.updatePeriodMs = updatePeriodMs;
  }
}
