/*******************************************************************************
* Copyright (c) 2009, 2011 Overture Team and others.
*
* Overture is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* Overture is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with Overture.  If not, see <http://www.gnu.org/licenses/>.
*
* The Overture Tool web-site: http://overturetool.org/
*******************************************************************************/

/* This file was generated by ASTcreator (http://www.lausdahl.com/). */

package org.destecs.script.ast.preprocessing;


import java.util.Map;
import org.destecs.script.ast.preprocessing.PInclude;
import org.destecs.script.ast.node.INode;
import java.lang.String;
import org.destecs.script.ast.node.NodeEnum;
import org.destecs.script.ast.preprocessing.EInclude;


/**
* Generated file by AST Creator
* @author Kenneth Lausdahl
*
*/
public interface PInclude extends INode
{	/**
	 * Removes the {@link INode} {@code child} as a child of this {@link PIncludeBase} node.
	 * Do not call this method with any graph fields of this node. This will cause any child's
	 * with the same reference to be removed unintentionally or {@link RuntimeException}will be thrown.
	 * @param child the child node to be removed from this {@link PIncludeBase} node
	 * @throws RuntimeException if {@code child} is not a child of this {@link PIncludeBase} node
	 */
	public void removeChild(INode child);
	/**
	 * Creates a deep clone of this {@link PIncludeBase} node while putting all
	 * old node-new node relations in the map {@code oldToNewMap}.
	 * @param oldToNewMap the map filled with the old node-new node relation
	 * @return a deep clone of this {@link PIncludeBase} node
	 */
	public abstract PInclude clone(Map<INode,INode> oldToNewMap);
	/**
	 * Returns a deep clone of this {@link PIncludeBase} node.
	 * @return a deep clone of this {@link PIncludeBase} node
	 */
	public abstract PInclude clone();
	/**
	 * Returns the {@link NodeEnum} corresponding to the
	 * type of this {@link INode} node.
	 * @return the {@link NodeEnum} for this node
	 */
	public NodeEnum kindNode();
	/**
	 * Returns the {@link EInclude} corresponding to the
	 * type of this {@link EInclude} node.
	 * @return the {@link EInclude} for this node
	 */
	public abstract EInclude kindPInclude();

	public String toString();

}
