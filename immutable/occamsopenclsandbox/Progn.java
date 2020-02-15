package immutable.occamsopenclsandbox;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Progn implements ControlFlow{
	
	public final List<ControlFlow> sequence;
	
	public Progn(ControlFlow... sequence){
		this.sequence = Collections.unmodifiableList(new ArrayList(Arrays.asList(sequence)));
	}

	public long cycles(Map<Var,Number> kernelParamConsts){
		long c = 1; //+1 for the progn itself
		for(ControlFlow cf : sequence) c += cf.cycles(kernelParamConsts);
		return c;
	}

}
