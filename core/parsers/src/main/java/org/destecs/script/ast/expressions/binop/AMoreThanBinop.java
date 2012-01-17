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

package org.destecs.script.ast.expressions.binop;


import org.destecs.script.ast.analysis.intf.IAnalysis;
import java.util.Map;
import org.destecs.script.ast.expressions.binop.AMoreThanBinop;
import org.destecs.script.ast.analysis.intf.IQuestion;
import org.destecs.script.ast.node.INode;
import java.lang.String;
import org.destecs.script.ast.analysis.intf.IAnswer;
import org.destecs.script.ast.expressions.binop.EBinop;
import org.destecs.script.ast.analysis.intf.IQuestionAnswer;
import org.destecs.script.ast.expressions.binop.PBinopBase;


/**
* Generated file by AST Creator
* @author Kenneth Lausdahl
*
*/
public class AMoreThanBinop extends PBinopBase
{
	private static final long serialVersionUID = 1L;


	/**
	 * Creates a new {@link AMoreThanBinop} node with no children.
	 */
	public AMoreThanBinop()
	{

	}






	/**
	 * Essentially this.toString().equals(o.toString()).
	**/
	@Override
	public boolean equals(Object o) {
	if (o != null && o instanceof AMoreThanBinop)
	 return toString().equals(o.toString());
	return false; }
	
	/**
	 * Returns the {@link EBinop} corresponding to the
	 * type of this {@link EBinop} node.
	 * @return the {@link EBinop} for this node
	 */
	@Override
	public EBinop kindPBinop()
	{
		return EBinop.MORETHAN;
	}


	/**
	 * Creates a deep clone of this {@link AMoreThanBinop} node while putting all
	 * old node-new node relations in the map {@code oldToNewMap}.
	 * @param oldToNewMap the map filled with the old node-new node relation
	 * @return a deep clone of this {@link AMoreThanBinop} node
	 */
	public AMoreThanBinop clone(Map<INode,INode> oldToNewMap)
	{
		AMoreThanBinop node = new AMoreThanBinop(
		);
		oldToNewMap.put(this, node);
		return node;
	}


	/**
	 * Returns a deep clone of this {@link AMoreThanBinop} node.
	 * @return a deep clone of this {@link AMoreThanBinop} node
	 */
	public AMoreThanBinop clone()
	{
		return new AMoreThanBinop(
		);
	}



	public String toString()
	{
		return super.toString();
	}


	/**
	 * Removes the {@link INode} {@code child} as a child of this {@link AMoreThanBinop} node.
	 * Do not call this method with any graph fields of this node. This will cause any child's
	 * with the same reference to be removed unintentionally or {@link RuntimeException}will be thrown.
	 * @param child the child node to be removed from this {@link AMoreThanBinop} node
	 * @throws RuntimeException if {@code child} is not a child of this {@link AMoreThanBinop} node
	 */
	public void removeChild(INode child)
	{
		throw new RuntimeException("Not a child.");
	}


	/**
	* Calls the {@link IAnalysis#caseAMoreThanBinop(AMoreThanBinop)} of the {@link IAnalysis} {@code analysis}.
	* @param analysis the {@link IAnalysis} to which this {@link AMoreThanBinop} node is applied
	*/
	@Override
	public void apply(IAnalysis analysis)
	{
		analysis.caseAMoreThanBinop(this);
	}


	/**
	* Calls the {@link IAnswer#caseAMoreThanBinop(AMoreThanBinop)} of the {@link IAnswer} {@code caller}.
	* @param caller the {@link IAnswer} to which this {@link AMoreThanBinop} node is applied
	*/
	@Override
	public <A> A apply(IAnswer<A> caller)
	{
		return caller.caseAMoreThanBinop(this);
	}


	/**
	* Calls the {@link IQuestion#caseAMoreThanBinop(AMoreThanBinop, Object)} of the {@link IQuestion} {@code caller}.
	* @param caller the {@link IQuestion} to which this {@link AMoreThanBinop} node is applied
	* @param question the question provided to {@code caller}
	*/
	@Override
	public <Q> void apply(IQuestion<Q> caller, Q question)
	{
		caller.caseAMoreThanBinop(this, question);
	}


	/**
	* Calls the {@link IQuestionAnswer#caseAMoreThanBinop(AMoreThanBinop, Object)} of the {@link IQuestionAnswer} {@code caller}.
	* @param caller the {@link IQuestionAnswer} to which this {@link AMoreThanBinop} node is applied
	* @param question the question provided to {@code caller}
	*/
	@Override
	public <Q, A> A apply(IQuestionAnswer<Q, A> caller, Q question)
	{
		return caller.caseAMoreThanBinop(this, question);
	}



}
