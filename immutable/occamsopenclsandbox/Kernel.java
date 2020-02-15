package immutable.occamsopenclsandbox;

/** A Mem (defines which vars and their types) and ControlFlow
define the work to be done in an opencl ndrange kernel.
There are technically a few other things it could do
(such as if/else statements) but I dont think those can be
done efficiently so I'm not modelling them here,
and if forExample you want an ifElse statement
then do both of them then merge by multiplying
by x and 1-x where x is 0 or 1 or similar bit logic ops.
*/
public class Kernel{
	
	public final ControlFlow code;
	
	public final Mem data;
	
	public Kernel(ControlFlow code, Mem data){
		this.code = code;
		this.data = data;
	}

}
