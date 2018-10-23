package org.cloudbus.cloudsim.examples;
import org.cloudbus.cloudsim.Cloudlet;
import org.cloudbus.cloudsim.UtilizationModel;

public class cloudlet3 extends Cloudlet {
	
	double[] comcost=new double[3];

	public cloudlet3(int cloudletId, long cloudletLength, int pesNumber, long cloudletFileSize, long cloudletOutputSize,
			UtilizationModel utilizationModelCpu, UtilizationModel utilizationModelRam,
			UtilizationModel utilizationModelBw,double[] comcost) {
		super(cloudletId, cloudletLength, pesNumber, cloudletFileSize, cloudletOutputSize, utilizationModelCpu,
				utilizationModelRam, utilizationModelBw);
		
		this.comcost=comcost;
		// TODO Auto-generated constructor stub
	}
	

}
