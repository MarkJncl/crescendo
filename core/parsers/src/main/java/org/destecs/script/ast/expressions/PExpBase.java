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

package org.destecs.script.ast.expressions;


import org.destecs.script.ast.expressions.PExp;
import org.destecs.script.ast.node.Node;
import java.util.Map;
import org.destecs.script.ast.node.INode;
import org.destecs.script.ast.node.NodeEnum;
import java.lang.String;
import org.destecs.script.ast.expressions.EExp;


/**
* Generated file by AST Creator
* @author Kenneth Lausdahl
*
*/
public abstract class PExpBase extends Node implements PExp
{
	private static final long serialVersionUID = 1L;


	/**
	 * Creates a new {@link PExpBase} node with no children.
	 */
	public PExpBase()
	{

	}






	/**
	 * Essentially this.toString().equals(o.toString()).
	**/
	@Override
	public boolean equals(Object o) {
	if (o != null && o instanceof PExpBase)
	 return toString().equals(o.toString());
	return false; }
	

	public String toString()
	{
		return super.toString();

	}


	/**
	 * Creates a deep clone of this {@link PExpBase} node while putting all
	 * old node-new node relations in the map {@code oldToNewMap}.
	 * @param oldToNewMap the map filled with the old node-new node relation
	 * @return a deep clone of this {@link PExpBase} node
	 */
	@Override
	public abstract PExp clone(Map<INode,INode> oldToNewMap);

	/**
	 * Removes the {@link INode} {@code child} as a child of this {@link PExpBase} node.
	 * Do not call this method with any graph fields of this node. This will cause any child's
	 * with the same reference to be removed unintentionally or {@link RuntimeException}will be thrown.
	 * @param child the child node to be removed from this {@link PExpBase} node
	 * @throws RuntimeException if {@code child} is not a child of this {@link PExpBase} node
	 */
	public void removeChild(INode child)
	{
		throw new RuntimeException("Not a child.");
	}


	/**
	 * Returns a deep clone of this {@link PExpBase} node.
	 * @return a deep clone of this {@link PExpBase} node
	 */
	@Override
	public abstract PExp clone();

	/**
	 * Returns the {@link EExp} corresponding to the
	 * type of this {@link EExp} node.
	 * @return the {@link EExp} for this node
	 */
	public abstract EExp kindPExp();

	/**
	 * Returns the {@link NodeEnum} corresponding to the
	 * type of this {@link INode} node.
	 * @return the {@link NodeEnum} for this node
	 */
	@Override
	public NodeEnum kindNode()
	{
		return NodeEnum.EXP;
	}



}
