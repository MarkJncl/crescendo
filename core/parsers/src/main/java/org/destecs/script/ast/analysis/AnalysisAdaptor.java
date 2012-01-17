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

package org.destecs.script.ast.analysis;


import org.destecs.script.ast.expressions.AMsTimeunit;
import org.destecs.script.ast.expressions.binop.AModBinop;
import java.lang.Boolean;
import org.destecs.script.ast.statement.AQuitStm;
import java.lang.Long;
import org.destecs.script.ast.preprocessing.AScriptInclude;
import org.destecs.script.ast.types.AIntType;
import org.destecs.script.ast.expressions.ASTimeunit;
import org.destecs.script.ast.expressions.ABoolSingleExp;
import org.destecs.script.ast.expressions.PExp;
import java.lang.Double;
import org.destecs.script.ast.statement.AErrorMessageStm;
import org.destecs.script.ast.expressions.binop.AMoreEqualBinop;
import org.destecs.script.ast.statement.APrintMessageStm;
import org.destecs.script.ast.expressions.binop.ADivideBinop;
import org.destecs.script.ast.node.INode;
import org.destecs.script.ast.statement.AAssignStm;
import org.destecs.script.ast.types.PType;
import org.destecs.script.ast.expressions.binop.PBinop;
import org.destecs.script.ast.expressions.binop.APlusBinop;
import org.destecs.script.ast.expressions.binop.ADifferentBinop;
import org.destecs.script.ast.ACtDomain;
import org.destecs.script.ast.expressions.AHTimeunit;
import org.destecs.script.ast.types.ATimeType;
import org.destecs.script.ast.analysis.intf.IAnalysis;
import org.destecs.script.ast.expressions.binop.AEqualBinop;
import java.lang.Integer;
import org.destecs.script.ast.statement.PStm;
import org.destecs.script.ast.expressions.binop.AForBinop;
import org.destecs.script.ast.expressions.binop.AMultiplyBinop;
import org.destecs.script.ast.statement.AWarnMessageStm;
import org.destecs.script.ast.expressions.AIdentifierSingleExp;
import org.destecs.script.ast.expressions.binop.AMinusBinop;
import org.destecs.script.ast.expressions.PTimeunit;
import org.destecs.script.ast.expressions.AMTimeunit;
import org.destecs.script.ast.PDomain;
import org.destecs.script.ast.preprocessing.PInclude;
import org.destecs.script.ast.expressions.ABinaryExp;
import org.destecs.script.ast.expressions.AUnaryExp;
import org.destecs.script.ast.expressions.AUsTimeunit;
import org.destecs.script.ast.expressions.unop.ACeilUnop;
import org.destecs.script.ast.ADeDomain;
import org.destecs.script.ast.expressions.binop.AOrBinop;
import org.destecs.script.ast.expressions.ANumericalSingleExp;
import org.destecs.script.ast.node.tokens.TInt;
import org.destecs.script.ast.expressions.unop.AMinusUnop;
import org.destecs.script.ast.expressions.binop.ADivBinop;
import org.destecs.script.ast.statement.AWhenStm;
import org.destecs.script.ast.expressions.unop.PUnop;
import org.destecs.script.ast.expressions.binop.AAndBinop;
import org.destecs.script.ast.types.ARealType;
import org.destecs.script.ast.expressions.unop.AFloorUnop;
import org.destecs.script.ast.expressions.ASystemTimeSingleExp;
import org.destecs.script.ast.expressions.SSingleExp;
import org.destecs.script.ast.statement.SMessageStm;
import org.destecs.script.ast.expressions.binop.AEquivBinop;
import java.lang.String;
import org.destecs.script.ast.expressions.ATimeSingleExp;
import org.destecs.script.ast.expressions.unop.AAbsUnop;
import org.destecs.script.ast.expressions.binop.ALessEqualBinop;
import org.destecs.script.ast.statement.ARevertStm;
import org.destecs.script.ast.expressions.binop.ALessThanBinop;
import org.destecs.script.ast.types.ABoolType;
import org.destecs.script.ast.expressions.binop.AMoreThanBinop;
import org.destecs.script.ast.expressions.binop.AImpliesBinop;
import org.destecs.script.ast.expressions.unop.AAddUnop;
import org.destecs.script.ast.node.IToken;


/**
* Generated file by AST Creator
* @author Kenneth Lausdahl
*
*/
public class AnalysisAdaptor implements IAnalysis
{
	private static final long serialVersionUID = 1L;



	/**
	 * Creates a new {@link AnalysisAdaptor} node with no children.
	 */
	public AnalysisAdaptor()
	{

	}




