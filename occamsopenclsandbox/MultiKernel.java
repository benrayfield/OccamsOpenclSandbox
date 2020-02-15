package immutable.occamsopenclsandbox;

/** a forest of Kernels, to be done potentially all at once in opencl
before returning to CPU, for extremely lower lag (20 times less lag for example).
*/
public class MultiKernel implements Runnable{
	
	TODO dependnet of KernelInContext
	
	//TODO how to represent the dependnet?
	
	map between OuterVar and Var per Kernel.
	Each Kernel object doesnt know which OuterVars
	since it can be used on multiple mappings of OuterVar to its Var.
	
	/** Does the work described by this MultiKernel.
	May be implemented any way such as preferably in OpenCL
	but possibly in Javassist or whatever system.
	If implemented as LWJGL OpenCL, runs all the the opencl kernels
	before returning to CPU for lower lag.
	*/
	public void run();
	FIXME this should take either primitive arrays or Buffers
	and do internal calculations then return primitive arrays or Buffers,
	using CLMem pooling by type and size garbcoled oldest first
	counting from the time it was last used,
	like I started coding in OpenclUtil.

}
