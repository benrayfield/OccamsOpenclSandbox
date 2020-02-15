# OccamsOpenclSandbox
Statelessly eval a forest of OpenCL NDRange kernels at gaming-low-lag in a sandbox comparable to WebCL. Safe (TODO verify) for malicious code to run openCL, which cant allocate more memory or compute cycles than allowed or access the wrong memory. Example: 100 calls per second, 15 kernels each call, 1-10000 gflops depending on your hardware.
