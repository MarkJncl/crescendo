/*******************************************************************************
 * Copyright (c) 2010, 2011 DESTECS Team and others.
 *
 * DESTECS is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * DESTECS is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with DESTECS.  If not, see <http://www.gnu.org/licenses/>.
 * 	
 * The DESTECS web-site: http://destecs.org/
 *******************************************************************************/
package org.destecs.ide.platform.splash;

import org.eclipse.core.runtime.Platform;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.splash.BasicSplashHandler;
import org.destecs.ide.platform.Activator;

public class SplashHandler extends BasicSplashHandler
{

	@Override
	public void init(Shell splash)
	{
		super.init(splash);

		int foregroundColorInteger;

		foregroundColorInteger = 0xD2D7FF; // off white
		setForeground(new RGB((foregroundColorInteger & 0xFF0000) >> 16,

		(foregroundColorInteger & 0xFF00) >> 8,

		foregroundColorInteger & 0xFF));

		final Point buildIdPoint = new Point(76, 200);

		final String productVersion = "Version " + Platform.getResourceString(Activator.getDefault().getBundle(), "%productVersion");
		final String productBuild = Platform.getResourceString(Activator.getDefault().getBundle(), "%productBuild");
				
		getContent().addPaintListener(new PaintListener()
		{
			public void paintControl(PaintEvent e)
			{
				e.gc.setForeground(getForeground());
				e.gc.drawText(productVersion, buildIdPoint.x, buildIdPoint.y, true);
				e.gc.drawText(productBuild, buildIdPoint.x, buildIdPoint.y+16, true);
			}
		});
	}

	@Override
	public void dispose()
	{

		super.dispose();
	}

}
