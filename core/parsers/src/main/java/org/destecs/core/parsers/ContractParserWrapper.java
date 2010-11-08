package org.destecs.core.parsers;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.destecs.core.contract.Contract;
import org.destecs.core.parsers.contract.ContractLexer;
import org.destecs.core.parsers.contract.ContractParser;

public class ContractParserWrapper extends ParserWrapper<Contract>
{
	public Contract parse(File source) throws IOException
	{
		ANTLRFileStream input = new ANTLRFileStream(source.getAbsolutePath());
		ContractLexer lexer = new ContractLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		ContractParser thisParser =new ContractParser(tokens);;
		parser=thisParser;
		thisParser.enableErrorMessageCollection(true);

		try
		{
			thisParser.contract();
			if (thisParser.hasExceptions())
			{

				List<RecognitionException> exps = thisParser.getExceptions();
				addErrors(source, exps);
			} else
			{
				return thisParser.getContract();
			}
		} catch (RecognitionException errEx)
		{
			errEx.printStackTrace();
			addError(new ParseError(source, errEx.line, errEx.charPositionInLine, getErrorMessage(errEx, parser.getTokenNames())));
		}
		return null;
	}

	public Contract parse(File source, String data) throws IOException
	{
		ANTLRStringStream input = new ANTLRStringStream(data);
		ContractLexer lexer = new ContractLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		ContractParser thisParser = new ContractParser(tokens);
		parser = thisParser;
		thisParser.enableErrorMessageCollection(true);

		try
		{
			thisParser.contract();
			if (thisParser.hasExceptions())
			{

				List<RecognitionException> exps = thisParser.getExceptions();
				addErrors(source, exps);
			} else
			{
				return thisParser.getContract();
			}
		} catch (RecognitionException errEx)
		{
			errEx.printStackTrace();
			addError(new ParseError(source, errEx.line, errEx.charPositionInLine, getErrorMessage(errEx, parser.getTokenNames())));
		}
		return null;
	}
}
