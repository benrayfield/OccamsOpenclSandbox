# OccamsOpenclSandbox
Statelessly eval a forest of OpenCL NDRange kernels at gaming-low-lag in a sandbox comparable to WebCL. Safe (TODO verify) for malicious code to run openCL, which cant allocate more memory or compute cycles than allowed or access the wrong memory. Example: 100 calls per second, 15 kernels each call, 1-10000 gflops depending on your hardware. Independent of OpenCL implementation, or could potentially even work compatibly in CUDA, but prototype uses LWJGL OpenCL.

Since LWJGL CLMem objects are slower to allocate than gaming-low-lag,
will pool them by type (such as float4, float, long, etc) and size
and garbcol whichever was used last when need to allocate more.

This is a refactoring of the OpenclUtil code in HumanAiNetNeural. 