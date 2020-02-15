/** Ben F Rayfield offers this software opensource MIT license */
package immutable.OccamsParallelComputeSandbox;
import java.util.Map;

public class KernelInContext{
	
	public final Kernel kernel;
	
	/** Map OuterVar to Var (as described in MultiKernel) */
	public final Map<OuterVar,Var> memToParam;
	
	public KernelInContext(Kernel kernel, Map<OuterVar,Var> memToParam){
		this.kernel = kernel;
		this.memToParam = memToParam;
	}

}
