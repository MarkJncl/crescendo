package org.destecs.ide.debug.launching.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import org.destecs.core.simulationengine.ScenarioSimulationEngine;
import org.destecs.core.simulationengine.SimulationEngine;
import org.destecs.core.simulationengine.exceptions.InvalidEndpointsExpection;
import org.destecs.core.simulationengine.exceptions.InvalidSimulationLauncher;
import org.destecs.core.simulationengine.exceptions.ModelPathNotValidException;
import org.destecs.core.simulationengine.launcher.Clp20SimLauncher;
import org.destecs.core.simulationengine.senario.Scenario;
import org.destecs.core.simulationengine.senario.ScenarioParser;
import org.destecs.ide.debug.IDebugConstants;
import org.destecs.ide.simeng.ISimengConstants;
import org.destecs.ide.simeng.internal.core.EngineListener;
import org.destecs.ide.simeng.internal.core.MessageListener;
import org.destecs.ide.simeng.internal.core.SimulationListener;
import org.destecs.ide.simeng.internal.core.VdmRtBundleLauncher;
import org.destecs.ide.simeng.ui.views.InfoTableView;
import org.destecs.protocol.structs.SetDesignParametersdesignParametersStructParam;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.model.ILaunchConfigurationDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.UIJob;

public class CoSimLaunchConfigurationDelegate implements
		ILaunchConfigurationDelegate {

	private String dtPath = null;
	private String ctPath = null;
	private String contractPath = null;
	private String scenarioPath = null;
	private final List<SetDesignParametersdesignParametersStructParam> shareadDesignParameters = new Vector<SetDesignParametersdesignParametersStructParam>();
	private String sharedDesignParamPath = null;
	private double totalSimulationTime = 0.0;

	public void launch(ILaunchConfiguration configuration, String mode,
			ILaunch launch, IProgressMonitor monitor) throws CoreException {
		System.out.println("Launching...");

		getSettings(configuration);

	}

	private void getSettings(ILaunchConfiguration configuration) {

		try {
			contractPath = configuration.getAttribute(
					IDebugConstants.CONTRACT_PATH, "");
			dtPath = configuration.getAttribute(IDebugConstants.DE_MODEL_PATH,
					"");
			ctPath = configuration.getAttribute(IDebugConstants.CT_MODEL_PATH,
					"");
			scenarioPath = configuration.getAttribute(
					IDebugConstants.SCENARIO_PATH, "");
			sharedDesignParamPath = configuration.getAttribute(
					IDebugConstants.SHARED_DESIGN_PARAM_PATH, "");
			totalSimulationTime = Double.parseDouble(configuration
					.getAttribute(IDebugConstants.SIMULATION_TIME, "0"));

			startSimulation();
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void startSimulation() {

		class TableUIJob extends UIJob {

			final String messageViewId = "org.destecs.ide.simeng.ui.views.SimulationMessagesView";
			final String engineViewId = "org.destecs.ide.simeng.ui.views.SimulationEngineView";
			final String simulationViewId = "org.destecs.ide.simeng.ui.views.SimulationView";
			
			public TableUIJob(String name) {
				super(name);
			}

			private InfoTableView messageView = null;
			private InfoTableView engineView = null;
			private InfoTableView simulationView = null;

			public InfoTableView getMessageViewTable() {
				return messageView;
			}
			
			public InfoTableView getEngineViewTable() {
				return engineView;
			}
			public InfoTableView getSimulationViewTable() {
				return simulationView;
			}
			
			
			@Override
			public IStatus runInUIThread(IProgressMonitor monitor) {
				messageView = getInfoTableView(messageViewId);
				engineView = getInfoTableView(engineViewId);
				simulationView = getInfoTableView(simulationViewId);
				return new Status(IStatus.OK, "debug", "Views OK");
			}
		}	
		
		TableUIJob uiJob = new TableUIJob("Get Views");		
		uiJob.schedule();
		
		while(uiJob.getResult() == null)
			try {
				Thread.sleep(50);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		final InfoTableView messageView = uiJob.getMessageViewTable();			
		final InfoTableView engineView = uiJob.getEngineViewTable();
		final InfoTableView simulationView = uiJob.getSimulationViewTable(); 

		

		Job runSimulation = null;
		try {

			SimulationEngine.eclipseEnvironment = true;
			final SimulationEngine engine = getEngine();

			UIJob listeners = new UIJob("Set Listeners") {
				
				@Override
				public IStatus runInUIThread(IProgressMonitor monitor) {
					engine.engineListeners.add(new EngineListener(engineView));
					engine.messageListeners.add(new MessageListener(messageView));
					engine.simulationListeners.add(new SimulationListener(
							simulationView));
					return new Status(IStatus.OK, "debug", "Listeners OK");
				}
			};
			
			listeners.schedule();
//			engine.engineListeners.add(new EngineListener(engineView));
//			engine.messageListeners.add(new MessageListener(messageView));
//			engine.simulationListeners.add(new SimulationListener(
//					simulationView));

			engine.setDtSimulationLauncher(new VdmRtBundleLauncher(new File(
					dtPath)));// new
			// File("C:\\destecs\\workspace\\watertank_new\\model")));
			engine.setDtModel(new File(dtPath));
			engine.setDtEndpoint(new URL("http://127.0.0.1:8080/xmlrpc"));

			engine.setCtSimulationLauncher(new Clp20SimLauncher());
			engine.setCtModel(new File(ctPath));
			engine.setCtEndpoint(new URL("http://localhost:1580"));

			setSharedDesignParameters(engine);

			runSimulation = new Job("Simulation") {

				@Override
				protected IStatus run(IProgressMonitor monitor) {
					final List<Throwable> exceptions = new Vector<Throwable>();
					class SimulationRunner extends Thread {
						public SimulationRunner() {
							setDaemon(true);
							setName("Simulation Engine");
						}

						public void run() {
							ISafeRunnable runnable = new ISafeRunnable() {

								public void run() throws Exception {
									runSumulation(engine);

								}

								public void handleException(Throwable e) {
									exceptions.add(e);
								}
							};

							SafeRunner.run(runnable);
						};

					}

					Thread simulationEngineThread = new SimulationRunner();

					simulationEngineThread.start();

					while (!simulationEngineThread.isInterrupted()
							&& simulationEngineThread.isAlive()) {
						sleep(2000);

						if (monitor.isCanceled()) {
							engine.forceSimulationStop();
						}
					}

					messageView.refreshPackTable();
//					engineView.refreshPackTable();
//					simulationView.refreshPackTable();

					if (exceptions.size() == 0) {
						return Status.OK_STATUS;
					} else {
						for (Throwable throwable : exceptions) {
							throwable.printStackTrace();
						}
						return new Status(IStatus.ERROR,
								ISimengConstants.PLUGIN_ID, "Simulation faild",
								exceptions.get(0));
					}

				}

				private void sleep(long i) {
					try {
						Thread.sleep(i);
					} catch (InterruptedException e) {
						// Ignore it
					}

				}
			};

		} catch (Exception ex) {
			ex.printStackTrace();
			messageView.refreshPackTable();
			engineView.refreshPackTable();
			simulationView.refreshPackTable();
		}
		runSimulation.schedule();

	}

	private SimulationEngine getEngine() {
		File contractFile = new File(contractPath.trim());
		File scenarioFile = new File(scenarioPath.trim());
		if (scenarioPath.trim().length() > 0) {

			Scenario scenario = new ScenarioParser(scenarioFile).parse();
			return new ScenarioSimulationEngine(contractFile, scenario);
		} else {

			return new SimulationEngine(contractFile);
		}
	}

	private void setSharedDesignParameters(SimulationEngine engine) {
		shareadDesignParameters.clear();
		Properties props = new Properties();
		try {
			props.load(new FileReader(new File(sharedDesignParamPath)));

			for (Object key : props.keySet()) {
				String name = key.toString();
				Double value = Double.parseDouble(props.getProperty(name));
				shareadDesignParameters
						.add(new SetDesignParametersdesignParametersStructParam(
								name, value));
				// shareadDesignParameters.add(new
				// SetDesignParametersdesignParametersStructParam("maxlevel",
				// 2.0));
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private InfoTableView getInfoTableView(String id) {
		IViewPart v;
		try {
			IWorkbenchWindow[] windows = PlatformUI.getWorkbench()
					.getWorkbenchWindows();
			if (windows.length > 0) {
				v = windows[0].getActivePage().getActivePart().getSite()
						.getPage().showView(id);
				return (InfoTableView) v;
			}
			return null;

		} catch (PartInitException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		}
	}

	private void runSumulation(final SimulationEngine engine)
			throws ModelPathNotValidException, MalformedURLException,
			InvalidEndpointsExpection, InvalidSimulationLauncher,
			FileNotFoundException {
		engine.simulate(shareadDesignParameters, totalSimulationTime);
	}
}