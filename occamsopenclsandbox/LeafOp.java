package immutable.occamsopenclsandbox;

import java.util.Map;

/** touches at most 3 vars. Reads up to 2. Writes 1.
May also copy between a param array into a certain var.
*/
public class LeafOp implements ControlFlow{
	
	public final OpType type;
	
	public final Var varA, varB, varC;
	//public final int varA, varB, varC;
	
	public LeafOp(OpType type, Var varA, Var varB, Var varC){
		this.type = type;
		this.varA = varA;
		this.varB = varB;
		this.varC = varC;
	}
	
	public long cycles(Map<Var,Number> kernelParamConsts){
		return 1;
	}
	
	public String toString(){
		if(type==OpType.in){
			return varC+"="+varA+"["+varB+"]"; //TODO is this the right order?
		}
		if(type==OpType.out){
			return varA+"["+varB+"]="+varC; //TODO is this the right order?
		}
		return varC+"="+varA+type+varB;
	}

}