	/**
	 * Essentially this.toString().equals(o.toString()).
	**/
	@Override
	public boolean equals(Object o) {
	if (o != null && o instanceof AnalysisAdaptor)
	 return toString().equals(o.toString());
	return false; }
	
	/**
	* Called by the {@link IToken} node from {@link IToken#apply(IAnalysis)}.
	* @param node the calling {@link IToken} node
	*/
	public void caseTInt(TInt node)
	{
		defaultIToken(node);
	}


	/**
	* Called by the {@link IToken} node from {@link IToken#apply(IAnalysis)}.
	* @param node the calling {@link IToken} node
	*/
	public void caseDouble(Double node)
	{

	}


	/**
	* Called by the {@link IToken} node from {@link IToken#apply(IAnalysis)}.
	* @param node the calling {@link IToken} node
	*/
	public void caseBoolean(Boolean node)
	{

	}


	/**
	* Called by the {@link IToken} node from {@link IToken#apply(IAnalysis)}.
	* @param node the calling {@link IToken} node
	*/
	public void caseInteger(Integer node)
	{

	}


	/**
	* Called by the {@link IToken} node from {@link IToken#apply(IAnalysis)}.
	* @param node the calling {@link IToken} node
	*/
	public void caseString(String node)
	{

	}


	/**
	* Called by the {@link IToken} node from {@link IToken#apply(IAnalysis)}.
	* @param node the calling {@link IToken} node
	*/
	public void caseLong(Long node)
	{

	}


	/**
	* Called by the {@link PUnop} node from {@link PUnop#apply(IAnalysis)}.
	* @param node the calling {@link PUnop} node
	*/
	public void defaultPUnop(PUnop node)
	{
		defaultINode(node);
	}


	/**
	* Called by the {@link AAddUnop} node from {@link AAddUnop#apply(IAnalysis)}.
	* @param node the calling {@link AAddUnop} node
	*/
	public void caseAAddUnop(AAddUnop node)
	{
		defaultPUnop(node);
	}


	/**
	* Called by the {@link AMinusUnop} node from {@link AMinusUnop#apply(IAnalysis)}.
	* @param node the calling {@link AMinusUnop} node
	*/
	public void caseAMinusUnop(AMinusUnop node)
	{
		defaultPUnop(node);
	}


	/**
	* Called by the {@link AAbsUnop} node from {@link AAbsUnop#apply(IAnalysis)}.
	* @param node the calling {@link AAbsUnop} node
	*/
	public void caseAAbsUnop(AAbsUnop node)
	{
		defaultPUnop(node);
	}


	/**
	* Called by the {@link AFloorUnop} node from {@link AFloorUnop#apply(IAnalysis)}.
	* @param node the calling {@link AFloorUnop} node
	*/
	public void caseAFloorUnop(AFloorUnop node)
	{
		defaultPUnop(node);
	}


	/**
	* Called by the {@link ACeilUnop} node from {@link ACeilUnop#apply(IAnalysis)}.
	* @param node the calling {@link ACeilUnop} node
	*/
	public void caseACeilUnop(ACeilUnop node)
	{
		defaultPUnop(node);
	}


	/**
	* Called by the {@link PBinop} node from {@link PBinop#apply(IAnalysis)}.
	* @param node the calling {@link PBinop} node
	*/
	public void defaultPBinop(PBinop node)
	{
		defaultINode(node);
	}


	/**
	* Called by the {@link APlusBinop} node from {@link APlusBinop#apply(IAnalysis)}.
	* @param node the calling {@link APlusBinop} node
	*/
	public void caseAPlusBinop(APlusBinop node)
	{
		defaultPBinop(node);
	}


	/**
	* Called by the {@link AMinusBinop} node from {@link AMinusBinop#apply(IAnalysis)}.
	* @param node the calling {@link AMinusBinop} node
	*/
	public void caseAMinusBinop(AMinusBinop node)
	{
		defaultPBinop(node);
	}


	/**
	* Called by the {@link AMultiplyBinop} node from {@link AMultiplyBinop#apply(IAnalysis)}.
	* @param node the calling {@link AMultiplyBinop} node
	*/
	public void caseAMultiplyBinop(AMultiplyBinop node)
	{
		defaultPBinop(node);
	}


	/**
	* Called by the {@link ADivideBinop} node from {@link ADivideBinop#apply(IAnalysis)}.
	* @param node the calling {@link ADivideBinop} node
	*/
	public void caseADivideBinop(ADivideBinop node)
	{
		defaultPBinop(node);
	}


