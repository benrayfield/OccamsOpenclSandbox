package immutable.occamsopenclsandbox;

public enum OpType{
	
	/** copy from aParamArray[aLocalVar] to bLocalVar */
	in,
	
	/** copy from bLocalVar to aParamArray[aLocalVar] */
	out,
	
	//Everything below reads 2 local vars and writes 1 local var
	
	mul,
	
	add,
	
	neg,
	
	castIntToFloat,
	
	castFloatToInt

}
