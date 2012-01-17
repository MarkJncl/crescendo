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

package org.destecs.script.ast.statement;


import org.destecs.script.ast.statement.SMessageStm;
import java.util.Map;
import org.destecs.script.ast.statement.EMessageStm;
import org.destecs.script.ast.statement.PStmBase;
import org.destecs.script.ast.node.INode;
import org.destecs.script.ast.statement.EStm;
import java.lang.String;


/**
* Generated file by AST Creator
* @author Kenneth Lausdahl
*
*/
public abstract class SMessageStmBase extends PStmBase implements SMessageStm
{
	private static final long serialVersionUID = 1L;

	protected String _message;


	/**
	* Creates a new {@code SMessageStmBase} node with the given nodes as children.
	* The basic child nodes are removed from their previous parents.
	* @param message_ the {@link String} node for the {@code message} child of this {@link SMessageStmBase} node
	*/
	public SMessageStmBase(String message_)
	{
		super();
		this.setMessage(message_);

	}


	/**
	 * Creates a new {@link SMessageStmBase} node with no children.
	 */
	public SMessageStmBase()
	{

	}




	/**
	 * Essentially this.toString().equals(o.toString()).
	**/
	@Override
	public boolean equals(Object o) {
	if (o != null && o instanceof SMessageStmBase)
	 return toString().equals(o.toString());
	return false; }
	

	public String toString()
	{
		return super.toString();

	}


	/**
	 * Returns the {@link EStm} corresponding to the
	 * type of this {@link EStm} node.
	 * @return the {@link EStm} for this node
	 */
	@Override
	public EStm kindPStm()
	{
		return EStm.MESSAGE;
	}


	/**
	 * Removes the {@link INode} {@code child} as a child of this {@link SMessageStmBase} node.
	 * Do not call this method with any graph fields of this node. This will cause any child's
	 * with the same reference to be removed unintentionally or {@link RuntimeException}will be thrown.
	 * @param child the child node to be removed from this {@link SMessageStmBase} node
	 * @throws RuntimeException if {@code child} is not a child of this {@link SMessageStmBase} node
	 */
	public void removeChild(INode child)
	{
		throw new RuntimeException("Not a child.");
	}


	/**
	 * Returns the {@link EMessageStm} corresponding to the
	 * type of this {@link EMessageStm} node.
	 * @return the {@link EMessageStm} for this node
	 */
	public abstract EMessageStm kindSMessageStm();

	/**
	 * Returns a deep clone of this {@link SMessageStmBase} node.
	 * @return a deep clone of this {@link SMessageStmBase} node
	 */
	@Override
	public abstract SMessageStm clone();

	/**
	 * Creates a deep clone of this {@link SMessageStmBase} node while putting all
	 * old node-new node relations in the map {@code oldToNewMap}.
	 * @param oldToNewMap the map filled with the old node-new node relation
	 * @return a deep clone of this {@link SMessageStmBase} node
	 */
	@Override
	public abstract SMessageStm clone(Map<INode,INode> oldToNewMap);

	/**
	 * Sets the {@code _message} child of this {@link SMessageStmBase} node.
	 * @param value the new {@code _message} child of this {@link SMessageStmBase} node
	*/
	public void setMessage(String value)
	{
		this._message = value;
	}


	/**
	 * @return the {@link String} node which is the {@code _message} child of this {@link SMessageStmBase} node
	*/
	public String getMessage()
	{
		return this._message;
	}



}