	/**
	* Called by the {@link ADivBinop} node from {@link ADivBinop#apply(IAnalysis)}.
	* @param node the calling {@link ADivBinop} node
	*/
	public void caseADivBinop(ADivBinop node)
	{
		defaultPBinop(node);
	}


	/**
	* Called by the {@link AModBinop} node from {@link AModBinop#apply(IAnalysis)}.
	* @param node the calling {@link AModBinop} node
	*/
	public void caseAModBinop(AModBinop node)
	{
		defaultPBinop(node);
	}


	/**
	* Called by the {@link ALessThanBinop} node from {@link ALessThanBinop#apply(IAnalysis)}.
	* @param node the calling {@link ALessThanBinop} node
	*/
	public void caseALessThanBinop(ALessThanBinop node)
	{
		defaultPBinop(node);
	}


	/**
	* Called by the {@link ALessEqualBinop} node from {@link ALessEqualBinop#apply(IAnalysis)}.
	* @param node the calling {@link ALessEqualBinop} node
	*/
	public void caseALessEqualBinop(ALessEqualBinop node)
	{
		defaultPBinop(node);
	}


	/**
	* Called by the {@link AMoreThanBinop} node from {@link AMoreThanBinop#apply(IAnalysis)}.
	* @param node the calling {@link AMoreThanBinop} node
	*/
	public void caseAMoreThanBinop(AMoreThanBinop node)
	{
		defaultPBinop(node);
	}


	/**
	* Called by the {@link AMoreEqualBinop} node from {@link AMoreEqualBinop#apply(IAnalysis)}.
	* @param node the calling {@link AMoreEqualBinop} node
	*/
	public void caseAMoreEqualBinop(AMoreEqualBinop node)
	{
		defaultPBinop(node);
	}


	/**
	* Called by the {@link AEqualBinop} node from {@link AEqualBinop#apply(IAnalysis)}.
	* @param node the calling {@link AEqualBinop} node
	*/
	public void caseAEqualBinop(AEqualBinop node)
	{
		defaultPBinop(node);
	}


	/**
	* Called by the {@link ADifferentBinop} node from {@link ADifferentBinop#apply(IAnalysis)}.
	* @param node the calling {@link ADifferentBinop} node
	*/
	public void caseADifferentBinop(ADifferentBinop node)
	{
		defaultPBinop(node);
	}


	/**
	* Called by the {@link AOrBinop} node from {@link AOrBinop#apply(IAnalysis)}.
	* @param node the calling {@link AOrBinop} node
	*/
	public void caseAOrBinop(AOrBinop node)
	{
		defaultPBinop(node);
	}


	/**
	* Called by the {@link AAndBinop} node from {@link AAndBinop#apply(IAnalysis)}.
	* @param node the calling {@link AAndBinop} node
	*/
	public void caseAAndBinop(AAndBinop node)
	{
		defaultPBinop(node);
	}


	/**
	* Called by the {@link AImpliesBinop} node from {@link AImpliesBinop#apply(IAnalysis)}.
	* @param node the calling {@link AImpliesBinop} node
	*/
	public void caseAImpliesBinop(AImpliesBinop node)
	{
		defaultPBinop(node);
	}


	/**
	* Called by the {@link AEquivBinop} node from {@link AEquivBinop#apply(IAnalysis)}.
	* @param node the calling {@link AEquivBinop} node
	*/
	public void caseAEquivBinop(AEquivBinop node)
	{
		defaultPBinop(node);
	}


	/**
	* Called by the {@link AForBinop} node from {@link AForBinop#apply(IAnalysis)}.
	* @param node the calling {@link AForBinop} node
	*/
	public void caseAForBinop(AForBinop node)
	{
		defaultPBinop(node);
	}


	/**
	* Called by the {@link PDomain} node from {@link PDomain#apply(IAnalysis)}.
	* @param node the calling {@link PDomain} node
	*/
	public void defaultPDomain(PDomain node)
	{
		defaultINode(node);
	}


	/**
	* Called by the {@link ADeDomain} node from {@link ADeDomain#apply(IAnalysis)}.
	* @param node the calling {@link ADeDomain} node
	*/
	public void caseADeDomain(ADeDomain node)
	{
		defaultPDomain(node);
	}


	/**
	* Called by the {@link ACtDomain} node from {@link ACtDomain#apply(IAnalysis)}.
	* @param node the calling {@link ACtDomain} node
	*/
	public void caseACtDomain(ACtDomain node)
	{
		defaultPDomain(node);
	}


