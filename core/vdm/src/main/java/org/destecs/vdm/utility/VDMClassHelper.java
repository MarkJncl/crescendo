package org.destecs.vdm.utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.destecs.protocol.exceptions.RemoteSimulationException;
import org.overturetool.vdmj.definitions.ClassDefinition;
import org.overturetool.vdmj.definitions.ClassList;
import org.overturetool.vdmj.definitions.Definition;
import org.overturetool.vdmj.lex.LexNameToken;
import org.overturetool.vdmj.runtime.ValueException;
import org.overturetool.vdmj.typechecker.NameScope;
import org.overturetool.vdmj.values.BooleanValue;
import org.overturetool.vdmj.values.NameValuePair;
import org.overturetool.vdmj.values.NameValuePairList;
import org.overturetool.vdmj.values.ObjectValue;
import org.overturetool.vdmj.values.ReferenceValue;
import org.overturetool.vdmj.values.SeqValue;
import org.overturetool.vdmj.values.Value;

public class VDMClassHelper {

	public static ClassDefinition findClassByName(String className, ClassList classes) {				
		for (ClassDefinition classDefinition : classes) {
			if(classDefinition.name.name.equals(className))
				return classDefinition;
		}
		
		return null;
		
	}
	
	public static Value digForVariable(List<String> varName, NameValuePairList list) throws RemoteSimulationException, ValueException {

		Value value = null;						
		if(list.size() >= 1)
		{
			String namePart = varName.get(0);
			for (NameValuePair p : list)
			{
				if (namePart.equals(p.name.getName()))
				{
					value = p.value.deref();
					
					if(canResultBeExpanded(value))
					{						
						NameValuePairList newArgs = getNamePairListFromResult(value);
						
						Value result = digForVariable(getNewName(varName), newArgs);
						return result;
					}
					else
					{
						return value;
						
					}
				}
			}
		}			
		
		if(value == null)
		{
			throw new RemoteSimulationException("Value: " + varName + " not found");
		}
		return value;
	}
		
	
	private static boolean canResultBeExpanded(Value result) {
		if(result instanceof ObjectValue || result instanceof ReferenceValue)
		{
			return true;
		}
		else
			return false;
	}
	
	private static NameValuePairList getNamePairListFromResult(Value value) {
		if(value instanceof ObjectValue)
		{
			return ((ObjectValue) value).members.asList();
		}
		else 
			return null;
	}
	
	private static List<String> getNewName(List<String> varName) {
		List<String> result = new ArrayList<String>();
		
		if(varName.size() > 1)
		{
			for(int i=1; i< varName.size(); i++)
			{
				result.add(varName.get(i));
			}
			return result;
		}
		else
		{
			return null;	
		}
	}
	
	public static List<Double> getDoubleListFromValue(Value value) throws ValueException
	{
		List<Double> result = new Vector<Double>();
		
		if(value instanceof SeqValue)
		{
			SeqValue seqValue = (SeqValue) value;
			result = getDoubleListFromSeq(seqValue);
			return result;		
		}		
		
		if (value.isNumeric())
		{
			result.add(value.realValue(null));
			return result;
		} else if (value instanceof BooleanValue)
		{
			result.add(((BooleanValue) value).boolValue(null) ? 1.0 : 0.0);
			return result;
		} 
		return null;
	}
	
	private static List<Double> getDoubleListFromSeq(SeqValue seqValue) throws ValueException {
		List<Double> result = new Vector<Double>();
		
		for (Value insideValue : seqValue.values) {
			
			result.addAll(getDoubleListFromValue(insideValue.deref()));
		}
		
		return result;
	}

	public static Definition findDefinitionInClass(ClassList classList,
			List<String> variableName) {
		
		if(variableName.size() <= 1)
		{
			return null;
		}
			
		String className = variableName.get(0);
		String varName = variableName.get(1);
		
		Definition s = classList.findName(new LexNameToken(className, varName, null), NameScope.NAMES);
		
		List<String> restOfQuantifier = variableName.subList(2, variableName.size());
		
		if(restOfQuantifier.size() == 0)
		{
			return s;
		}
		else
		{
			for(int i = 0; i<restOfQuantifier.size(); i++)
			{
				className = s.getType().getName();
				
				s = classList.findName(new LexNameToken(className, restOfQuantifier.get(i),null), NameScope.NAMESANDSTATE);
			}
		}
		
		return s;
		
	}
	
	public static List<Integer> getValueDimensions(Value value)
	{
		List<Integer> result = new Vector<Integer>();
		
		if(value instanceof SeqValue)
		{
			SeqValue seqValue = (SeqValue) value;
			result.add(seqValue.values.size());
			if(seqValue.values.size() > 0)
			{
				if(seqValue.values.get(0) instanceof SeqValue)
				{
					result.addAll(getSequenceInnerDimensions(seqValue));
				}
			}
		}
		else
		{
			result.add(1);
		}
		
		return result;
	}

	private static List<Integer> getSequenceInnerDimensions(
			SeqValue seqValue) {
		List<Integer> result = new Vector<Integer>();
		if(seqValue.values.size() > 0)
		{
			result.addAll(getValueDimensions(seqValue.values.get(0)));
		}
		
		return result;
	}
}