package org.destecs.ide.debug;

public interface IDebugConstants {
	
	public static final String PLUGIN_ID = "org.destecs.ide.debug";
	
	public static final String DESTECS_LAUNCH_CONFIG_PROJECT_NAME = "DESTECS_LAUNCH_CONFIG_PROJECT_NAME".toLowerCase(); 
	public static final String DESTECS_LAUNCH_CONFIG_CT_MODEL_PATH = "DESTECS_LAUNCH_CONFIG_CT_MODEL_PATH".toLowerCase();
	public static final String DESTECS_LAUNCH_CONFIG_DE_MODEL_PATH = "DESTECS_LAUNCH_CONFIG_DE_MODEL_PATH".toLowerCase();
	public static final String DESTECS_LAUNCH_CONFIG_SCENARIO_PATH = "DESTECS_LAUNCH_CONFIG_SCENARIO_PATH".toLowerCase();
	public static final String DESTECS_LAUNCH_CONFIG_CONTRACT_PATH = "DESTECS_LAUNCH_CONFIG_CONTRACT_PATH".toLowerCase();
	public static final String DESTECS_LAUNCH_CONFIG_SIMULATION_TIME = "DESTECS_LAUNCH_CONFIG_SIMULATION_TIME".toLowerCase();
	public static final String DESTECS_LAUNCH_CONFIG_SHARED_DESIGN_PARAM = "DESTECS_LAUNCH_CONFIG_SHARED_DESIGN_PARAM".toLowerCase();
	public static final String DESTECS_LAUNCH_CONFIG_DE_ENDPOINT = "DESTECS_LAUNCH_CONFIG_DE_ENDPOINT".toLowerCase();
	public static final String DESTECS_LAUNCH_CONFIG_CT_ENDPOINT = "DESTECS_LAUNCH_CONFIG_CT_ENDPOINT".toLowerCase();
	public static final String DESTECS_LAUNCH_CONFIG_REMOTE_DEBUG = "DESTECS_LAUNCH_CONFIG_REMOTE_DEBUG".toLowerCase();

	public static final String DESTECS_LAUNCH_CONFIG_DE_REPLACE = "DESTECS_LAUNCH_CONFIG_DE_REPLACE".toLowerCase();
	public static final String DESTECS_LAUNCH_CONFIG_DE_ARCHITECTURE = "DESTECS_LAUNCH_CONFIG_DE_ARCHITECTURE".toLowerCase();
	
	
	public static final String ATTR_DESTECS_PROGRAM = "org.destecs.ide.debug.launchConfigurationType";

	public static final String MESSAGE_VIEW_ID = "org.destecs.ide.simeng.ui.views.SimulationMessagesView";
	public static final String ENGINE_VIEW_ID = "org.destecs.ide.simeng.ui.views.SimulationEngineView";
	public static final String SIMULATION_VIEW_ID = "org.destecs.ide.simeng.ui.views.SimulationView";

	public static final String DEFAULT_DE_ENDPOINT = "http://127.0.0.1:PORT/xmlrpc";
	public static final String DEFAULT_CT_ENDPOINT = "http://localhost:1580";

	public static final String VDMRT_CONTENT_TYPE_ID = "org.overture.ide.vdmrt.core.content-type";
}
