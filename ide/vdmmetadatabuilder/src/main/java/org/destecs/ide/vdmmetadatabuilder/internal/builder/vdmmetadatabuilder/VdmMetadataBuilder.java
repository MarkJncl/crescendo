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
package org.destecs.ide.vdmmetadatabuilder.internal.builder.vdmmetadatabuilder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Vector;

import org.destecs.ide.core.resources.IDestecsProject;
import org.destecs.ide.vdmmetadatabuilder.VdmMetadataBuilderPlugin;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.overture.ide.core.IVdmModel;
import org.overture.ide.core.resources.IVdmProject;
import org.overturetool.vdmj.ast.IAstNode;
import org.overturetool.vdmj.definitions.BUSClassDefinition;
import org.overturetool.vdmj.definitions.CPUClassDefinition;
import org.overturetool.vdmj.definitions.ClassDefinition;
import org.overturetool.vdmj.definitions.Definition;
import org.overturetool.vdmj.definitions.ExplicitOperationDefinition;
import org.overturetool.vdmj.definitions.InstanceVariableDefinition;
import org.overturetool.vdmj.definitions.LocalDefinition;
import org.overturetool.vdmj.definitions.SystemDefinition;
import org.overturetool.vdmj.definitions.ValueDefinition;
import org.overturetool.vdmj.modules.Module;
import org.overturetool.vdmj.types.BooleanType;
import org.overturetool.vdmj.types.CharacterType;
import org.overturetool.vdmj.types.ClassType;
import org.overturetool.vdmj.types.IntegerType;
import org.overturetool.vdmj.types.NaturalOneType;
import org.overturetool.vdmj.types.NaturalType;
import org.overturetool.vdmj.types.OptionalType;
import org.overturetool.vdmj.types.RealType;
import org.overturetool.vdmj.types.SeqType;
import org.overturetool.vdmj.types.Type;

