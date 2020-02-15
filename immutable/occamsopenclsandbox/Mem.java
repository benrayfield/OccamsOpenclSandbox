package immutable.occamsopenclsandbox;

import java.util.Set;

/** describes the amount and types of parts of private memory
in an opencl ndrange kernel.
*/
public interface Mem{
	
	public Set<Var> vars();
	
	/** Loops can only loop from 0 to aConstantInt-1 *
	public int constInts();
	
	public int varFloats();
	
	public int varInts();
	*/

}