grammar VdmLink;

options {
  language = Java;
}

tokens{
  OUTPUT = 'vdm.outputs';
  INPUT = 'vdm.inputs';
  SHARED = 'vdm.sdp';
  EVENT = 'vdm.events';
}

@header {
package org.destecs.core.parsers.vdmlink;

import org.destecs.core.vdmLink.LinksFactory;
import org.destecs.core.vdmLink.StringPair;
import org.destecs.core.vdmLink.Links;
import java.util.ArrayList;
import java.util.List;
}

@lexer::header{  
package org.destecs.core.parsers.vdmlink;
}

@members{
    private boolean mMessageCollectionEnabled = false;
    private boolean mHasErrors = false;
    private LinksFactory links = new LinksFactory();
    private List<String> mMessages;
    private List<RecognitionException> mExceptions = new ArrayList<RecognitionException>();

      
    public Links getLinks(){
        return links.getLinks();
    }
      
    public boolean hasExceptions()
    {
        return mExceptions.size() > 0;
    }

    public List<RecognitionException> getExceptions()
    {
        return mExceptions;
    }

    public String getErrorMessage(RecognitionException e, String[] tokenNames)
    {
        String msg = super.getErrorMessage(e, tokenNames);
        mExceptions.add(e);
        return msg;
    }

    /**
     *  Switches error message collection on or of.
     *
     *  The standard destination for parser error messages is <code>System.err</code>.
     *  However, if <code>true</code> gets passed to this method this default
     *  behaviour will be switched off and all error messages will be collected
     *  instead of written to anywhere.
     *
     *  The default value is <code>false</code>.
     *
     *  @param pNewState  <code>true</code> if error messages should be collected.
     */
    public void enableErrorMessageCollection(boolean pNewState) {
        mMessageCollectionEnabled = pNewState;
        if (mMessages == null && mMessageCollectionEnabled) {
            mMessages = new ArrayList<String>();
        }
    }
    
    /**
     *  Collects an error message or passes the error message to <code>
     *  super.emitErrorMessage(...)</code>.
     *
     *  The actual behaviour depends on whether collecting error messages
     *  has been enabled or not.
     *
     *  @param pMessage  The error message.
     */
     @Override
    public void emitErrorMessage(String pMessage) {
        if (mMessageCollectionEnabled) {
            mMessages.add(pMessage);
        } else {
            super.emitErrorMessage(pMessage);
        }
    }
    
    /**
     *  Returns collected error messages.
     *
     *  @return  A list holding collected error messages or <code>null</code> if
     *           collecting error messages hasn't been enabled. Of course, this
     *           list may be empty if no error message has been emited.
     */
    public List<String> getMessages() {
        return mMessages;
    }
    
    /**
     *  Tells if parsing a Java source has caused any error messages.
     *
     *  @return  <code>true</code> if parsing a Java source has caused at least one error message.
     */
    public boolean hasErrors() {
        return mHasErrors;
    }
} 

WS  :   ( ' '
        | '\t'
        | '\r'
        | '\n'
        ) {$channel=HIDDEN;}
    ;

ID  : ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')*
    ;
    
COMMENT
    :   '--' ~('\n'|'\r')* '\r'? '\n'? {$channel=HIDDEN;}
    |   '//' ~('\n'|'\r')* '\r'? '\n'? {$channel=HIDDEN;}
    |   '/*' ( options {greedy=false;} : . )* '*/' {$channel=HIDDEN;}
    ;

start 
    : (link | intf)* EOF ';'
    ;
    
link
    : a=ID '=' b=ID '.' c=ID
    { links.addLink($a.text, new StringPair($b.text,$c.text)); }
    ;

intf
    : OUTPUT '=' r=idList
    {links.addOutputs(r);}
    | INPUT '=' r=idList
    {links.addInputs(r);}
    | SHARED '=' r=idList
    {links.addSDPs(r);}
    | EVENT '=' r=idList
    {links.addEvents(r);}
    ;

idList returns [List<String> ids]
@init{
  ids = new ArrayList<String>();
} 
    : a=ID {$ids.add($a.text);} (',' b=ID {$ids.add($b.text);} )*
    
    ;
    