public class VdmMetadataBuilder extends
		org.eclipse.core.resources.IncrementalProjectBuilder
{

	private static final String BUS_TYPE_NAME = "#BUS";
	private static final String CPU_TYPE_NAME = "CPU";
	private static final String SYSTEM_TYPE_NAME = "_system";
	private static final String UNKNOWN = "unknown";

	protected IProject[] build(int kind,
			@SuppressWarnings("rawtypes") Map args, IProgressMonitor monitor)
			throws CoreException
	{
		try
		{
			expandedDefinitions.clear();
			IVdmProject project = (IVdmProject) getProject().getAdapter(IVdmProject.class);
			if (project != null)
			{
				Properties props = new Properties();

				IVdmModel model = project.getModel();

				if (!model.isTypeChecked())
				{
					if(!project.typeCheck(new NullProgressMonitor()))
					{
						props.put("TYPE_CHECK_STATUS", "false");
						storeProperties(monitor, props);
						return null;
					}
				}

				props.put("TYPE_CHECK_STATUS", "true");
				for (IAstNode node : model.getRootElementList())
				{
					if (node instanceof SystemDefinition)
					{
						SystemDefinition sd = (SystemDefinition) node;
						expandAndSave(props, "", sd, model);
						List<String> values = new Vector<String>();
						for (Definition def : sd.getDefinitions())
						{
							if (def instanceof ValueDefinition
									|| def instanceof LocalDefinition)
							{
								values.add(def.getName());
							}
						}

						save(props, sd.getName(), toCsvString(values));
						expandAndSave(props, "", sd, model);
					} else
					{
						List<String> values = new Vector<String>();
						for (Definition def : getDefinitions(node))
						{
							if (def instanceof ValueDefinition
									|| def instanceof LocalDefinition)
							{
								values.add(def.getName());

								String typeName = getVdmTypeName(def);
								if (!typeName.equals(UNKNOWN))
								{
									save(props, node.getName() + "."
											+ def.getName(), typeName + "," + "const");
								}
							}
							else
							if(def instanceof InstanceVariableDefinition)
							{
								values.add(def.getName());

								String typeName = getVdmTypeName(def);
								if (!typeName.equals(UNKNOWN))
								{
									save(props, node.getName() + "."
											+ def.getName(), typeName+ "," + "variable");
								}
							}
							else
							if(def instanceof ExplicitOperationDefinition)
							{
								ExplicitOperationDefinition op = (ExplicitOperationDefinition) def;
								values.add(def.getName());
								
								save(props, node.getName() + "."
										+ def.getName(), "_operation"+ "," + (op.accessSpecifier.isAsync  ? "async" : "sync") );
							}
							
						}
					}
				}

				storeProperties(monitor, props);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return null;
	}

	protected void storeProperties(IProgressMonitor monitor, Properties props)
			throws CoreException
	{
		IDestecsProject dp = (IDestecsProject) getProject().getAdapter(IDestecsProject.class);
		if (dp != null)
		{
			IFile file = dp.getVdmModelFolder().getFile(".metadata");

			ByteArrayOutputStream out = new ByteArrayOutputStream();
			try
			{
				props.store(out, "");
			} catch (IOException e)
			{
				VdmMetadataBuilderPlugin.log("Failed to store metadatafile for project: "+getProject(), e);
			}

			if (file.exists())
			{
				file.setContents(new ByteArrayInputStream(out.toByteArray()), IFile.FORCE, monitor);
			} else
			{
				file.create(new ByteArrayInputStream(out.toByteArray()), IFile.FORCE, monitor);
			}

		}
	}

	Set<Definition> expandedDefinitions = new HashSet<Definition>();

	private void expandAndSave(Properties props, String prefix, Definition def,
			IVdmModel model)
	{
		expandAndSave(props, prefix, def, model, false);
	}

	private void expandAndSave(Properties props, String prefix, Definition def,
			IVdmModel model, boolean defIsField)
	{

		if (!prefix.isEmpty())
		{
			expandedDefinitions.add(def);
		}

		String name = prefix + (prefix == "" ? "" : ".") + def.getName();
		if (def instanceof SystemDefinition)
		{
			save(props, name, SYSTEM_TYPE_NAME + ",systemclass");
		} else
		{
			save(props, name, getVdmTypeName(def) + ",class");
		}

		if (defIsField)
		{
			return;
		}

		for (Definition field : getFieldDefinitions(getTypeName(def), model))
		{
			Definition child = field;// getDefinition(getTypeName(field),
										// model);
			if (child != null)
			{
				if (!expandedDefinitions.contains(child))
				{

					expandAndSave(props, name, child, model, true);
				}
			}
		}
	}

	private void save(Properties props, String name, String list)
	{
		if (name.trim().length() > 0 && list.trim().length() > 0)
		{
			props.put(name, list);
		}
	}

	private String toCsvString(List<String> list)
	{
		StringBuffer sb = new StringBuffer();
		for (String string : list)
		{
			sb.append(",");
			sb.append(string);
		}
		sb.append(" ");
		return sb.substring(1).trim();
	}

	private String getVdmTypeName(IAstNode node)
	{
		Type t = null;

		if (node instanceof InstanceVariableDefinition)
		{
			t = ((InstanceVariableDefinition) node).type;
		} else if (node instanceof ValueDefinition)
		{
			t = ((ValueDefinition) node).type;
		} else if (node instanceof LocalDefinition)
		{
			t = ((LocalDefinition) node).type;
		}

		return getTypeName(t);

	}

	private String getTypeName(Type t)
	{
		
		
		if (t instanceof RealType || t.isType("real") != null)
		{
			return "real";
		} else if (t instanceof IntegerType || t.isType("int") != null)
		{
			return "int";
		} else if (t instanceof NaturalType || t.isType("nat") != null)
		{
			return "nat";
		}else if (t instanceof NaturalOneType || t.isType("nat1") != null)
		{
			return "nat1";
		}  else if (t instanceof BooleanType || t.isType("bool") != null)
		{
			return "bool";
		}else if (t instanceof CharacterType || t.isType("char") != null)
		{
			return "char";
		}
		 else if (t instanceof SeqType)
		 {
			 SeqType t1 = (SeqType) t;
		 return "seq of (" + getTypeName(t1.seqof) + ")";
		 }
		else if (t instanceof ClassType)
		{
			ClassType ct = (ClassType) t;
			if(ct.classdef instanceof CPUClassDefinition)
			{
				return CPU_TYPE_NAME;
			}else if(ct.classdef instanceof BUSClassDefinition)
			{
				return BUS_TYPE_NAME;
			}
			return ct.getName();// "Class";
		} else if (t instanceof OptionalType)
		{
			return getTypeName(((OptionalType) t).type);
		}
		
//		if(t.isNumeric()) //this is trying to fit the value in a real (if it is compatible with real it is ok)
//		{
//			return "real";
//		}
		
		return UNKNOWN;
	}

	private String getTypeName(Definition def)
	{
		Type t = null;

		if (def instanceof InstanceVariableDefinition)
		{
			t = ((InstanceVariableDefinition) def).type;
		} else if (def instanceof ValueDefinition)
		{
			t = ((ValueDefinition) def).type;
		} else if (def instanceof LocalDefinition)
		{
			t = ((LocalDefinition) def).type;
		}

		String typeName = "";
		if (t instanceof OptionalType)
		{
			OptionalType opType = (OptionalType) t;
			typeName = opType.type.getName();

		}
		if (t instanceof ClassType)
		{
			typeName = t.getName();
		} else if (t instanceof OptionalType
				&& ((OptionalType) t).type instanceof ClassType)
		{
			typeName = t.getName();

		} else
		{
			typeName = def.getName();
		}
		return typeName;
	}

	private List<Definition> getFieldDefinitions(String type, IVdmModel model)
	{
		List<Definition> variable = new Vector<Definition>();
		for (IAstNode node : model.getRootElementList())
		{
			if (!node.getName().equals(type))
			{
				continue;
			}
			for (Definition def : getDefinitions(node))
			{
				if (def instanceof InstanceVariableDefinition)
				{
					variable.add(def);
				}
			}
		}
		return variable;
	}

	private List<Definition> getDefinitions(IAstNode node)
	{
		if (node instanceof ClassDefinition)
		{
			return ((ClassDefinition) node).getDefinitions();
		} else if (node instanceof Module)
		{
			return ((Module) node).defs;
		}
		return new Vector<Definition>();

	}
}