	/**
	* Called by the {@link PExp} node from {@link PExp#apply(IAnalysis)}.
	* @param node the calling {@link PExp} node
	*/
	public void defaultPExp(PExp node)
	{
		defaultINode(node);
	}


	/**
	* Called by the {@link SSingleExp} node from {@link SSingleExp#apply(IAnalysis)}.
	* @param node the calling {@link SSingleExp} node
	*/
	public void defaultSSingleExp(SSingleExp node)
	{
		defaultPExp(node);
	}


	/**
	* Called by the {@link AUnaryExp} node from {@link AUnaryExp#apply(IAnalysis)}.
	* @param node the calling {@link AUnaryExp} node
	*/
	public void caseAUnaryExp(AUnaryExp node)
	{
		defaultPExp(node);
	}


	/**
	* Called by the {@link ABinaryExp} node from {@link ABinaryExp#apply(IAnalysis)}.
	* @param node the calling {@link ABinaryExp} node
	*/
	public void caseABinaryExp(ABinaryExp node)
	{
		defaultPExp(node);
	}


	/**
	* Called by the {@link ABoolSingleExp} node from {@link ABoolSingleExp#apply(IAnalysis)}.
	* @param node the calling {@link ABoolSingleExp} node
	*/
	public void caseABoolSingleExp(ABoolSingleExp node)
	{
		defaultSSingleExp(node);
	}


	/**
	* Called by the {@link ANumericalSingleExp} node from {@link ANumericalSingleExp#apply(IAnalysis)}.
	* @param node the calling {@link ANumericalSingleExp} node
	*/
	public void caseANumericalSingleExp(ANumericalSingleExp node)
	{
		defaultSSingleExp(node);
	}


	/**
	* Called by the {@link ATimeSingleExp} node from {@link ATimeSingleExp#apply(IAnalysis)}.
	* @param node the calling {@link ATimeSingleExp} node
	*/
	public void caseATimeSingleExp(ATimeSingleExp node)
	{
		defaultSSingleExp(node);
	}


	/**
	* Called by the {@link AIdentifierSingleExp} node from {@link AIdentifierSingleExp#apply(IAnalysis)}.
	* @param node the calling {@link AIdentifierSingleExp} node
	*/
	public void caseAIdentifierSingleExp(AIdentifierSingleExp node)
	{
		defaultSSingleExp(node);
	}


	/**
	* Called by the {@link ASystemTimeSingleExp} node from {@link ASystemTimeSingleExp#apply(IAnalysis)}.
	* @param node the calling {@link ASystemTimeSingleExp} node
	*/
	public void caseASystemTimeSingleExp(ASystemTimeSingleExp node)
	{
		defaultSSingleExp(node);
	}


	/**
	* Called by the {@link PTimeunit} node from {@link PTimeunit#apply(IAnalysis)}.
	* @param node the calling {@link PTimeunit} node
	*/
	public void defaultPTimeunit(PTimeunit node)
	{
		defaultINode(node);
	}


	/**
	* Called by the {@link AUsTimeunit} node from {@link AUsTimeunit#apply(IAnalysis)}.
	* @param node the calling {@link AUsTimeunit} node
	*/
	public void caseAUsTimeunit(AUsTimeunit node)
	{
		defaultPTimeunit(node);
	}


	/**
	* Called by the {@link AMsTimeunit} node from {@link AMsTimeunit#apply(IAnalysis)}.
	* @param node the calling {@link AMsTimeunit} node
	*/
	public void caseAMsTimeunit(AMsTimeunit node)
	{
		defaultPTimeunit(node);
	}


	/**
	* Called by the {@link ASTimeunit} node from {@link ASTimeunit#apply(IAnalysis)}.
	* @param node the calling {@link ASTimeunit} node
	*/
	public void caseASTimeunit(ASTimeunit node)
	{
		defaultPTimeunit(node);
	}


	/**
	* Called by the {@link AMTimeunit} node from {@link AMTimeunit#apply(IAnalysis)}.
	* @param node the calling {@link AMTimeunit} node
	*/
	public void caseAMTimeunit(AMTimeunit node)
	{
		defaultPTimeunit(node);
	}


	/**
	* Called by the {@link AHTimeunit} node from {@link AHTimeunit#apply(IAnalysis)}.
	* @param node the calling {@link AHTimeunit} node
	*/
	public void caseAHTimeunit(AHTimeunit node)
	{
		defaultPTimeunit(node);
	}


	/**
	* Called by the {@link PStm} node from {@link PStm#apply(IAnalysis)}.
	* @param node the calling {@link PStm} node
	*/
	public void defaultPStm(PStm node)
	{
		defaultINode(node);
	}


