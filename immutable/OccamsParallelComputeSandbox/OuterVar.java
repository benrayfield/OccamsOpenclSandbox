/** Ben F Rayfield offers this software opensource MIT license */
package immutable.OccamsParallelComputeSandbox;

/** This comment moved from Var.
A CLMem that can be used by multiple ndrange kernel calls
is an outerParam. In each of those calls, its referred to
as an innerParam in the (integer indexed) namespace of that kernel.
FIXME how much index space (short,long,string,what?)
is needed for outerParams? Should they really be named this way?
Also, where is its size stored?
outerParam should probably be a different datastruct
than Var. 
*
public final boolean isOuterParam;
*/
public class OuterVar{

	public final long id;
	
	public final int varType;
	
	public final int sizeBytes;
	
	public OuterVar(long id, int varType, int sizeBytes){
		this.id = id;
		this.varType = varType;
		this.sizeBytes = sizeBytes;
	}
	
}
