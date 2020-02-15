# OccamsParallelComputeSandbox
(not working yet) Is a spec of computing to be done not depending on OpenCL etc. Statelessly eval a forest of OpenCL NDRange kernels at gaming-low-lag in a sandbox comparable to WebCL. Safe (TODO verify) for malicious code to run openCL, which cant allocate more memory or compute cycles than allowed or access the wrong memory. Example: 100 calls per second, 15 kernels each call, 1-10000 gflops depending on your hardware. Independent of OpenCL implementation, or could potentially even work compatibly in CUDA, but prototype uses LWJGL OpenCL.

Prototype will work on at least Windows and Linux, wherever LWJGL works. Can be ported to any system without changing the API.

Data format supports int1 to int32768, float1 to float32768 (most of those arent defined by
IEEE754 spec yet but room for expansion), signed and unsigned,
block vars such as opencl's float4 (up to 32768 bits per block),
and up to 2^31-1 bytes per array, arrays of 1 dimension, up to 2^31 GPU threads
(normally they have hundreds to low thousands of cores per GPU),
readable, writableOnce, and writableMulti per var.

Supports dependnet of opencl-like ndrange kernels which read and write multiple arrays,
sharing those arrays among the kernel calls,
without having to copy data between CPU and GPU for the kernel calls
in the middle of the forest (most of them) since IO is usually the bottleneck.   

Since LWJGL CLMem objects are slower to allocate than gaming-low-lag,
will pool them by type (such as float4, float, long, etc) and size
and garbcol whichever was used least recently when need to allocate more.

This is a refactoring of the OpenclUtil code in HumanAiNetNeural.

ControlFlow is 1 of:
-- Loop of a Var counter to a const int Var loopSize.
-- Progn, a sequence of ControlFlows to do.
-- LeafOp, a function (such as copy between array and private var or multiply or plus) and 3 Vars, at most 1 of which is an array or could be z=x*y etc.

MultiKernel is a forest of KernelInContext.

OuterVar is like a name of a memory with type and size, not allocated yet but poolable.

KernelInContext has a Kernel and map of OuterVar to Var.

Kernel has a ControlFlow and set of Var it acts on. Those Vars include Kernel params and private Vars.

MultiKernel is a dependnet of KernelInContext and is the thing you statelessly and atomicly do. It knows its amount of memory and compute cycles required in advance, and it takes arrays and const numbers (such as float4 or int) as inputs, does internal calculations at low lag, then returns some of those as outputs, and its done, no need to clean up anything or manage opencl state. 