package org.destecs.ide.simeng.internal.core;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.destecs.core.simulationengine.ISimulatorLauncher;
import org.destecs.ide.simeng.internal.util.WindowsUtils;

public class Clp20SimProgramLauncher implements ISimulatorLauncher
{
	static final String processName = "20sim.exe";
	private static final int RETRIES = 10;
	private Process p;
	private File model;

	public Clp20SimProgramLauncher(File model)
	{
		this.model = model;
	}

	public void kill()
	{

	}

	public Process launch()
	{
		if (!isWindowsPlatform())
		{
			return null; // not supported
		}
		
		try
		{
			if (WindowsUtils.isProcessRunning(processName))
			{
				return null; // 20-sim is already running
			} else
			{
				List<String> commandList = new ArrayList<String>();
				// commandList.add("explorer");
				commandList.add("cmd.exe /C");
				commandList.add(toPlatformPath(this.model.getAbsolutePath()));
				p = Runtime.getRuntime().exec(getArgumentString(commandList), null, model.getParentFile());

				new ProcessConsolePrinter(p.getInputStream()).start();
				new ProcessConsolePrinter(p.getErrorStream()).start();

				sleep(5000);
				return p;
			}

		} catch (IOException e)
		{
			//ignore it
		}

		for (int i = 0; i < RETRIES; i++)
		{
			try
			{
				if (WindowsUtils.isProcessRunning(processName))
				{
					return null;
				}else
				{
					sleep(2000);
				}
			} catch (IOException e)
			{
				//ignore it
			}
		}
		return null;// TODO ignors return
	}

	private void sleep(int millis)
	{
		try
		{
			Thread.sleep(millis);
		} catch (InterruptedException e)
		{
			// ignore it
		}
	}

	private String getArgumentString(List<String> args)
	{
		StringBuffer executeString = new StringBuffer();
		for (String string : args)
		{
			executeString.append(string);
			executeString.append(" ");
		}
		return executeString.toString().trim();

	}

	public static boolean isWindowsPlatform()
	{
		return System.getProperty("os.name").toLowerCase().contains("win");
	}

	protected static String toPlatformPath(String path)
	{
		if (isWindowsPlatform())
		{
			return "\"" + path + "\"";
		} else
		{
			return path.replace(" ", "\\ ");
		}
	}

	public boolean isRunning()
	{
		return false;// We don't know
	}

	public String getName()
	{
		return processName;
	}

}