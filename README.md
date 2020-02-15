# OccamsOpenclSandbox
Statelessly eval a forest of OpenCL NDRange kernels at gaming-low-lag in a sandbox comparable to WebCL. Safe (TODO verify) for malicious code to run openCL, which cant allocate more memory or compute cycles than allowed or access the wrong memory. Example: 100 calls per second, 15 kernels each call, 1-10000 gflops depending on your hardware. Independent of OpenCL implementation, or could potentially even work compatibly in CUDA, but prototype uses LWJGL OpenCL.

Since LWJGL CLMem objects are slower to allocate than gaming-low-lag,
will pool them by type (such as float4, float, long, etc) and size
and garbcol whichever was used last when need to allocate more.

This is a refactoring of the OpenclUtil code in HumanAiNetNeural.

ControlFlow is 1 of:
-- Loop of a Var counter to a const int Var loopSize.
-- Progn, a sequence of ControlFlows to do.
-- LeafOp, a function (such as copy between array and private var or multiply or plus) and 3 Vars, at most 1 of which is an array or could be z=x*y etc.

MultiKernel is a forest of KernelInContext.

OuterVar is like a name of a memory with type and size, not allocated yet but poolable.

KernelInContext has a Kernel and map of OuterVar to Var.

Kernel has a ControlFlow and set of Var it acts on. Those Vars include Kernel params and private Vars.

MultiKernel is the thing you statelessly and atomicly do. It knows its amount of memory and compute cycles required in advance, and it takes arrays and const numbers (such as float4 or int) as inputs, does internal calculations at low lag, then returns some of those as outputs, and its done, no need to clean up anything or manage opencl state. 