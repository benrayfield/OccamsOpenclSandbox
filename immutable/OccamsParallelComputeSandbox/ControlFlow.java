/** Ben F Rayfield offers this software opensource MIT license */
package immutable.OccamsParallelComputeSandbox;

import java.util.Map;

/** a specific amount of work to be done in loops and sequences */
public interface ControlFlow{
	
	/** number of compute cycles.
	Example kernelParamConsts:
	Maps the 3 size vars, informally named x y z,
	in matmul, to the opencl ndrange kernel param values
	where array sizes are x*y and y*z and x*z.
	Each of x y z is named by an int described in Var.
	*/
	public long cycles(Map<Var,Number> kernelParamConsts);

}