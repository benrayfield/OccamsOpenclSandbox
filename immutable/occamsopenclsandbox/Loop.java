package immutable.occamsopenclsandbox;

import java.util.Map;

/** for(int varCounter=0; varCounter<varCycles; varCounter++) body;
*/
public class Loop implements ControlFlow{
	
	public final Var counter;
	
	public final Var cycles;
	
	public final ControlFlow body;
	
	public Loop(Var counter, Var cycles, ControlFlow body){
		this.counter = counter;
		this.cycles = cycles;
		this.body = body;
	}

	public long cycles(Map<Var,Number> kernelParamConsts){
		return body.cycles(kernelParamConsts)
			* kernelParamConsts.get(cycles).longValue();
	}

}
