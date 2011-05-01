package org.destecs.ide.simeng.listener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import org.destecs.core.simulationengine.SimulationEngine.Simulator;
import org.destecs.core.simulationengine.listener.IEngineListener;
import org.destecs.core.simulationengine.listener.IMessageListener;
import org.destecs.core.simulationengine.listener.ISimulationListener;
import org.destecs.core.simulationengine.listener.IVariableSyncListener;
import org.destecs.protocol.structs.StepStruct;
import org.destecs.protocol.structs.StepStructoutputsStruct;

public class ListenerToLog implements IEngineListener, IMessageListener, ISimulationListener,IVariableSyncListener
{
	private final File base;
	
	final private CachedLogFileWriter engine =new CachedLogFileWriter();
	final private CachedLogFileWriter message=new CachedLogFileWriter();
	final private CachedLogFileWriter simulation=new CachedLogFileWriter();
	final private CachedLogFileWriter variables=new CachedLogFileWriter();
	public char split = System.getProperty("user.country").equals("DK")? ';':',';

	public ListenerToLog(File base) throws FileNotFoundException
	{
		this.base = base;
		
		engine.setLogfile(new PrintWriter(new File(this.base,"Engine.log")));
		message.setLogfile(new PrintWriter(new File(this.base,"Message.log")));
		simulation.setLogfile(new PrintWriter(new File(this.base,"Simulation.log")));
		variables.setLogfile(new PrintWriter(new File(this.base,"SharedVariables.csv")));
	}

	public void from(Simulator simulator, Double time, String messageName)
	{
		message.log(pad(10,simulator.toString())+ " "+split+" "+pad(75, messageName)+ " "+split+" "+ time.toString());	
	}
	
	private String pad(int c, String data)
	{
		StringBuffer sb = new StringBuffer(data);
		while(sb.length()<c)
		{
			sb.append(" ");
		}
		return sb.toString();
	}

	public void stepInfo(Simulator simulator, StepStruct result)
	{
		StringBuilder sb = new StringBuilder();
		for (StepStructoutputsStruct o : result.outputs)
		{
			sb.append(o.name + "=" + o.value+ " ");
		}
		simulation.log(pad(10,simulator.toString())+ " "+split+" "+ pad(20,sb.toString())+ " "+split+" "+ result.time.toString());
		
	}

	public void info(Simulator simulator, String message)
	{
		engine.log(pad(10,simulator.toString())+ " "+split+" "+ message.replace('\n', ' '));		
	}
	
	
	public void close()
	{
		engine.dump(true);
		message.dump(true);
		simulation.dump(true);
		variables.dump(true);
	}
	
	public void flush()
	{
		engine.dump(false);
		message.dump(false);
		simulation.dump(false);
		variables.dump(false);
	}

	public void info(List<String> colls)
	{
		StringBuilder sb = new StringBuilder();
		for (String col : colls)
		{
			sb.append(col);
			sb.append(split);
		}
		variables.log(sb.substring(0, sb.length() - 1));
	}
}