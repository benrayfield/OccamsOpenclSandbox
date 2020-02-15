/** Ben F Rayfield offers this software opensource MIT license */
package immutable.OccamsParallelComputeSandbox;

/** UPDATE: var is int. These are static funcs about it.
Any var in an opencl ndrange kernel.
Can be a param array, const param int, or private var.
Can be float, int, and later will add more types (TODO float4? double? etc).
*/
public interface Var{
	
	/** definition of this var */
	public int def();
	
	/*Should vars come in pairs in case they're array?
	Or should each array be paired with a const int of its size?
			
	Should private arrays (not in params) be allowed?
			
	Should Var be represented as an int with masks and some ranges being
	sizes such as log2OfPrims is 4 bits?
	
	
	FIXME where to define size of (Var) param array?
	Can that size depend on const ints also in params?
	For example, in a matmul kernel,
	theres 3 param arrays and 3 const ints a b c
	and the param arrays are sizes a*b (read) and b*c (read) and a*c (write).
	For example, a bayesnode array could be size 2^x
	for some small x, or maybe size y*2^x for y number of bayesnodes,
	or maybe an array of size y choose x such as 50 vars and choose all combos
	of 5 of them for conditionalProbability of the combo of any 5 vars
	each being true or false. If x is 3 that can represent 3sat,
	thought I dont know of an efficient way to compute anything useful about it
	cuz thats an NP problem.
	Imagine abstractly an array size 2^50 containing the values 0 and 1,
	0 wherever a combo of the 50 vars violates constraints and 1 where its allowed,
	and various smaller arrays of conditionalProbability of less than 50 vars
	at a time could describe that by summing those 2^50 things that
	are inside the bigger boxes in the 50 dimensional space.
	I dont think we really need that kind of var indexing to be optimized
	since neuralnets seem to outperform bayesnets cuz they're
	better aligned to parallel calculations.
	Imagine a bayesaddr (bayesian address) as a trinary number
	whose digits are either 0, 1, or BOTH.
	A 3sat constraint has BOTH for all except 3 digit indexs,
	and has 0 or 1 in those, and is 7 of those things allowed
	while the 8th is not allowed.
	*/
	
	public static final int mask_isOuterParam = 1<<31;
	public default boolean isOuterParam(){ return (def()&mask_isOuterParam)!=0; };
	
	public static final int mask_isInnerParam = 1<<30;
	public default boolean isInnerParam(){ return (def()&mask_isInnerParam)!=0; };
	
	public static final int mask_isArray = 1<<29;
	public default boolean isArray(){ return (def()&mask_isArray)!=0; };
	
	/** is integer(s) such as int4 or int or long */
	public static final int mask_isInteger = 1<<28;
	public default boolean isInteger(){ return (def()&mask_isInteger)!=0; };
	
	/** float and double are always signed. int32 is signed. uint32 is not. */
	public static final int mask_isSigned = 1<<27;
	public default boolean isSigned(){ return (def()&mask_isSigned)!=0; };
	
	public static final int mask_isReadable = 1<<26;
	public default boolean isReadable(){ return (def()&mask_isReadable)!=0; };
	
	public static final int mask_isWritableMulti = 1<<25;
	public default boolean isWritableMulti(){ return (def()&mask_isWritableMulti)!=0; };
	
	/** such as...
	ndrangeSize is const int in params;
	const int id = get_global_id(0);
	const int y = id/ndrangeSize;
	const int x = id%ndrangeSize;
	*/ 
	public static final int mask_isWritableOnce = 1<<24;
	public default boolean isWritableOnce(){ return (def()&mask_isWritableOnce)!=0; };
	
	public default boolean isWritableEver(){
		return (def()&(mask_isWritableOnce|mask_isWritableMulti))!=0; 
	};
	
	/** float4 is 128 bits each. long is 64 bits each. */
	public default int bitsEach(){
		return 1<<log2OfBitsEach();
	}
	
	/** float4 is 4. float is 1. long is 1. */
	public default int primsEach(){
		return 1<<log2OfPrimsEach();
	}
	
	public default int log2OfBitsEach(){
		return log2OfBitsEach(def());
	}
	
	public default int log2OfPrimsEach(){
		return log2OfPrimsEach(def());
	}
	
	/** Range 0 to 15. Size of this type, such as log2(128)=7 for float4 or log2(32)=5 for float */
	public static int log2OfBitsEach(int var){
		return (var>>>20)&0xf;
	}
	
	/** Range 0 to 15. Number of primitives, such as 2 for float4 or 1 for float */
	public static int log2OfPrimsEach(int var){
		return (var>>>16)&0xf;
	}
	
	public default int index(){
		return index(def());
	}
	
	/** FIXME I'm undecided if the other fields (such as isInteger and log2OfBits)
	should be part of this index vs if index 5 of one type is the same as
	index 5 of another type. I want all these fields to fit in a single number,
	probably an int. Could shrink log2OfPrims and log2OfBits
	since they dont each need a whole byte.
	*/
	public static int index(int var){
		return var&0xffff;
	}

}