	/**
	* Called by the {@link AWhenStm} node from {@link AWhenStm#apply(IAnalysis)}.
	* @param node the calling {@link AWhenStm} node
	*/
	public void caseAWhenStm(AWhenStm node)
	{
		defaultPStm(node);
	}


	/**
	* Called by the {@link AAssignStm} node from {@link AAssignStm#apply(IAnalysis)}.
	* @param node the calling {@link AAssignStm} node
	*/
	public void caseAAssignStm(AAssignStm node)
	{
		defaultPStm(node);
	}


	/**
	* Called by the {@link ARevertStm} node from {@link ARevertStm#apply(IAnalysis)}.
	* @param node the calling {@link ARevertStm} node
	*/
	public void caseARevertStm(ARevertStm node)
	{
		defaultPStm(node);
	}


	/**
	* Called by the {@link SMessageStm} node from {@link SMessageStm#apply(IAnalysis)}.
	* @param node the calling {@link SMessageStm} node
	*/
	public void defaultSMessageStm(SMessageStm node)
	{
		defaultPStm(node);
	}


	/**
	* Called by the {@link AQuitStm} node from {@link AQuitStm#apply(IAnalysis)}.
	* @param node the calling {@link AQuitStm} node
	*/
	public void caseAQuitStm(AQuitStm node)
	{
		defaultPStm(node);
	}


	/**
	* Called by the {@link APrintMessageStm} node from {@link APrintMessageStm#apply(IAnalysis)}.
	* @param node the calling {@link APrintMessageStm} node
	*/
	public void caseAPrintMessageStm(APrintMessageStm node)
	{
		defaultSMessageStm(node);
	}


	/**
	* Called by the {@link AErrorMessageStm} node from {@link AErrorMessageStm#apply(IAnalysis)}.
	* @param node the calling {@link AErrorMessageStm} node
	*/
	public void caseAErrorMessageStm(AErrorMessageStm node)
	{
		defaultSMessageStm(node);
	}


	/**
	* Called by the {@link AWarnMessageStm} node from {@link AWarnMessageStm#apply(IAnalysis)}.
	* @param node the calling {@link AWarnMessageStm} node
	*/
	public void caseAWarnMessageStm(AWarnMessageStm node)
	{
		defaultSMessageStm(node);
	}


	/**
	* Called by the {@link PType} node from {@link PType#apply(IAnalysis)}.
	* @param node the calling {@link PType} node
	*/
	public void defaultPType(PType node)
	{
		defaultINode(node);
	}


	/**
	* Called by the {@link ARealType} node from {@link ARealType#apply(IAnalysis)}.
	* @param node the calling {@link ARealType} node
	*/
	public void caseARealType(ARealType node)
	{
		defaultPType(node);
	}


	/**
	* Called by the {@link AIntType} node from {@link AIntType#apply(IAnalysis)}.
	* @param node the calling {@link AIntType} node
	*/
	public void caseAIntType(AIntType node)
	{
		defaultPType(node);
	}


	/**
	* Called by the {@link ABoolType} node from {@link ABoolType#apply(IAnalysis)}.
	* @param node the calling {@link ABoolType} node
	*/
	public void caseABoolType(ABoolType node)
	{
		defaultPType(node);
	}


	/**
	* Called by the {@link ATimeType} node from {@link ATimeType#apply(IAnalysis)}.
	* @param node the calling {@link ATimeType} node
	*/
	public void caseATimeType(ATimeType node)
	{
		defaultPType(node);
	}


	/**
	* Called by the {@link PInclude} node from {@link PInclude#apply(IAnalysis)}.
	* @param node the calling {@link PInclude} node
	*/
	public void defaultPInclude(PInclude node)
	{
		defaultINode(node);
	}


	/**
	* Called by the {@link AScriptInclude} node from {@link AScriptInclude#apply(IAnalysis)}.
	* @param node the calling {@link AScriptInclude} node
	*/
	public void caseAScriptInclude(AScriptInclude node)
	{
		defaultPInclude(node);
	}


	/**
	* Called by the {@link INode} node from {@link INode#apply(IAnalysis)}.
	* @param node the calling {@link INode} node
	*/
	public void defaultINode(INode node)
	{
		//nothing to do
	}


	/**
	* Called by the {@link IToken} node from {@link IToken#apply(IAnalysis)}.
	* @param node the calling {@link IToken} node
	*/
	public void defaultIToken(IToken node)
	{
		//nothing to do
	}



}
