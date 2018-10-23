package org.cloudbus.cloudsim.examples;

import org.cloudbus.cloudsim.CloudletScheduler;
import org.cloudbus.cloudsim.Vm;

public class vm3 extends Vm {
	double[] executioncost=new double[3];
	int[] datasize=new int[3];

	public vm3(int id, int userId, double mips, int numberOfPes, int ram, long bw, long size, String vmm,
			CloudletScheduler cloudletScheduler, double[] executioncost,int[] datasize) {
		super(id, userId, mips, numberOfPes, ram, bw, size, vmm, cloudletScheduler);
		this.executioncost = executioncost;
		this.datasize=datasize;
		// TODO Auto-generated constructor stub
	}
	

}
