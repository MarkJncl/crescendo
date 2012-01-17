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


import org.destecs.script.ast.analysis.intf.IAnalysis;
import java.util.Map;
import java.lang.Double;
import org.destecs.script.ast.expressions.ESingleExp;
import org.destecs.script.ast.analysis.intf.IQuestion;
import org.destecs.script.ast.expressions.SSingleExpBase;
import org.destecs.script.ast.node.INode;
import java.lang.String;
import org.destecs.script.ast.expressions.ATimeSingleExp;
import org.destecs.script.ast.analysis.intf.IAnswer;
import org.destecs.script.ast.expressions.PTimeunit;
import org.destecs.script.ast.analysis.intf.IQuestionAnswer;


/**
* Generated file by AST Creator
* @author Kenneth Lausdahl
*
*/
public class ATimeSingleExp extends SSingleExpBase
{
	private static final long serialVersionUID = 1L;

	private Double _value;
	private PTimeunit _unit;

	/**
	* Creates a new {@code ATimeSingleExp} node with the given nodes as children.
	* The basic child nodes are removed from their previous parents.
	* @param value_ the {@link Double} node for the {@code value} child of this {@link ATimeSingleExp} node
	* @param unit_ the {@link PTimeunit} node for the {@code unit} child of this {@link ATimeSingleExp} node
	*/
	public ATimeSingleExp(Double value_, PTimeunit unit_)
	{
		super();
		this.setValue(value_);
		this.setUnit(unit_);

	}


	/**
	 * Creates a new {@link ATimeSingleExp} node with no children.
	 */
	public ATimeSingleExp()
	{

	}





	/**
	 * Essentially this.toString().equals(o.toString()).
	**/
	@Override
	public boolean equals(Object o) {
	if (o != null && o instanceof ATimeSingleExp)
	 return toString().equals(o.toString());
	return false; }
	
	/**
	 * Returns a deep clone of this {@link ATimeSingleExp} node.
	 * @return a deep clone of this {@link ATimeSingleExp} node
	 */
	public ATimeSingleExp clone()
	{
		return new ATimeSingleExp(
			_value,
			cloneNode(_unit)
		);
	}


	/**
	 * Returns the {@link ESingleExp} corresponding to the
	 * type of this {@link ESingleExp} node.
	 * @return the {@link ESingleExp} for this node
	 */
	@Override
	public ESingleExp kindSSingleExp()
	{
		return ESingleExp.TIME;
	}


	/**
	 * Creates a deep clone of this {@link ATimeSingleExp} node while putting all
	 * old node-new node relations in the map {@code oldToNewMap}.
	 * @param oldToNewMap the map filled with the old node-new node relation
	 * @return a deep clone of this {@link ATimeSingleExp} node
	 */
	public ATimeSingleExp clone(Map<INode,INode> oldToNewMap)
	{
		ATimeSingleExp node = new ATimeSingleExp(
			_value,
			cloneNode(_unit, oldToNewMap)
		);
		oldToNewMap.put(this, node);
		return node;
	}


	/**
	 * Removes the {@link INode} {@code child} as a child of this {@link ATimeSingleExp} node.
	 * Do not call this method with any graph fields of this node. This will cause any child's
	 * with the same reference to be removed unintentionally or {@link RuntimeException}will be thrown.
	 * @param child the child node to be removed from this {@link ATimeSingleExp} node
	 * @throws RuntimeException if {@code child} is not a child of this {@link ATimeSingleExp} node
	 */
	public void removeChild(INode child)
	{
		if (this._unit == child) {
			this._unit = null;
			return;
		}

		throw new RuntimeException("Not a child.");
	}



	public String toString()
	{
		return (_value!=null?_value.toString():this.getClass().getSimpleName())+ (_unit!=null?_unit.toString():this.getClass().getSimpleName());
	}


	/**
	 * Sets the {@code _value} child of this {@link ATimeSingleExp} node.
	 * @param value the new {@code _value} child of this {@link ATimeSingleExp} node
	*/
	public void setValue(Double value)
	{
		this._value = value;
	}


	/**
	 * @return the {@link Double} node which is the {@code _value} child of this {@link ATimeSingleExp} node
	*/
	public Double getValue()
	{
		return this._value;
	}


	/**
	 * Sets the {@code _unit} child of this {@link ATimeSingleExp} node.
	 * @param value the new {@code _unit} child of this {@link ATimeSingleExp} node
	*/
	public void setUnit(PTimeunit value)
	{
		if (this._unit != null) {
			this._unit.parent(null);
		}
		if (value != null) {
			if (value.parent() != null) {
				value.parent().removeChild(value);
		}
			value.parent(this);
		}
		this._unit = value;

	}


	/**
	 * @return the {@link PTimeunit} node which is the {@code _unit} child of this {@link ATimeSingleExp} node
	*/
	public PTimeunit getUnit()
	{
		return this._unit;
	}


	/**
	* Calls the {@link IAnalysis#caseATimeSingleExp(ATimeSingleExp)} of the {@link IAnalysis} {@code analysis}.
	* @param analysis the {@link IAnalysis} to which this {@link ATimeSingleExp} node is applied
	*/
	@Override
	public void apply(IAnalysis analysis)
	{
		analysis.caseATimeSingleExp(this);
	}


	/**
	* Calls the {@link IAnswer#caseATimeSingleExp(ATimeSingleExp)} of the {@link IAnswer} {@code caller}.
	* @param caller the {@link IAnswer} to which this {@link ATimeSingleExp} node is applied
	*/
	@Override
	public <A> A apply(IAnswer<A> caller)
	{
		return caller.caseATimeSingleExp(this);
	}


	/**
	* Calls the {@link IQuestion#caseATimeSingleExp(ATimeSingleExp, Object)} of the {@link IQuestion} {@code caller}.
	* @param caller the {@link IQuestion} to which this {@link ATimeSingleExp} node is applied
	* @param question the question provided to {@code caller}
	*/
	@Override
	public <Q> void apply(IQuestion<Q> caller, Q question)
	{
		caller.caseATimeSingleExp(this, question);
	}


	/**
	* Calls the {@link IQuestionAnswer#caseATimeSingleExp(ATimeSingleExp, Object)} of the {@link IQuestionAnswer} {@code caller}.
	* @param caller the {@link IQuestionAnswer} to which this {@link ATimeSingleExp} node is applied
	* @param question the question provided to {@code caller}
	*/
	@Override
	public <Q, A> A apply(IQuestionAnswer<Q, A> caller, Q question)
	{
		return caller.caseATimeSingleExp(this, question);
	}



}
